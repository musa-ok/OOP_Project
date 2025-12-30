# 📚 Task Manager - Teknik Dokümantasyon

Bu dokümantasyon, Task Manager projesinde kullanılan nesne yönelimli programlama (OOP) kavramlarını ve mimari tasarımı detaylı olarak açıklar.

## 📋 İçindekiler

1. [Proje Mimarisi](#proje-mimarisi)
2. [OOP Prensipleri](#oop-prensipleri)
3. [Sınıf Yapıları](#sınıf-yapıları)
4. [İlişkiler ve Bağımlılıklar](#ilişkiler-ve-bağımlılıklar)
5. [Tasarım Desenleri](#tasarım-desenleri)

---

## 🏗️ Proje Mimarisi

### Genel Bakış

Task Manager, üç katmanlı bir mimariye sahiptir:

1. **Model Katmanı**: Veri yapıları ve iş mantığı (`Task`, `User`, `Project`)
2. **View Katmanı**: Kullanıcı arayüzü (`ConsoleHelper`, `Main`)
3. **Controller Katmanı**: İş akışı yönetimi (`Main` içindeki metodlar)

### Paket Yapısı

```
com.arel.taskmanager
├── Model Sınıfları
│   ├── User.java
│   ├── Task.java
│   ├── TimedTask.java
│   ├── Project.java
│   └── Priority.java
├── Interface
│   └── Completable.java
├── Utility Sınıfları
│   ├── ConsoleHelper.java
│   └── Notification.java
└── Ana Sınıflar
    ├── Main.java
    └── TestRunner.java
```

---

## 🎯 OOP Prensipleri

### 1. Kapsülleme (Encapsulation)

**Tanım**: Verilerin ve metodların bir sınıf içinde gizlenmesi ve kontrollü erişim sağlanması.

#### Örnek: `Task` Sınıfı

```java
public class Task implements Completable {
    private String id;           // Private alan - dışarıdan erişilemez
    private String title;        // Private alan
    private Priority priority;   // Private alan
    private boolean isCompleted; // Private alan

    // Getter metodları - kontrollü erişim
    public String getTitle() { return title; }
    public Priority getPriority() { return priority; }
    public String getId() { return id; }
}
```

**Faydaları**:
- Veri bütünlüğü korunur
- Sınıfın iç yapısı değişse bile dış arayüz sabit kalır
- Hatalı veri girişi engellenir

#### Örnek: `User` Sınıfı

```java
public class User {
    private String password; // Şifre private - güvenlik

    // Şifre kontrolü için güvenli metod
    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }
}
```

**Güvenlik**: Şifre doğrudan erişilemez, sadece kontrol metodu ile doğrulanabilir.

---

### 2. Kalıtım (Inheritance)

**Tanım**: Bir sınıfın başka bir sınıfın özelliklerini ve davranışlarını miras alması.

#### Örnek: `TimedTask` extends `Task`

```java
public class TimedTask extends Task {
    private LocalDateTime deadline; // Ekstra özellik

    public TimedTask(String id, String title, Priority priority, LocalDateTime deadline) {
        super(id, title, priority); // Üst sınıfın yapıcısını çağır
        this.deadline = deadline;
    }
}
```

**Miras Alınan Özellikler**:
- `id`, `title`, `priority`, `isCompleted` alanları
- `getTitle()`, `getPriority()`, `getId()` metodları
- `complete()`, `isComplete()` metodları

**Ek Özellikler**:
- `deadline` alanı
- `getDeadline()` metodu

**Faydaları**:
- Kod tekrarı azalır
- Yeni özellikler kolayca eklenir
- Tip uyumluluğu sağlanır (`TimedTask` bir `Task`'tır)

---

### 3. Polimorfizm (Polymorphism)

**Tanım**: Aynı arayüzün farklı sınıflarda farklı şekillerde uygulanması.

#### Örnek: `toString()` Metodunun Override Edilmesi

**Task Sınıfı**:
```java
@Override
public String toString() {
    String status = isCompleted ? "[Bitti]" : "[Aktif]";
    return status + " " + title + " (" + priority + ")";
}
```

**TimedTask Sınıfı**:
```java
@Override
public String toString() {
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    return super.toString() + " | Son Tarih: " + deadline.format(fmt);
}
```

**Kullanım**:
```java
Task task1 = new Task("T1", "Normal Görev", Priority.ORTA);
Task task2 = new TimedTask("T2", "Süreli Görev", Priority.YUKSEK, deadline);

System.out.println(task1); // [Aktif] Normal Görev (ORTA)
System.out.println(task2); // [Aktif] Süreli Görev (YUKSEK) | Son Tarih: 2024-12-25 14:30
```

**Faydaları**:
- Aynı metod farklı davranışlar sergiler
- Kod daha esnek ve genişletilebilir olur
- Runtime'da doğru metod çağrılır

---

### 4. Arayüz (Interface)

**Tanım**: Sınıfların uyması gereken sözleşmeleri tanımlar.

#### Örnek: `Completable` Interface

```java
public interface Completable {
    void complete();      // Tamamla
    boolean isComplete(); // Bitti mi kontrol et
}
```

**Uygulama**:
```java
public class Task implements Completable {
    @Override
    public void complete() {
        this.isCompleted = true;
    }

    @Override
    public boolean isComplete() {
        return isCompleted;
    }
}
```

**Kullanım**:
```java
Completable task = new Task("T1", "Görev", Priority.ORTA);
task.complete(); // Interface referansı ile metod çağrısı
```

**Faydaları**:
- Çoklu kalıtım benzeri davranış sağlar
- Kod esnekliği artar
- Farklı sınıflar aynı arayüzü uygulayabilir

---

### 5. Enum (Enumeration)

**Tanım**: Sabit değerler kümesini tanımlar.

#### Örnek: `Priority` Enum

```java
public enum Priority {
    YUKSEK, ORTA, DUSUK
}
```

**Kullanım**:
```java
Task task = new Task("T1", "Görev", Priority.YUKSEK);
```

**Faydaları**:
- Tip güvenliği sağlar
- Geçersiz değerler engellenir
- Kod okunabilirliği artar

---

## 📦 Sınıf Yapıları

### User Sınıfı

**Amaç**: Kullanıcı bilgilerini ve kimlik doğrulamayı yönetir.

**Özellikler**:
- `username`: Kullanıcı adı
- `role`: Kullanıcı rolü
- `password`: Şifre (private, güvenlik için)

**Metodlar**:
- `checkPassword(String)`: Şifre doğrulama
- `getUsername()`: Kullanıcı adını döndürür
- `getRole()`: Rolü döndürür

---

### Task Sınıfı

**Amaç**: Temel görev yapısını tanımlar.

**Özellikler**:
- `id`: Görev kimliği
- `title`: Görev başlığı
- `priority`: Öncelik seviyesi
- `isCompleted`: Tamamlanma durumu

**Metodlar**:
- `complete()`: Görevi tamamlar
- `isComplete()`: Tamamlanma durumunu kontrol eder
- `toString()`: Görev bilgisini string olarak döndürür

**Interface**: `Completable` interface'ini uygular.

---

### TimedTask Sınıfı

**Amaç**: Deadline'lı görevleri yönetir.

**Kalıtım**: `Task` sınıfından türer.

**Ek Özellikler**:
- `deadline`: Bitiş tarihi ve saati

**Metodlar**:
- `getDeadline()`: Deadline'ı döndürür
- `toString()`: Üst sınıfın toString'ini genişletir

---

### Project Sınıfı

**Amaç**: Görev listesini ve proje bilgilerini yönetir.

**Özellikler**:
- `name`: Proje adı
- `manager`: Proje yöneticisi (User)
- `tasks`: Görev listesi (List<Task>)

**Metodlar**:
- `addTask(Task)`: Görev ekler
- `getTasks()`: Görev listesini döndürür
- `listAll()`: Tüm görevleri listeler
- `listUpcoming()`: 3 gün içinde bitmesi gereken görevleri listeler

**Polimorfizm Kullanımı**:
```java
for (Task t : tasks) {
    if (t instanceof TimedTask) { // Tip kontrolü
        TimedTask tt = (TimedTask) t;
        // TimedTask'a özel işlemler
    }
}
```

---

### Priority Enum

**Değerler**:
- `YUKSEK`: Yüksek öncelikli görevler
- `ORTA`: Orta öncelikli görevler
- `DUSUK`: Düşük öncelikli görevler

---

### Completable Interface

**Amaç**: Tamamlanabilir nesneler için sözleşme tanımlar.

**Metodlar**:
- `void complete()`: Nesneyi tamamlar
- `boolean isComplete()`: Tamamlanma durumunu döndürür

**Uygulayan Sınıflar**: `Task` (ve dolayısıyla `TimedTask`)

---

## 🔗 İlişkiler ve Bağımlılıklar

### Sınıf İlişkileri

```
User
  ↑
  │ (manager)
  │
Project ──→ List<Task>
              ↑
              │ (inheritance)
              │
          TimedTask

Task ──implements──→ Completable
```

### Bağımlılık Grafi

- `Main` → `User`, `Project`, `Task`, `TimedTask`, `ConsoleHelper`
- `Project` → `User`, `Task`, `TimedTask`
- `TimedTask` → `Task` (extends)
- `Task` → `Priority`, `Completable` (implements)
- `TestRunner` → Tüm model sınıfları

---

## 🎨 Tasarım Desenleri

### 1. Helper/Utility Pattern

**ConsoleHelper Sınıfı**: Konsol işlemleri için yardımcı metodlar sağlar.

**Özellikler**:
- Statik metodlar (instance gerekmez)
- Renk kodları (ANSI escape codes)
- Ekran temizleme
- Mesaj formatlama

### 2. Factory Pattern (Basit)

**Main Sınıfı**: Görev oluşturma işlemlerini yönetir.

```java
// Normal görev oluşturma
currentProject.addTask(new Task("N-" + System.currentTimeMillis()%1000, t1, Priority.ORTA));

// Süreli görev oluşturma
currentProject.addTask(new TimedTask("T-" + System.currentTimeMillis()%1000, t2, Priority.YUKSEK, deadline));
```

### 3. Strategy Pattern (Örtük)

**Priority Enum**: Farklı öncelik stratejilerini temsil eder.

---

## 🔒 Güvenlik Özellikleri

### Şifre Yönetimi

- Şifreler private alan olarak saklanır
- Doğrudan erişim engellenir
- `checkPassword()` metodu ile güvenli doğrulama

### Veri Kapsülleme

- Tüm hassas veriler private
- Getter metodları ile kontrollü erişim
- Setter metodları yok (immutability benzeri yaklaşım)

---

## 📊 Veri Akışı

### Görev Ekleme Akışı

```
Kullanıcı Girdisi
    ↓
Main.runDashboard()
    ↓
currentProject.addTask()
    ↓
Project.tasks.add()
    ↓
Task/TimedTask nesnesi oluşturulur
```

### Görev Listeleme Akışı

```
Kullanıcı Seçimi
    ↓
Main.runDashboard()
    ↓
currentProject.listAll()
    ↓
Her Task için toString() çağrılır
    ↓
Polimorfizm: TimedTask farklı format gösterir
```

---

## 🧪 Test Stratejisi

### TestRunner Sınıfı

**Test Edilen Kavramlar**:
1. **Kapsülleme**: Private alanlara getter ile erişim
2. **Kalıtım**: `instanceof` kontrolü
3. **Interface**: Interface referansı ile metod çağrısı
4. **Güvenlik**: Şifre doğrulama

**Test Metodolojisi**:
- Manuel test framework simülasyonu
- Exception tabanlı assertion
- Test istatistikleri takibi

---

## 🚀 Gelecek Geliştirmeler

### Önerilen İyileştirmeler

1. **Veri Kalıcılığı**: Dosya veya veritabanı desteği
2. **Gelişmiş Filtreleme**: Tarih, öncelik, durum bazlı filtreleme
3. **Bildirim Sistemi**: Deadline yaklaşan görevler için bildirim
4. **Çoklu Proje Desteği**: Kullanıcı başına birden fazla proje
5. **GUI**: Swing veya JavaFX ile grafik arayüz

---

## 📝 Notlar

- Bu proje eğitim amaçlıdır ve OOP kavramlarını göstermek için tasarlanmıştır
- Production ortamında kullanım için ek güvenlik önlemleri gerekir
- Şifreler plain text olarak saklanmaktadır (eğitim amaçlı)
- Gerçek uygulamalarda şifre hash'leme kullanılmalıdır

---

**Son Güncelleme**: 2024

