package com.aliments.spotifydashboard.controller;

import com.aliments.spotifydashboard.model.Artist;
import com.aliments.spotifydashboard.model.Song;
import com.aliments.spotifydashboard.repository.ArtistRepository;
import com.aliments.spotifydashboard.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Controller
public class ArtistGraphqlController {

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    SongRepository songRepository;

    @QueryMapping("getArtist")
    public Artist getArtist(@Argument String name) {
        Artist artist = artistRepository.findByName(name);
        artist.setSongs(songRepository.findTopSongsByArtist(name,5));
        return artist;
    }
    @QueryMapping("getSongsByArtist")
    public List<Song> getSongsByArtist(@Argument String name) {
        return songRepository.findByArtistOrderByViewsDesc(name);
    }
    @QueryMapping("getArtists")
    public List<Artist> getArtists() {
//        Artist artist = artistRepository.findByName(name);
//        artist.setSongs(songRepository.findTopSongsByArtist(name,5));
        return artistRepository.findAll().subList(0,5);
    }
}
