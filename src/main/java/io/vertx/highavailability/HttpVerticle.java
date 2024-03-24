package io.vertx.highavailability;

import io.vertx.core.*;

public class HttpVerticle extends AbstractVerticle {

  public static void main(String[] args) {
    Vertx.clusteredVertx(new VertxOptions().setHAEnabled(true), ar -> {
      ar.result().deployVerticle(HttpVerticle.class.getName(), new DeploymentOptions().setHa(true));
    });
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    vertx.createHttpServer().requestHandler(req -> {
      req.response()
        .putHeader("content-type", "text/plain")
        .end("Hello from Vert.x! - Served from : " + Thread.currentThread().getName() +
          " - PID: " + ProcessHandle.current().pid());
    }).listen(8080, http -> {
      if (http.succeeded()) {
        startPromise.complete();
        System.out.println("HTTP server started on port 8080");
      } else {
        startPromise.fail(http.cause());
      }
    });
  }
}
