## Principais Rotas

### Users

| Método | Rota                                    | Descrição |
|--------|-----------------------------------------|-----------|
| POST   | `http://localhost:8080/users`           | CREATE    |
| GET    | `http://localhost:8080/users`           | GET ALL   |
| GET    | `http://localhost:8080/users/<user_id>` | GET BY ID |
| PUT    | `http://localhost:8080/users/<user_id>` | UPDATE    |
| DELETE | `http://localhost:8080/users/<user_id>` | DELETE    |

#### Exemplos cURL

##### **POST http://localhost:8080/users**

```bash
curl -X POST "http://localhost:8080/users" \
  -H "Content-Type: application/json" \
  -H "Access-Control-Allow-Origin: *" \
  -d '{
	"name": "Juliano Aleixo",
	"email": "juliano@email.com",
	"password": "123123"
}'
```

##### **GET http://localhost:8080/users**

```bash
curl -X GET "http://localhost:8080/users"
```

##### **GET http://localhost:8080/users/<user_id>**

```bash
curl -X GET "http://localhost:8080/users/8fdb65dc-743a-44a4-b735-7472e59e6297"
```

##### **PUT http://localhost:8080/users/<user_id>**

```bash
curl -X PUT "http://localhost:8080/users/8fdb65dc-743a-44a4-b735-7472e59e6297" \
  -H "Content-Type: application/json" \
  -d '{
	"name": "Tiago Braga",
	"email": "tiago@email.com",
	"password": "123123"
}'
```

##### **DELETE http://localhost:8080/users/<user_id>**

```bash
curl -X DELETE "http://localhost:8080/users/8fdb65dc-743a-44a4-b735-7472e59e6297"
```

---

### Follows

| Método | Rota                                                | Descrição |
| ------ |-----------------------------------------------------| ---------- |
| POST | `http://localhost:8080/follows`                     | FOLLOW |
| DELETE | `http://localhost:8080/follows`                     | UNFOLLOW |
| GET | `http://localhost:8080/follows/followers/<user_id>` | GET FOLLOWERS |
| GET | `http://localhost:8080/follows/following/<user_id>` | GET FOLLOWING |

#### Exemplos cURL

##### **POST http://localhost:8080/follows**

```bash
curl -X POST "http://localhost:8080/follows" \
  -H "Content-Type: application/json" \
  -H "Access-Control-Allow-Origin: *" \
  -d '{
	"followedId": "0e0d9915-da70-4863-a043-c27442a96c3f",
	"followerId": "623d4830-bb62-45c0-b4a8-1005716b9d76"
}'
```

##### **DELETE http://localhost:8080/follows**

```bash
curl -X DELETE "http://localhost:8080/follows" \
  -H "Content-Type: application/json" \
  -d '{
	"followerId": "0e0d9915-da70-4863-a043-c27442a96c3f",
	"followedId": "623d4830-bb62-45c0-b4a8-1005716b9d76"
}'
```

##### **GET http://localhost:8080/follows/followers/<user_id>**

```bash
curl -X GET "http://localhost:8080/follows/followers/623d4830-bb62-45c0-b4a8-1005716b9d76" \
  -H "Access-Control-Allow-Origin: *"
```

##### **GET http://localhost:8080/follows/following/<user_id>**

```bash
curl -X GET "http://localhost:8080/follows/following/623d4830-bb62-45c0-b4a8-1005716b9d76" \
  -H "Access-Control-Allow-Origin: *"
```

---

### Genres

| Método | Rota                                      | Descrição |
| ------ |-------------------------------------------| ---------- |
| POST | `http://localhost:8080/genres`            | CREATE |
| GET | `http://localhost:8080/genres`            | GET ALL |
| GET | `http://localhost:8080/genres/<genre_id>` | GET BY ID |
| PUT | `http://localhost:8080/genres/<genre_id>` | UPDATE |
| DELETE | `http://localhost:8080/genres/<genre_id>` | DELETE |

#### Exemplos cURL

##### **POST http://localhost:8080/genres**

```bash
curl -X POST "http://localhost:8080/genres" \
  -H "Content-Type: application/json" \
  -H "Access-Control-Allow-Origin: *" \
  -d '{
	"name": "Rock"
}'
```

##### **GET http://localhost:8080/genres**

```bash
curl -X GET "http://localhost:8080/genres"
```

##### **GET http://localhost:8080/genres/<genre_id>**

```bash
curl -X GET "http://localhost:8080/genres/07f2d23c-3783-4aee-9476-23e62a657cec"
```

