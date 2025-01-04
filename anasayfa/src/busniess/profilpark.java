package busniess;

import java.sql.*;

import kullanici.session;

public class profilpark {

    private static final String URL = "jdbc:mysql://localhost:3306/javaotopark";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Bu metot, kullanıcıya ait park yerini döndürür
    public String getOtoparkYeri() {
        String OtoparkYeri = null;  // Park yerini tutacak değişken
        int parkid = 0;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            session session = new session();
            int id = session.getkullanicid();
            String query = "SELECT * FROM yer WHERE kullanici_id = ?";  // Kullanıcıya ait otopark adını çekiyoruz
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            
            String otoparkad ="SELECT otopark_adi FROM otopark WHERE otopark_id = ?";
            
            if (rs.next()) {
                parkid = rs.getInt("otopark_id"); // Eğer veri varsa, otopark adını alıyoruz
                try(PreparedStatement pss = connection.prepareStatement(otoparkad)){
                	pss.setInt(1, parkid);
                	ResultSet rss = pss.executeQuery();
                	
                	if(rss.next());
                	
                	OtoparkYeri = rss.getString("otopark_adi");
                	
                }
            }

        } catch (Exception e) {
            e.printStackTrace();  // Hata durumunda stack trace'i yazdırıyoruz
        }

        return OtoparkYeri;  // Sonuç null olabilir, eğer park yeri yoksa
    }
    public String getParkYeri() {
        String parkYeri = null;  // Park yerini tutacak değişken

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            session session = new session();
            int id = session.getkullanicid();

            // Kullanıcıya ait yer_id ve otopark_adi'ni çekmek için birleştirilmiş sorgu
            String query = "SELECT yer_id FROM yer WHERE kullanici_id = ?" ;
                     

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                parkYeri = rs.getString("yer_id");  // Otopark adını alıyoruz
            }

        } catch (Exception e) {
            e.printStackTrace();  // Hata durumunda stack trace'i yazdırıyoruz
        }

        return parkYeri;  // Sonuç null olabilir, eğer park yeri yoksa
    }
}
