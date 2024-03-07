package com.aliments.spotifydashboard.repository;

import com.aliments.spotifydashboard.model.Song;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findByArtistOrderByViewsDesc(String name, Pageable pageable);

    default List<Song> findTopSongsByArtist(String name, int count) {
        return findByArtistOrderByViewsDesc(name, PageRequest.of(0,count));
    }
}
