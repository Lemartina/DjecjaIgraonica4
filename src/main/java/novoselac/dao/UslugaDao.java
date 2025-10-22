package novoselac.dao;

import novoselac.model.Usluga;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UslugaDao {
    private static final Logger LOGGER = Logger.getLogger(UslugaDao .class.getName());
    
    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "MySQL JDBC Driver nije pronađen", ex);
            throw new SQLException("Driver nije pronađen", ex);
        }
        return DriverManager.getConnection("jdbc:mysql://localhost/djecjaigraonicahib", "root", "");
    }
    
    // Metoda za brisanje po nazivu
    public boolean deleteByNaziv(String naziv) throws SQLException {
        String sql = "DELETE FROM usluga WHERE naziv = ?";
        
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setString(1, naziv);
            int row = pst.executeUpdate();
            return row > 0;
        }
    }
    
    // Metoda za provjeru zavisnih podataka
    public boolean hasDependentRecords(String naziv) throws SQLException {
        String sql = "SELECT COUNT(*) FROM usluga a " +
                    "INNER JOIN uslugaposjeta b ON a.sifra = b.usluga " +
                    "WHERE a.naziv = ?";
        
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setString(1, naziv);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
    
    // Poboljšana metoda za dohvat svih usluga
    public List<Usluga> getAllUsluge() {
        List<Usluga> usluge = new ArrayList<>();
        String sql = "SELECT * FROM usluga ORDER BY naziv";
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            LOGGER.info("DEBUG: Pozvan getAllUsluge()");
            
            while (rs.next()) {
                Usluga usluga = new Usluga();
                
                // Fleksibilno čitanje šifre - pokušaj s različitim nazivima stupaca
                try {
                    usluga.setSifra(rs.getInt("sifra"));
                } catch (SQLException e) {
                    try {
                        usluga.setSifra(rs.getInt("id"));
                    } catch (SQLException e2) {
                        LOGGER.warning("DEBUG: Nije pronađen stupac 'sifra' ili 'id'");
                        // Možete postaviti default vrijednost ili nastaviti bez šifre
                        usluga.setSifra(0);
                    }
                }
                
                // Ostali stupci
                usluga.setNaziv(rs.getString("naziv"));
                usluga.setJedinicaMjere(rs.getString("jedinicaMjere"));
                usluga.setCijena(rs.getBigDecimal("cijena"));
                usluga.setKolicina(rs.getBigDecimal("kolicina"));
                
                LOGGER.info("DEBUG: Učitana usluga: " + usluga.getNaziv() + 
                           ", ID: " + usluga.getSifra());
                
                usluge.add(usluga);
            }
            
            LOGGER.info("DEBUG: Pronađeno usluga: " + usluge.size());
            
        } catch (SQLException e) {
            LOGGER.severe("DEBUG: Greška u getAllUsluge: " + e.getMessage());
            e.printStackTrace();
        }
        return usluge;
    }
    
    // Metoda za pronalaženje usluge po nazivu
    public Usluga findByNaziv(String naziv) throws SQLException {
        String sql = "SELECT * FROM usluga WHERE naziv = ?";
        
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setString(1, naziv);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    Usluga usluga = new Usluga();
                    
                    // Fleksibilno čitanje šifre
                    try {
                        usluga.setSifra(rs.getInt("sifra"));
                    } catch (SQLException e) {
                        try {
                            usluga.setSifra(rs.getInt("id"));
                        } catch (SQLException e2) {
                            usluga.setSifra(0);
                        }
                    }
                    
                    usluga.setNaziv(rs.getString("naziv"));
                    usluga.setCijena(rs.getBigDecimal("cijena"));
                    usluga.setJedinicaMjere(rs.getString("jedinicaMjere"));
                    usluga.setKolicina(rs.getBigDecimal("kolicina"));
                    
                    return usluga;
                }
            }
        }
        return null;
    }
    
    // Metoda za dodavanje nove usluge
    public boolean dodajUslugu(Usluga usluga) throws SQLException {
        String sql = "INSERT INTO usluga (naziv, cijena, jedinicaMjere, kolicina) VALUES (?, ?, ?, ?)";
        
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setString(1, usluga.getNaziv());
            pst.setBigDecimal(2, usluga.getCijena());
            pst.setString(3, usluga.getJedinicaMjere());
            pst.setBigDecimal(4, usluga.getKolicina());
            
            int row = pst.executeUpdate();
            return row > 0;
        }
    }
    
    // Metoda za ažuriranje usluge
    public boolean updateUsluga(Usluga usluga) throws SQLException {
        String sql = "UPDATE usluga SET naziv = ?, cijena = ?, jedinicaMjere = ?, kolicina = ? WHERE sifra = ?";
        
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setString(1, usluga.getNaziv());
            pst.setBigDecimal(2, usluga.getCijena());
            pst.setString(3, usluga.getJedinicaMjere());
            pst.setBigDecimal(4, usluga.getKolicina());
            pst.setInt(5, usluga.getSifra());
            
            int row = pst.executeUpdate();
            return row > 0;
        }
    }
}