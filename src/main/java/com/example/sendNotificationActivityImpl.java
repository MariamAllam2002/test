package com.example;

public class sendNotificationActivityImpl implements sendNotificationActivity{
@Override
public void sendNotification(String notificationBody){
    System.out.println("Sendinging notification :" + notificationBody);
}
}
