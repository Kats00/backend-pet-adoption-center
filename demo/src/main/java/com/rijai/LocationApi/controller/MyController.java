package com.rijai.LocationApi.controller;

//import com.rijai.LocationApi.model.Country;
import com.rijai.LocationApi.model.Dog;
//import com.rijai.LocationApi.service.ICountryService;
import com.rijai.LocationApi.service.IDogService;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class MyController {
    @Autowired
    private IDogService dogService;


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
        return dogService.addDog(dog);
    }

    @RequestMapping(value="/api/update-dog/{id}", method=RequestMethod.PUT)
    public Dog updateDog(@PathVariable int id, @RequestBody Dog updatedDog, HttpServletRequest request) {
        String requestUrl = request.getRequestURL().toString();
        System.out.println("Received request at URL: " + requestUrl);
            
        return dogService.updateDog(id, updatedDog);
    }
    

    @RequestMapping(value="/api/delete-dog/{id}", method=RequestMethod.DELETE)
    public Dog deleteDog(@PathVariable int id)
    {
        System.out.println("deleting dog with id: " + id);
        return dogService.deleteDog(id);
    }

    /*
     * 
     * private ICountryService countryService;


    @RequestMapping("/api/countries")
    public List<Country> findCountries(){
       return countryService.getCountries();
    }

    @RequestMapping(value = "/api/show-country/{id}")
    public Country showCountry(@PathVariable long id) {
       return countryService.getCountry(id);
    }

    @RequestMapping(value="/api/add-country", method= RequestMethod.POST)
    public Country addCountrySubmit(@RequestBody Country country) {
        return countryService.addCountry(country);
    }

    @RequestMapping(value="/api/update-country/{id}", method=RequestMethod.PUT)
    public Country updateCountry(@PathVariable int id, @RequestBody Country updatedCountry, HttpServletRequest request) {
        String requestUrl = request.getRequestURL().toString();
        System.out.println("Received request at URL: " + requestUrl);
            
        return countryService.updateCountry(id, updatedCountry);
    }
    

    @RequestMapping(value="/api/delete-country/{id}", method=RequestMethod.DELETE)
    public Country deleteCountry(@PathVariable int id)
    {
        System.out.println("deleting country with id: " + id);
        return countryService.deleteCountry(id);
    }
     */

}
