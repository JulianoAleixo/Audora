package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import model.PlaylistTrack;
import service.PlaylistTrackService;
import java.io.*;
import java.nio.charset.StandardCharsets;
import com.google.gson.Gson;

public class PlaylistTrackController implements HttpHandler {
    private final PlaylistTrackService service = new PlaylistTrackService();
    private final Gson gson = new Gson();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path =  exchange.getRequestURI().getPath();

        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");

        if (method.equalsIgnoreCase("GET")) {
            if (path.equals("/playlist_tracks")) {
                listPlaylistTrack(exchange);
            } else if (path.startsWith("/playlist_tracks/")) {
                searchPlaylistTrackByUser(exchange, path);
            }
        } else if (method.equalsIgnoreCase("POST")) {
            createPlaylistTrack(exchange);
        } else if (method.equalsIgnoreCase("DELETE")) {
            deletePlaylistTrack(exchange);
        } else if (method.equalsIgnoreCase("OPTIONS")) {
            exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, DELETE, OPTIONS");
            exchange.sendResponseHeaders(204, -1);
        } else {
            sendResponse(exchange, 405, "{\"error\": \"Method not allowed\"}");
        }
    }

    private void listPlaylistTrack(HttpExchange exchange) throws IOException {
        String response = gson.toJson(service.getList());
        sendResponse(exchange, 200, response);
    }

    private void createPlaylistTrack(HttpExchange exchange) throws IOException {
        InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
        PlaylistTrack playlist_track = gson.fromJson(isr, PlaylistTrack.class);
        PlaylistTrack created = service.create(playlist_track);
        sendResponse(exchange, 201, gson.toJson(created));
    }

    private void searchPlaylistTrackByUser(HttpExchange exchange, String path) throws IOException {
        String user = extractIdFromUrl(path);
        String response = gson.toJson(service.getPlaylistTracksByUser(user));
        sendResponse(exchange, 200, response);
    }

    private void deletePlaylistTrack(HttpExchange exchange) throws IOException {
        PlaylistTrack playlist_track = gson.fromJson(new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8), PlaylistTrack.class);
        boolean deleted = service.delete(playlist_track);
        sendResponse(exchange, deleted ? 204 : 404, "{\"message\": \"Follow relationship not found\"}");
    }


    private String extractIdFromUrl(String path) {
        String[] parts = path.split("/");
        return parts[parts.length - 1];
    }

    private void sendResponse(HttpExchange exchange, int status, String response) throws IOException {
        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(status, bytes.length);
        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();
    }
}
