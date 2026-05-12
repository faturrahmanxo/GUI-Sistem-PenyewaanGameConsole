GUI-Sistem-Penyewaan-Game-Console
Sistem Penyewaan Game Console adalah aplikasi berbasis Java GUI (Swing) yang dirancang untuk membantu pengelolaan data penyewaan console game secara sederhana dan efisien. Aplikasi ini dibuat sebagai proyek akhir mata kuliah Pemrograman Berorientasi Objek (PBO) dengan tujuan agar mahasiswa memahami penerapan konsep OOP, GUI, serta koneksi database dalam satu proyek nyata.
Aplikasi ini menyediakan fitur utama untuk mengelola data pelanggan, memilih jenis console game yang ingin disewa, serta menentukan durasi penyewaan dengan tampilan yang modern dan mudah digunakan.
🎯 Tujuan Sistem
1. Menerapkan konsep OOP dalam pengembangan aplikasi Java.
2. Menggunakan Java Swing untuk membuat tampilan antarmuka (GUI).
3. Mengimplementasikan proses CRUD (Create, Read, Update, Delete) dengan database MySQL.
4. Mensimulasikan sistem penyewaan game console yang sederhana dan mudah digunakan.
5. Mengembangkan aplikasi dengan tampilan modern agar nyaman digunakan.

⚙️ Fitur Utama
1. Input Data Penyewaan
-Nama pelanggan
-Jenis console game
-Lama penyewaan
-Harga sewa
-Tanggal penyewaan
-Data yang dimasukkan akan disimpan dalam database MySQL.

2. Pemilihan Console Game
-Data console dimuat langsung dari database.
-Pelanggan dapat memilih console yang tersedia.
-Console dimodelkan menggunakan class tersendiri (class Console).

3. Pengelolaan Data Penyewaan (CRUD)
-Aplikasi menyediakan tombol:
-Tambah Data → Menyimpan data penyewaan baru ke database
-Edit Data → Mengubah data penyewaan yang dipilih
-Hapus Data → Menghapus data penyewaan dari database
-Clear Form → Mengosongkan form input
Semua data ditampilkan dalam tabel agar memudahkan proses pengelolaan.

4. Tabel Data Penyewaan
-Menampilkan daftar seluruh data penyewaan game console.
-Tabel otomatis refresh setelah proses CRUD.
-Menggunakan tampilan modern seperti warna header dan zebra stripes.

📚 Teknologi yang Digunakan
1. Java Swing → GUI aplikasi
2. Java OOP → Class Console, Penyewaan, dan struktur program
3. MySQL → Penyimpanan data
4. JDBC → Koneksi Java ↔ Database
5. Visual Studio Code → Editor pembuatan aplikasi

🎮 Alur Penggunaan Sistem
-User menjalankan aplikasi → GUI terbuka.
-Aplikasi otomatis memuat daftar console dan data penyewaan dari database.
-User mengisi data pelanggan pada form.
-User memilih jenis console dan durasi penyewaan.
-User menekan:
  -Tambah → jika ingin menyimpan data baru
  -Edit → untuk memperbarui data penyewaan
  -Hapus → untuk menghapus data
  -Clear → untuk mengosongkan form input
-Tabel di sisi kanan akan menampilkan seluruh data penyewaan yang telah tersimpan.

🔍 Konsep PBO yang Digunakan
1. Encapsulation → Penggunaan getter & setter pada class Console dan Penyewaan
2. Class & Object → Console dan Penyewaan dibuat dalam bentuk objek
3. Constructor → Untuk inisialisasi data console
4. Modularization → Kode dipisahkan berdasarkan package (model, database, view)


Instance Object → Digunakan untuk memanipulasi data penyewaan console


