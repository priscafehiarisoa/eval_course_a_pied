
<%@ page import="org.springframework.data.domain.Page" %>
<%@ page import="ev.eval_course_a_pied.model.Pagination" %>
<%@ page import="ev.eval_course_a_pied.entity.Etape" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="ev.eval_course_a_pied.entity.ChronoCoureurs" %><%--
  Created by IntelliJ IDEA.
  User: priscafehiarisoadama
  Date: 13/05/2024
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../1-basic_setup/head.jsp"%>
<%@include file="../1-basic_setup/side_menu.jsp"%>
<%@include file="../1-basic_setup/pageHeader.jsp"%>

<%
    List<Etape> etapeList = (List<Etape>) request.getAttribute("etapes");
    HashMap<Etape,List<ChronoCoureurs>> chrono = (HashMap<Etape, List<ChronoCoureurs>>) request.getAttribute("coureurs");
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
        <div class="row">

       <%
           for (int i = 0; i < etapeList.size(); i++) { %>
        <div class="card m-5 col-5">
            <div class="card-header">
                        <a href="#" class="col-auto ">
                            <span class="avatar" > <%=etapeList.get(i).getRangEtape()%></span>
                        </a>
                        <div class="mx-3 card-title">
                            <div class=" mb-3 col text-uppercase"><%=etapeList.get(i).getLieu() %></div> (<%=etapeList.get(i).getLongueur()%> km )  <span><%=etapeList.get(i).getNombreCoureursParEquipe()%> coureur(s)</span>
                        </div>
            </div>

            <div class="card-body">
                <div class="table-responsive">
                    <table class="table card-table">
                        <tr>
                            <th>numero</th>
                            <th>nom</th>
                            <th>Chrono</th>
                        </tr>
                        <% List<ChronoCoureurs> chronos = chrono.get(etapeList.get(i));
                            for (int j = 0; j < chronos.size(); j++) { %>
                        <tr>
                            <td><%=chronos.get(j).getCoureur().getNumeroDeDossard()%></td>
                            <td><%=chronos.get(j).getCoureur().getNomCoureur()%></td>
                            <td><%=chronos.get(j).getChronoFormatted()%></td>
                        </tr>
                          <%
                            }
                        %>

                    </table>
                </div>

                <div class="m-3 mt-3 right">
                    <a href="/equipe/coureurParEtape?id=<%=etapeList.get(i).getId()%>" class="btn btn-primary">Ajouter un coureur par etape</a>
                </div>
            </div>
        </div>

     <% }%>
        </div>


    <%-- end main content --%>
    </div>
</div>

<%@include file="../1-basic_setup/footer.jsp"%>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        const list = new List('table-default', {
            sortClass: 'table-sort',
            listClass: 'table-tbody',
            valueNames: [ 'sort-name', 'sort-type', 'sort-city', 'sort-score',
                { attr: 'data-date', name: 'sort-date' },
                { attr: 'data-progress', name: 'sort-progress' },
                'sort-quantity'
            ]
        });
    })
</script>