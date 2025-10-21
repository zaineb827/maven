package com.example.demo.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.beans.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    List<Country> findByNameContainingIgnoreCase(String name);
 // Recherche par continent
    List<Country> findByContinent(String continent);
    
    // Requête personnalisée exemple
    @Query("SELECT c FROM Country c WHERE c.population > :minPopulation")
    List<Country> findByPopulationGreaterThan(@Param("minPopulation") Integer minPopulation);

}