package com.example;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class httpServerVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        System.out.println("hello");
        HttpServer server = vertx.createHttpServer();
      
        Router router = Router.router(vertx);

    //     router.route().handler(ctx -> {
    //     HttpServerResponse response = ctx.response();
    //     response.putHeader("content-type", "text/plain");
    //     response.end("Hello World from Vert.x-Web!");
    //    });

       router.route().handler(BodyHandler.create());

           router.post("/notification").handler(ctx -> {
               System.out.println("in");
            
               String notificationBody = ctx.getBodyAsString();
               EventBus eb = vertx.eventBus();
               eb.send("temporal-verticle-address", notificationBody );

        
               ctx.response().setStatusCode(200).end("Notification body received successfully");
           });

     server.requestHandler(router).listen(8000);
    }
    @Override
    public void stop() throws Exception {
    }
}