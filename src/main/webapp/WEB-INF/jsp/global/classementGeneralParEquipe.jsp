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



<div class="row">
    <div class="col-6 mx-3 mt-5">
        <div class="card">
            <div class="card-header">
                <div class="mb-3">
                    <h3 class="card-title">${pageTitle}</h3>
                </div>

            </div>

            <div class="mb-3 px-3 pt-4">
                <%
                    Categorie categorie = (Categorie)request.getAttribute("categorie");

                    if (categorie!=null){
                %>
                <h4><%=categorie.getNomCategorie()%></h4>
                <%} else {%>
                <h4>Classement générale</h4>
                <%}%>
            </div>


            <%
                int previousRank = -1;
                for (int i = 0; i < classement.size(); i++) {
                    int currentRank = classement.get(i).getRang();
                    boolean isExaequo = (i > 0 && currentRank == previousRank) || (i < classement.size() - 1 && currentRank == classement.get(i + 1).getRang());
                     previousRank = classement.get(i).getRang();


            %>

            <div class="list-group list-group-flush overflow-auto" style="max-height: 35rem">
                <div class="list-group-item  <%if (isExaequo){%> bg-purple-lt <%}%> ">
                    <div class="row">
                        <a href="#" class="col-auto">
                            <span class="avatar" > <%=classement.get(i).getRang()%></span>
                        </a>
                        <div class="col text-truncate">
                            <a href="/listPoimtsParEtapeEquipe?id=<%=classement.get(i).getEquipe().getId()%>">
                                 <p class="text-body d-block"><%=classement.get(i).getEquipe().getNomEquipe()%> </p>
                             </a>
                            <div class="text-muted text-truncate mt-n1">Points : <%=classement.get(i).getPoints()%></div>
                        </div>

                            <c:if test="${not empty pageContext.request.userPrincipal.authorities}">
                                <c:forEach var="authority" items="${pageContext.request.userPrincipal.authorities}">
                                    <c:if test="${ authority.authority == 'ADMIN'}">
                                        <div class="col-auto">
                                            <% if (classement.get(i).getRang()==1){ %>
                                            <a class="btn btn-yellow" href="/certificat?idEquipe=<%=classement.get(i).getEquipe().getId()%>&points=<%=classement.get(i).getPoints()%>&class=<% if(categorie!=null){%>category : ${categorie.nomCategorie}<%} else {%>general category<%}%>"> <i class="ti ti-certificate icon "></i> certificat</a>
                                            <%}%>
                                        </div>                                    </c:if>
                                </c:forEach>
                            </c:if>
                    </div>
                </div>
            </div>

            <%  }%>

        </div>
    </div>
    <div class="card col-5 mx-3 mt-5">
        <div class="card-header">
            <div class="card-title">
                Graphe <% if(categorie!=null){%>categorie : ${categorie.nomCategorie}<%} else {%>categorie générale<%}%>
            </div>
        </div>
        <div id="chart-mentions" class="chart-lg ">
        </div>
    </div>

<%--    <div class="chartCard">--%>
<%--        <div class="chartBox">--%>
<%--            <canvas id="myChart"></canvas>--%>
<%--        </div>--%>
<%--    </div>--%>

    <script>
        // @formatter:off


        document.addEventListener("DOMContentLoaded", function () {
            var equipe=[]
            var points = []
            var i = 0;
            <c:forEach items="${classement}" var="current" varStatus="status">
            equipe[i] = '<c:out value='${current.equipe.nomEquipe}'/>';
            points[i]=<c:out value='${current.points}'/>
            i++;
            </c:forEach>
            console.log("points : "+points)
            window.ApexCharts && (new ApexCharts(document.getElementById('chart-mentions'), {
                chart: {
                    type: "pie",
                    fontFamily: 'inherit',
                    fontSize:20
                    ,
                    parentHeightOffset: 0,
                    stacked: true,
                },
                plotOptions: {
                    bar: {
                        columnWidth: '50%',
                    }
                },
                    labels: equipe

                ,dataLabels: {
                    enabled: true,
                    formatter : function (val, opts){
                        return opts.w.config.series[opts.seriesIndex];
                    },
                    style: {
                        colors: [tabler.getColor('gray-200'),'rgba(252,198,198,0.6)'],
                        fontSize: 16,
                    },
                    background: {
                        enabled: true,
                        foreColor: 'rgba(239,0,0,0.6)',
                        borderWidth: 0,
                    },

                },

                // plugins :[ChartDataLabels],
                fill: {
                    opacity: 1,
                },
                colors: [
                    'rgba(255, 26, 104, 0.6)',
                    'rgba(54, 162, 235, 0.6)',
                    'rgba(3,220,220,0.6)',
                    'rgba(44,2,128,0.6)',
                    'rgba(255, 206, 86, 0.6)',
                    'rgba(75, 192, 192, 0.6)',
                    'rgba(153, 102, 255, 0.6)',
                    'rgba(255, 159, 64, 0.6)',
                    'rgba(159,77,106,0.6)',

                    'rgba(0, 0, 0, 0.6)'
                ],

                borderColor: [
                    'rgba(255, 26, 104, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)',
                    'rgba(0, 0, 0, 1)'
                ],
                series: points,
                tooltip: {
                    theme: 'dark'
                },
                legend: {
                    position: 'bottom'                },
            })).render();
        });
        // @formatter:on
    </script>




