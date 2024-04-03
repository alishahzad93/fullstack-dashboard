import {React} from "react";
import { Link } from 'react-router-dom'

export const SongDetailCard= ({song}) => {
    return (
        <div className="SongDetailCard">
            <h1>Top Songs</h1>
            <h2>{song.track} | views: {song.views}</h2>
            <h3>{song.album}</h3>
            <h4><Link to={song.urlYoutube}>{song.youtubeTitle}</Link></h4>
            <h5><Link to={song.urlSpotify}>Spotify</Link></h5>

        </div>
    );
}

