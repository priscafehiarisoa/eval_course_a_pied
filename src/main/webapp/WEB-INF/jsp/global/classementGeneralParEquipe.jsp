<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="ev.eval_course_a_pied.entity.*" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: priscafehiarisoadama
  Date: 13/05/2024
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../1-basic_setup/head.jsp"%>
<%@include file="../1-basic_setup/side_menu.jsp"%>
<%@include file="../1-basic_setup/pageHeader.jsp"%>
<%
    List<ClassementEquipe> classement = (List<ClassementEquipe>) request.getAttribute("classement");
    List<Categorie> categories = (List<Categorie>) request.getAttribute("categorieList");
%>

<div class="page-wrapper">
    <div class="page-body m-5">

        <div class="page-header d-print-none mb-5">
            <div class="container-xl">
                <div class="row g-2 align-items-center">
                    <div class="col">
                        <!-- Page pre-title -->
                        <h2 class="page-title">
                            <c:out value="${pageTitle}"></c:out>
                        </h2>
                    </div>
                </div>
            </div>
        </div>

        <%-- main content --%>
        <div class="card">
            <div class="card-body">
                <div class="card-title">
                    liste des categories :
                </div>
                <div class="row">
                    <div class="m-3 col-auto">
                        <a href="/classementGeneralParEquipe" class="btn btn-outline-dark">classement générale</a>
                    </div>
                    <%
                        for (int i = 0; i < categories.size(); i++) { %>
                    <div class="m-3 col-auto">
                        <a href="/classementGeneralParEquipeCategorie?id=<%=categories.get(i).getId()%>" class="btn btn-outline-dark">catégorie : <%=categories.get(i).getNomCategorie()%></a>
                    </div>
                    <%}%>
                </div>

            </div>
        </div>



        <div class="col-12 mt-5">
            <div class="card">
                <div class="card-header">
                    <div class="mb-3">
                        <h3 class="card-title">${pageTitle}</h3>
                    </div>

                </div>

                    <div class="mb-3 px-3 pt-4">
                        <%
                            if (request.getAttribute("categorie")!=null){
                                Categorie categorie = (Categorie)request.getAttribute("categorie");
                        %>
                        <h4><%=categorie.getNomCategorie()%></h4>
                        <%} else {%>
                        <h4>Classement générale</h4>
                        <%}%>
                    </div>

                    <%
                        for (int i = 0; i < classement.size(); i++) { %>

                    <div class="list-group list-group-flush overflow-auto" style="max-height: 35rem">
                        <div class="list-group-item">
                            <div class="row">
                                <a href="#" class="col-auto">
                                    <span class="avatar" > <%=classement.get(i).getRang()%></span>
                                </a>
                                <div class="col text-truncate">
                                    <p class="text-body d-block"><%=classement.get(i).getEquipe().getNomEquipe()%> </p>
                                    <div class="text-muted text-truncate mt-n1">Points : <%=classement.get(i).getPoints()%></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%  }%>

            </div>
        </div>

        <%-- end main content --%>

    </div>
</div>

<%@include file="../1-basic_setup/footer.jsp"%>
