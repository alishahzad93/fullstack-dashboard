package com.aliments.spotifydashboard.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongInput {
    private String id;
    private String Artist;
    private String Url_spotify;
    private String Track;
    private String Album;
    private String Album_type;
    private String Uri;
    private String Danceability;
    private String Energy;
    private String Key;
    private String Loudness;
    private String Speechiness;
    private String Acousticness;
    private String Instrumentalness;
    private String Liveness;
    private String Valence;
    private String Tempo;
    private String Duration_ms;
    private String Url_youtube;
    private String Title;
    private String Channel;
    private String Views;
    private String Likes;
    private String Comments;
    private String Licensed;
    private String official_video;
    private String Stream;
}
