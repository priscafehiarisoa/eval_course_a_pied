<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../1-basic_setup/head.jsp"%>

<div class="page page-center">
    <div class="container container-tight py-4">
        <div class="row align-items-center g-4">

           <div class="col-12">
               <div class="card card-md col-8 col-lg-4 offset-lg-4">
                   <div class="card-body">
                       <%--       logo --%>
                       <%@include file="../1-basic_setup/logo/logo_simple.jsp"%>
                       <%--        end logo--%>
                       <h2 class="h2 text-center mb-4">Login to your Admin account</h2>

                       <%--                login form starts here ;) --%>
                       <form action="${pageContext.request.contextPath}/login" method="post">

                           <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                           <input type="hidden" name="admin" value="admin" />

                           <%--                    show errors--%>
                           <%--                    <c:if test="${param.error}">--%>
                           <%if (request.getParameter("error")!=null){%>
                           <p class="text-center text-danger">
                               <strong>Wrong email or Password</strong>
                           </p>
                           <% }%>

                           <%--                    </c:if>--%>
                           <div class="mb-3">
                               <label class="form-label">username</label>
                               <input type="text" name="username" class="form-control <% if(request.getParameter("error")!=null){%> is-invalid <%}%>" value="prisca@gmail.com" placeholder="" autocomplete="off">
                           </div>
                           <div class="mb-2">
                               <label class="form-label">
                                   Password
                                   <span class="form-label-description">

                                       <%--                                here to configure forgotten password--%>
                                   </span>
                               </label>
                               <div class="input-group input-group-flat">
                                   <input type="password" name="password" id="password" value="prisca" class="form-control <% if(request.getParameter("error")!=null){%> is-invalid <%}%>"  placeholder="Your password"  autocomplete="off">
                                   <span class="input-group-text">
                        <a href="#" class="link-secondary " title="Show password" data-bs-toggle="tooltip"><!-- Download SVG icon from http://tabler-icons.io/i/eye -->
                            <i class="ti ti-eye icon toggle-password"></i>
                        </a>
                      </span>
                               </div>
                           </div>
                           <div class="form-footer">
                               <button type="submit" class="btn btn-primary w-100">Sign in</button>
                           </div>
                       </form>
                   </div>

               </div>
               <div class="text-center text-muted mt-3">
                   Don't have account yet? <a href="${pageContext.request.contextPath}/registerAdmin" tabindex="-1">Sign up</a>
               </div>
           </div>
<%--            <div class="col-3 " style=" width:20%; height:100%">--%>
<%--                <img src="../../../public/img/runner.jpg"  class="d-block mx-auto" alt="">--%>
<%--            </div>--%>
        </div>
        <script>
            document.addEventListener('DOMContentLoaded', function() {
                const togglePassword = document.querySelectorAll('.toggle-password');
                togglePassword.forEach(toggle => {
                    toggle.addEventListener('click', function(event) {
                        const passwordInput = document.getElementById('password');
                        if (passwordInput.type === 'password') {
                            passwordInput.type = 'text';
                        } else {
                            passwordInput.type = 'password';
                        }
                    });
                });
            });
        </script>
    </div>

</div>


<%@include file="../1-basic_setup/footer.jsp"%>
