package novoselac.dao;

import novoselac.model.Usluga;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UslugaDao {
    
    public List<Usluga> getAllUsluge() {
        List<Usluga> usluge = new ArrayList<>();
        String sql = "SELECT * FROM usluga";
        
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/djecjaigraonicahib", "root", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            System.out.println("DEBUG: Pozvan getAllUsluge()"); // DEBUG
            
            while (rs.next()) {
                Usluga usluga = new Usluga();
                
                // POKUŠAJTE RAZLIČITE KOMBINACIJE NAZIVA STUPACA
                // Opcija 1: Ako koristite 'sifra'
                try {
                    usluga.setSifra(rs.getInt("sifra"));
                } catch (SQLException e) {
                    try {
                        // Opcija 2: Ako koristite 'id'
                        usluga.setSifra(rs.getInt("id"));
                    } catch (SQLException e2) {
                        System.err.println("DEBUG: Nije pronađen stupac 'sifra' ili 'id'");
                    }
                }
                
                // OSTALI STUPCI
                usluga.setNaziv(rs.getString("naziv"));
                usluga.setJedinicaMjere(rs.getString("jedinicaMjere"));
                usluga.setCijena(rs.getBigDecimal("cijena"));
                usluga.setKolicina(rs.getBigDecimal("kolicina"));
                
                // DEBUG ispis
                System.out.println("DEBUG: Učitana usluga: " + usluga.getNaziv() + 
                                 ", ID: " + usluga.getSifra());
                
                usluge.add(usluga);
            }
            
            System.out.println("DEBUG: Pronađeno usluga: " + usluge.size()); // DEBUG
            
        } catch (SQLException e) {
            System.err.println("DEBUG: Greška u getAllUsluge: " + e.getMessage());
            e.printStackTrace();
        }
        return usluge;
    }
    
    // Ostatak koda...

    public boolean dodajUslugu(Usluga usluga) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}