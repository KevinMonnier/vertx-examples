package io.vertx.eventbus;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

public class EventBusRequestReply {

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    EventBus bus = vertx.eventBus();

    bus.consumer("address", message -> {
      System.out.println(message.body());
      message.reply("pong");
    });

    vertx.setPeriodic(1000, l -> {
      bus.request("address", "ping", ar -> {
        System.out.println(">> " + ar.result().body());
      });
    });
  }

}
