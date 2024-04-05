import {React} from "react";
import { Link } from 'react-router-dom'
import "./SongDetailCard.css";

export const SongDetailCard= ({song}) => {
    return (
        <div className="SongDetailCard card">
            <div className="top-songs-section"><h1>Top Songs</h1></div>
            <div>
                <h2>{song.track}</h2>
                <h3 className="song-views">Views</h3>
                <p>{song.views}</p>
                <h3 className="song-album">Album</h3>
                <p>{song.album}</p>
            </div>
            <div className="song-links">
                <h3 className="song-url">Youtube</h3>
                <p><Link to={song.urlYoutube}>{song.youtubeTitle}</Link></p>
                <h3 className="song-spotify"><Link to={song.urlSpotify}>Spotify</Link></h3>
            </div>
        </div>
    );
}

