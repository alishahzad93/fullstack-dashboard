package com.aliments.spotifydashboard.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.TimeUnit;

@Entity
@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Song {
    @Id
    private long id;
    private String artist;
    private String urlSpotify;
    private String Track;
    private String Album;
    private String albumType;
    private double Danceability;
    private double Energy;
    private long duration;
    private String urlYoutube;
    private String youtubeTitle;
    private long views;
    private long Likes;
    private long Comments;
    private long Stream;

    public Song() {

    }
}
