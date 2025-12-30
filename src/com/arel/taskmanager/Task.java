package com.arel.taskmanager;

// Kapsülleme: Değişkenler private, erişim metodlarla
public class Task implements Completable {
    private String id;
    private String title;
    private Priority priority;
    private boolean isCompleted;

    public Task(String id, String title, Priority priority) {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.isCompleted = false;
    }

    // Interface metodunu uyguluyoruz
    @Override
    public void complete() {
        this.isCompleted = true;
    }

    @Override
    public boolean isComplete() {
        return isCompleted;
    }

    // Getter metodları
    public String getTitle() { return title; }
    public Priority getPriority() { return priority; }
    public String getId() { return id; }

    @Override
    public String toString() {
        String status = isCompleted ? "[Bitti]" : "[Aktif]";
        return status + " " + title + " (" + priority + ")";
    }
}