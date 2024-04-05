import {React, useEffect, useState} from "react";
import {SongDetailCard} from "../components/SongDetailCard";
import {SongSmallCard} from "../components/SongSmallCard";
import { gql, useQuery } from '@apollo/client';
import {GET_ARTIST, GET_SONGS} from '../Graphql/Queries'
import {useParams} from "react-router-dom";

export const SongPage= () => {
    const [songs, setSongs] = useState([]);
    const {artistName} = useParams();
    const { loading, error, data } = useQuery(GET_SONGS, {
        variables: { name: artistName }, // Pass the artist name as a variable
    });
    useEffect(() => {
        const fetchSongs = async () => {
            const response = await data;
            if(response) {
                console.log(response);
                const songsRes = await response.getSongsByArtist;
                setSongs(songsRes);

            }
        };
        fetchSongs();

    }, [data]);
    return (
        <div className="SongPage">
            <h1>Song Page</h1>
            {songs.map(song => <SongSmallCard song={song}/>)}
        </div>
    );
}

