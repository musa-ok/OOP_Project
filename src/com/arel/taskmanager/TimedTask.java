package com.arel.taskmanager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Kalıtım: Task sınıfının özelliklerini miras aldık
public class TimedTask extends Task {
    private LocalDateTime deadline; // Ekstra özellik: Tarih

    public TimedTask(String id, String title, Priority priority, LocalDateTime deadline) {
        super(id, title, priority); // Üst sınıfın yapıcısını çağır
        this.deadline = deadline;
    }

    public LocalDateTime getDeadline() { return deadline; }

    // Polimorfizm: Yazdırma şeklini değiştirdik
    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return super.toString() + " | Son Tarih: " + deadline.format(fmt);
    }
}