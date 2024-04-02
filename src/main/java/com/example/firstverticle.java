package com.example;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class firstverticle extends AbstractVerticle {
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

               System.out.println("Received notification body:");
               System.out.println(notificationBody);
   
               WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
               WorkflowClient client = WorkflowClient.newInstance(service);
              
               
               WorkflowOptions options = WorkflowOptions.newBuilder()
                  .setTaskQueue(shared.SEND_NOTIFICATION_TASK_QUEUE).build();

              // Create the workflow client stub. It is used to start our workflow execution.
               sendNotificationWorkflow workflow = client.newWorkflowStub(sendNotificationWorkflow.class, options);
               workflow.executeWorkflow(notificationBody);
        
               ctx.response().setStatusCode(200).end("Notification body received successfully");
           });

     server.requestHandler(router).listen(8000);
    }
    @Override
    public void stop() throws Exception {
    }
}