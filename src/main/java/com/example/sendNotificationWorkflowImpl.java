package com.example;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;
public class sendNotificationWorkflowImpl implements sendNotificationWorkflow {
    ActivityOptions options = ActivityOptions.newBuilder()
         .setStartToCloseTimeout(Duration.ofSeconds(60)).build();
    private final sendNotificationActivity activity = Workflow.newActivityStub(sendNotificationActivity.class,options);
     @Override
    public void executeWorkflow(String notificationBody)
    {
      activity.sendNotification(notificationBody);
     }
}