<%--        <div class="chartCard">--%>
<%--            <div class="chartBox">--%>
<%--                <canvas id="myChart"></canvas>--%>
<%--            </div>--%>
<%--        </div>--%>

<%--    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/chart.js/dist/chart.umd.min.js"></script>--%>
<%--    <script src="https://cdnjs.cloudflare.com/ajax/libs/chartjs-plugin-datalabels/2.2.0/chartjs-plugin-datalabels.min.js" integrity="sha512-JPcRR8yFa8mmCsfrw4TNte1ZvF1e3+1SdGMslZvmrzDYxS69J7J49vkFL8u6u8PlPJK+H3voElBtUCzaXj+6ig==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>--%>
<%--    <script>--%>
<%--        var equipe=[]--%>
<%--        var points = []--%>
<%--        var i = 0;--%>
<%--        <c:forEach items="${classement}" var="current" varStatus="status">--%>
<%--        equipe[i] = '<c:out value='${current.equipe.nomEquipe}'/>';--%>
<%--        points[i]=<c:out value='${current.points}'/>--%>
<%--            i++;--%>
<%--        </c:forEach>--%>
<%--        // setup--%>
<%--        const data = {--%>
<%--            labels: equipe,--%>
<%--            datasets: [{--%>
<%--                label: "points",--%>
<%--                data: points,--%>
<%--                backgroundColor: [--%>
<%--                    'rgba(255, 26, 104, 0.2)',--%>
<%--                    'rgba(54, 162, 235, 0.2)',--%>
<%--                    'rgba(255, 206, 86, 0.2)',--%>
<%--                    'rgba(75, 192, 192, 0.2)',--%>
<%--                    'rgba(153, 102, 255, 0.2)',--%>
<%--                    'rgba(255, 159, 64, 0.2)',--%>
<%--                    'rgba(0, 0, 0, 0.2)'--%>
<%--                ],--%>
<%--                borderColor: [--%>
<%--                    'rgba(255, 26, 104, 1)',--%>
<%--                    'rgba(54, 162, 235, 1)',--%>
<%--                    'rgba(255, 206, 86, 1)',--%>
<%--                    'rgba(75, 192, 192, 1)',--%>
<%--                    'rgba(153, 102, 255, 1)',--%>
<%--                    'rgba(255, 159, 64, 1)',--%>
<%--                    'rgba(0, 0, 0, 1)'--%>
<%--                ],--%>
<%--                borderWidth: 1--%>
<%--            }]--%>
<%--        };--%>

<%--        // config--%>
<%--        const config = {--%>
<%--            type: 'pie',--%>
<%--            data ,--%>
<%--            options: {--%>

<%--            }--%>
<%--            ,--%>
<%--            plugins:[ChartDataLabels],--%>

<%--                datalabels: {--%>

<%--                    color: 'blue',--%>
<%--                    labels: {--%>
<%--                        title: {--%>
<%--                            font: {--%>
<%--                                weight: 'bold'--%>
<%--                            }--%>
<%--                        },--%>
<%--                        value: {--%>
<%--                            color: 'green'--%>
<%--                        }--%>
<%--                    }--%>
<%--                }--%>


<%--        };--%>

<%--        Chart.defaults.font.size = 16;--%>
<%--        Chart.defaults.font.weight = 'bold';--%>
<%--        // render init block--%>
<%--        const myChart = new Chart(--%>
<%--            document.getElementById('myChart'),--%>
<%--            config--%>
<%--        );--%>

<%--        // Instantly assign Chart.js version--%>
<%--        const chartVersion = document.getElementById('chartVersion');--%>
<%--        chartVersion.innerText = Chart.version;--%>
<%--    </script>--%>


</div>
        <%-- end main content --%>

    </div>
</div>

<%@include file="../1-basic_setup/footer.jsp"%>
