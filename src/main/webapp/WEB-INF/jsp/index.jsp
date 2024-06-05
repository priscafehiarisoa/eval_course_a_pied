    <%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>--%>
<%--
  Created by IntelliJ IDEA.
  User: priscafehiarisoadama
  Date: 17/04/2024
  Time: 21:11
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="1-basic_setup/head.jsp"%>


    <!-- Sidebar -->
    <%@include file="1-basic_setup/side_menu.jsp"%>
    <%@include file="1-basic_setup/pageHeader.jsp"%>



    <%-- end sidebar --%>
<div class="page-wrapper">
<div class="page-body m-5 ">
    <!-- Page header -->
    <div class="page-header d-print-none mb-5">
        <div class="container-xl">
            <div class="row g-2 align-items-center">
                <div class="col">
                    <!-- Page pre-title -->
                    <h2 class="page-title">
                        Index
                    </h2>
                </div>
                <!-- Page title actions -->
                <div class="col-auto ms-auto d-print-none">
                    <div class="btn-list">
                        <a href="#" class="btn btn-primary d-none d-sm-inline-block" data-bs-toggle="modal" data-bs-target="#modal-report">
                           <i class="ti ti-plus icon"></i>
                            Create new report
                        </a>
                        <a href="#" class="btn btn-primary d-sm-none btn-icon" data-bs-toggle="modal" data-bs-target="#modal-report" aria-label="Create new report">
                            <i class="icon ti ti-plus"></i>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>

<%--    main content --%>
    <div class="card" id="card">
        <div class="card-body">
            <h4>
                Checked URL
                <i class="ti ti-3d-cube-sphere"></i>
            </h4>
            <div>
                <pre><code>GET <a class="text-reset" target="_blank" href="https://preview.tabler.io">https://preview.tabler.io</a></code></pre>
            </div>
            <h4>Request Timing</h4>
            <div>
                <pre>Effective URL&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="text-reset" target="_blank" href="https://preview.tabler.io">https://preview.tabler.io</a><br>Redirect count&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0<br>Name lookup time&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.4e-05<br>Connect time&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0.000521<br>Pre-transfer time&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0.0<br>Start-transfer time&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0.0<br>App connect time&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0.0<br>Redirect time&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0.0<br>Total time&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;28.000601<br>Response code&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0<br>Return keyword&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;operation_timedout</pre>
            </div>
            <h4 class="text-center">Response Headers</h4>
            <div>
                <pre>HTTP/1.1 200 Connection established</pre>
            </div>
        </div>
        <div class="card-footer">
            <h4>Escalation</h4>
            <div>Entire team</div>
        </div>
    </div>
    <button class="mt-3 btn btn-primary" onclick="generatePDF()">print Pdf</button>
    <div class="card">
        <form action="/">

            <button type="submit" class="ti ti-">submit crud</button>
        </form>
    </div>

    <div class="card">
        <div class="card-header">
            <h1 class="card-title">test authorities</h1>
        </div>
        <div class="card-body">
            <div>
                <c:if test="${not empty pageContext.request.userPrincipal.authorities}">
                    <c:forEach var="authority" items="${pageContext.request.userPrincipal.authorities}">
                        <c:if test="${authority.authority == 'CREATOR' || authority.authority == 'ADMIN'}">
                            <a href="/new">Create New Product</a>
                        </c:if>
                    </c:forEach>
                </c:if>

            </div>
        </div>
    </div>

</div>
</div>

<%@include file="1-basic_setup/footer.jsp"%>

    <script>
        function generatePDF() {
            var content = document.getElementById("card").innerHTML ;

            var opt = {
                margin:       1,
                filename:     'Demopdf.pdf',
                image:        { type: 'jpeg', quality: 0.98 },
                html2canvas:  { scale: 2 },
                jsPDF:        { unit: 'in', format: 'a4', orientation: 'landscape' }
            };
            // Choose the element that our invoice is rendered in.
            // html2pdf().set(opt).from(content).save();
            html2pdf().set(opt).from(content).toPdf().get('pdf').then(function (pdf) {
                window.open(pdf.output('bloburl'), '_blank');
            });
        }

    </script>

<%-- end main content --%>





<%--end content --%>

