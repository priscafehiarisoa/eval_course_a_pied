<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: priscafehiarisoadama
  Date: 10/05/2024
  Time: 13:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../1-basic_setup/head.jsp"%>
<%@include file="../1-basic_setup/side_menu.jsp"%>
<%@include file="../1-basic_setup/pageHeader.jsp"%>

<div class="page-wrapper">
    <div class="page-body m-5">
        <div class="page-header d-print-none mb-5">
            <div class="container-xl">
                <div class="row g-2 align-items-center">
                    <div class="col">
                        <!-- Page pre-title -->
                        <h2 class="page-title">
                            <c:out value="${pageTitle}"> </c:out>
                        </h2>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-6">
                <div class="card">
                    <div class="card-body">
                        <h3 class="card-title">Traffic summary</h3>
                        <div id="chart-mentions" class="chart-lg"></div>
                    </div>
                </div>
            </div>
            <script>
                // @formatter:off
                document.addEventListener("DOMContentLoaded", function () {
                    window.ApexCharts && (new ApexCharts(document.getElementById('chart-mentions'), {
                        chart: {
                            type: "bar",
                            fontFamily: 'inherit',
                            height: 240,
                            parentHeightOffset: 0,
                            toolbar: {
                                show: false,
                            },
                            animations: {
                                enabled: false
                            },
                            stacked: true,
                        },
                        plotOptions: {
                            bar: {
                                columnWidth: '85%',
                            }
                        },
                        dataLabels: {
                            enabled: true,
                        },
                        fill: {
                            opacity: 1,
                        },
                        series: [{
                            name: "Web",
                            data: [1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 2, 12, 5, 8, 22, 6, 8, 6, 4, 1, 8, 24, 29, 51, 40, 47, 23, 26, 50, 26, 41, 22, 46, 47, 81, 46, 6]
                        },
                            {
                            name: "Social",
                            data: [2, 5, 4, 3, 3, 1, 4, 7, 5, 1, 2, 5, 3, 2, 6, 7, 7, 1, 5, 5, 2, 12, 4, 6, 18, 3, 5, 2, 13, 15, 20, 47, 18, 15, 11, 10, 0]
                        },{
                            name: "Other",
                            data: [2, 9, 1, 7, 8, 3, 6, 5, 5, 4, 6, 4, 1, 9, 3, 6, 7, 5, 2, 8, 4, 9, 1, 2, 6, 7, 5, 1, 8, 3, 2, 3, 4, 9, 7, 1, 6]
                        }],
                        tooltip: {
                            theme: 'dark'
                        },
                        grid: {
                            padding: {
                                top: -20,
                                right: 0,
                                left: -4,
                                bottom: -4
                            },
                            strokeDashArray: 4,
                            xaxis: {
                                lines: {
                                    show: true
                                }
                            },
                        },
                        xaxis: {
                            labels: {
                                padding: 0,
                            },
                            tooltip: {
                                enabled: false
                            },
                            axisBorder: {
                                show: false,
                            },
                            type: 'datetime',
                        },
                        yaxis: {
                            labels: {
                                padding: 4
                            },
                        },
                        labels: [
                            '2020-06-20', '2020-06-21', '2020-06-22', '2020-06-23', '2020-06-24', '2020-06-25', '2020-06-26', '2020-06-27', '2020-06-28', '2020-06-29', '2020-06-30', '2020-07-01', '2020-07-02', '2020-07-03', '2020-07-04', '2020-07-05', '2020-07-06', '2020-07-07', '2020-07-08', '2020-07-09', '2020-07-10', '2020-07-11', '2020-07-12', '2020-07-13', '2020-07-14', '2020-07-15', '2020-07-16', '2020-07-17', '2020-07-18', '2020-07-19', '2020-07-20', '2020-07-21', '2020-07-22', '2020-07-23', '2020-07-24', '2020-07-25', '2020-07-26'
                        ],
                        colors: [tabler.getColor("primary"), tabler.getColor("primary", 0.8), tabler.getColor("green", 0.8)],
                        legend: {
                            show: false,
                        },
                    })).render();
                });
                // @formatter:on
            </script>

            <div class="col-lg-6">
                <div class="card">
                    <div class="card-body">
                        <div id="chart" class="chart-lg"></div>
                    </div>
                </div>
            </div>

        </div>
    </div>


    <script>
        document.addEventListener("DOMContentLoaded", function () {
            window.ApexCharts && (new ApexCharts(document.getElementById('chart'), {
                chart: {
                    type: 'area'
                },
                series: [{
                    name: 'sales',
                    data: [30,40,45,50,49,60,70,91,125,3,4,5,6,4,3,2,3,4]
                },{
                    name: 'something',
                    data: [45,77,45,6,54,54,88,6,88,2,3,44,5,44,3,2,2,22]
                }],
                xaxis: {
                    categories: [1991,1992,1993,1994,1995,1996,1997, 1998,1999,2000,2001,2002,2003,2004,2005,2006, 2022,2025],
                    type:"year"
                }
                , colors: [tabler.getColor("primary"),tabler.getColor("red")]
                , legend: {
                }
            })).render();
        });
    </script>
</div>




<%@include file="../1-basic_setup/footer.jsp"%>