package model;

// Inheritance
public class ps extends seri {

    public ps(String namaSeri, int hargaSewa, String lamaWaktu) {
        super(namaSeri, hargaSewa, lamaWaktu);
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("=== PlayStation ===");
        super.tampilkanInfo();
    }
}
