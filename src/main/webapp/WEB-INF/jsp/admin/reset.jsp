<%--
  Created by IntelliJ IDEA.
  User: priscafehiarisoadama
  Date: 13/05/2024
  Time: 00:21
  To change this template use File | Settings | File Templates.
--%><%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                            <c:out value="${pageTitle}"></c:out>
                        </h2>
                    </div>
                </div>
            </div>
        </div>
        <%-- main content --%>
        <form:form action="${action}" method="post">
            <input type="submit" value="RESET DATABASE" class="btn btn-danger">
        </form:form>
        <%-- end main content --%>
    </div>
</div>

<%@include file="../1-basic_setup/footer.jsp"%>
