package com.neosoft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.entity.Rating;
import com.neosoft.service.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {

	@Autowired
	private RatingService ratingService;

	@PostMapping("/add")
	public ResponseEntity<Rating> addRating(@RequestBody Rating rating) {
		Rating rating1 = ratingService.addRating(rating);
		return ResponseEntity.status(HttpStatus.CREATED).body(rating1);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Rating> getRatingById(@PathVariable("id") String ratingId) {
		Rating rating = ratingService.getRatingById(ratingId);
		return ResponseEntity.status(HttpStatus.OK).body(rating);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Rating>> getAllRatings() {
		List<Rating> allRating = ratingService.getAllRating();
		return ResponseEntity.ok(allRating);
	}
	
	
	
	@GetMapping("/users/{id}")
	public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable("id") String userId) {
		List<Rating> ratings = ratingService.getRatingByUserId(userId);
		return ResponseEntity.status(HttpStatus.OK).body(ratings);
	}
	
	
	@GetMapping("/hotels/{id}")
	public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable("id") String hotelId) {
		List<Rating> ratings = ratingService.getRatingByHotelId(hotelId);
		return ResponseEntity.status(HttpStatus.OK).body(ratings);
	}
	
}
