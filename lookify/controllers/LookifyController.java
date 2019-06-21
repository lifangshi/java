package com.codingdojo.lookify.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.lookify.models.Lookify;
import com.codingdojo.lookify.services.LookifyService;

@Controller
public class LookifyController {
	private final LookifyService lookifyService;
	
	public LookifyController(LookifyService lookifyService) {
	this.lookifyService = lookifyService;
	}
	
	@RequestMapping("/")
	public String index(@ModelAttribute("song") Lookify lookify) {
	    return "index.jsp";
	}
	
    @RequestMapping("/dashboard")
    public String dash(@ModelAttribute("song") Lookify lookify, Model model) {
        List<Lookify> allLookify = lookifyService.allLookify();
        model.addAttribute("songs", allLookify);
        return "/dashboard.jsp";
    }
    
    @RequestMapping("/songs/new")
    public String newLanguage(@ModelAttribute("song") Lookify lookify) {
    	return "/new.jsp";
    }
    
    @RequestMapping(value="/songs/new", method=RequestMethod.POST)
    public String create(@Valid @ModelAttribute("song") Lookify lookify, BindingResult result) {
        if (result.hasErrors()) {
            return "new.jsp";
        } else {
            lookifyService.createSong(lookify);
            return "redirect:/dashboard";
        }
    }
    
	//  show a language
	@RequestMapping("/songs/{id}")
	public String show(@PathVariable("id") Long	id, Model model) {
	Lookify lookify = lookifyService.findSong(id);
	model.addAttribute("song", lookify);
		return "search_id.jsp";
	}
    
    @RequestMapping(value="/delete/{id}")
    public String destroy(@PathVariable("id") Long id) {
        lookifyService.deleteSong(id);
        return "redirect:/dashboard";
    }
    
    @RequestMapping("/search/topTen")
    public String top(@ModelAttribute("song") Lookify lookify, Model model) {
        List<Lookify> tenSongs = lookifyService.topSong();
        model.addAttribute("songs", tenSongs);
        return "/search_top.jsp";
    }
    
//    @RequestMapping("/search/{artist}")
//    public String artistSong(@PathVariable("artist") String artist, @ModelAttribute("songs") Lookify lookify, Model model) {
//        List<Lookify> artistSongs = lookifyService.artistSong(artist);
//        model.addAttribute("songs", artistSongs);
//        return "/search_name.jsp";
//    }
//    @PostMapping("/search")
//    public String search(@RequestParam("artist_name") String artist) {
//        return "redirect:/search/" + artist;
//    }
//    
    @RequestMapping("/search/{artist}")
    public String artistSong(@PathVariable("artist") String artist, Model model) {
        List<Lookify> artistSongs = lookifyService.artistSong(artist);
        model.addAttribute("songs", artistSongs);
        model.addAttribute("artist", artist);
        return "/search_name.jsp";
    }
    @RequestMapping(value="/search", method=RequestMethod.POST)
    public String artistSearch(@RequestParam("artist") String artist) {
    	return "redirect:/search/"+ artist;
    }

}
