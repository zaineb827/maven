package com.example.demo.controllers;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import java.util.List;
import  com.example.demo.beans.Country; 
import  com.example.demo.services.CountryService;; 


@RestController
public class CountryController {

    @Autowired  // ← INJECTION du Service
    private CountryService countryservice; 

   @GetMapping("/getcountries")
	public ResponseEntity<List<Country>> getCountries() {
	    try {
	        List<Country> countries = countryservice.getAllCountries();
	        return new ResponseEntity<List<Country>>(countries, HttpStatus.FOUND);
	    }
	    catch (Exception e) {
	        return new ResponseEntity<List<Country>>(HttpStatus.NOT_FOUND);
	    }
	}
   
   //Get by id
   @GetMapping("/getcountries/{id}")
   public ResponseEntity<Country> getCountryById(@PathVariable Long id) {
	   try {
	        Country country = countryservice.getCountryById(id);
	        
	        if (country != null) {
                return new ResponseEntity<>(country, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }	    }
	    catch (Exception e) {
	        return new ResponseEntity<Country>(HttpStatus.NOT_FOUND);
	    }
   }
   
   // GEt by name
   @GetMapping("/getcountries/countryname")
   public ResponseEntity<List<Country>> getCountriesByName(@RequestParam String name) {
       try {
           List<Country> countries = countryservice.getCountriesByName(name);
           return new ResponseEntity<>(countries, HttpStatus.OK);
       } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
   }
   
   // Post
   @PostMapping("/addcountry")
   public ResponseEntity<Country> addCountry(@RequestBody Country country) {
       try {
           Country savedCountry = countryservice.addCountry(country);
           return new ResponseEntity<>(savedCountry, HttpStatus.CREATED);
       } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
   }
   
    // PUT 
   @PutMapping("/updatecountry/{id}")
   public ResponseEntity<Country> updateCountry(@PathVariable Long id, @RequestBody Country countryDetails) {
       try {
           Country updatedCountry = countryservice.updateCountry(id, countryDetails);
           if (updatedCountry != null) {
               return new ResponseEntity<>(updatedCountry, HttpStatus.OK);
           } else {
               return new ResponseEntity<>(HttpStatus.NOT_FOUND);
           }
       } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
   }

   // DELETE 
   @DeleteMapping("/deletecountry/{id}")
   public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
       try {
           boolean deleted = countryservice.deleteCountry(id);
           if (deleted) {
               return new ResponseEntity<>(HttpStatus.NO_CONTENT);
           } else {
               return new ResponseEntity<>(HttpStatus.NOT_FOUND);
           }
       } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
   }
   
   
}



























