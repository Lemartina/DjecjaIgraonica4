<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dodaj novu uslugu</title>
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css'>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/stili.css">
    
</head>
<body>
    <div class="usluga-container">
        <div class="usluga-wrapper">
            <!-- NAVIGACIJA -->
            <div class="usluga-nav">
                <h2><i class="fas fa-plus-circle"></i> Dodavanje nove usluge</h2>
                <a href="DodajUsluguController" class="usluga-link btn-view">
                  <%-- <i class="fas fa-list"></i> Pregled svih usluga--%>
                </a>
                <a href="${pageContext.request.contextPath}/index.html" class="usluga-link">
                    <i class="fas fa-home"></i> Početna stranica
                </a>
            </div>

            <!-- PORUKE -->
            <c:if test="${not empty poruka}">
                <div class="usluga-success">
                    <i class="fas fa-check-circle"></i> ${poruka}
                </div>
            </c:if>
            <c:if test="${not empty greska}">
                <div class="usluga-error">
                    <i class="fas fa-exclamation-triangle"></i> ${greska}
                </div>
            </c:if>

            <!-- FORMA ZA DODAVANJE - ISPRAVLJEN ACTION -->
            <form action="DodajUsluguController" method="post" class="usluga-form">
                <div class="usluga-form-group">
                    <label for="naziv"><i class="fas fa-tag"></i> Naziv usluge:</label>
                    <input type="text" id="naziv" name="naziv" placeholder='Unesite naziv usluge' required>
                </div>
                
                <div class="usluga-form-group">
                    <label for="cijena"><i class="fas fa-euro-sign"></i> Cijena (EUR):</label>
                    <input type="text" id="cijena" name="cijena" placeholder='Unesite cijenu' required>
                </div>
                
                <div class="usluga-form-group">
                    <label for="kolicina"><i class="fas fa-balance-scale"></i> Količina:</label>
                    <input type="text" id="kolicina" name="kolicina" placeholder='Unesite količinu' required>
                </div>
                
                <div class="usluga-form-group">
                    <label for="jedinicaMjere"><i class="fas fa-ruler"></i> Jedinica mjere:</label>
                    <input type="text" id="jedinicaMjere" name="jedinicaMjere" placeholder='Unesite jedinicu mjere' required>
                </div>

                <button type="submit" class="usluga-btn usluga-btn-success">
                    <i class="fas fa-save"></i> Spremi uslugu
                </button>
                
                <a href="DodajUsluguController" class="usluga-link">
                    <i class="fas fa-times"></i> Odustani
                </a>
            </form>

            <!-- TABLICA ZA PRIKAZ POSTOJEĆIH USLUGA -->
            <c:if test="${not empty usluge}">
                <h3>Postojeće usluge</h3>
                <table class="usluga-table">
                    <thead>
                        <tr>
                            <th>Šifra</th>
                            <th>Naziv</th>
                            <th>Cijena</th>
                            <th>Količina</th>
                            <th>Jedinica mjere</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="usluga" items="${usluge}">
                            <tr>
                                <td>${usluga.sifra}</td>
                                <td>${usluga.naziv}</td>
                                <td>${usluga.cijena} €</td>
                                <td>${usluga.kolicina}</td>
                                <td>${usluga.jedinicaMjere}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
    </div>
</body>
</html>