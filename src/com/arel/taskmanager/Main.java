package com.arel.taskmanager;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.io.FileWriter;

public class Main {
    // Basit bir veritabanı simülasyonu (Kullanıcı Adı -> User Nesnesi)
    private static Map<String, User> users = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    
    // Şu an giriş yapmış kullanıcıyı tutar
    private static User currentUser = null;
    // Kullanıcının projesi
    private static Project currentProject = null;

    public static void main(String[] args) {
        // --- BAŞLANGIÇ AYARLARI ---
        // Varsayılan Admin hesabı (Kullanıcı: admin, Şifre: 123)
        users.put("admin", new User("admin", "Yönetici", "123"));
        users.put("musa", new User("musa", "Mühendis", "12345"));

        // Açılış Logosu
        ConsoleHelper.clearScreen();
        printLogo();

        // --- GİRİŞ DÖNGÜSÜ ---
        boolean loginSuccess = false;
        while (!loginSuccess) {
            System.out.println(ConsoleHelper.CYAN + "\n=== GÜVENLİ GİRİŞ EKRANI ===");
            System.out.println("1. GİRİŞ YAP 🔓");
            System.out.println("2. KAYIT OL 📝");
            System.out.println("0. ÇIKIŞ ❌" + ConsoleHelper.RESET);
            System.out.print("Seçiminiz: ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                loginSuccess = login();
            } else if (choice.equals("2")) {
                register();
            } else if (choice.equals("0")) {
                System.out.println("Güle güle...");
                return;
            } else {
                ConsoleHelper.printError("Geçersiz seçim!");
            }
        }

        // --- ANA UYGULAMA DÖNGÜSÜ ---
        ConsoleHelper.showLoading("Sistem Hazırlanıyor");
        ConsoleHelper.clearScreen();
        
        // Her giriş yapan kullanıcı için yeni bir proje oturumu açıyoruz
        currentProject = new Project("OOP FİNAL PROJESİ", currentUser);

        runDashboard();
    }

    // ---------------- LOGIC METODLARI ----------------

    private static boolean login() {
        System.out.print("\nKullanıcı Adı: ");
        String username = scanner.nextLine();
        
        System.out.print("Şifre: ");
        String password = scanner.nextLine();

        // Kullanıcı var mı ve şifresi doğru mu?
        if (users.containsKey(username)) {
            User u = users.get(username);
            if (u.checkPassword(password)) {
                currentUser = u;
                ConsoleHelper.printSuccess("Giriş Başarılı! Hoş geldin, " + ConsoleHelper.WHITE_BOLD + currentUser.getUsername());
                return true;
            } else {
                ConsoleHelper.printError("Hatalı Şifre!");
                return false;
            }
        } else {
            ConsoleHelper.printError("Kullanıcı bulunamadı! Lütfen kayıt olun.");
            return false;
        }
    }

    private static void register() {
        System.out.print("\nYeni Kullanıcı Adı: ");
        String username = scanner.nextLine();
        
        if (users.containsKey(username)) {
            ConsoleHelper.printError("Bu kullanıcı zaten var!");
            return;
        }

        System.out.print("Rolünüz (Örn: Ogrenci, Yonetici): ");
        String role = scanner.nextLine();
        
        System.out.print("Şifre Belirleyin: ");
        String password = scanner.nextLine();

        // Yeni kullanıcıyı şifresiyle kaydediyoruz
        User newUser = new User(username, role, password);
        users.put(username, newUser);
        
        ConsoleHelper.showLoading("Kullanıcı oluşturuluyor");
        ConsoleHelper.printSuccess("Kayıt tamamlandı! Lütfen giriş yapın.");
    }

