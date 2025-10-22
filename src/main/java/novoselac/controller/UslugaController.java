package novoselac.controller;

//Prima i vraća zahtjeve

import novoselac.model.Usluga;
import novoselac.service.UslugaService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
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
}