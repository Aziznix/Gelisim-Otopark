package fonks;

import java.security.Identity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kullanici.bakiye;
import kullanici.session;

public class hızlıödeme {

    private static final String URL = "jdbc:mysql://localhost:3306/javaotopark";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    
    public int borcisleme(int kullaniciid) {
    	int bakiye = 0;
    	String query = "SELECT borc FROM isim WHERE id = ?";
    	try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement ps = connection.prepareStatement(query)) {
    		ps.setInt(1, kullaniciid);
    		try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    bakiye = rs.getInt("borc");
                } else {
                    System.out.println("Kullanıcı bulunamadı.");
                }
            }
    	}
    	catch (Exception e) {
			// TODO: handle exception
		}
    	return bakiye;
    }
    
    
    public String BorcSorgu(String plaka) {
        String borc = null; // Borç bilgisini tutacak değişken
        String query = "SELECT borc FROM isim WHERE plaka = ?";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, plaka); // Plaka bilgisini sorguya ekle
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) { // Veri varsa ilk satıra geç
                    borc = rs.getString("borc"); // Borç bilgisini al
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Hata oluşursa konsola yazdır
        }

        return borc; // Sonuç yoksa null döner
    }
    
    
    public boolean borcödeme(int id) {
        String borcquery = "UPDATE isim SET borc = 0 WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(borcquery)) {
            ps.setInt(1, id);
            int update = ps.executeUpdate();
            return update > 0; // Eğer etkilenen satır varsa true döndür
        } catch (Exception e) {
            e.printStackTrace(); // Hata durumunda istisnayı yazdır
        }

        return false; // Hata durumunda false döndür
    }

}