    private static void runDashboard() {
        while (true) {
            System.out.println("\n" + ConsoleHelper.BLUE + "┌──────────────────────────────────────────┐");
            System.out.println("│  " + ConsoleHelper.WHITE_BOLD + "KULLANICI: " + String.format("%-26s", currentUser.getUsername()) + ConsoleHelper.BLUE + "│");
            System.out.println("└──────────────────────────────────────────┘" + ConsoleHelper.RESET);
            
            System.out.println("1. ➕ Normal Görev Ekle");
            System.out.println("2. ⏰ Süreli Görev Ekle (Deadline)");
            System.out.println("3. 📋 Görevleri Listele");
            System.out.println("4. ✅ Görev Tamamla");
            System.out.println("5. ⚠️ Yaklaşanları Gör");
            System.out.println("6. 💾 Dosyaya Kaydet");
            System.out.println("0. 🚪 Çıkış");
            System.out.print(ConsoleHelper.YELLOW + ">> Seçim: " + ConsoleHelper.RESET);
            
            String choice = scanner.nextLine();
            
            ConsoleHelper.clearScreen();

            try {
                switch (choice) {
                    case "1":
                        System.out.print("Görev Başlığı: ");
                        String t1 = scanner.nextLine();
                        currentProject.addTask(new Task("N-" + System.currentTimeMillis()%1000, t1, Priority.ORTA));
                        ConsoleHelper.printSuccess("Görev eklendi.");
                        break;
                        
                    case "2":
                        System.out.print("Görev Başlığı: ");
                        String t2 = scanner.nextLine();
                        
                        // YENİ: Kullanıcıdan gün sayısı istiyoruz
                        System.out.print("Kaç gün sonra tamamlansın? (Sayı girin): ");
                        String gunInput = scanner.nextLine();
                        
                        try {
                            int gunSayisi = Integer.parseInt(gunInput);
                            LocalDateTime deadline = LocalDateTime.now().plusDays(gunSayisi);
                            
                            // Süreli Görev Ekleme
                            currentProject.addTask(new TimedTask("T-" + System.currentTimeMillis()%1000, t2, Priority.YUKSEK, deadline));
                            ConsoleHelper.printSuccess("Görev " + gunSayisi + " gün sonrasına (" + deadline.toLocalDate() + ") ayarlandı.");
                        } catch (NumberFormatException e) {
                            ConsoleHelper.printError("Hata: Lütfen geçerli bir sayı girin! Görev eklenemedi.");
                        }
                        break;
                        
                    case "3":
                        ConsoleHelper.printHeader("GÖREV LİSTESİ");
                        currentProject.listAll(); 
                        break;
                        
                    case "4":
                        System.out.print("Tamamlanacak Başlık: ");
                        String titleToComplete = scanner.nextLine();
                        boolean found = false;
                        for(Task t : currentProject.getTasks()) {
                            if(t.getTitle().equalsIgnoreCase(titleToComplete)) {
                                t.complete();
                                ConsoleHelper.printSuccess("Görev tamamlandı!");
                                found = true;
                            }
                        }
                        if(!found) ConsoleHelper.printError("Görev bulunamadı.");
                        break;
                        
                    case "5":
                        currentProject.listUpcoming();
                        break;
                        
                    case "6":
                         try (FileWriter writer = new FileWriter(currentUser.getUsername() + "_Rapor.txt")) {
                            for(Task t : currentProject.getTasks()) writer.write(t.toString() + "\n");
                            ConsoleHelper.printSuccess("Rapor kaydedildi: " + currentUser.getUsername() + "_Rapor.txt");
                        }
                        break;
                        
                    case "0":
                        System.out.println(ConsoleHelper.PURPLE + "Çıkış yapılıyor... Görüşmek üzere!" + ConsoleHelper.RESET);
                        return;
                        
                    default:
                        ConsoleHelper.printError("Geçersiz işlem!");
                }
            } catch (Exception e) {
                ConsoleHelper.printError("Sistem Hatası: " + e.getMessage());
            }
        }
    }

    private static void printLogo() {
        System.out.println(ConsoleHelper.PURPLE);
        System.out.println("  _____         _      __  __                                   ");
        System.out.println(" |_   _|__ __ _| | __ |  \\/  | __ _ _ __   __ _  __ _  ___ _ __ ");
        System.out.println("   | |/ _ ` __| |/ / | |\\/| |/ _` | '_ \\ / _` |/ _` |/ _ \\ '__|");
        System.out.println("   | | (_| (__|   <  | |  | | (_| | | | | (_| | (_| |  __/ |    ");
        System.out.println("   |_|\\__,_|___|_|\\_\\ |_|  |_|\\__,_|_| |_|\\__,_|\\__, |\\___|_|    ");
        System.out.println("                                                |___/           ");
        System.out.println(ConsoleHelper.RESET);
    }
}