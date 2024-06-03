<%@ page import="java.time.LocalDate" %><%--
  Created by IntelliJ IDEA.
  User: priscafehiarisoadama
  Date: 18/04/2024
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../1-basic_setup/head.jsp"%>

<div class="page page-center">
    <div class="container container-tight py-4">

        <div class="card card-md col-lg-4 offset-md-4">
            <div class="card-body">
                <%--       logo --%>
                <%@include file="../1-basic_setup/logo/logo_simple.jsp"%>
                <%--        end logo--%>
                <h2 class="h2 text-center mb-4">Register a new account</h2>

                <%--                login form starts here ;) --%>
                <form:form action="/register" method="post" autocomplete="off" >


                    <div class="mb-3">
                        <label class="form-label">username</label>
                        <input type="text" name="username" class="form-control <c:if test="${email!=null}" >is-invalid</c:if>" placeholder="username" autocomplete="off">
                        <div class="invalid-feedback"><c:if test="${email!=null}" ><c:out value="${email}"></c:out></c:if></div>
                    </div>

                    <div class="mb-2">
                        <label class="form-label">
                            Password
                        </label>
                        <div class="input-group input-group-flat">
                            <input type="password" name="password" class="form-control <c:if test="${password!=null}" >is-invalid</c:if>"  placeholder="Your password"  autocomplete="off">
                            <span class="input-group-text">
                                <a href="#" class="link-secondary" title="Show password" data-bs-toggle="tooltip"><!-- Download SVG icon from http://tabler-icons.io/i/eye -->
                                  <i class="icon ti ti-eye"></i>
                                </a>
                            </span>
                            <div class="invalid-feedback"><c:if test="${password!=null}" ><c:out value="${password}"></c:out></c:if></div>

                        </div>
                    </div>
                    <div class="mb-2">
                        <label class="form-label">
                            Confirmation
                        </label>
                        <div class="input-group input-group-flat">
                            <input type="password" name="confirmPassword" class="form-control <c:if test="${password!=null}" >is-invalid</c:if>"  placeholder="confirm your password"  autocomplete="off">
                            <span class="input-group-text">
                                <a href="#" class="link-secondary" title="Show password" data-bs-toggle="tooltip"><!-- Download SVG icon from http://tabler-icons.io/i/eye -->
                                  <i class="icon ti ti-eye"></i>
                                </a>
                            </span>
                            <div class="invalid-feedback"><c:if test="${passeord!=null}" ><c:out value="${password}"></c:out></c:if></div>

                        </div>
                    </div>

                    <div class="form-footer">
                        <button type="submit" class="btn btn-primary w-100">Sign in</button>
                    </div>
                </form:form>
            </div>

        </div>
        <div class="text-center text-muted mt-3">
            Don't have account yet? <a href="${pageContext.request.contextPath}/register" tabindex="-1">Sign up</a>
        </div>
    </div>
</div>


<%@include file="../1-basic_setup/footer.jsp"%>
