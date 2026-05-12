package model;

// Inheritance
public class nintendo extends seri {

    public nintendo(String namaSeri, int hargaSewa, String lamaWaktu) {
        super(namaSeri, hargaSewa, lamaWaktu);
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("=== Nintendo ===");
        super.tampilkanInfo();
    }
}
