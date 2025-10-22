package novoselac.service;

import novoselac.dao.UslugaDao;
import novoselac.model.Usluga;
import java.util.List;

public class UslugaService {
    private UslugaDao uslugaDao;  //  INSTANCE VARIJABLA
    
    // KONSTRUKTOR SA DEPENDENCY INJECTION
    public UslugaService() {
        this.uslugaDao = new UslugaDao();  // INSTANCIRANJE DAO OBJEKTA
    }
    
    public List<Usluga> dohvatiSveUsluge() {
        return uslugaDao.getAllUsluge();
    }
    
    // KONSTRUKTOR ZA TESTIRANJE (dependency injection)
    public UslugaService(UslugaDao uslugaDao) {
        this.uslugaDao = uslugaDao;
    }
    
    public boolean dodajUslugu(Usluga usluga) {
        // VALIDACIJA
        if (usluga.getCijena() == null || usluga.getCijena().compareTo(java.math.BigDecimal.ZERO) <= 0 || 
            usluga.getKolicina() == null || usluga.getKolicina().compareTo(java.math.BigDecimal.ZERO) < 0 ||
            usluga.getNaziv() == null || usluga.getNaziv().trim().isEmpty()) {
            return false;
        }
        
        // POZIV INSTANCE METODE NA DAO OBJEKTU
        return uslugaDao.dodajUslugu(usluga);
    }
}