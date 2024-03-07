import {React, useEffect, useState} from "react";
import {SongDetailCard} from "../components/SongDetailCard";
import {SongSmallCard} from "../components/SongSmallCard";

export const ArtistPage= () => {

    const [artist, setArtist] = useState({
        songs: []
    });

    useEffect(() => {
        const fetchArtist = async () => {
            const response = await fetch('http://localhost:8080/artists/Coldplay');
            const data = await response.json();
            setArtist(data);
        };
        fetchArtist();

    }, []);
    return (
        <div className="ArtistPage">
            <h1>{artist.name}</h1>
            <SongDetailCard song={artist.songs[0]}/>
            {artist.songs.slice(1).map(song => <SongSmallCard song={song}/>)}
        </div>
    );
}

