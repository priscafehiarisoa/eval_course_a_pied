
<%@ page import="org.springframework.data.domain.Page" %>
<%@ page import="ev.eval_course_a_pied.model.Pagination" %>
<%@ page import="ev.eval_course_a_pied.entity.Etape" %>
<%@ page import="java.util.List" %><%--
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
    List<Object[]> liste= (List<Object[]>) request.getAttribute("listEquipes");
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
            <div class="card-header">
                <h3 class="card-title">List</h3>
            </div>
            <div class="card-body border-bottom py-3">
                <div class="d-flex">
                    <div class="text-muted">
                        Show
                        <div class="mx-2 d-inline-block">

                        </div>
                        entries
                    </div>
                    <div class="ms-auto text-muted">
                        Search:
                        <div class="ms-2 d-inline-block">
                            <input type="text" class="form-control form-control-sm" aria-label="Search invoice">
                        </div>
                    </div>
                </div>
            </div>
            <div class="table-responsive">
                <table id="table-default" class="table card-table table-vcenter text-nowrap datatable">

                    <thead>
                    <tr>

                        <th><button class="table-sort" data-sort="sort-name">Equipe</button></th>
                        <th><button class="table-sort" data-sort="sort-name">Etape</button></th>
                        <th><button class="table-sort" data-sort="sort-city">somme des points </button></th>

                    </tr>
                    </thead>
                    <tbody class="table-tbody">
                    <%
                        int i=0;
                        for (Object[] etape:liste) { i++;%>

                    <tr>
                        <td class="sort-name"><%=etape[1]%></td>
                        <td class="sort-name"><%=etape[2]%> </td>
                        <td class="sort-name"><%=etape[0]%></td>

                        <%--           pay modals --%>


                    </tr>

                    <%}%>
                    </tbody>
                </table>
            </div>

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