package com.example;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface sendNotificationWorkflow {

    @WorkflowMethod
    void executeWorkflow(String notificationBody);
}
