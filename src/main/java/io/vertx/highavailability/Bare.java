package io.vertx.highavailability;

import io.vertx.core.*;

public class Bare {

  public static void main(String[] args) {
    Vertx.clusteredVertx(new VertxOptions().setHAEnabled(true), ar -> {
      // we do nothing here, the purpose of this verticle is to keep the cluster alive
    });
  }
}
