package com.hotelbooking.HotelBooking.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelbooking.HotelBooking.model.RoomModel;
import com.hotelbooking.HotelBooking.repo.RoomRepo;

@Service
public class RoomService {
	@Autowired
	private RoomRepo roomRepo;
	
	public List<RoomModel> getAllRooms() {
        return roomRepo.findAll();
    }

}
