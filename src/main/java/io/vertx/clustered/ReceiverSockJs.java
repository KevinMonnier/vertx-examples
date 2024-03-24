package io.vertx.clustered;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.SockJSBridgeOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;

public class ReceiverSockJs {

  public static void main(String[] args) {
    Vertx.clusteredVertx(new VertxOptions(), ar -> {
      if (ar.failed()) {
        System.err.println("Cannot create a clustered vert.x : " + ar.cause());
      } else {
        Vertx vertx = ar.result();
        configureSockJsBridge(vertx);
      }
    });
  }

  private static void configureSockJsBridge(Vertx vertx) {
    Router router = Router.router(vertx);

    SockJSBridgeOptions options = new SockJSBridgeOptions()
      .addInboundPermitted(new PermittedOptions().setAddress("data"))
      .addOutboundPermitted(new PermittedOptions().setAddress("data"));

    router.mountSubRouter("/eventbus", SockJSHandler.create(vertx).bridge(options));
    router.route("/assets/*").handler(StaticHandler.create("assets"));

    vertx.createHttpServer().requestHandler(router).listen(8080);
  }

}
