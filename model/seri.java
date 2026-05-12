package model;

public class seri {
    private String namaSeri;
    private int hargaSewa;
    private String lamaWaktu;

    // Constructor
    public seri(String namaSeri, int hargaSewa, String lamaWaktu) {
        this.namaSeri = namaSeri;
        this.hargaSewa = hargaSewa;
        this.lamaWaktu = lamaWaktu;
    }

    // getter - Enkapsulasi
    public String getNamaSeri() {
        return namaSeri;
    }

    public int getHargaSewa() {
        return hargaSewa;
    }

    public String getLamaWaktu() {
        return lamaWaktu;
    }

    // method hitung total & Exception
    public int hitungTotal() {
        try {
            int hari = Integer.parseInt(lamaWaktu.replaceAll("[^0-9]", ""));
            return hargaSewa * hari;
        } catch (Exception e) {
            return hargaSewa;
        }
    }

    // method tampilkan info
    public void tampilkanInfo() {
        System.out.println("Seri: " + namaSeri);
        System.out.println("Harga Sewa: " + hargaSewa);
        System.out.println("Lama Waktu: " + lamaWaktu);
        System.out.println("Total: " + hitungTotal());
    }
}
  

