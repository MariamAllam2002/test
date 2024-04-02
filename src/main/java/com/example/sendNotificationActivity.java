package com.example;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface sendNotificationActivity {
  @ActivityMethod
  void sendNotification(String notificationBody);
}
