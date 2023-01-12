package com.neosoft.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosoft.entity.Hotel;
import com.neosoft.exception.ResourceNotFoundException;
import com.neosoft.repository.HotelRepository;

@Service
public class HotelService {
	
	@Autowired
	private HotelRepository hotelRepository;
	
	public Hotel addHotel(Hotel hotel) {
		String hotelId = UUID.randomUUID().toString();
		hotel.setHotelId(hotelId);
		return hotelRepository.save(hotel);
	}
	
	public Hotel getHotelById(String hotelId) {
		return hotelRepository.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("hotel with given id does not exist "+hotelId));
	}
	
	public List<Hotel> getAllHotels(){
		return hotelRepository.findAll();
	}

}
