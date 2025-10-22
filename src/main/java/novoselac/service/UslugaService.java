package novoselac.service;

import novoselac.dao.UslugaDao;
import novoselac.model.Usluga;
import java.util.List;

public class UslugaService {
    private UslugaDao UslugaDao;  //  INSTANCE VARIJABLA
    
    // KONSTRUKTOR SA DEPENDENCY INJECTION
    public UslugaService() {
        this.UslugaDao = new UslugaDao();  // INSTANCIRANJE DAO OBJEKTA
    }
    
     public List<Usluga> dohvatiSveUsluge() {
        return UslugaDao.getAllUsluge();
    }
    // KONSTRUKTOR ZA TESTIRANJE (dependency injection)
    public UslugaService(UslugaDao uslugaDao) {
        this.UslugaDao = uslugaDao;
    }
    
    public boolean dodajUslugu(Usluga usluga) {
        // VALIDACIJA
        if (usluga.getCijena().compareTo(java.math.BigDecimal.ZERO) <= 0 || 
            usluga.getKolicina().compareTo(java.math.BigDecimal.ZERO) < 0) {
            return false;
        }
        
        // POZIV INSTANCE METODE NA DAO OBJEKTU
        return UslugaDao.getAllUsluge().add(usluga);
    }
}