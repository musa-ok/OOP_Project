package com.arel.taskmanager;

// Arayüz: Tamamlanabilir her şey bu kurallara uymalı
public interface Completable {
    void complete();      // Tamamla
    boolean isComplete(); // Bitti mi kontrol et
}