##### **PUT http://localhost:8080/genres/<genre_id>**

```bash
curl -X PUT "http://localhost:8080/genres/07f2d23c-3783-4aee-9476-23e62a657cec" \
  -H "Content-Type: application/json" \
  -d '{
	"name": "Rap"
}'
```

##### **DELETE http://localhost:8080/genres/<genre_id>**

```bash
curl -X DELETE "http://localhost:8080/genres/07f2d23c-3783-4aee-9476-23e62a657cec"
```

---

### Artists

| Método | Rota                                        | Descrição |
| ------ |---------------------------------------------| ---------- |
| POST | `http://localhost:8080/artists`             | CREATE |
| GET | `http://localhost:8080/artists`             | GET ALL |
| GET | `http://localhost:8080/artists/<artist_id>` | GET BY ID |
| PUT | `http://localhost:8080/artists/<artist_id>` | UPDATE |
| DELETE | `http://localhost:8080/artists/<artist_id>` | DELETE |

#### Exemplos cURL

##### **POST http://localhost:8080/artists**

```bash
curl -X POST "http://localhost:8080/artists" \
  -H "Content-Type: application/json" \
  -H "Access-Control-Allow-Origin: *" \
  -d '{
	"name": "Metallica",
	"country": "United States of America",
	"mainGenre": "fb6c9412-d99d-4b18-909f-496221be696e"
}'
```

##### **GET http://localhost:8080/artists**

```bash
curl -X GET "http://localhost:8080/artists"
```

##### **GET http://localhost:8080/artists/<artist_id>**

```bash
curl -X GET "http://localhost:8080/artists/ce628894-2d4f-427d-8653-4eddc5be80a7"
```

##### **PUT http://localhost:8080/artists/<artist_id>**

```bash
curl -X PUT "http://localhost:8080/artists/ce628894-2d4f-427d-8653-4eddc5be80a7" \
  -H "Content-Type: application/json" \
  -d '{
	"name": "Megadeth",
	"country": "United States of America",
	"mainGenre": "fb6c9412-d99d-4b18-909f-496221be696e"
}'
```

##### **DELETE http://localhost:8080/artists/<artist_id>**

```bash
curl -X DELETE "http://localhost:8080/artists/ce628894-2d4f-427d-8653-4eddc5be80a7"
```

---

### Albums

| Método | Rota | Descrição |
| ------ | ----- | ---------- |
| POST | `http://localhost:8080/albums` | CREATE |
| GET | `http://localhost:8080/albums` | GET ALL |
| GET | `http://localhost:8080/albums/<album_id>` | GET BY ID |
| PUT | `http://localhost:8080/albums/<album_id>` | UPDATE |
| DELETE | `http://localhost:8080/albums/<album_id>` | DELETE |

#### Exemplos cURL

##### **POST http://localhost:8080/albums**

```bash
curl -X POST "http://localhost:8080/albums" \
  -H "Content-Type: application/json" \
  -H "Access-Control-Allow-Origin: *" \
  -d '{
	"title": "Ride the Lightning",
	"releaseYear": 1984,
	"coverUrl": "https://cdn.media.amplience.net/i/metallica/ride-the-lightning_cover",
	"artists": "8514f044-5588-4641-a1ad-89c418773a6c",
	"genres": "fb6c9412-d99d-4b18-909f-496221be696e"
}'
```

##### **GET http://localhost:8080/albums**

```bash
curl -X GET "http://localhost:8080/albums"
```

##### **GET http://localhost:8080/albums/<album_id>**

```bash
curl -X GET "http://localhost:8080/albums/2bd988e7-9a90-45c6-8744-95cdf1d6091e"
```

##### **PUT http://localhost:8080/albums/<album_id>**

```bash
curl -X PUT "http://localhost:8080/albums/2bd988e7-9a90-45c6-8744-95cdf1d6091e" \
  -H "Content-Type: application/json" \
  -d '{
	"title": "Ride the Lightning",
	"releaseYear": 1984,
	"coverUrl": "https://cdn.media.amplience.net/i/metallica/ride-the-lightning_cover",
	"artists": "8514f044-5588-4641-a1ad-89c418773a6c",
	"genres": "fb6c9412-d99d-4b18-909f-496221be696e"
}'
```

##### **DELETE http://localhost:8080/albums/<album_id>**

```bash
curl -X DELETE "http://localhost:8080/albums/2bd988e7-9a90-45c6-8744-95cdf1d6091e"
```

