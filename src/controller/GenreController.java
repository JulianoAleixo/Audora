package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import model.Genre;
import service.GenreService;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import com.google.gson.Gson;

public class GenreController implements HttpHandler {
    private final GenreService service = new GenreService();
    private final Gson gson = new Gson();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path =  exchange.getRequestURI().getPath();

        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");

        if (method.equalsIgnoreCase("GET")) {
            if (path.equals("/genres")) {
                listGenre(exchange);
            } else if (path.startsWith("/genres/")) {
                searchGenre(exchange, path);
            }
        } else if (method.equalsIgnoreCase("POST")) {
            createGenre(exchange);
        } else if (method.equalsIgnoreCase("PUT")) {
            updateGenre(exchange, path);
        } else if (method.equalsIgnoreCase("DELETE")) {
            deleteGenre(exchange, path);
        } else if (method.equalsIgnoreCase("OPTIONS")) {
            exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, DELETE, OPTIONS");
            exchange.sendResponseHeaders(204, -1);
        } else {
            sendResponse(exchange, 405, "{\"error\": \"Method not allowed\"}");
        }
    }

    private void listGenre(HttpExchange exchange) throws IOException {
        String response = gson.toJson(service.getList());
        sendResponse(exchange, 200, response);
    }

    private void createGenre(HttpExchange exchange) throws IOException {
        InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
        Genre genre = gson.fromJson(isr, Genre.class);
        Genre created = service.create(genre);
        sendResponse(exchange, 201, gson.toJson(created));
    }

    private void searchGenre(HttpExchange exchange, String path) throws IOException {
        String id = extractIdFromUrl(path);
        Optional<Genre> genre = service.get(id);
        if (genre.isPresent()) {
            sendResponse(exchange, 200, gson.toJson(genre.get()));
        } else {
            sendResponse(exchange, 404, "{\"error\": \"Genre not found\"}");
        }
    }

    private void deleteGenre(HttpExchange exchange, String path) throws IOException {
        String id = extractIdFromUrl(path);
        boolean deleted = service.delete(id);
        if (deleted) {
            sendResponse(exchange, 204, "{\"message\": \"Genre successfully deleted\"}");
        } else {
            sendResponse(exchange, 404, "{\"error\": \"Genre not found\"}");
        }
    }

    private void updateGenre(HttpExchange exchange, String path) throws IOException {
        String id = extractIdFromUrl(path);
        InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
        Genre genre = gson.fromJson(isr, Genre.class);
        genre.setId(id);

        boolean updated = service.update(genre);
        if (updated) {
            sendResponse(exchange, 200, gson.toJson(genre));
        } else {
            sendResponse(exchange, 404, "{\"error\": \"Genre not found\"}");
        }
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
