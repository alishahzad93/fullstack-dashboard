package com.aliments.spotifydashboard.controller;

import com.aliments.spotifydashboard.model.Artist;
import com.aliments.spotifydashboard.repository.ArtistRepository;
import com.aliments.spotifydashboard.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArtistController {

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    SongRepository songRepository;

    @GetMapping("/artists/{name}")
    public Artist getArtist(@PathVariable String name) {
        Artist artist = artistRepository.findByName(name);
        artist.setSongs(songRepository.findTopSongsByArtist(name,5));
        return artist;
    }
}
