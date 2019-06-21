package com.codingdojo.lookify.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.lookify.models.Lookify;

public interface LookifyRepository extends CrudRepository<Lookify, Long> {
    // this method retrieves all the languages from the database
	List<Lookify> findAll();
    // this method find a song by their artist
    List<Lookify> findByArtistContaining(String search);
    // this method find top 10 songs by rating 
    List<Lookify> findTop10ByOrderByRatingDesc();
}
 