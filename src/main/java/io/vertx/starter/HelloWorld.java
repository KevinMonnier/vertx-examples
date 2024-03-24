package io.vertx.starter;

import io.vertx.core.Vertx;

public class HelloWorld {

  public static void main(String[] args) {
    System.out.println("Hello world !");
    Vertx vertx = Vertx.vertx();
    vertx.setPeriodic(1000, l -> {
      System.out.println("Hi ! - " + Thread.currentThread().getName());
      grace();
    });
  }

  private static void grace() {
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
