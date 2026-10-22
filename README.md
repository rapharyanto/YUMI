# 🍜 YUMI: Your Unique Meal Inside
<img src="https://cdn.simpleicons.org/youtube/FF0000" width="18" align="center" /> **YouTube Demo Video**: [https://youtu.be/MLOUfUYB9ew](https://youtu.be/MLOUfUYB9ew)  
<img src="https://cdn.simpleicons.org/github/FFFFFF" width="18" align="center" /> **GitHub Repository**: [https://github.com/rapharyanto/YUMI.git](https://github.com/rapharyanto/YUMI.git)

### A. BRIEF DESCRIPTION OF THE APP

**YUMI** merupakan aplikasi mobile berbasis Android yang dirancang untuk membantu pengguna menemukan dan menentukan pilihan makanan sesuai lokasi, anggaran, serta preferensi mereka. Aplikasi ini hadir untuk mengatasi permasalahan informasi kuliner yang tersebar di berbagai platform, banyaknya pilihan makanan yang membingungkan, serta sulitnya membandingkan harga, lokasi, popularitas, dan ulasan restoran. Dengan mengintegrasikan informasi kuliner ke dalam satu platform, YUMI membantu pengguna mengambil keputusan secara lebih praktis dan efisien.

YUMI menyediakan berbagai fitur utama, antara lain:
* **Pencarian & Detail Restoran**: Informasi lengkap mengenai lokasi, jam operasional, ulasan, hingga rating pengguna.
* **Rekomendasi Berbasis Tab**: Tab *For You* (rekomendasi personal) dan *Nearby* (eksplorasi kuliner terdekat).
* **Filter Interaktif & Pilihan Harga**: Menyaring tempat makan berdasarkan preferensi harga, kategori kuliner, dan lokasi.
* **Pencapaian Foodie & Tempat Tersimpan**: Menyimpan tempat favorit, mengelompokkan rekomendasi, dan mencatat riwayat kunjungan.
* **Integrasi Voice AI**: Kemudahan pencarian kuliner interaktif berbasis perintah suara.

Aplikasi ini dikembangkan menggunakan **Android Studio**, **Kotlin**, dan **Jetpack Compose** (dengan *forced light theme*), serta mengintegrasikan **Firebase Authentication** untuk manajemen akun dan **Jetpack Navigation** (`NavHost` & `NavController`) untuk navigasi terpusat. YUMI memerlukan koneksi internet untuk mengakses dan menggunakan layanan aplikasi secara maksimal.

---

### B. GROUP MEMBERS

| No | Nama Lengkap | NIM |
| :---: | :--- | :---: |
| 1 | **Rafi Athallah Ahmad Haryanto** | `00000130426` |
| 2 | **Keisha Aria Lai** | `000000128606` |
| 3 | **Shalomita Amanda Christy Lasamahu** | `000000125861` |
| 4 | **Zahra Ariestya Sennova** | `00000126847` |

---

### C. DIVISION OF TASKS AND WORKING SYSTEM

Pengembangan aplikasi YUMI dilakukan melalui kolaborasi antaranggota kelompok dengan pembagian tugas berdasarkan halaman dan fitur yang menjadi tanggung jawab masing-masing. Setiap anggota berperan dalam merancang, mengembangkan, dan menyempurnakan bagian aplikasi menggunakan Android Studio, Kotlin, dan Jetpack Compose. Koordinasi dilakukan melalui diskusi dan pertukaran informasi mengenai progres pengerjaan untuk memastikan setiap bagian dapat terintegrasi dengan baik, baik dari segi tampilan, fungsionalitas, maupun konsistensi desain aplikasi.

Dalam proses pengembangan, kelompok menggunakan **Git** dan **GitHub** sebagai sarana pengelolaan versi kode dan kolaborasi:
1. **Branching Strategy**: Setiap anggota melakukan perubahan pada *branch* masing-masing (seperti `explore/review-clean`).
2. **Version Control**: Melakukan pembaruan kode secara berkala melalui proses *commit* dan *push*.
3. **Code Integration**: Melakukan *pull* dan *merge* untuk menyelaraskan hasil pekerjaan serta menggabungkan kontribusi setiap anggota ke dalam *main branch* (`main`).

Melalui sistem kerja ini, setiap perubahan dapat dikelola secara terstruktur, kendala integrasi dapat ditangani bersama, dan pengembangan aplikasi dapat terus berjalan secara kolaboratif menuju hasil akhir yang sesuai dengan tujuan kelompok.

---

### D. SPECIFIC TASK/CONTRIBUTION OF EACH INDIVIDUAL MEMBER

#### 1. Rafi Athallah Ahmad Haryanto (`00000130426`)
* **Authentication Module**: Membangun UI dan logika modul autentikasi (`Opening.kt`, `Login.kt`, `CreateAccount.kt`, `ForgotPassword.kt`) menggunakan Jetpack Compose dengan *forced light theme* dan *state hoisting*.
* **Firebase Integration**: Mengintegrasikan Firebase Authentication (Email/Password, Google Sign-In, serta Reset Password).
* **Central Navigation**: Merancang navigasi terpusat (`NavHost` & `NavController`) di `MainActivity.kt` menggunakan `popUpTo` dan *argument passing* (`detail/{foodId}`).
* **Project & Repository Setup**: Mengonfigurasi identitas aplikasi (nama YUMI & logo), merancang struktur direktori repository GitHub, serta menyusun video demonstrasi aplikasi.

#### 2. Keisha Aria Lai (`000000128606`)
* **Home Screen Module**: Merancang dan mengimplementasikan `ForYouTab.kt` dan `NearbyTab.kt` beserta seluruh komponen utamanya.
* **UI Components**: Membangun header atas (logo YUMI, indikator lokasi, notifikasi, ikon profil), kategori cepat pilihan kuliner, banner interaktif Voice AI, dan daftar rekomendasi berbasis `LazyColumn`.
* **Nearby Features**: Menambahkan barisan *chip filter* interaktif serta dialog pilihan harga *sticky* di atas daftar restoran yang dapat digulir secara mandiri.
* **Navigation & Bottom Bar**: Menyusun `BottomNavigationBar.kt` dan mengonfigurasi rute navigasi antarhalaman di `MainActivity.kt`.

#### 3. Shalomita Amanda Christy Lasamahu (`000000125861`)
* **Profile Screen Module**: Merancang dan mengembangkan halaman Profil (`ProfileScreen`) secara menyeluruh.
* **Profile Features**:
  * Fitur **Notifikasi** dan **Riwayat Kunjungan** kuliner.
  * Fitur **Tempat Tersimpan** yang terklasifikasi (Kopi, Makan Malam) lengkap dengan jam operasional, rating, alamat, dan ulasan.
  * Fitur **Pencapaian Foodie** untuk memantau progres eksplorasi tempat kuliner.
  * Fitur **Share** dan **Pengaturan Preferensi** aplikasi.
* **QA & GitHub Collaboration**: Berkontribusi aktif dalam kolaborasi GitHub (*pull*, *merge*, *push*), pengujian aplikasi (QA), serta identifikasi dan pembenahan *bug*.

#### 4. Zahra Ariestya Sennova (`00000126847`)
* **Explore & Detail Module**: Mengembangkan UI halaman **Jelajah** (`ExploreScreen.kt`) dan **Detail Restoran** (`DetailRestoScreen.kt`) meliputi ulasan, rating, gambar, fitur pencarian, kategori filter, dan interaksi kartu ulasan.
* **Component & Visual Assets**: Mengembangkan komponen `BottomNavigationBar.kt` dengan 5 menu utama serta menyesuaikan tema warna dan aset visual menggunakan Kotlin & Jetpack Compose.
* **Git Management**: Bertanggung jawab dalam pengelolaan dan sinkronisasi kode di GitHub (*commit*, *push branch*, *pull main*, *merge*), menjaga konsistensi codebase, serta menangani integrasi antar-branch tim.

---


YUMI hadir untuk mengubah dilema *"bingung mau makan apa"* menjadi pengalaman memilih kuliner yang praktis dan menyenangkan[cite: 7]. Lebih dari sekadar platform pencari makanan, YUMI hadir sebagai teman dalam mengambil keputusan kuliner yang terpercaya, efisien, dan terpersonalisasi sesuai selera setiap pengguna
<br />

<p align="center">
  <b>"YOUR UNIQUE MEAL INSIDE"</b>
</p>
