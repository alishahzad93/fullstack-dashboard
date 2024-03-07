package com.aliments.spotifydashboard.data;

import com.aliments.spotifydashboard.model.Song;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class DataProcessor  implements ItemProcessor<SongInput, Song> {
    private static final Logger log = LoggerFactory.getLogger(DataProcessor.class);


    @Override
    public Song process(final SongInput songInput) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Song song = objectMapper.convertValue(songInput, Song.class);
        song.setUrlSpotify(songInput.getUrl_spotify());
        song.setDuration(Long.parseLong(songInput.getDuration_ms().equals("")?"0":songInput.getDuration_ms()));
        song.setAlbumType(songInput.getAlbum_type());
        song.setUrlYoutube(songInput.getUrl_youtube());
        song.setYoutubeTitle(songInput.getTitle());
        return song;
    }
}
