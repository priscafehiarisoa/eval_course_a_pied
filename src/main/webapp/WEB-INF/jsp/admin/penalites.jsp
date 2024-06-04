
<%@ page import="org.springframework.data.domain.Page" %>
<%@ page import="ev.eval_course_a_pied.model.Pagination" %>
<%@ page import="ev.eval_course_a_pied.entity.Etape" %>
<%@ page import="ev.eval_course_a_pied.entity.Penalite" %>
<%@ page import="ev.eval_course_a_pied.entity.Equipe" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %><%--
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
    Page<Penalite> etapeListe= null;
    if(request.getAttribute("listObject")!=null) {
        etapeListe= (Page<Penalite>) request.getAttribute("listObject");
    }
    String pageRedirection= (String) request.getAttribute("pageRedirection");
    List<Equipe> equipeList= (List<Equipe>) request.getAttribute("equipe");
    List<Etape> etapes = (List<Etape>) request.getAttribute("etape");
   HashMap<String,String> errors = (HashMap<String,String>)request.getAttribute("formError");
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

        <div class="card mb-5">
            <div class="card-header">
                <div class="card-title">
                    Ajouter une pénalité
                </div>
            </div>
            <div class="card-body">
                <form:form action="/admin/penalites" method="post">
                    <div class="row">
                        <div class="mb-3 col-4">
                            <label class="form-label">Equipes</label>
                            <select type="text" class="form-select" required id="equipe" value="" name="idEquipe">
                                <%for (int i = 0; i < equipeList.size(); i++) { %>
                                <option value="<%=equipeList.get(i).getId()%>"><%=equipeList.get(i).getNomEquipe()%></option>
                                <% }%>
                            </select>
                        </div>
                        <div class="mb-3 col-4">
                            <label class="form-label">Etapes</label>
                            <select type="text" class="form-select" required id="etape" value="" name="idEtape">
                                <%for (int i = 0; i < etapes.size(); i++) { %>
                                <option value="<%=etapes.get(i).getId()%>"><%=etapes.get(i).getLieu()%></option>
                                <% }%>
                            </select>
                        </div>
                        <div class="mb-3 col-4">
                            <label for="" class="form-label">Pénalités à ajouter </label>
                            <input type="time" step="1" required name="penalites" id="" class="form-control">
                        </div>

                        <div class="mb-3">

                            <button type="submit" class="btn btn-primary ">Ajouter</button>
                        </div>
                    </div>
                </form:form>
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

                        <th><button class="table-sort" data-sort="sort-name">Etape</button></th>
                        <th><button class="table-sort" data-sort="sort-name">Equipe</button></th>
                        <th><button class="table-sort" data-sort="sort-city">Penalites </button></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody class="table-tbody">
                    <%
                        int i=0;
                        for (Penalite etape:etapeListe) { i++;%>

                    <tr>
                        <td class="sort-name"><%=etape.getEtape().getLieu()%></td>
                        <td class="sort-name"><%=etape.getEquipe().getNomEquipe()%> </td>
                        <td class="sort-name"><%=etape.getPenalites()%></td>
                        <td class="sort-score">
                            <form:form action="/admin/deletePenalite" method="post">
                                <input type="hidden" value="<%=etape.getId()%>" name="id">
                                <a href="#" class="btn btn-danger " data-bs-toggle="modal" data-bs-target="#modal-small">
                                    <span class="ti ti-trash"></span>
                                </a>
                                <div class="modal modal-blur fade" id="modal-small" tabindex="-1" role="dialog" aria-hidden="true">
                                    <div class="modal-dialog modal-sm modal-dialog-centered" role="document">
                                        <div class="modal-content">
                                            <div class="modal-body">
                                                <div class="modal-title">Are you sure?</div>
                                                <p class="text-wrap ">Voulez vous vraiment effacer la pénalité pour l'équipe <%=etape.getEquipe().getNomEquipe()%> ? </p>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-link link-secondary me-auto" data-bs-dismiss="modal">Non</button>
                                                <button type="submit" class="btn btn-danger" data-bs-dismiss="modal">Oui</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form:form>
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

<script>
    // @formatter:off
    document.addEventListener("DOMContentLoaded", function () {
        var el;
        window.TomSelect && (new TomSelect(el = document.getElementById('equipe'), {
            copyClassesToDropdown: false,
            dropdownParent: 'body',
            controlInput: '<input>',
            render:{
                item: function(data,escape) {
                    if( data.customProperties ){
                        return '<div><span class="dropdown-item-indicator">' + data.customProperties + '</span>' + escape(data.text) + '</div>';
                    }
                    return '<div>' + escape(data.text) + '</div>';
                },
                option: function(data,escape){
                    if( data.customProperties ){
                        return '<div><span class="dropdown-item-indicator">' + data.customProperties + '</span>' + escape(data.text) + '</div>';
                    }
                    return '<div>' + escape(data.text) + '</div>';
                },
            },
        }));
    });
    // @formatter:on
</script>

<script>
    // @formatter:off
    document.addEventListener("DOMContentLoaded", function () {
        var el;
        window.TomSelect && (new TomSelect(el = document.getElementById('etape'), {
            copyClassesToDropdown: false,
            dropdownParent: 'body',
            controlInput: '<input>',
            render:{
                item: function(data,escape) {
                    if( data.customProperties ){
                        return '<div><span class="dropdown-item-indicator">' + data.customProperties + '</span>' + escape(data.text) + '</div>';
                    }
                    return '<div>' + escape(data.text) + '</div>';
                },
                option: function(data,escape){
                    if( data.customProperties ){
                        return '<div><span class="dropdown-item-indicator">' + data.customProperties + '</span>' + escape(data.text) + '</div>';
                    }
                    return '<div>' + escape(data.text) + '</div>';
                },
            },
        }));
    });
    // @formatter:on
</script>