package model;

// Inheritance
public class xbox extends seri {

    public xbox(String namaSeri, int hargaSewa, String lamaWaktu) {
        super(namaSeri, hargaSewa, lamaWaktu);
    }

    // polimorfisme
    @Override
    public void tampilkanInfo() {
        System.out.println("=== Xbox ===");
        super.tampilkanInfo();
    }
}
