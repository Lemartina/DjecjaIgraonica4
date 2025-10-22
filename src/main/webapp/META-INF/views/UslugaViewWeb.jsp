<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Upravljanje uslugama</title>
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css'>
    <style>
        .index-intro { margin: 20px; }
        .wrapper { max-width: 800px; margin: 0 auto; }
        table { border-collapse: collapse; width: 100%; margin: 20px 0; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        input[type="text"] { width: 100%; padding: 8px; margin: 5px 0; }
        button { padding: 10px 20px; background-color: #007bff; color: white; border: none; cursor: pointer; }
    </style>
</head>
<body>
    <div class="index-intro">
        <div class="wrapper">
            <h1>Unos usluga</h1>
            
            <!-- OBRAZAC ZA DODAVANJE NOVE USLUGE -->
            <form action="UslugaController" method="post">
               <div>
                  <input type="text" id="naziv" name="naziv" placeholder='Naziv' required>
                  <br><br>
               </div>                
               <div>                  
                   <input type="text" id="jedinicaMjere" name="jedinicaMjere" placeholder='Jedinica mjere' required>
                   <br><br>
               </div>
                <div>
                   <input type="text" id="cijena" name="cijena" placeholder='Cijena' required>
                   <br><br>
                </div>
                 <div>
                   <input type="text" id="kolicina" name="kolicina" placeholder='Količina' required>
                   <br><br>
               </div>
        
                  <button type="submit" class="btn btn-primary">Dodaj</button>
            </form>

            <!-- Tablica za prikaz usluga -->
            <h2>Popis usluga</h2>
            <c:choose>
                <c:when test="${empty usluge}">
                    <p>Nema unesenih usluga.</p>
                </c:when>
                <c:otherwise>
                    <table>
                        <tr>
                            <th>Cijena</th>
                            <th>Jedinica mjere</th>
                            <th>Količina</th>
                            <th>Naziv</th>
                            <th>Uredi</th>
                            <th>Briši</th>
                        </tr>
                        <c:forEach var="usluga" items="${usluge}">
                            <tr>
                                <td>${usluga.cijena} €</td>
                                <td>${usluga.jedinicaMjere}</td>
                                <td>${usluga.kolicina}</td>
                                <td>${usluga.naziv}</td>
                                <td>
                                    <a href="UrediUslugu?id=${usluga.sifra}">
                                        <i class="fas fa-edit"></i> Uredi
                                    </a>
                                </td>
                                <td>
                                    <a href="BrisiUslugu?id=${usluga.sifra}" 
                                       onclick="return confirm('Jeste li sigurni da želite obrisati uslugu?')">
                                        <i class="fas fa-trash"></i> Briši
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:otherwise>
            </c:choose>

            <br><br>
            <a href="index.html"><i class="fas fa-home"></i> Vrati se nazad na glavni izbornik</a>
        </div>
    </div>
</body>
</html>