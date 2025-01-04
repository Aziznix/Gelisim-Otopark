package fonks;

import java.sql.*;

import kontrol.mail;
import kullanici.session;

public class islem {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/login_register_db";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";
    
    // Kullanıcı girişi
    public boolean giris(String username, String password) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            // Kullanıcı adı ve şifreyi kontrol eden ve id'yi getiren sorgu
            String query = "SELECT id FROM users WHERE username = ? AND password = ? AND onaydurumu = 1";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                // Kullanıcı bulunduysa, ID'yi session'a kaydediyoruz
                session.setkullanid(resultSet.getInt("id"));
                
                return true;
            }
            return false; // Kullanıcı bulunamadı
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Kullanıcı kaydı
    public boolean kayit(String username, String password, String eposta) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            // Kullanıcı kaydı için SQL sorgusu
            String query = "INSERT INTO users (username, password, mail, onaykodu, onaydurumu) VALUES (?, ?, ?, ?, ?)";
            String onayKodu = String.valueOf((int) (Math.random() * 9000) + 1000); // 1000 ile 9999 arası bir onay kodu oluştur
            String onayDurumu = "0"; // Kullanıcı henüz onaylanmamış, 0 değeri gönderilsin

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, eposta);
            preparedStatement.setString(4, onayKodu);
            preparedStatement.setString(5, onayDurumu); // Onay durumu burada belirtiliyor
            
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) { // Eğer 1 veya daha fazla satır etkilendiyse kayıt başarılıdır
            	String queryy = "SELECT id FROM users WHERE username = ? AND password = ?";
                PreparedStatement preparedStatement3 = connection.prepareStatement(queryy);
                preparedStatement3.setString(1, username);
                preparedStatement3.setString(2, password);
                ResultSet resultSet = preparedStatement3.executeQuery();
                if (resultSet.next()) {
                	
                	session.setkullanid(resultSet.getInt("id"));
					
				}
                mail.sendEmail(eposta, "Kayıt Başarılı! Hesabınızı Onaylayınız", "Merhaba "+username+" Sisteme Kayıt Olduğun İçin Teşekkür Ederim.\nOnay Kodunuz: " + onayKodu);
                return true;  // Kayıt başarılı, onay kodu gönderildi
            }
        } catch (SQLException e) {
            if (e.getSQLState().equals("23000")) { // Eğer hata "duplicate entry" hatası ise (yani kullanıcı adı zaten varsa)
                System.out.println("Kullanıcı adı zaten mevcut.");
            } else {
                e.printStackTrace();
            }
            return false; // Kayıt başarısız
        }
        return false; // Genel hata durumu
    }

}
