<%@ page import="java.util.List" %>
<%@ page import="ev.eval_course_a_pied.entity.CoureurEtape" %>
<%@ page import="ev.eval_course_a_pied.entity.Coureur" %>
<%@ page import="java.util.HashMap" %>
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
    List<Coureur> coureurs = (List<Coureur>)request.getAttribute("coureurs");
    HashMap<String,String> formError= (HashMap<String, String>) request.getAttribute("formError");
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
        <form:form>

        <div class="card">
            <div class="card-body">
                <input type="hidden" name="idEtape" value="<%=request.getAttribute("idEtape")%>">
                <div class="mb-3">
                    <label class="form-label">Liste des Coureurs</label>
                    <select type="text" class="form-select <% if(formError.get("coureur")!=null){ out.print("is-invalid");} %>" id="select-users" value="" name="idCoureur">
                        <% for (int i = 0; i < coureurs.size(); i++) {%>
                        <option value=<%=coureurs.get(i).getId()%>>N* : <%=coureurs.get(i).getNumeroDeDossard()%> , Nom: <%=coureurs.get(i).getNomCoureur()%></option>
                        <% }%>
                    </select>
                    <div class="invalid-feedback"><%=formError.get("coureur")%></div>

                </div>
                <div class="mb-3">
                    <label for="date" class="form-label">Heure départ</label>
                    <input type="datetime-local" step="1" disabled  class="form-control <% if(formError.get("date")!=null){ out.print("is-invalid");} %>" id="date" value="${etape.heureDepart}" name="heureDepart">
                    <div class="invalid-feedback"><%=formError.get("date")%></div>

                </div>
                <div class="mb-3">
                    <label for="date2" class="form-label">Heure Arrivée</label>
                    <input type="datetime-local" step="1" class="form-control <% if(formError.get("date")!=null){ out.print("is-invalid");} %>" id="date2" name="heureArrive">
                    <div class="invalid-feedback"><%=formError.get("date")%></div>

                </div>


                <div class="mb-3">
                    <input type="submit" class="btn btn-primary" value="valider">
                </div>
            </div>
        </div>


    </div>
    </form:form>

    <%-- end main content --%>
<script>
    // @formatter:off
    document.addEventListener("DOMContentLoaded", function () {
        var el;
        window.TomSelect && (new TomSelect(el = document.getElementById('select-users'), {
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

</div>
</div>

<%@include file="../1-basic_setup/footer.jsp"%>
