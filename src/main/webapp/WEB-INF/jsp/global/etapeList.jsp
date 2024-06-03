
<%@ page import="org.springframework.data.domain.Page" %>
<%@ page import="ev.eval_course_a_pied.model.Pagination" %>
<%@ page import="ev.eval_course_a_pied.entity.Etape" %><%--
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
    Pagination pagination= (Pagination) request.getAttribute("pagination");
    Page<Etape> etapeListe= null;
    if(request.getAttribute("listObject")!=null) {
        etapeListe= (Page<Etape>) request.getAttribute("listObject");
    }
    String pageRedirection= (String) request.getAttribute("pageRedirection");
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
                            <form action="<%=pageRedirection%>">
                                <input type="text" class="form-control form-control-sm" name="pageSize" value="<%=pagination.getPageSize()%>" size="3" aria-label="<c:out value="${pageTitle}"></c:out> count">
                                <%--                                <input type="submit" value="valider">--%>
                            </form>
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

                        <th><button class="table-sort" data-sort="sort-name">Rang Etape</button></th>
                        <th><button class="table-sort" data-sort="sort-name">longueur</button></th>
                        <th><button class="table-sort" data-sort="sort-city">lieu </button></th>
                        <th><button class="table-sort" data-sort="sort-type">heure de depart</button></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody class="table-tbody">
                    <%
                        int i=0;
                        for (Etape etape:etapeListe) { i++;%>

                    <tr>
                        <td class="sort-name"><%=etape.getRangEtape()%></td>
                        <td class="sort-name"><%=etape.getLongueurFormated()%> km</td>
                        <td class="sort-name"><%=etape.getLieu()%></td>
                        <td class="sort-name"><%=etape.getHeureDepart()%></td>
                        <td class="sort-score">
                            <c:if test="${not empty pageContext.request.userPrincipal.authorities}">
                                <c:forEach var="authority" items="${pageContext.request.userPrincipal.authorities}">
                                    <c:if test="${ authority.authority == 'ADMIN'}">
                                        <a href="/admin/tempsCoureurParEtape?id=<%=etape.getId()%>" class="btn btn-primary">Ajouter le temps par coureur</a>
                                    </c:if>
                                    <c:if test="${ authority.authority == 'EQUIPE'}">
                                        <a href="/equipe/coureurParEtape?id=<%=etape.getId()%>" class="btn btn-primary">Ajouter un coureur par etape</a>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </td>

                        <%--           pay modals --%>


                    </tr>

                    <%}%>
                    </tbody>
                </table>
            </div>
            <div class="card-footer d-flex align-items-center">
                <p class="m-0 text-muted">Showing <span><%=pagination.getPageNumber()+1%></span> to <span><%=pagination.getPageSize()%></span> of <span><%=pagination.getTotalPages()%></span> entries</p>
                <ul class="pagination m-0 ms-auto">
                    <li class="page-item <%= (!pagination.isHasPrevious()) ? "disabled" : ""%>">
                        <a class="page-link" href="${pageContext.request.contextPath}<%=pageRedirection%>?pageNumber=<%=pagination.getPageNumber()-1%>&pageSize=<%=pagination.getPageSize()%>" tabindex="-1" aria-disabled="true">
                            <i class="icon ti ti-chevron-left"></i>
                            prev
                        </a>
                    </li>
                    <li class="page-item "><span class="page-link"><%=pagination.getPageNumber()+1%></span></li>
                    <li class="page-item <%= (!pagination.isHasNext()) ? "disabled" : ""%>">
                        <a class="page-link "    href="${pageContext.request.contextPath}<%=pageRedirection%>?pageNumber=<%=pagination.getPageNumber()+1%>&pageSize=<%=pagination.getPageSize()%>">
                            next
                            <i class="icon ti ti-chevron-right"></i>

                        </a>
                    </li>
                </ul>
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