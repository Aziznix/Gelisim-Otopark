package kontrol;
import java.sql.*;

public class onaykod {
	private static final String URL = "jdbc:mysql://localhost:3306/login_register_db";
    private static final String USER= "root";
    private static final String PASSWORD = "";
  
	public boolean kullaniciOnayla(int id, String girilenKod) {
	    String selectQuery = "SELECT onaykodu FROM users WHERE id = ?";
	    String updateQuery = "UPDATE users SET onaydurumu = 1 WHERE id = ?";

	    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
	         PreparedStatement selectPs = connection.prepareStatement(selectQuery)) {
	        selectPs.setInt(1, id);
	        ResultSet rs = selectPs.executeQuery();

	        if (rs.next()) {
	            String dbKod = rs.getString("onaykodu");
	            if (dbKod.equals(girilenKod)) {
	                try (PreparedStatement updatePs = connection.prepareStatement(updateQuery)) {
	                    updatePs.setInt(1, id);
	                    int rowsUpdated = updatePs.executeUpdate();
	                    return rowsUpdated > 0;
	                }
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return false;
	}

}
