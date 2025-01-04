package fonks;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import busniess.profilpark;
import kullanici.bakiye;
import kullanici.session;

public class parkandquit extends profilpark {
	 session session = new session();
     int id = session.getkullanicid();

    private static final String URL = "jdbc:mysql://localhost:3306/javaotopark";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public boolean parket() {
        String query = "SELECT durum FROM yer WHERE kullanici_id = ?";
        String queryy = "UPDATE yer SET durum = 2 WHERE kullanici_id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                int durum = resultSet.getInt("durum");
                if (durum == 1) {
                    try (PreparedStatement pss = connection.prepareStatement(queryy)) {
                        pss.setInt(1, id);
                        int update = pss.executeUpdate();
                        return update > 0; 
                    }
                } else if (durum == 2) {
                    System.out.println("Yer zaten dolu.");
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false; 
    }
    
    public boolean parkçıkar() {
        String query = "SELECT tarih FROM yer WHERE kullanici_id = ? AND durum = 2";
        String updateQuery = "UPDATE yer SET durum = 0, kullanici_id = NULL, tarih = NULL WHERE kullanici_id = ?";
        String borcQuery = "SELECT borc FROM isim WHERE id = ?";
        String bakiyeUpdateQuery = "UPDATE isim SET borc = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Tarih sorgula
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Tarih farkını hesapla
                Date tarihDate = rs.getDate("tarih");
                LocalDate localDate = tarihDate.toLocalDate();
                LocalDate currentDate = LocalDate.now();
                long daysBetween = ChronoUnit.DAYS.between(localDate, currentDate);

                // Kullanıcının mevcut borcunu sorgula
                PreparedStatement borcPs = connection.prepareStatement(borcQuery);
                borcPs.setInt(1, id);
                ResultSet borcRs = borcPs.executeQuery();
                int mevcutBorc = 0;

                if (borcRs.next()) {
                    mevcutBorc = borcRs.getInt("borc");
                }

                // Yeni borcu hesapla
                int yeniBorc = mevcutBorc + ((int) daysBetween + 1) * 200;

                // Borcu güncelle
                PreparedStatement bakiyeUpdatePs = connection.prepareStatement(bakiyeUpdateQuery);
                bakiyeUpdatePs.setInt(1, yeniBorc);
                bakiyeUpdatePs.setInt(2, id);
                bakiyeUpdatePs.executeUpdate();

                // Yer tablosunu güncelle
                PreparedStatement updatePs = connection.prepareStatement(updateQuery);
                updatePs.setInt(1, id);
                int rowsUpdated = updatePs.executeUpdate();

                // Eğer tüm işlemler başarılıysa true döndür
                return rowsUpdated > 0;
            }
        } catch (Exception e) {
            e.printStackTrace(); // Hata durumunda hata yazdırılır
        }

        // Hata durumunda false döndür
        return false;
    }


}
