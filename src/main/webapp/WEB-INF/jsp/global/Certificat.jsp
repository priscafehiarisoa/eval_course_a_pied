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
<%@include file="../1-basic_setup/head.jsp"%>


<!-- Sidebar -->
<%@include file="../1-basic_setup/side_menu.jsp"%>
<%@include file="../1-basic_setup/pageHeader.jsp"%>



<%-- end sidebar --%>
<div class="page-wrapper">
    <div class="page-body m-5 ">
        <!-- Page header -->

        <%--    main content --%>
        <div class="card" id="card">
            <div class="card-body text-center bg-dark p-6 text-white "
                 style="
                 background-image: url('../../../public/img/winner-png-25161.png');
                    background-position: right top;
                    background-repeat: no-repeat;
                    background-size: 10%;
                    background-position-x: 90%;
                    background-position-y: 20%;
                  ">
                <div style="
                height: 100%;
                border-radius: 10px;
                border: ghostwhite solid 5px;" class="p-5">
                    <div class="text-center mt-8">
                        <h1 style="font-size: 36px ; font-family: Papyrus">ULTIMATE TEAM RACE CERTIFICATE</h1>
                    </div>
                    <div class="text-center mt-2" style="font-family: Noteworthy">
                        <h1>is granted by these presents to:</h1>
                    </div>
                    <div class=" mt-4">
                        <p style="font-family: Papyrus ; font-size: 56px" >
                            EQUIPE ${equipe.nomEquipe}
                        </p>
                    </div>
                    <div style="font-family: Noteworthy">
                        <p>they have successfully won the race hold from : __/__/__ to __/__/__ </p>
                    </div>
                    <div style="font-family: Noteworthy">
                        <h1>Points : ${points} </h1>

                    </div>
                </div>
            </div>
        </div>
        <button class="mt-3 btn btn-primary" onclick="generatePDF()">print Pdf</button>
        <div class="card">
            <form action="/log" method="post">

                <button type="submit">submit crud</button>
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

<%@include file="../1-basic_setup/footer.jsp"%>

<script>
    function generatePDF() {
        var content = document.getElementById("card").innerHTML ;

        var opt = {
            margin:       0.5,
            filename:     'raceCertificate.pdf',
            image:        { type: 'jpeg', quality: 0.90 },
            html2canvas:  { scale: 2 },
            jsPDF:        { unit: 'in', format: 'a4', orientation: 'landscape' }
        };
        // Choose the element that our invoice is rendered in.
        // html2pdf().set(opt).from(content).save();
        html2pdf().set(opt).from(content).toPdf().get('pdf').then(function (pdf) {
            window.open(pdf.output('bloburl'), '_blank');
            window.location.href = "/classementGeneralParEquipe";
        });
    }
    window.onload = function() {
        generatePDF();
    };

</script>

<%-- end main content --%>





<%--end content --%>

