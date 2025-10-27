<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dodaj novu uslugu</title>
 <meta charset="UTF-8">
<link href='http://fonts.googleapis.com/css?family=Lato&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="mystyle.css">
</head>
<body>
    <div>
        <div>
            <!-- NAVIGACIJA -->
            <div>
                <h2>Dodavanje nove usluge</h2>
                <a href="DodajUsluguController"></a>
                
            </div>

            <!-- PORUKE -->
            <c:if test="${not empty poruka}">
                <div>
                    ${poruka}
                </div>
            </c:if>
            <c:if test="${not empty greska}">
                <div>
                    ${greska}
                </div>
            </c:if>

            <!-- FORMA ZA DODAVANJE -->
            <form action="DodajUsluguController" method="post">
                <div>
                    
                    <input type="text" id="naziv" name="naziv" placeholder='Unesite naziv usluge' required>
                </div>
                
                <div>
                    
                    <input type="text" id="cijena" name="cijena" placeholder='Unesite cijenu' required>
                </div>
                
                <div>
                  
                    <input type="text" id="kolicina" name="kolicina" placeholder='Unesite količinu' required>
                </div>
                
                <div>
                    <input type="text" id="jedinicaMjere" name="jedinicaMjere" placeholder='Unesite jedinicu mjere' required>
                </div>

                <button type="submit">
                    Dodaj
                </button>
                <button type="submit">
                <a href="DodajUsluguController">
                    Odustani
                </a>
                     </button>
            </form>

            <!-- TABLICA ZA PRIKAZ POSTOJEĆIH USLUGA -->
            <c:if test="${not empty usluge}">
                <h3>Usluge</h3>
                <table>
                    <thead>
                        <tr>
                     
                            <th>Naziv</th>
                            <th>Cijena</th>
                            <th>Količina</th>
                            <th>Jedinica mjere</th>
                              <th>Uredi</th>
                                <th>Briši</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="usluga" items="${usluge}">
                            <tr>
                              
                                <td>${usluga.naziv}</td>
                                <td>${usluga.cijena} €</td>
                                <td>${usluga.kolicina}</td>
                                <td>${usluga.jedinicaMjere}</td>
                                <td><a href='VratiDjelatnika?id=" + rs.getInt("id") + "'><i class='fas fa-edit'></i> Uredi</a></td>
                                <td><a href='BrisiDjelatnika?id=" + rs.getInt("id") + "'><i class='fas fa-trash'></i> Briši</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
    </div>
</body>

<a href="${pageContext.request.contextPath}/index.html">
                    
                    
                    Početna stranica
                </a>
</html>