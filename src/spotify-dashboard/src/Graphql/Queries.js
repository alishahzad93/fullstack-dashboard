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