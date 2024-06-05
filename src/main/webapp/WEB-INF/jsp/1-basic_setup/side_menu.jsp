<%--
  Created by IntelliJ IDEA.
  User: priscafehiarisoadama
  Date: 18/04/2024
  Time: 06:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.userdetails.UserDetails" %>
<%@ page import="ev.eval_course_a_pied.entity.user.UserModel" %>
<%@ page import="ev.eval_course_a_pied.services.auth.UserService" %>
<%@ page import="ev.eval_course_a_pied.utils.Menu" %>
<%
    UserModel usermodel2= UserService.getAuthenticatedUser();
        try{
            usermodel2.setMenuList();
        }catch (Exception e){}

%>
    <div class="page">
        <!-- Sidebar -->
<%--        head sidebar --%>
        <aside class="navbar navbar-vertical navbar-expand-lg" data-bs-theme="dark">
            <div class="container-fluid">
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#sidebar-menu" aria-controls="sidebar-menu" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <h1 class="navbar-brand navbar-brand-autodark">
                    <%--       logo --%>
                        <div class=" mb-4 mx-auto" id="fehy-dark" style="width: 25%; height: 25%">
                            <img src="../../../../public/logo/fehy-high-resolution-logo-white-transparent.png" alt="brand Logo">
                        </div>                    <%--        end logo--%>
                </h1>

<%--            div Menu --%>
            <div class="collapse navbar-collapse" id="sidebar-menu">
                <ul class="navbar-nav pt-lg-3">
<%--                    ceci caracterise un menu --%>
                    <%
                        assert usermodel2 != null;
                        for (Menu menu: usermodel2.getMenuList()){%>
                    <li class="nav-item mb-3">
                        <a class="nav-link" href="<%=menu.getMenuPath()%>" >
                          <span class="nav-link-icon d-md-none d-lg-inline-block">
                            <i class="ti ti-<%=menu.getIcon()%>" style="font-size: 20px"></i>
                          </span>
                          <span class="nav-link-title">
                            <%=menu.getMenuName().toUpperCase()%>
                          </span>
                        </a>
                    </li>
                    <%}%>


                </ul>
                <i class="ti ti-movie"></i>
            </div>

            </div>
        </aside>

