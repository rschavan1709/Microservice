package com.neosoft.user.service.Controller;

import com.neosoft.user.service.Service.UserService;
import com.neosoft.user.service.entity.User;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity<User> save(@RequestBody User user){
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> allUser = userService.getAllUsers();
        return ResponseEntity.ok(allUser);
    }

    @GetMapping("/getUser/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId){
        User user1= userService.getUser(userId);
        return ResponseEntity.ok(user1);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> delete(@PathVariable String userId){
        userService.delete(userId);
        return ResponseEntity.status(HttpStatus.OK).body("deleted");
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<User> update(@RequestBody User user,@PathVariable String userId){
        User user1 = userService.update(user,userId);
        return ResponseEntity.status(HttpStatus.OK).body(user1);
    }
}
