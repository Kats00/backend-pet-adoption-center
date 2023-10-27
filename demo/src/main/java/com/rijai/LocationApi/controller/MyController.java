package com.rijai.LocationApi.controller;

import com.rijai.LocationApi.model.*;
import com.rijai.LocationApi.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class MyController {
    @Autowired
    private IDogService dogService;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IAdminService adminService;
    @Autowired
    private IUserService userService;
    @Autowired
    private JwtTokenService jwtTokenService;

    @RequestMapping(value="/api/login", method= RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("Received login request for username: " + loginRequest);
        
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        Account account = accountService.authenticate(username, password);

        if (account != null) {
            System.out.println("Authentication successful for user: " + username);
            Role role = account.getRole();
            String token = jwtTokenService.generateToken(account.getUsername(), role);
            return ResponseEntity.ok(new AuthResponse(token));
        } else {
            System.out.println("Authentication failed for user: " + username);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    

    @RequestMapping("/api/dogs")
    public List<Dog> findDogs(){
       return dogService.getDogs();
    }

    @RequestMapping(value = "/api/show-dog/{id}")
    public Dog showDog(@PathVariable long id) {
       return dogService.getDog(id);
    }

    @RequestMapping(value="/api/add-dog", method= RequestMethod.POST)
    public Dog addDogSubmit(@RequestBody Dog dog) {
        System.out.println(dog);
        return adminService.addDog(dog);
    }

    @RequestMapping(value="/api/update-dog/{id}", method=RequestMethod.PUT)
    public Dog updateDog(@PathVariable int id, @RequestBody Dog updatedDog, HttpServletRequest request) {
        String requestUrl = request.getRequestURL().toString();
        System.out.println("Received request at URL: " + requestUrl);
            
        return adminService.updateDog(id, updatedDog);
    }

    @RequestMapping(value="/api/delete-dog/{id}", method=RequestMethod.DELETE)
    public Dog deleteDog(@PathVariable int id)
    {
        System.out.println("deleting dog with id: " + id);
        return adminService.deleteDog(id);
    }

    @RequestMapping(value="/api/add-user", method=RequestMethod.POST)
    public Account addUser(@RequestBody User user)
    {
        System.out.println("error adding user: " + user);
        return accountService.addAccount(user);
    }

    @RequestMapping(value="/api/add-admin", method=RequestMethod.POST)
    public Account addAdmin(@RequestBody Admin admin)
    {
        System.out.println("error adding admin: " + admin);
        return accountService.addAccount(admin);
    }

    @RequestMapping(value = "/api/adopt-dog/{userId}", method = RequestMethod.POST)
    public ResponseEntity<?> adoptDog(@PathVariable long userId, @RequestBody long dogId) {
        try {
            User user = (User) accountService.getAccount(userId);
            Dog dog = dogService.getDog(dogId);
            if (user != null) {
                User adoptedUser = userService.adoptDog(dog, user);
                return ResponseEntity.ok(adoptedUser);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adopting dog");
        }
    }


}
