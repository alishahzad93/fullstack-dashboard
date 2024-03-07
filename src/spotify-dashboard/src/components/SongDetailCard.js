import {React} from "react";

export const SongDetailCard= ({song}) => {
    return (
        <div className="SongDetailCard">
            <h1>Top Songs</h1>
            <h3>{song.track} | views: {song.views}</h3>
        </div>
    );
}

