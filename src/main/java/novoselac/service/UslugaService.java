package novoselac.service;

import java.sql.SQLException;
import novoselac.dao.UslugaDao;
import novoselac.model.Usluga;
import java.util.List;

public class UslugaService {
    private UslugaDao uslugaDAO;  //  INSTANCE VARIJABLA
    
    // KONSTRUKTOR SA DEPENDENCY INJECTION
    public UslugaService() {
        this.uslugaDAO = new UslugaDao();  // INSTANCIRANJE DAO OBJEKTA
    }
    
    public List<Usluga> dohvatiSveUsluge() {
        return uslugaDAO.getAllUsluge();
    }
    
    // KONSTRUKTOR ZA TESTIRANJE (dependency injection)
    public UslugaService(UslugaDao uslugaDao) {
        this.uslugaDAO = uslugaDao;
    }
    
    public boolean dodajUslugu(Usluga usluga) throws SQLException {
        // VALIDACIJA
        if (usluga.getCijena() == null || usluga.getCijena().compareTo(java.math.BigDecimal.ZERO) <= 0 || 
            usluga.getKolicina() == null || usluga.getKolicina().compareTo(java.math.BigDecimal.ZERO) < 0 ||
            usluga.getNaziv() == null || usluga.getNaziv().trim().isEmpty()) {
            return false;
        }
        
        // POZIV INSTANCE METODE NA DAO OBJEKTU
        return uslugaDAO.dodajUslugu(usluga);
    }
}