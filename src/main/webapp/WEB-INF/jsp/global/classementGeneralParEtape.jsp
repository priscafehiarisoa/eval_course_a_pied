<%@ page import="java.util.List" %>
<%@ page import="ev.eval_course_a_pied.entity.CoureurEtape" %>
<%@ page import="ev.eval_course_a_pied.entity.Coureur" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="ev.eval_course_a_pied.entity.Etape" %>
<%@ page import="ev.eval_course_a_pied.entity.ClassementCoureurParEtape" %>
<%@ page import="ev.eval_course_a_pied.utils.Utils" %>
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
    List<Etape> etapes = (List<Etape>)request.getAttribute("etapes");
    List<ClassementCoureurParEtape> classement = (List<ClassementCoureurParEtape>) request.getAttribute("classement");
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
                    liste des étapes :
                </div>
                <div class="row">
                    <%
                        for (int i = 0; i < etapes.size(); i++) { %>
                    <div class="m-3 col-auto">
                        <a href="/classementGeneralPArEtape?etapeId=<%=etapes.get(i).getId()%>" class="btn btn-outline-dark"><%=etapes.get(i).getLieu()%></a>
                    </div>
                    <%}%>
                </div>

            </div>
        </div>

        <div class="col-12 mt-5">
            <div class="card">
                <div class="card-header">
                    <h3 class="card-title">${pageTitle}</h3>
                </div>


<%--                <div class="list-group list-group-flush overflow-auto" style="max-height: 35rem">--%>
<%--                    <div class="list-group-item">--%>
<%--                        <div class="row">--%>
<%--                            <a href="#" class="col-auto">--%>
<%--                                <span class="avatar" > <%=classement.get(i).getRangs()%></span>--%>
<%--                            </a>--%>
<%--                            <div class="col text-truncate">--%>
<%--                                <p class="text-body d-block">--%>
<%--&lt;%&ndash;                                    ND* :<%=classement.get(i).getCoureur().getNumeroDeDossard()%> ,&ndash;%&gt;--%>
<%--                                    Nom : <%=classement.get(i).getCoureur().getNomCoureur()%></p>--%>
<%--                                <div class="text-muted text-truncate mt-n1">Points : <%=classement.get(i).getPoints()%></div>--%>
<%--                                <div class="text-muted text-truncate mt-n1">Temps : <%=classement.get(i).getFormatedDurre()%></div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>

                <div class="table-responsive">
                    <table class="table card-table table-vcenter text-nowrap datatable">
                        <thead>
                        <tr>
                            <th>Rang</th>
                            <th>Nom Coureur</th>
                            <th>Equipe</th>
                            <th>Genre</th>
                            <th>Chrono</th>
                            <th>Penalites</th>
                            <th>Temps Final</th>
                        </tr>
                        </thead>

                        <%
                            for (int i = 0; i < classement.size(); i++) { %>
                        <tbody class="table-body">
                            <tr>
                                <td>
                                    <a href="#" class="col-auto">
                                    <span class="avatar" > <%=classement.get(i).getRangs()%></span>
                                    </a>
                                </td>
                                <td><%=classement.get(i).getCoureur().getNomCoureur()%></td>
                                <td><%=classement.get(i).getCoureur().getEquipe().getNomEquipe()%></td>
                                <td><%=classement.get(i).getCoureur().getGenre().getNom_genre()%></td>
                                <td><%=classement.get(i).getFormatedDurre()%></td>
                                <td><%=classement.get(i).getFormatedPenalites()%></td>
                                <td><%=classement.get(i).getFormatedTempsFinal()%></td>
                            </tr>
                        </tbody>
                        <%  }%>
                    </table>
                </div>


            </div>
        </div>

    <%-- end main content --%>

</div>
</div>

<%@include file="../1-basic_setup/footer.jsp"%>
