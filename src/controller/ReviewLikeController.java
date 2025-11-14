package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import model.ReviewLike;
import service.ReviewLikeService;
import java.io.*;
import java.nio.charset.StandardCharsets;
import com.google.gson.Gson;

public class ReviewLikeController implements HttpHandler {
    private final ReviewLikeService service = new ReviewLikeService();
    private final Gson gson = new Gson();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path =  exchange.getRequestURI().getPath();

        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");

        if (method.equalsIgnoreCase("GET")) {
            if (path.equals("/review_likes")) {
                listReviewLike(exchange);
            } else if (path.startsWith("/review_likes/")) {
                searchReviewLikeByUser(exchange, path);
            }
        } else if (method.equalsIgnoreCase("POST")) {
            createReviewLike(exchange);
        } else if (method.equalsIgnoreCase("DELETE")) {
            deleteReviewLike(exchange);
        } else if (method.equalsIgnoreCase("OPTIONS")) {
            exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, DELETE, OPTIONS");
            exchange.sendResponseHeaders(204, -1);
        } else {
            sendResponse(exchange, 405, "{\"error\": \"Method not allowed\"}");
        }
    }

    private void listReviewLike(HttpExchange exchange) throws IOException {
        String response = gson.toJson(service.getList());
        sendResponse(exchange, 200, response);
    }

    private void createReviewLike(HttpExchange exchange) throws IOException {
        InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
        ReviewLike review_like = gson.fromJson(isr, ReviewLike.class);
        ReviewLike created = service.create(review_like);
        sendResponse(exchange, 201, gson.toJson(created));
    }

    private void searchReviewLikeByUser(HttpExchange exchange, String path) throws IOException {
        String user = extractIdFromUrl(path);
        String response = gson.toJson(service.getReviewLikesByUser(user));
        sendResponse(exchange, 200, response);
    }

    private void deleteReviewLike(HttpExchange exchange) throws IOException {
        ReviewLike review_like = gson.fromJson(new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8), ReviewLike.class);
        boolean deleted = service.delete(review_like);
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
