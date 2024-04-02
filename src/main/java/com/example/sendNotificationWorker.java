package com.example;


import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

public class sendNotificationWorker {
  public static void startWorker (){
    
    WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
    WorkflowClient client = WorkflowClient.newInstance(service);
    WorkerFactory factory =WorkerFactory.newInstance(client);

    Worker worker = factory.newWorker(shared.SEND_NOTIFICATION_TASK_QUEUE);

    worker.registerWorkflowImplementationTypes(sendNotificationWorkflowImpl.class);
    worker.registerActivitiesImplementations(new sendNotificationActivityImpl());
    
    factory.start();
  }
}
