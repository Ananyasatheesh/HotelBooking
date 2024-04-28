package com.hotelbooking.HotelBooking.controller;

import java.io.InputStream;
import java.nio.file.*;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hotelbooking.HotelBooking.Service.RoomService;
import com.hotelbooking.HotelBooking.model.RoomModel;
import com.hotelbooking.HotelBooking.model.RoomModelDto;
import com.hotelbooking.HotelBooking.repo.RoomRepo;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/room")
public class Room {
	@Autowired
	private RoomRepo repo;
	
	@GetMapping({"","/"})
	public String showRoomList(Model model) {
	    List<RoomModel> room = repo.findAll();
	    model.addAttribute("rooms", room);
	    return "room/index";
	}
	
	@GetMapping("/create")
	public String showCreatePage(Model model) {
		RoomModelDto roomDto = new RoomModelDto();
		model.addAttribute("roomDto",roomDto);
		return "room/CreateRoom";
	}
	
	@PostMapping("/create")
	public String createProduct(@RequestBody @Valid @ModelAttribute("RoomDto") RoomModelDto roomDto,BindingResult result, Model model) {
		if(result.hasErrors()) {
			 model.addAttribute("roomDto", roomDto);
			return "room/CreateRoom";
		}
		if(roomDto.getImageFile().isEmpty()) {
			result.addError(new FieldError("roomDto","imageFile","The Image File is Empty"));
		}
		
		MultipartFile img = roomDto.getImageFile();
		String storageFileName =img.getOriginalFilename();
		
		try {
				String uploadDir = "public/images/";
				Path uploadPath = Paths.get(uploadDir);
				if (!Files.exists (uploadPath)) 
					{ Files.createDirectories (uploadPath);
				}
				try (InputStream inputStream = img.getInputStream()) {
					Files.copy(inputStream, Paths.get (uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
					}
		}
		catch (Exception ex){
			System.out.println("Exception: "+ex.getMessage());
		}
		RoomModel room = new RoomModel();
		room.setRoomName(roomDto.getRoomName());
		room.setRoomPrice(roomDto.getRoomPrice());
		room.setImageFileName(storageFileName);
		repo.save(room);
		return "redirect:/room";
	}
	@GetMapping("/edit")
	public String showEditRoomPage(Model model, @RequestParam int id) {
		try {
			RoomModel room = repo.findById(id).get();
			model.addAttribute("room",room);

			
			RoomModelDto roomDto = new RoomModelDto();
			roomDto.setRoomName(room.getRoomName());
			roomDto.setRoomPrice(room.getRoomPrice());
			model.addAttribute("roomDto", roomDto);		
		}
		
		catch(Exception ex) {
			System.out.println("Exception: "+ex.getMessage());
			return "redirect:/room";
		}
		return "room/EditRoom";
	}

	@PostMapping("/edit")
	public String updateRoom(Model model, @RequestParam int id, @Valid @ModelAttribute("roomDto") RoomModelDto roomDto, BindingResult result) {
		
		try {
			RoomModel room = repo.findById(id).get();
			model.addAttribute("room",room);
			
			if(result.hasErrors()) {
				return "room/EditRoom";
			}
			if(!roomDto.getImageFile().isEmpty()) {
				String uploadDir = "public/images";
				Path oldImagePath = Paths.get(uploadDir + room.getImageFileName());
				try {
					Files.delete(oldImagePath);
				}
				catch(Exception ex) {
					System.out.println("Exception"+ex.getMessage());
				}
				MultipartFile img = roomDto.getImageFile();
				String storageFileName = img.getOriginalFilename();
				try (InputStream inputStream = img.getInputStream()) {
					Files.copy(inputStream, Paths.get (uploadDir + storageFileName), 
							StandardCopyOption.REPLACE_EXISTING);
					}
				room.setImageFileName(storageFileName);
			}
			room.setRoomName(roomDto.getRoomName());
			room.setRoomPrice(roomDto.getRoomPrice());
			
			repo.save(room);
		}
		catch(Exception ex) {
			System.out.println("Exception"+ex.getMessage());
		}
		return "redirect:/room";
	}
	
	@GetMapping("/delete")
	public String deleteRoom(@RequestParam int id) {
		try {
			RoomModel room = repo.findById(id).get();
			repo.delete(room);
		}
		catch(Exception ex) {
			System.out.println("Exception:"+ex.getMessage());
		}
		return "redirect:/room";
	}
	
	
	@Autowired
	private RoomService roomServ;
	@GetMapping("/roomCard")
    public String ShowAllRooms(Model model) {
		List<RoomModel> rooms = roomServ.getAllRooms();
        model.addAttribute("rooms", rooms);
        return "room/viewBookingCard";
    }
}
