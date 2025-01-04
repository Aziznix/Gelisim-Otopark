package fonks;

import java.sql.*;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import kullanici.session;

public class rezerv {

    private static final String URL = "jdbc:mysql://localhost:3306/javaotopark";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public boolean rezerv(String parkYeri, String ParkId) {
        String queryCheck = "SELECT kullanici_id FROM yer WHERE kullanici_id = ? AND durum = 1"; // Kullanıcı zaten rezervasyon yaptı mı kontrolü
        String queryGetParkId = "SELECT otopark_id FROM otopark WHERE otopark_adi = ?";
        String queryUpdate = "UPDATE yer SET durum = 1, kullanici_id = ?, tarih = ? WHERE yer_id = ? AND otopark_id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            session session = new session();
            int kullaniciid = session.getkullanicid(); 
            LocalDate günDate = LocalDate.now();
            java.sql.Date sqlDate = java.sql.Date.valueOf(günDate);

            // Kullanıcının zaten rezervasyon yapıp yapmadığını kontrol et
            try (PreparedStatement psCheck = connection.prepareStatement(queryCheck)) {
                psCheck.setInt(1, kullaniciid);
                try (ResultSet rsCheck = psCheck.executeQuery()) {
                    if (rsCheck.next()) {
                        // Eğer kullanıcı zaten rezervasyon yapmışsa, mesaj göster
                        JOptionPane.showMessageDialog(null, "Sadece bir kere işlem yapabilirsiniz.");
                        return false;
                    }
                }
            }

            // Park yeri bilgilerini al
            try (PreparedStatement psGetParkId = connection.prepareStatement(queryGetParkId)) {
                psGetParkId.setString(1, parkYeri);
                try (ResultSet rs = psGetParkId.executeQuery()) {
                    if (rs.next()) {
                        int otoparkid = rs.getInt("otopark_id");

                        // Yer bilgisini güncelle
                        try (PreparedStatement psUpdate = connection.prepareStatement(queryUpdate)) {
                            psUpdate.setInt(1, kullaniciid);
                            psUpdate.setDate(2, sqlDate); 
                            psUpdate.setString(3, ParkId);
                            psUpdate.setInt(4, otoparkid);

                            // Eğer sorgu başarılıysa true döndür
                            int rowsAffected = psUpdate.executeUpdate();
                            return rowsAffected > 0;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;  // Hata durumu
    }


}
