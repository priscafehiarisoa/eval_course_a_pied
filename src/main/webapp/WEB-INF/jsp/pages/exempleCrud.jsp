<%@ page import="ev.eval.entity.cinepax.Film" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.springframework.data.domain.Page" %>
<%@ page import="ev.eval.model.Pagination" %>
<%@ page import="ev.eval.entity.cinepax.Categorie_Film" %><%--
  Created by IntelliJ IDEA.
  User: priscafehiarisoadama
  Date: 01/05/2024
  Time: 01:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String basicSetup="../1-basic_setup";%>
<%@include file="../1-basic_setup/head.jsp"%>
<%@include file="../1-basic_setup/side_menu.jsp"%>
<%@include file="../1-basic_setup/pageHeader.jsp"%>

<%
    String createLabel= "create Film";
    // setup pagination
    Pagination pagination= (Pagination) request.getAttribute("pagination");
    //setup page title
    String pageTitle= (String) request.getAttribute("pageTitle");
    // setup page redirection
    String pageRedirection= (String) request.getAttribute("pageredirection");
    // setup create controller
    String createController=(String) request.getAttribute("createController");
    // setup modify controller
    String modifyController=(String) request.getAttribute("modifyController");

    // get the list of string
    Page<Film> films= null;
    if(request.getAttribute("listObject")!=null)
        films= (Page<Film>) request.getAttribute("listObject");
    List<Categorie_Film> categories=new ArrayList<>();
    if(request.getAttribute("categorie")!=null)
        categories= (List<Categorie_Film>) request.getAttribute("categorie");
%>

<div class="page-wrapper">
    <div class="page-body m-5">

        <%if(request.getAttribute("showSuccessAlert")!=null){%>
<%--        success Alert --%>
    <div class="alert alert-success" role="alert">
        <h4 class="alert-title">Success!</h4>
        <div class="text-secondary"><%=request.getAttribute("showSuccessAlert")%></div>
    </div>
        <%} else if(request.getAttribute("showDangerAlert")!=null){
<%--        danger Alert --%>
    <div class="alert alert-danger" role="alert">
        <h4 class="alert-title">Something went wrong</h4>
        <div class="text-danger"><%=request.getAttribute("showDangerAlert")%></div>
    </div>
        <%}%>


<%-- main content --%>
        <%--            header --%>
        <!-- Page header -->
        <div class="page-header d-print-none mb-5">
            <div class="container-xl">
                <div class="row g-2 align-items-center">
                    <div class="col">
                        <!-- Page pre-title -->
                        <h2 class="page-title">
                            <%=pageTitle%>
                        </h2>
                    </div>
                    <!-- Page title actions -->
                    <div class="col-auto ms-auto d-print-none">
                        <div class="btn-list">
                            <a href="#" class="btn btn-primary d-none d-sm-inline-block" data-bs-toggle="modal" data-bs-target="#modal-report">
                                <i class="ti ti-plus icon"></i>
                                <%= createLabel%>
                            </a>
                            <a href="#" class="btn btn-primary d-sm-none btn-icon" data-bs-toggle="modal" data-bs-target="#modal-report" aria-label="Create new report">
                                <i class="icon ti ti-plus"></i>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%--            end header--%>

        <%--           create modals --%>
        <div class="modal modal-blur fade" id="modal-report" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">New <%=pageTitle%></h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <form action="/cinepax/film" method="post"></form>
                    <div class="modal-body">
                        <%--                            film name --%>
                        <div class="mb-3">
                            <label class="form-label">Film name</label>
                            <input type="text" class="form-control" name="nomFilm" required >
                        </div>

                            <%--                            film duration --%>
                            <div class="mb-3">
                                <label class="form-label">Film duration</label>
                                <input type="time" class="form-control" name="duree" required >
                            </div>
                            <%--             synopsys       --%>
                            <div class="col-lg-12">
                                <div>
                                    <label class="form-label">Synopsys</label>
                                    <textarea class="form-control" rows="3"></textarea>
                                </div>
                            </div>
<%--                                        categorie      --%>
                            <div class="col-lg-4">
                                <div class="mb-3">
                                    <label class="form-label">Categorie</label>
                                    <select class="form-select">
                                        <%for(Categorie_Film categ:categories){%>
                                        <option value=<%=categ.getId()%> ><%=categ.getNom_categorie()%></option>
                                        <%}%>
                                    </select>
                                </div>
                            </div>

                    </div>
                    <div class="modal-footer">
                        <a href="#" class="btn btn-link link-secondary" data-bs-dismiss="modal">
                            Cancel
                        </a>
                        <a href="#" class="btn btn-primary ms-auto" data-bs-dismiss="modal">
                            <i class="icon ti ti-plus"></i>
                            <%= createLabel%>
                        </a>
                    </div>
                </div>
            </div>
        </div>


        <div class="card">
            <div class="card-header">
                <h3 class="card-title">List</h3>
            </div>
            <div class="card-body border-bottom py-3">
                <div class="d-flex">
                    <div class="text-muted">
                        Show
                        <div class="mx-2 d-inline-block">
                            <input type="text" class="form-control form-control-sm" value="8" size="3" aria-label="Invoices count">
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

                        <th><button class="table-sort" data-sort="sort-name">Nom Film</button></th>
                        <th><button class="table-sort" data-sort="sort-city">Durrée</button></th>
                        <th><button class="table-sort" data-sort="sort-type">Synopsys</button></th>
                        <th><button class="table-sort" data-sort="sort-score">Categorie</button></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody class="table-tbody">
                    <% for (Film film:films) {%>

                    <tr>
                        <td class="sort-name"><%=film.getNomFilm()%></td>
                        <td class="sort-city"><%=film.getDurree()%></td>
                        <td class="sort-type"><%=film.getSynopsys()%></td>
                        <td class="sort-score"><%=film.getCategorieFilm().getNom_categorie()%></td>

                        <td class="text-end">
                            <button class="btn btn-secondary"><i class=" ti ti-edit"></i></button>
                            <button class="btn btn-danger"><i class=" ti ti-trash"></i></button>
                        </td>
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

<%--script--%>
<!-- Libs JS -->
<script src="../../../public/libs/list.js/dist/list.min.js?1684106062" defer></script>
<!-- Tabler Core -->
<script src="../../../public/js/tabler.min.js?1684106062" defer></script>
<script src="../../../public/js/demo.min.js?1684106062" defer></script>
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

<%@include file="../1-basic_setup/footer.jsp"%>
