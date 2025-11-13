package controller;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import model.Follow;
import service.FollowService;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FollowController implements HttpHandler {
    private final FollowService service = new FollowService();
    private final Gson gson = new Gson();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");

        if (method.equalsIgnoreCase("POST")) {
            follow(exchange);
        } else if (method.equalsIgnoreCase("DELETE")) {
            unfollow(exchange);
        } else if (method.equalsIgnoreCase("GET")) {
            if (path.contains("/followers/")) {
                listFollowers(exchange, path);
            } else if (path.contains("/following/")) {
                listFollowing(exchange, path);
            } else {
                sendResponse(exchange, 404, "{\"error\": \"Invalid route\"}");
            }
        } else if (method.equalsIgnoreCase("OPTIONS")) {
            exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, DELETE, OPTIONS");
            exchange.sendResponseHeaders(204, -1);
        } else {
            sendResponse(exchange, 405, "{\"error\": \"Method not allowed\"}");
        }
    }

    private void follow(HttpExchange exchange) throws IOException {
        Follow follow = gson.fromJson(new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8), Follow.class);
        boolean created = service.create(follow);
        sendResponse(exchange, created ? 201 : 400, gson.toJson(follow));
    }

    private void unfollow(HttpExchange exchange) throws IOException {
        Follow follow = gson.fromJson(new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8), Follow.class);
        boolean deleted = service.delete(follow);
        sendResponse(exchange, deleted ? 204 : 404, "{\"message\": \"Follow relationship not found\"}");
    }

    private void listFollowers(HttpExchange exchange, String path) throws IOException {
        String userId = extractId(path, "/followers/");
        List<String> followers = service.getFollowers(userId);
        sendResponse(exchange, 200, gson.toJson(followers));
    }

    private void listFollowing(HttpExchange exchange, String path) throws IOException {
        String userId = extractId(path, "/following/");
        List<String> following = service.getFollowing(userId);
        sendResponse(exchange, 200, gson.toJson(following));
    }

    private String extractId(String path, String base) {
        return path.substring(path.indexOf(base) + base.length());
    }

    private void sendResponse(HttpExchange exchange, int status, String response) throws IOException {
        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(status, bytes.length);
        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();
    }
}
