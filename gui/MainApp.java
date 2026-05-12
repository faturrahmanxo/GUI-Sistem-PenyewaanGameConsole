package gui;

import model.*;

public class MainApp {
    public static void main(String[] args) {
        // Polymorphism 
        seri ps = new ps("PS5", 10000, "1 Jam");
        seri xbox = new xbox("Xbox Series X", 20000, "3 Jam");
        seri nintendo = new nintendo("Nintendo switch oled", 12000, "1 Jam");

        // Manggil method override
        ps.tampilkanInfo();
        System.out.println();
        xbox.tampilkanInfo();
        System.out.println();
        nintendo.tampilkanInfo();
        System.out.println();

        // Jalankan GUI
        formpenyewaan.main(args);
    }
}