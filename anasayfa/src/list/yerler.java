package list;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class yerler {
    private static final String URL = "jdbc:mysql://localhost:3306/javaotopark";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public List<String> fetchParkYerleri(String parkAdi) {
        List<String> yerList = new ArrayList<>();

        String queryOtoparkId = "SELECT otopark_id FROM otopark WHERE `otopark_adi` = ?";
        
       
        String queryParkYerleri = "SELECT yer_id FROM yer WHERE otopark_id = ? AND durum = 0";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {

      
            try (PreparedStatement preparedStatement1 = connection.prepareStatement(queryOtoparkId)) {
                preparedStatement1.setString(1, parkAdi);
                ResultSet resultSet1 = preparedStatement1.executeQuery();

                if (resultSet1.next()) {
                    int otoparkId = resultSet1.getInt("otopark_id");

 
                    try (PreparedStatement preparedStatement2 = connection.prepareStatement(queryParkYerleri)) {
                        preparedStatement2.setInt(1, otoparkId);
                        ResultSet resultSet2 = preparedStatement2.executeQuery();


                        while (resultSet2.next()) {
                            String parkId = resultSet2.getString("yer_id");
                            yerList.add(parkId);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return yerList;
    }
}
