<%--
  Created by IntelliJ IDEA.
  User: priscafehiarisoadama
  Date: 15/05/2024
  Time: 01:57
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
                            ${pageTitle}
                        </h2>
                    </div>
                </div>
            </div>
        </div>
        <%-- main content --%>
        <div class="card">
            <div class="card-body">
                <form:form action="/admin/importPoints" method="post" enctype="multipart/form-data">

                    <div class="m-3">
                        <label for="devis">import points</label>
                        <input type="file" name="points" class="form-control <c:if test="${error!=null}">is-invalid</c:if><c:if test="${success!=null}">is-valid</c:if>  " id="devis">
                        <div class="invalid-feedback">${error}</div>
                        <div class="valid-feedback">${success}</div>

                    </div>
                    <div class="m-3">
                        <input type="submit" value="valider" class="btn btn-primary">
                    </div>
                </form:form>
            </div>
        </div>
        <%-- end main content --%>
    </div>
</div>

<%@include file="../1-basic_setup/footer.jsp"%>
