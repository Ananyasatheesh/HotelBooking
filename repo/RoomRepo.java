package com.hotelbooking.HotelBooking.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelbooking.HotelBooking.model.RoomModel;

public interface RoomRepo extends JpaRepository<RoomModel,Integer>{

}
