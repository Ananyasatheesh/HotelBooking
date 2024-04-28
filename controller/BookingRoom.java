package com.hotelbooking.HotelBooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import com.hotelbooking.HotelBooking.model.BookingRoomModel;
import com.hotelbooking.HotelBooking.repo.BookingRoomRepo;



@Controller
public class BookingRoom {
	@Autowired
    private BookingRoomRepo bookingRoomRepo;
	@GetMapping("/bookRoom")
	public String showBookingPage(@RequestParam int roomId, @RequestParam String roomName, @RequestParam double roomPrice, Model model) {
		BookingRoomModel bookroom = new BookingRoomModel();
		bookroom.setRoomName(roomName);
	    bookroom.setRoomPrice(roomPrice);
	    model.addAttribute("bookroom", bookroom);
	    return "room/booking";
	}

    @PostMapping("/saveRoom")
    public String saveBookingDetails(@ModelAttribute BookingRoomModel bookroom, Model model) {
        System.out.println(bookroom);
        bookingRoomRepo.save(bookroom);
        //redirectAttributes.addFlashAttribute("message", "Room booked successfully!");
        return "room/confirmation";
    }
	
}