---

### Tracks

| Método | Rota | Descrição |
| ------ | ----- | ---------- |
| POST | `http://localhost:8080/tracks` | CREATE |
| GET | `http://localhost:8080/tracks` | GET ALL |
| GET | `http://localhost:8080/tracks/<track_id>` | GET BY ID |
| PUT | `http://localhost:8080/tracks/<track_id>` | UPDATE |
| DELETE | `http://localhost:8080/tracks/<track_id>` | DELETE |

#### Exemplos cURL

##### **POST http://localhost:8080/tracks**

```bash
curl -X POST "http://localhost:8080/tracks" \
  -H "Content-Type: application/json" \
  -H "Access-Control-Allow-Origin: *" \
  -d '{
	"title": "For Whom the Bell Tolls",
	"duration": 309,
	"genres": "fb6c9412-d99d-4b18-909f-496221be696e",
	"albums": "8a913dc9-81d6-4de2-a89c-df9e94425c9f"
}'
```

##### **GET http://localhost:8080/tracks**

```bash
curl -X GET "http://localhost:8080/tracks"
```

##### **GET http://localhost:8080/tracks/<track_id>**

```bash
curl -X GET "http://localhost:8080/tracks/9d02c6c5-8f81-4caa-8652-2667bac42b0c"
```

##### **PUT http://localhost:8080/tracks/<track_id>**

```bash
curl -X PUT "http://localhost:8080/tracks/9d02c6c5-8f81-4caa-8652-2667bac42b0c" \
  -H "Content-Type: application/json" \
  -d '{
	"title": "For Whom the Bell Tolls",
	"duration": 309,
	"genres": "fb6c9412-d99d-4b18-909f-496221be696e",
	"albums": "8a913dc9-81d6-4de2-a89c-df9e94425c9f"
}'
```

##### **DELETE http://localhost:8080/tracks/<track_id>**

```bash
curl -X DELETE "http://localhost:8080/tracks/9d02c6c5-8f81-4caa-8652-2667bac42b0c"
```

---

### Reviews

| Método | Rota | Descrição |
| ------ | ----- | ---------- |
| POST | `http://localhost:8080/reviews` | CREATE |
| GET | `http://localhost:8080/reviews` | GET ALL |
| GET | `http://localhost:8080/reviews/<review_id>` | GET BY ID |
| PUT | `http://localhost:8080/reviews/<review_id>` | UPDATE |
| DELETE | `http://localhost:8080/reviews/<review_id>` | DELETE |

#### Exemplos cURL

##### **POST http://localhost:8080/reviews**

```bash
curl -X POST "http://localhost:8080/reviews" \
  -H "Content-Type: application/json" \
  -H "Access-Control-Allow-Origin: *" \
  -d '{
	"rating": 5,
	"comment": "Um classico do Metallica!",
	"users": "623d4830-bb62-45c0-b4a8-1005716b9d76"
}'
```

##### **GET http://localhost:8080/reviews**

```bash
curl -X GET "http://localhost:8080/reviews"
```

##### **GET http://localhost:8080/reviews/<review_id>**

```bash
curl -X GET "http://localhost:8080/reviews/989f40a8-d05d-40fb-9de9-03fbb7197727"
```

##### **PUT http://localhost:8080/reviews/<review_id>**

```bash
curl -X PUT "http://localhost:8080/reviews/989f40a8-d05d-40fb-9de9-03fbb7197727" \
  -H "Content-Type: application/json" \
  -d '{
	"rating": 5,
	"comment": "Um classico do Metallica!",
	"users": "623d4830-bb62-45c0-b4a8-1005716b9d76"
}'
```

##### **DELETE http://localhost:8080/reviews/<review_id>**

```bash
curl -X DELETE "http://localhost:8080/reviews/989f40a8-d05d-40fb-9de9-03fbb7197727"
```

---

### ReviewLikes

| Método | Rota                                           | Descrição |
| ------ |------------------------------------------------| ---------- |
| POST | `http://localhost:8080/review_likes`           | CREATE |
| GET | `http://localhost:8080/review_likes`           | GET ALL |
| GET | `http://localhost:8080/review_likes/<user_id>` | GET BY USER |
| DELETE | `http://localhost:8080/review_likes`           | DELETE |

#### Exemplos cURL

##### **POST http://localhost:8080/review_likes**

