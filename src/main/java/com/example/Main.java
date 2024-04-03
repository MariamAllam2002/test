package com.example;

import io.vertx.core.Vertx;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        sendNotificationWorker.startWorker();
        Vertx vertx= Vertx.vertx();
      
        vertx.deployVerticle(new httpServerVerticle());
        vertx.deployVerticle(new temporalVerticle());
    }
}