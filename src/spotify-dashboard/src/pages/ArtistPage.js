import {React, useEffect, useState} from "react";
import {SongDetailCard} from "../components/SongDetailCard";
import {SongSmallCard} from "../components/SongSmallCard";
import { gql, useQuery } from '@apollo/client';
import {GET_ARTIST} from '../Graphql/Queries'
import {useParams} from "react-router-dom";
import  './ArtistPage.css';

export const ArtistPage= () => {

    const [artist, setArtist] = useState({
        songs: [{
            "track": "",
            "views": 0
        }]
    });
    const {artistName} = useParams();
    const { loading, error, data } = useQuery(GET_ARTIST, {
        variables: { name: artistName }, // Pass the artist name as a variable
    });

    useEffect(() => {
        const fetchArtist = async () => {
            //const response = await fetch('http://localhost:8080/artists/Coldplay');
            // const response = useQuery(GET_ARTIST, {
            //     variables: { name: 'Coldplay' }, // Pass the artist name as a variable
            // });
            // console.log(response);
            // const data = await response.json();
            const response = await data;
            if(response) {
                const artistRes = await response.getArtist;
                setArtist(artistRes);
                console.log(artist);
            }
        };
        fetchArtist();

    }, [data]);
    if(!artist || !artist.name) {
        return <h1>Artist not found</h1>
    }
    return (
        <div className="ArtistPage">
            <div className="artist-name-section"><h1 className="artist-name">{artist.name}</h1></div>
            <div className="views-graph-section">views graph</div>
            <div className="song-detail-section">
            <SongDetailCard song={artist.songs[0]}/>
            </div>
            {artist.songs.slice(1).map(song => <SongSmallCard song={song}/>)}
            <div className="more-link">
                <a href="#">More ></a>
            </div>
        </div>
    );
}

