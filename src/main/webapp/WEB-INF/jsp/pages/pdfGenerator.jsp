<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../1-basic_setup/head.jsp"%>
<%@include file="../1-basic_setup/side_menu.jsp"%>
<%@include file="../1-basic_setup/pageHeader.jsp"%>

<%-- end sidebar --%>
<div class="page-wrapper">
    <div class="page-body m-5">
        <%--    main content --%>
        <div class="card" id="card">
            <div class="card-body">
                <h4>
                    Checked URL
                </h4>
                <div>
                    <pre><code>GET <a class="text-reset" target="_blank" href="https://preview.tabler.io">https://preview.tabler.io</a></code></pre>
                </div>
                <h4>Request Timing</h4>
                <div>
                    <pre>Effective URL&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="text-reset" target="_blank" href="https://preview.tabler.io">https://preview.tabler.io</a><br>Redirect count&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0<br>Name lookup time&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.4e-05<br>Connect time&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0.000521<br>Pre-transfer time&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0.0<br>Start-transfer time&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0.0<br>App connect time&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0.0<br>Redirect time&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0.0<br>Total time&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;28.000601<br>Response code&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0<br>Return keyword&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;operation_timedout</pre>
                </div>
                <h4>Response Headers</h4>
                <div>
                    <pre>HTTP/1.1 200 Connection established</pre>
                </div>
            </div>
            <div class="card-footer">
                <h4>Escalation</h4>
                <div>Entire team</div>
            </div>
        </div>
        <button class="mt-3 btn btn-primary" onclick="printContent()">print Pdf</button>

    </div>
</div>



<script>
    function generatePDF() {
        var content = document.getElementById("card").innerHTML;

        const page = 'Hello World!';
        var opt = {
            margin:       1,
            filename:     'Demopdf.pdf',
            image:        { type: 'jpeg', quality: 0.98 },
            html2canvas:  { scale: 2 },
            jsPDF:        { unit: 'in', format: 'letter', orientation: 'portrait' }
        };
        // Choose the element that our invoice is rendered in.
        html2pdf().set(opt).from(content).save();
    }

</script>

<%-- end main content --%>



</div>

<%--end content --%>

<%@include file="../1-basic_setup/footer.jsp"%>