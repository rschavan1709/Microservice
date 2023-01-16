package com.neosoft.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosoft.entity.Rating;
import com.neosoft.exception.ResourceNotFoundException;
import com.neosoft.repository.RatingRepository;

@Service
public class RatingService {
	
	@Autowired
	private RatingRepository ratingRepository;
	
	public Rating addRating(Rating rating) {
		String ratingId = UUID.randomUUID().toString();
		rating.setRatingId(ratingId);
		return ratingRepository.save(rating);
	}
	
	public Rating getRatingById(String ratingId) {
		return ratingRepository.findById(ratingId).orElseThrow(()->new ResourceNotFoundException("Rating with given id not found "+ratingId));
	}
	
	public List<Rating> getAllRating(){
		return ratingRepository.findAll();
	}
	
	public List<Rating> getRatingByUserId(String userId) {
		return ratingRepository.findByUserId(userId);
	}
	
	public List<Rating> getRatingByHotelId(String hotelId) {
		return ratingRepository.findByHotelId(hotelId);
	}
	


}
