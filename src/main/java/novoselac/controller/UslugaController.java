package novoselac.controller;

import novoselac.model.Usluga;
import novoselac.service.UslugaService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/UslugaController")
public class UslugaController extends HttpServlet {
    private UslugaService uslugaService;
    
    @Override
    public void init() {
        this.uslugaService = new UslugaService();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // DOHVAĆANJE PODATAKA preko servisa
        List<Usluga> usluge = uslugaService.dohvatiSveUsluge();
        
        // PROSLJEĐIVANJE PODATAKA VIEW-u
        request.setAttribute("usluge", usluge);
        request.getRequestDispatcher("/META-INF/views/UslugaViewWeb.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // DOHVAĆANJE PARAMETARA IZ FORME
        String naziv = request.getParameter("naziv");
        String jedinicaMjere = request.getParameter("jedinicaMjere");
        String cijenaStr = request.getParameter("cijena");
        String kolicinaStr = request.getParameter("kolicina");
        
        try {
            // KONVERZIJA STRINGOVA U BIGDECIMAL
            BigDecimal cijena = new BigDecimal(cijenaStr);
            BigDecimal kolicina = new BigDecimal(kolicinaStr);
            
            // KREIRANJE NOVE USLUGE
            Usluga novaUsluga = new Usluga();
            novaUsluga.setNaziv(naziv);
            novaUsluga.setJedinicaMjere(jedinicaMjere);
            novaUsluga.setCijena(cijena);
            novaUsluga.setKolicina(kolicina);
            
            // DODAVANJE USLUGE
            boolean uspjeh = uslugaService.dodajUslugu(novaUsluga);
            
            if (uspjeh) {
                request.setAttribute("poruka", "Usluga je uspješno dodana!");
            } else {
                request.setAttribute("greska", "Došlo je do greške pri dodavanju usluge!");
            }
            
        } catch (NumberFormatException e) {
            request.setAttribute("greska", "Cijena i količina moraju biti brojevi!");
        } catch (Exception e) {
            request.setAttribute("greska", "Došlo je do greške: " + e.getMessage());
        }
        
        // PREUSMJERAVANJE NA GET METODU ZA OSNVJEŽAVANJE POPISA
        doGet(request, response);
    }
}