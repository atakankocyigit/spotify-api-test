# Spotify API

The project was created using the API of the Spotify application. Description of the functions used can be found at https://developer.spotify.com/documentation/web-api/reference/#/.

To use the application, authorization must be obtained by following the steps in the authorization guide https://developer.spotify.com/documentation/general/guides/authorization/. What to choose in authorization scopes:

- user-read-playback-position
- user-library-modify
- user-library-read
- user-read-email
- playlist-modify-private
- playlist-read-collaborative
- user-follow-modify
- user-follow-read

The access Token variable obtained after following the steps must be changed in the RequestSpec.java file in the \src\main\java\com\spotify\spec directory.

Functions tested in the project:

- Get Several Shows
- Get Show Episodes
- Save Tracks for Current User
- Remove Tracks for Current User
- Get User's Saved Tracks
- Get Current User's Profile
- Follow Playlist
- Get Current User's Playlists
- Follow Artists or Users
- Check If User Follows Artists or Users
- Get User's Profile


## LICENSE
MIT LICENSE
