package com.example.demo.services;
import com.example.demo.beans.Country; 

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.*;


@Service
public class CountryService {
	
	@Autowired
	private CountryRepository countryRep;
	
	//GET All
	public List<Country> getAllCountries() {
	    List<Country> countries = countryRep.findAll();
	    return countries;
	}
	
	
	// GET BY ID
    public Country getCountryById(Long id) {
        return countryRep.findById(id).orElse(null);
    }
    // Get by name
    public List<Country> getCountriesByName(String name) {
        return countryRep.findByNameContainingIgnoreCase(name);
    }
    
    // CREATE
    public Country addCountry(Country country) {
        return countryRep.save(country);
    }
    
    // UPDATE
    public Country updateCountry(Long id, Country countryDetails) {
        Optional<Country> optionalCountry = countryRep.findById(id);
        if (optionalCountry.isPresent()) {
            Country country = optionalCountry.get();
            country.setName(countryDetails.getName());
            country.setCapital(countryDetails.getCapital());
            country.setContinent(countryDetails.getContinent());
            country.setPopulation(countryDetails.getPopulation());
            return countryRep.save(country);
        }
        return null;
    }

    // DELETE
    public boolean deleteCountry(Long id) {
        if (countryRep.existsById(id)) {
            countryRep.deleteById(id);
            return true;
        }
        return false;
    }
    
}