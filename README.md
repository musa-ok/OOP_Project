# 📋 Task Manager - OOP Final Projesi

Modern bir görev yönetim sistemi. Java ile nesne yönelimli programlama (OOP) prensiplerini uygulayan konsol tabanlı bir uygulama.

## 🚀 Özellikler

- ✅ **Kullanıcı Yönetimi**: Güvenli giriş ve kayıt sistemi
- 📝 **Görev Yönetimi**: Normal ve süreli (deadline'lı) görevler
- 🎯 **Öncelik Sistemi**: Yüksek, Orta, Düşük öncelik seviyeleri
- ⏰ **Yaklaşan Görevler**: 3 gün içinde bitmesi gereken görevleri görüntüleme
- 💾 **Raporlama**: Görevleri dosyaya kaydetme
- 🎨 **Renkli Konsol Arayüzü**: Modern ve kullanıcı dostu arayüz

## 📦 Proje Yapısı

```
src/com/arel/taskmanager/
├── Main.java              # Ana uygulama ve kullanıcı arayüzü
├── User.java              # Kullanıcı sınıfı (kapsülleme)
├── Task.java              # Temel görev sınıfı (interface implementasyonu)
├── TimedTask.java         # Süreli görev sınıfı (kalıtım)
├── Project.java           # Proje yönetim sınıfı
├── Priority.java          # Öncelik enum'u
├── Completable.java       # Tamamlanabilir arayüz
├── ConsoleHelper.java     # Konsol yardımcı sınıfı
├── Notification.java      # Bildirim sınıfı
└── TestRunner.java        # Test çalıştırıcı
```

## 🛠️ Kurulum ve Çalıştırma

### Gereksinimler
- Java JDK 8 veya üzeri
- Terminal/Konsol erişimi

### Çalıştırma

1. **Projeyi klonlayın veya indirin**

2. **Kaynak kodları derleyin:**
```bash
cd src
javac com/arel/taskmanager/*.java
```

3. **Uygulamayı çalıştırın:**
```bash
java com.arel.taskmanager.Main
```

4. **Testleri çalıştırın:**
```bash
java com.arel.taskmanager.TestRunner
```

## 👤 Varsayılan Kullanıcılar

Uygulama başlangıçta iki kullanıcı ile gelir:

| Kullanıcı Adı | Şifre | Rol |
|--------------|-------|-----|
| `admin` | `123` | Yönetici |
| `musa` | `12345` | Mühendis |

## 📖 Kullanım

### Giriş Yapma
1. Uygulamayı başlattığınızda giriş ekranı açılır
2. "1. GİRİŞ YAP" seçeneğini seçin
3. Kullanıcı adı ve şifrenizi girin

### Yeni Kullanıcı Kaydı
1. Giriş ekranında "2. KAYIT OL" seçeneğini seçin
2. Kullanıcı adı, rol ve şifre bilgilerinizi girin

### Görev Ekleme
- **Normal Görev**: Öncelik seviyesi ile görev ekleyin
- **Süreli Görev**: Deadline (bitiş tarihi) ile görev ekleyin

### Görev Yönetimi
- Görevleri listeleme
- Görevleri tamamlama
- Yaklaşan görevleri görüntüleme (3 gün içinde)
- Görevleri dosyaya kaydetme

## 🧪 Testler

Proje, OOP kavramlarını test eden bir test suite içerir:

- ✅ Görev oluşturma ve kapsülleme testleri
- ✅ Kalıtım ve TimedTask testleri
- ✅ Interface implementasyonu testleri
- ✅ Kullanıcı kimlik doğrulama testleri

Testleri çalıştırmak için:
```bash
java com.arel.taskmanager.TestRunner
```

## 🎓 OOP Kavramları

Bu proje aşağıdaki OOP prensiplerini gösterir:

- **Kapsülleme (Encapsulation)**: Private alanlar ve getter metodları
- **Kalıtım (Inheritance)**: `TimedTask` extends `Task`
- **Polimorfizm (Polymorphism)**: `toString()` metodunun override edilmesi
- **Arayüz (Interface)**: `Completable` interface implementasyonu
- **Enum**: `Priority` enum sınıfı

Detaylı açıklamalar için [DOCUMENTATION.md](DOCUMENTATION.md) dosyasına bakın.

## 📝 Lisans

Bu proje eğitim amaçlıdır.

## 👨‍💻 Geliştirici

OOP Final Projesi - Task Manager

---

**Not**: Bu proje, nesne yönelimli programlama prensiplerini öğrenmek ve uygulamak amacıyla geliştirilmiştir.

