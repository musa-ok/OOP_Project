package com.arel.taskmanager;

public class ConsoleHelper {
    // Renk Kodları (ANSI Escape Codes)
    public static final String RESET = "\033[0m";
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    public static final String BLUE = "\033[0;34m";
    public static final String PURPLE = "\033[0;35m";
    public static final String CYAN = "\033[0;36m";
    public static final String WHITE_BOLD = "\033[1;37m";

    // Konsolu Temizleme Metodu (Ekranı siler)
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Şık Başlık Yazdırma
    public static void printHeader(String title) {
        System.out.println(PURPLE + "==========================================" + RESET);
        System.out.println("   " + WHITE_BOLD + title.toUpperCase() + RESET);
        System.out.println(PURPLE + "==========================================" + RESET);
    }

    // Hata Mesajı
    public static void printError(String msg) {
        System.out.println(RED + "❌ HATA: " + msg + RESET);
    }

    // Başarı Mesajı
    public static void printSuccess(String msg) {
        System.out.println(GREEN + "✅ BAŞARILI: " + msg + RESET);
    }

    // Fake Yükleme Animasyonu (Hocalar bayılır buna)
    public static void showLoading(String msg) {
        System.out.print(YELLOW + msg + " " + RESET);
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(300); // 0.3 saniye bekle
                System.out.print(".");
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
        System.out.println();
    }
}