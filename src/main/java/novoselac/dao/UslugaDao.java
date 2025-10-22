package novoselac.dao;

import novoselac.model.Usluga;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UslugaDao {
    
    public List<Usluga> getAllUsluge() {
        List<Usluga> usluge = new ArrayList<>();
        String sql = "SELECT * FROM usluga";  // ← SQL UPIT
        
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/djecjaigraonicahib", "root", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Usluga usluga = new Usluga();
               // usluga.setId(rs.getInt("id"));
                usluga.setNaziv(rs.getString("naziv"));
                usluga.setJedinicaMjere(rs.getString("jedinicaMjere"));
                usluga.setCijena(rs.getBigDecimal("cijena"));
                usluga.setKolicina(rs.getBigDecimal("kolicina"));
                usluge.add(usluga);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usluge;  // ← VRATI LISTU IZ BAZE
    }
}