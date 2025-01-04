package busniess;

import data.Database;
import model.profile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfileService {
	
    // insertProfile metodunun dönüş türünü boolean olarak değiştirdik.
    public boolean insertProfile(int id) {
        try (Connection connection = Database.getConnection()) {
            String query = "INSERT INTO isim (id) VALUES (?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            int kontrol = ps.executeUpdate();
            return kontrol > 0;  // Eğer bir satır eklendiyse true döndürür
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false; // Hata durumunda false döndürür
        }
    }

    public profile getProfileById(int id) {
        profile profile = null;
        try (Connection conn = Database.getConnection()) {
            String query = "SELECT * FROM isim WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                profile = new profile(
                        rs.getInt("id"),
                        rs.getString("ad"),
                        rs.getString("soyad"),
                        rs.getString("telefon"),
                        rs.getString("plaka"),
                        rs.getString("krediKarti")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return profile;  // Eğer profil bulunmazsa null döner.
    }

    public boolean updateProfile(profile profile) {
        try (Connection conn = Database.getConnection()) {
            String updateQuery = "UPDATE isim SET ad = ?, soyad = ?, telefon = ?, plaka = ?, krediKarti = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(updateQuery);
            ps.setString(1, profile.getAd());
            ps.setString(2, profile.getSoyad());
            ps.setString(3, profile.getTelefon());
            ps.setString(4, profile.getPlaka());
            ps.setString(5, profile.getKrediKartı());
            ps.setInt(6, profile.getId());

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;  // Güncelleme başarılıysa true döner
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;  // Güncelleme başarısızsa false döner
        }
    }
    
    public List<String> getKrediKartlariByUserId(int userId) {
        List<String> krediKartlari = new ArrayList<>();
        String query = "SELECT krediKarti FROM isim WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    krediKartlari.add(rs.getString("krediKarti"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return krediKartlari;
    }
}
