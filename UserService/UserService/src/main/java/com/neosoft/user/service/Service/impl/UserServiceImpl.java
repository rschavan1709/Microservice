package com.neosoft.user.service.Service.impl;

import com.neosoft.user.service.Exception.ResourceNotFoundException;
import com.neosoft.user.service.Service.UserService;
import com.neosoft.user.service.entity.Hotel;
import com.neosoft.user.service.entity.Rating;
import com.neosoft.user.service.entity.User;
import com.neosoft.user.service.external.service.HotelService;
import com.neosoft.user.service.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    HotelService hotelService;

    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList=userRepository.findAll();
        for(User user: userList){
            String id=user.getUserId();
            Rating[] ratingArray=restTemplate.getForObject("http://localhost:8082/ratings/users/"+id, Rating[].class);
            List<Rating> ratingList=Arrays.stream(ratingArray).toList();
            for(Rating rating:ratingList){
                //Hotel hotel = restTemplate.getForObject("http://localhost:8081/hotels/"+rating.getHotelId(), Hotel.class);
                Hotel hotel=hotelService.getHotel(rating.getHotelId());
                rating.setHotel(hotel);
            }
            user.setRatings(ratingList);
        }
        return userList;
    }

    @Override
    public User getUser(String userId) {
        User user= userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User Not Found"));
        Rating[] ratingArray= restTemplate.getForObject("http://localhost:8082/ratings/users/"+user.getUserId(), Rating[].class
        );
        // logger.info("{}",ratingList);
        List<Rating> ratingList=Arrays.stream(ratingArray).toList();
//        List<Rating> ratingAllList=ratingList.stream().map(rating -> {
//                    Hotel hotel = restTemplate.getForObject("http://localhost:8081/hotels/"+rating.getHotelId(), Hotel.class);
//                    rating.setHotel(hotel);
//                    return rating;
//                }).collect(Collectors.toList());
        for(Rating rating:ratingList){
           // Hotel hotel = restTemplate.getForObject("http://localhost:8081/hotels/"+rating.getHotelId(), Hotel.class);
           Hotel hotel=hotelService.getHotel(rating.getHotelId());
           rating.setHotel(hotel);
        }
        user.setRatings(ratingList);
        return user;
    }

    @Override
    public void delete(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User update(User user,String userId) {
        User user1 = userRepository.findById(userId).get();
        user1=user;
        return userRepository.save(user1);
    }
}
