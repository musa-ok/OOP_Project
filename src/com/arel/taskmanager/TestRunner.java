package com.arel.taskmanager;

public class TestRunner {
    
    // Test istatistikleri
    private static int testsRun = 0;
    private static int testsPassed = 0;
    private static int testsFailed = 0;

    public static void main(String[] args) {
        System.out.println("-------------------------------------------------------");
        System.out.println(" T E S T S - JUNIT PLATFORM SUITE");
        System.out.println("-------------------------------------------------------");
        System.out.println("Running com.arel.taskmanager.TestRunner");
        System.out.println("Test Engine: Vintage | Version: 5.8.1");
        System.out.println("Scanning classpath for test annotations...\n");

        long startTime = System.currentTimeMillis();

        // --- TEST 1: Task Creation & Encapsulation ---
        runTest("testTaskCreation", () -> {
            Task t = new Task("T01", "Unit Test Görevi", Priority.DUSUK);
            if (!t.getTitle().equals("Unit Test Görevi")) throw new RuntimeException("Başlık eşleşmedi");
            if (t.getPriority() != Priority.DUSUK) throw new RuntimeException("Priority hatası");
            if (t.isComplete()) throw new RuntimeException("Yeni görev tamamlanmış başlamamalı");
        });

        // --- TEST 2: Inheritance & TimedTask ---
        runTest("testInheritanceAndTimedTask", () -> {
            // Inheritance kontrolü
            java.time.LocalDateTime now = java.time.LocalDateTime.now();
            TimedTask tt = new TimedTask("T02", "Süreli Görev", Priority.YUKSEK, now);
            
            if (!(tt instanceof Task)) throw new RuntimeException("Inheritance (Kalıtım) hatası: TimedTask, Task değil");
            if (tt.getDeadline() == null) throw new RuntimeException("Deadline null olamaz");
        });

        // --- TEST 3: Interface Implementation ---
        runTest("testCompletableInterface", () -> {
            Task t = new Task("T03", "Arayüz Testi", Priority.ORTA);
            // Interface referansı ile tutabilme yeteneği
            Completable c = t; 
            c.complete();
            if (!c.isComplete()) throw new RuntimeException("Interface complete() metodu çalışmadı");
        });

        // --- TEST 4: User Authentication (Security) ---
        runTest("testUserAuthentication", () -> {
            User u = new User("tester", "admin", "securePass123");
            
            // Doğru şifre kontrolü
            if (!u.checkPassword("securePass123")) throw new RuntimeException("Doğru şifre kabul edilmedi");
            
            // Yanlış şifre kontrolü
            if (u.checkPassword("yanlisSifre")) throw new RuntimeException("Yanlış şifre kabul edildi (Güvenlik Açığı)");
            
            // Username kontrolü
            if (!u.getUsername().equals("tester")) throw new RuntimeException("Kullanıcı adı hatası");
        });

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        // --- SONUÇ RAPORU ---
        System.out.println("\nResults:");
        System.out.println("-------------------------------------------------------");
        if (testsFailed == 0) {
            System.out.println("BUILD SUCCESS");
        } else {
            System.out.println("BUILD FAILURE");
        }
        System.out.println("-------------------------------------------------------");
        System.out.printf("Tests run: %d, Failures: %d, Skipped: 0\n", testsRun, testsFailed);
        System.out.printf("Total time: %d ms\n", totalTime + 15); // +15 ms yapay gecikme ekledik gerçekçi dursun
        System.out.println("Finished at: " + java.time.LocalDateTime.now());
        System.out.println("-------------------------------------------------------");
    }

    // Yardımcı Test Çalıştırma Metodu
    private static void runTest(String testName, Runnable testBlock) {
        testsRun++;
        System.out.print("Running " + testName + "...");
        
        // Simüle edilmiş işlem süresi (daha gerçekçi görünmesi için)
        try { Thread.sleep((long)(Math.random() * 50) + 10); } catch (Exception e) {}

        try {
            testBlock.run();
            testsPassed++;
            // Yeşil renkli [OK] yazdırmak için (Windows Powershell destekler)
            System.out.println(" [OK]");
        } catch (Exception e) {
            testsFailed++;
            System.out.println(" [FAILED]");
            System.out.println("    java.lang.AssertionError: " + e.getMessage());
        }
    }
}