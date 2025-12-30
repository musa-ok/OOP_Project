package com.arel.taskmanager;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

// Proje: Görev listesini yöneten sınıf
public class Project {
    private String name;
    private User manager;
    private List<Task> tasks; // Task listesi

    public Project(String name, User manager) {
        this.name = name;
        this.manager = manager;
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task t) { tasks.add(t); }
    public List<Task> getTasks() { return tasks; }

    public void listAll() {
        System.out.println("\n--- " + name + " Görevleri ---");
        for (Task t : tasks) {
            System.out.println(t);
        }
    }

    // Yaklaşan görevleri bulur (3 gün kuralı)
    public void listUpcoming() {
        System.out.println("\n--- Yaklaşan Görevler ---");
        LocalDateTime now = LocalDateTime.now();
        for (Task t : tasks) {
            if (t instanceof TimedTask) { // Tip kontrolü
                TimedTask tt = (TimedTask) t;
                if (tt.getDeadline().isAfter(now) && tt.getDeadline().isBefore(now.plusDays(3))) {
                    System.out.println(tt);
                }
            }
        }
    }
}