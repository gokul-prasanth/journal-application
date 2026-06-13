package com.gokul.journal.application.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gokul.journal.application.api.response.WeatherResponse;
import com.gokul.journal.application.dtos.UpdateUserDTO;
import com.gokul.journal.application.entity.User;
import com.gokul.journal.application.repos.UserRepository;
import com.gokul.journal.application.service.UserService;
import com.gokul.journal.application.service.WeatherService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/user")
@Tag(
        name = "User APIs",
        description = "APIs for logged-in users to read profile details, update account information, delete their account, and get weather-based greetings."
)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;

    @Operation(
            summary = "Update User Details",
            description = "Allows an authenticated user to update their username and password. The current username is fetched from the authentication context."
    )
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserDTO updateUserDTO) {
        //get authentication details
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDB = userService.findByUserName(userName);

        if (userInDB != null ) {
            userInDB.setUserName(updateUserDTO.getUserName());
            userInDB.setPassword(updateUserDTO.getPassword());
            userInDB.setEmail(updateUserDTO.getEmail());
            userService.saveNewUser(userInDB);
        }
        String updateMessage = "User details updated for: " + updateUserDTO.getUserName();
        return new ResponseEntity<>(updateMessage, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete User Account",
            description = "Deletes the authenticated user's account from the database. This action cannot be undone."
    )
    @DeleteMapping("/delete-user")
    public ResponseEntity<?> deleteUserById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());

        String deletedMessage = "User Deleted Successfully: " + authentication.getName();
        return new ResponseEntity<>(deletedMessage, HttpStatus.OK);
    }

    @Operation(
            summary = "Get Weather-Based Greeting",
            description = "Returns a personalized greeting for the logged-in user along with the current weather 'feels like' temperature for Bangalore."
    )
    @GetMapping("/weather")
    public ResponseEntity<?> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getWeather("Bangalore");
        String greeting = "";
        if (weatherResponse != null) {
            greeting = ", Bangalore Weather feels like " + weatherResponse.getCurrent().getFeelslike() + " degree celsius";
        }
        return new ResponseEntity<>("Hi " + authentication.getName().toUpperCase(Locale.ROOT) + greeting, HttpStatus.OK);
    }
    
}