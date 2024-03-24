package io.vertx.eventbus;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

public class EventBusPublishSubscribe {

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    EventBus bus = vertx.eventBus();

    bus.consumer("channelId", message -> {
      System.out.println("Message received by A : " + message.body() +
        " / " + Thread.currentThread().getName());
    });

    bus.consumer("channelId", message -> {
      System.out.println("Message received by B : " + message.body() +
        " / " + Thread.currentThread().getName());
    });

    vertx.setPeriodic(1000, l -> {
      bus.publish("channelId", "Hello");
    });
  }

}
