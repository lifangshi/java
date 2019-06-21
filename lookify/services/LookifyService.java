package com.codingdojo.lookify.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.lookify.models.Lookify;
import com.codingdojo.lookify.repositories.LookifyRepository;

@Service
public class LookifyService {
	// adding the song repository as a dependency
	private final LookifyRepository lookifyRepository;
	
	public LookifyService(LookifyRepository lookifyRepository) {
		this.lookifyRepository = lookifyRepository;
	}
    // returns all the songs
    public List<Lookify> allLookify() {
        return (List<Lookify>) lookifyRepository.findAll();
    }
    // creates a song
    public Lookify createSong(Lookify b) {
        return lookifyRepository.save(b);
    }
    // retrieves a song
    public Lookify findSong(Long id) {
    	Optional<Lookify> optionalLookify = lookifyRepository .findById(id);
    	if(optionalLookify.isPresent()) {
    		return optionalLookify.get();
    	} else {
    		return null;
    	}
    }
    
    // deletes a song
    public void deleteSong(Long id) {
    	lookifyRepository.deleteById((id));
    }   
    // top ten song
    public List<Lookify> topSong() {
        return (List<Lookify>) lookifyRepository.findTop10ByOrderByRatingDesc();
    }
    
    // find one artist's song(s)
    public List<Lookify> artistSong(String search) {
    	return (List<Lookify>) lookifyRepository.findByArtistContaining(search);
    }
}
