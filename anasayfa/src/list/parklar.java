package list;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class parklar {
    private static final String URL = "jdbc:mysql://localhost:3306/javaotopark";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public List<String> fetchColumnData() {
        List<String> dataList = new ArrayList<>();
        String query = "SELECT `otopark_adi` FROM otopark"; 

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                dataList.add(resultSet.getString("otopark_adi")); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }
}
