package com.arel.taskmanager;

// Bildirim: Ekrana bilgi basan yardımcı sınıf
public class Notification {
    public static void send(String msg) {
        System.out.println(">> BİLDİRİM: " + msg);
    }
}