package com.hotelbooking.HotelBooking.model;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class RoomModelDto {
	private int id;
	@NotEmpty(message = "Name of room is required")
	private String RoomName;
	
	@Min(0)
	private double RoomPrice;
	
	private MultipartFile imageFile;

	public String getRoomName() {
		return RoomName;
	}

	public void setRoomName(String roomName) {
		RoomName = roomName;
	}

	public double getRoomPrice() {
		return RoomPrice;
	}

	public void setRoomPrice(double roomPrice) {
		RoomPrice = roomPrice;
	}

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
