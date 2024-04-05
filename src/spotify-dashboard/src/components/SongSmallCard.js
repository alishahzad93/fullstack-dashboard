import {React} from "react";
import {Link} from "react-router-dom";
import "./SongSmallCard.css";
export const SongSmallCard= ({song}) => {
    return (
        <div className="SongSmallCard card">
            <h3>{song.track}</h3>
            <h3 className="song-views">Views</h3>
            <p>{song.views}</p>
            <h3 className="song-album">Album</h3>
            <p>{song.album}</p>
            <p className="song-url">
                <Link to={song.urlYoutube}>{song.youtubeTitle}</Link>
            </p>
        </div>
    );
}

