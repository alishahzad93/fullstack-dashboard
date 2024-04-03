import {React} from "react";
import {Link} from "react-router-dom";

export const SongSmallCard= ({song}) => {
    return (
        <div className="SongSmallCard">
            <p>{song.track} | views: {song.views}</p>
            <p>{song.album}</p>
            <p>
                <Link to={song.urlYoutube}>{song.youtubeTitle}</Link>
            </p>
        </div>
    );
}

