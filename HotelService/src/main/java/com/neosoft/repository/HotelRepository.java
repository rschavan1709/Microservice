package com.neosoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neosoft.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String>{

}
