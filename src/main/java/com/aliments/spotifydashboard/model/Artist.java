package com.aliments.spotifydashboard.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private long totalSongs;
    private long totalTenMilViews;
    @Transient
    private List<Song> songs;

    public Artist(String name, long totalSongs) {
        this.name = name;
        this.totalSongs = totalSongs;
    }
}
