package com.aliments.spotifydashboard.data;

import com.aliments.spotifydashboard.model.Song;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@Configuration
public class BatchConfig {
    private final String[] FIELD_NAMES = new String[] {
            "id","Artist", "Url_spotify", "Track", "Album", "Album_type", "Uri", "Danceability", "Energy",
            "Key", "Loudness", "Speechiness", "Acousticness", "Instrumentalness", "Liveness", "Valence",
            "Tempo", "Duration_ms", "Url_youtube", "Title", "Channel", "Views", "Likes", "Comments",
            "Licensed", "official_video", "Stream"
    };
    @Bean
    public FlatFileItemReader<SongInput> reader() {
        return new FlatFileItemReaderBuilder<SongInput>()
                .name("SongItemReader")
                .resource(new ClassPathResource("spotify-data.csv"))
                .delimited()
                .names(FIELD_NAMES)
                .targetType(SongInput.class)
                .build();
    }

    @Bean
    public DataProcessor processor() {
        return new DataProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Song> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Song>()
                .sql("INSERT INTO song (id,artist,url_spotify,track,album,album_type,danceability,energy,duration,url_youtube,youtube_title,views,likes,comments,stream) "
                        + "VALUES (:id, :artist,:urlSpotify,:Track,:Album,:albumType,:Danceability,:Energy,:duration,:urlYoutube,:youtubeTitle,:views,:Likes,:Comments,:Stream)")
                .dataSource(dataSource)
                .beanMapped()
                .build();
    }
    @Bean
    public Job importUserJob(JobRepository jobRepository, Step step1, JobCompletionNotificationListener listener) {
        return new JobBuilder("importSongJob", jobRepository)
                .listener(listener)
                .start(step1)
                .build();
    }
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    @Bean
    public Step step1(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
                      FlatFileItemReader<SongInput> reader, DataProcessor processor, JdbcBatchItemWriter<Song> writer) {
        return new StepBuilder("step1", jobRepository)
                .<SongInput, Song> chunk(3, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(final CorsRegistry registry) {
                registry.addMapping("/graphql/**")
                        .allowedOrigins(CorsConfiguration.ALL)
                        .allowedHeaders(CorsConfiguration.ALL)
                        .allowedMethods(CorsConfiguration.ALL);
            }
        };
    }
}
