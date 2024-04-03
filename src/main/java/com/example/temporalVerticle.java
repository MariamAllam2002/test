package com.example;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;

public class temporalVerticle  extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    System.out.println("int the temporal verticle");
    WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
    WorkflowClient client = WorkflowClient.newInstance(service);
    WorkflowOptions options = WorkflowOptions.newBuilder()
       .setTaskQueue(shared.SEND_NOTIFICATION_TASK_QUEUE).build();
    // Create the workflow client stub. It is used to start our workflow execution.
    sendNotificationWorkflow workflow = client.newWorkflowStub(sendNotificationWorkflow.class, options);
     EventBus eb = vertx.eventBus();

      eb.consumer("temporal-verticle-address", message -> {
        String notificationBody = (String) message.body();
        workflow.executeWorkflow(notificationBody);
        System.out.println("after the execution");
    });
        
              
  }
}
