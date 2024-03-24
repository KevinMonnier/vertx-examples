package io.vertx.clustered;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class Receiver {

  public static void main(String[] args) {
    Vertx.clusteredVertx(new VertxOptions(), ar -> {
      if (ar.failed()) {
        System.err.println("Cannot create a clustered vert.x : " + ar.cause());
      } else {
        Vertx vertx = ar.result();
        vertx.eventBus().consumer("data", message -> System.out.println(message.body()));
      }
    });
  }

}
