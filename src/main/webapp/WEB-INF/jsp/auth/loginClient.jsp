<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../1-basic_setup/head.jsp"%>

<div class="page page-center">
    <div class="container container-tight py-4">

        <div class="card card-md col-lg-4 offset-lg-4">
            <div class="card-body">
                <%--       logo --%>
                <%@include file="../1-basic_setup/logo/logo_simple.jsp"%>
                <%--        end logo--%>
                <h2 class="h2 text-center mb-4">Login to An "EQUIPE" account</h2>

                <%--                login form starts here ;) --%>
                <form action="${pageContext.request.contextPath}/login" method="post">

                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

                    <%--                    show errors--%>
                    <%--                    <c:if test="${param.error}">--%>
                    <%if (request.getParameter("error")!=null){%>
                    <p class="text-center text-danger">
                        <strong>Wrong email or Password</strong>
                    </p>
                    <% }%>

                    <%--                    </c:if>--%>
                    <div class="mb-3">
                        <label class="form-label">Email address</label>
                        <input type="text" name="username" class="form-control <% if(request.getParameter("error")!=null){%> is-invalid <%}%>" value="equipe2" placeholder="your@email.com" autocomplete="off">
                    </div>
                    <div class="mb-2">
                        <label class="form-label">
                            Password
                            <span class="form-label-description">

                                <%--                                here to configure forgotten password--%>
                            </span>
                        </label>
                        <div class="input-group input-group-flat">
                            <input type="password" name="password" id="password" value="equipe2" class="form-control <% if(request.getParameter("error")!=null){%> is-invalid <%}%>"  placeholder="Your password"  autocomplete="off">
                            <span class="input-group-text">
                    <a href="#" class="link-secondary" title="Show password" data-bs-toggle="tooltip"><!-- Download SVG icon from http://tabler-icons.io/i/eye -->
                      <svg xmlns="http://www.w3.org/2000/svg" class="icon" width="24" height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M10 12a2 2 0 1 0 4 0a2 2 0 0 0 -4 0" /><path d="M21 12c-2.4 4 -5.4 6 -9 6c-3.6 0 -6.6 -2 -9 -6c2.4 -4 5.4 -6 9 -6c3.6 0 6.6 2 9 6" /></svg>
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
            Don't have account yet? <a href="${pageContext.request.contextPath}/register" tabindex="-1">Sign up</a>
        </div>

    </div>
</div>


<%@include file="../1-basic_setup/footer.jsp"%>