```bash
curl -X POST "http://localhost:8080/review_likes" \
  -H "Content-Type: application/json" \
  -H "Access-Control-Allow-Origin: *" \
  -d '{
	"reviews": "254ec946-28e1-46c8-b046-5fc2362abb4f",
	"users": "623d4830-bb62-45c0-b4a8-1005716b9d76"
}'
```

##### **GET http://localhost:8080/review_likes**

```bash
curl -X GET "http://localhost:8080/review_likes"
```

##### **GET http://localhost:8080/review_likes/<user_id>**

```bash
curl -X GET "http://localhost:8080/review_likes/623d4830-bb62-45c0-b4a8-1005716b9d76"
```

##### **DELETE http://localhost:8080/review_likes**

```bash
curl -X DELETE "http://localhost:8080/review_likes" \
  -H "Content-Type: application/json" \
  -d '{
	"reviews": "254ec946-28e1-46c8-b046-5fc2362abb4f",
	"users": "623d4830-bb62-45c0-b4a8-1005716b9d76"
}'
```

---

### Playlists

| Método | Rota | Descrição |
| ------ | ----- | ---------- |
| POST | `http://localhost:8080/playlists` | CREATE |
| GET | `http://localhost:8080/playlists` | GET ALL |
| GET | `http://localhost:8080/playlists/<playlist_id>` | GET BY ID |
| PUT | `http://localhost:8080/playlists/<playlist_id>` | UPDATE |
| DELETE | `http://localhost:8080/playlists/<playlist_id>` | DELETE |

#### Exemplos cURL

##### **POST http://localhost:8080/playlists**

```bash
curl -X POST "http://localhost:8080/playlists" \
  -H "Content-Type: application/json" \
  -H "Access-Control-Allow-Origin: *" \
  -d '{
	"title": "Rock'n Roll",
	"description": "Aqui ├® do rock!",
	"users": "623d4830-bb62-45c0-b4a8-1005716b9d76"
}'
```

##### **GET http://localhost:8080/playlists**

```bash
curl -X GET "http://localhost:8080/playlists"
```

##### **GET http://localhost:8080/playlists/<playlist_id>**

```bash
curl -X GET "http://localhost:8080/playlists/7670d36c-06bd-4563-8f75-9d613b1549b2"
```

##### **PUT http://localhost:8080/playlists/<playlist_id>**

```bash
curl -X PUT "http://localhost:8080/playlists/7670d36c-06bd-4563-8f75-9d613b1549b2" \
  -H "Content-Type: application/json" \
  -d '{
	"title": "Rock'n Roll",
	"description": "Aqui é do rock!",
	"users": "623d4830-bb62-45c0-b4a8-1005716b9d76"
}'
```

##### **DELETE http://localhost:8080/playlists/<playlist_id>**

```bash
curl -X DELETE "http://localhost:8080/playlists/7670d36c-06bd-4563-8f75-9d613b1549b2"
```

---

### PlaylistTracks

| Método | Rota | Descrição |
| ------ | ----- | ---------- |
| POST | `http://localhost:8080/playlist_tracks` | CREATE |
| GET | `http://localhost:8080/playlist_tracks` | GET ALL |
| GET | `http://localhost:8080/playlist_tracks/<playlist_id>` | GET BY PLAYLIST |
| DELETE | `http://localhost:8080/playlist_tracks` | DELETE |

#### Exemplos cURL

##### **POST http://localhost:8080/playlist_tracks**

```bash
curl -X POST "http://localhost:8080/playlist_tracks" \
  -H "Content-Type: application/json" \
  -H "Access-Control-Allow-Origin: *" \
  -d '{
	"playlists": "9b6fd680-cb98-4cc8-a010-f7728f86ab43",
	"tracks": "b73941e8-776f-428c-a9b9-310ff2f65256"
}'
```

##### **GET http://localhost:8080/playlist_tracks**

```bash
curl -X GET "http://localhost:8080/playlist_tracks"
```

##### **GET http://localhost:8080/playlist_tracks/<playlist_id>**

```bash
curl -X GET "http://localhost:8080/playlist_tracks/9b6fd680-cb98-4cc8-a010-f7728f86ab43"
```

##### **DELETE http://localhost:8080/playlist_tracks**

```bash
curl -X DELETE "http://localhost:8080/playlist_tracks" \
  -H "Content-Type: application/json" \
  -d '{
	"playlists": "9b6fd680-cb98-4cc8-a010-f7728f86ab43",
	"tracks": "b73941e8-776f-428c-a9b9-310ff2f65256"
}'
```

---


