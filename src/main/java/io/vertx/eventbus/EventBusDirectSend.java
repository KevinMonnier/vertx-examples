package io.vertx.eventbus;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

public class EventBusDirectSend {

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    EventBus bus = vertx.eventBus();

    bus.consumer("address", message -> {
      System.out.println("Received : " + message.body() + " / " + Thread.currentThread().getName());
    });

    vertx.setPeriodic(1000, l -> {
      bus.send("address", "Hello");
    });
  }

}
