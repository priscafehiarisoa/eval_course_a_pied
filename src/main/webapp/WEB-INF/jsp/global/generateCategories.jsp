
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
      <div class="card-body">
        <form:form method="post">
          <button type="submit" class="btn btn-github"> generate categories</button>
        </form:form>
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