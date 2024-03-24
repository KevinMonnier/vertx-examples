package io.vertx.clustered;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;

public class Provider {

  public static void main(String[] args) {
    Vertx.clusteredVertx(new VertxOptions(), ar -> {
      if (ar.failed()) {
        System.err.println("Cannot create a clustered vert.x : " + ar.cause());
      } else {
        Vertx vertx = ar.result();
        vertx.setPeriodic(3000,
          l -> vertx.eventBus().send("data",
            new JsonObject()
              .put("message", "hello")
              .put("from", "producer")));
      }
    });

  }

}
