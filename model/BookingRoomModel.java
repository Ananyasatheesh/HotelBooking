package com.hotelbooking.HotelBooking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class BookingRoomModel {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		private String GuestName;
		private String GuestEmail;
		private int NumOfguests;
		private String BookingId;
		private String RoomName;
		private double RoomPrice;
		private String fromDate;
		private String toDate;
		public BookingRoomModel(String fromDate, String toDate, double roomPrice, int NumOfguests) {
			this();
			this.fromDate = fromDate;
	        this.toDate = toDate;
	        this.RoomPrice = roomPrice;
	        this.NumOfguests = NumOfguests;
	    }
		public String getFromDate() {
			return fromDate;
		}
		public void setFromDate(String fromDate) {
			this.fromDate = fromDate;
		}
		
		public String getToDate() {
			return toDate;
		}
		public void setToDate(String toDate) {
			this.toDate = toDate;
		}
		public double getRoomPrice() {
			return RoomPrice;
		}
		public void setRoomPrice(double roomPrice) {
		
			RoomPrice = roomPrice;
			//calculateTotalPrice();
		}
//		public double calculateTotalPrice() {
//		    if (fromDate == null || toDate == null) {
//		        return 0.0; 
//		    }
//		    long durationInMillis = toDate.getTime() - fromDate.getTime();
//		    double durationInDays = durationInMillis / (24.0 * 60 * 60 * 1000);
//		    return durationInDays * RoomPrice;
//		}

		public String getRoomName() {
			return RoomName;
		}
		public void setRoomName(String roomName) {
			RoomName = roomName;
		}
		
		public BookingRoomModel() {
	        UUID uuid = UUID.randomUUID();
	        this.BookingId = uuid.toString().substring(0, 10);
	    }
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getGuestName() {
			return GuestName;
		}
		public void setGuestName(String guestName) {
			GuestName = guestName;
		}
		public String getGuestEmail() {
			return GuestEmail;
		}
		public void setGuestEmail(String guestEmail) {
			GuestEmail = guestEmail;
		}
		public int getNumOfguests() {
			return NumOfguests;
		}
		public void setNumOfguests(int numOfguests) {
			NumOfguests = numOfguests;
		}
		public String getBookingId() {
			return BookingId;
		}
		public void setBookingId(String bookingId) {
			BookingId = bookingId;
		}
		
		
}
