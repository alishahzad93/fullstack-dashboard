import {gql} from '@apollo/client'

export const GET_ARTIST = gql`
    query GetArtist($name: String!) {
        getArtist(name: $name) {
            id
            name
            songs {
                track
                album
                urlSpotify
                urlYoutube
                youtubeTitle
                views
            }
        }
    }
`;
export const GET_SONGS = gql`
    query GetSongsByArtist($name: String!) {
        getSongsByArtist(name: $name) {
            artist
            urlSpotify
            track
            album
            urlYoutube
            youtubeTitle
            views
            likes
            comments
            stream
            
        }
    }
`;