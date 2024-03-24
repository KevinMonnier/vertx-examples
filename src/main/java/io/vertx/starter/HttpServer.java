package io.vertx.starter;

import io.vertx.core.Vertx;

public class HttpServer {

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.createHttpServer().requestHandler(request -> {
        request.response().end("Bonjour");
      })
      .listen(8080, ar -> {
        if (ar.failed()) {
          System.out.println("D'oh ! " + ar.cause());
        } else {
          System.out.println("Server started");
        }
      });
  }

}
