package com.hotelbooking.HotelBooking.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelbooking.HotelBooking.model.BookingRoomModel;

public interface BookingRoomRepo extends JpaRepository<BookingRoomModel, Integer>{

}
