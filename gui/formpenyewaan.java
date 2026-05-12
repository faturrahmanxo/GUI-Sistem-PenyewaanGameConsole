package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import database.databasemanager;

public class formpenyewaan extends JFrame {
    private JTextField tfNama, tfNoHp, tfLamaWaktu;
    private JComboBox<String> cbJenis;
    private JTable table;
    private DefaultTableModel model;

    public formpenyewaan() {
        setTitle("🎮 Zexxa Game Console");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 10));
        getContentPane().setBackground(new Color(245, 247, 250));

        // ====== PANEL INPUT ======
        JPanel panelInput = new JPanel(new GridBagLayout());
        panelInput.setBorder(BorderFactory.createTitledBorder("📝 Data Penyewaan"));
        panelInput.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblNama = new JLabel("Nama:");
        JLabel lblNoHp = new JLabel("No HP:");
        JLabel lblJenis = new JLabel("Jenis Konsol:");
        JLabel lblLama = new JLabel("Lama Waktu (dalam jam): ");

        tfNama = new JTextField(15);
        tfNoHp = new JTextField(15);
        tfLamaWaktu = new JTextField(15);
        cbJenis = new JComboBox<>(new String[]{"PS", "Xbox", "Nintendo"});

        gbc.gridx = 0; gbc.gridy = 0; panelInput.add(lblNama, gbc);
        gbc.gridx = 1; gbc.gridy = 0; panelInput.add(tfNama, gbc);
        gbc.gridx = 0; gbc.gridy = 1; panelInput.add(lblNoHp, gbc);
        gbc.gridx = 1; gbc.gridy = 1; panelInput.add(tfNoHp, gbc);
        gbc.gridx = 0; gbc.gridy = 2; panelInput.add(lblJenis, gbc);
        gbc.gridx = 1; gbc.gridy = 2; panelInput.add(cbJenis, gbc);
        gbc.gridx = 0; gbc.gridy = 3; panelInput.add(lblLama, gbc);
        gbc.gridx = 1; gbc.gridy = 3; panelInput.add(tfLamaWaktu, gbc);

        // ====== TOMBOL ======
        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelTombol.setBackground(Color.WHITE);

        JButton btnTambah = new JButton("Tambah");
        JButton btnUbah = new JButton("Ubah");
        JButton btnHapus = new JButton("Hapus");

        // Gaya tombol
        styleButton(btnTambah, new Color(51, 153, 255));
        styleButton(btnUbah, new Color(255, 204, 0));
        styleButton(btnHapus, new Color(255, 77, 77));

        panelTombol.add(btnTambah);
        panelTombol.add(btnUbah);
        panelTombol.add(btnHapus);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        panelInput.add(panelTombol, gbc);

        add(panelInput, BorderLayout.NORTH);

        // ====== TABEL ======
        model = new DefaultTableModel(new String[]{"ID", "Nama", "No HP", "Jenis", "Lama Waktu", "Harga"}, 0);
        table = new JTable(model);
        table.setRowHeight(25);
        table.setFont(new Font("Poppins", Font.PLAIN, 13));

        // ====== STYLE TABEL =====
        table.getTableHeader().setFont(new Font("Poppins", Font.BOLD, 13));
        table.getTableHeader().setBackground(new Color(0, 120, 215));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setGridColor(new Color(230, 230, 230));

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scroll, BorderLayout.CENTER);

        loadData();

        // ====== EVENT ======
        btnTambah.addActionListener(e -> tambahData());
        btnUbah.addActionListener(e -> ubahData());
        btnHapus.addActionListener(e -> hapusData());

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = table.getSelectedRow();
                if (i != -1) {
                    tfNama.setText(model.getValueAt(i, 1).toString());
                    tfNoHp.setText(model.getValueAt(i, 2).toString());
                    cbJenis.setSelectedItem(model.getValueAt(i, 3).toString());
                    tfLamaWaktu.setText(model.getValueAt(i, 4).toString());
                }
            }
        });
    }

    // ====== STYLE BUTTON ======
    private void styleButton(JButton btn, Color color) {
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Poppins", Font.BOLD, 13));
        btn.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    // ====== LOAD DATA ======
    private void loadData() {
        try (Connection conn = databasemanager.getConnection()) {
            model.setRowCount(0);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM sewa");
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("no_hp"),
                    rs.getString("jenis"),
                    rs.getString("lama_waktu"),
                    rs.getInt("harga")
                });

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "❌ Gagal memuat data: " + e.getMessage());
        }
    }

    private void tambahData() {
    try (Connection conn = databasemanager.getConnection()) {
        String jenis = cbJenis.getSelectedItem().toString();
        String inputLama = tfLamaWaktu.getText().trim();

        // Ambil angka dari teks (contoh: "2 Jam" -> 2)
        int lamaWaktu = 1;
        try {
            lamaWaktu = Integer.parseInt(inputLama.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException ex) {
            lamaWaktu = 1;
        }

        // ===== Tentukan harga per jenis =====
        int hargaPerJam = 0;
        switch (jenis) {
            case "PS":
                hargaPerJam = 10000;
                break;
            case "Xbox":
                hargaPerJam = 20000;
                break;
            case "Nintendo":
                hargaPerJam = 12000;
                break;
        }

        // ===== Hitung total harga =====
        int totalHarga = hargaPerJam * lamaWaktu;

        // ===== Simpan ke database =====
        String sql = "INSERT INTO sewa (nama, no_hp, jenis, lama_waktu, harga) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, tfNama.getText());
        stmt.setString(2, tfNoHp.getText());
        stmt.setString(3, jenis);
        stmt.setString(4, tfLamaWaktu.getText()); // tetap simpan teks aslinya
        stmt.setInt(5, totalHarga);
        stmt.executeUpdate();

        JOptionPane.showMessageDialog(this, "✅ Data berhasil ditambahkan!\nTotal Harga: Rp" + totalHarga);
        clearForm();
        loadData();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "❌ Gagal menambah data: " + e.getMessage());
    }
}

    private void ubahData() {
    int row = table.getSelectedRow();
    if (row == -1) {
        JOptionPane.showMessageDialog(this, "⚠️ Pilih data yang ingin diubah!");
        return;
    }

    int id = (int) model.getValueAt(row, 0);

    try (Connection conn = databasemanager.getConnection()) {

        String jenis = cbJenis.getSelectedItem().toString();
        String inputLama = tfLamaWaktu.getText().trim();

        // Ambil angka dari teks lama waktu
        int lamaWaktu = 1;
        try {
            lamaWaktu = Integer.parseInt(inputLama.replaceAll("[^0-9]", ""));
        } catch (Exception ex) {
            lamaWaktu = 1;
        }

        // Tentukan harga per jam (SAMA seperti di tambah)
        int hargaPerJam = 0;
        switch (jenis) {
            case "PS": hargaPerJam = 10000; break;
            case "Xbox": hargaPerJam = 20000; break;
            case "Nitendo": hargaPerJam = 12000; break;
        }

        int totalHarga = hargaPerJam * lamaWaktu;

        // UPDATE lengkap termasuk harga
        String sql = "UPDATE sewa SET nama=?, no_hp=?, jenis=?, lama_waktu=?, harga=? WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, tfNama.getText());
        stmt.setString(2, tfNoHp.getText());
        stmt.setString(3, jenis);
        stmt.setString(4, tfLamaWaktu.getText());
        stmt.setInt(5, totalHarga);
        stmt.setInt(6, id);
        stmt.executeUpdate();

        JOptionPane.showMessageDialog(this, "✅ Data berhasil diubah!\nTotal Harga: Rp" + totalHarga);
        clearForm();
        loadData();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "❌ Gagal mengubah data: " + e.getMessage());
    }
}


    // ====== HAPUS DATA ======
    private void hapusData() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "⚠️ Pilih data yang ingin dihapus!");
            return;
        }
        int id = (int) model.getValueAt(row, 0);

        int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection conn = databasemanager.getConnection()) {
                String sql = "DELETE FROM sewa WHERE id=?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, id);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "🗑️ Data berhasil dihapus!");
                clearForm();
                loadData();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "❌ Gagal menghapus data: " + e.getMessage());
            }
        }
    }

    // ====== CLEAR FORM ======
    private void clearForm() {
        tfNama.setText("");
        tfNoHp.setText("");
        tfLamaWaktu.setText("");
        cbJenis.setSelectedIndex(0);
        table.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new formpenyewaan().setVisible(true));
    }
}
