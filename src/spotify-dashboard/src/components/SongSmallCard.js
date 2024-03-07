import {React} from "react";

export const SongSmallCard= ({song}) => {
    return (
        <div className="SongSmallCard">
            <p>{song.track} | views: {song.views}</p>
        </div>
    );
}

