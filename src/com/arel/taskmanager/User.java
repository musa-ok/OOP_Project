package com.arel.taskmanager;

public class User {
    private String username;
    private String role;
    private String password; // YENİ: Şifre alanı eklendi

    // Yapıcı metod artık şifre de istiyor
    public User(String username, String role, String password) {
        this.username = username;
        this.role = role;
        this.password = password;
    }

    public String getUsername() { return username; }
    public String getRole() { return role; }
    
    // Şifre kontrolü için güvenli metod
    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }
}