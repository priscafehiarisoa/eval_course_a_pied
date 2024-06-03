<%--
  Created by IntelliJ IDEA.
  User: priscafehiarisoadama
  Date: 03/05/2024
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String basicSetup="../1-basic_setup";%>
<%@include file="../1-basic_setup/head.jsp"%>
<%@include file="../1-basic_setup/side_menu.jsp"%>
<%@include file="../1-basic_setup/pageHeader.jsp"%>
<%
    UserModel userModel= (UserModel) request.getAttribute("user");
    String pageTitle= (String) request.getAttribute("pageTitle");
%>

<div class="page-wrapper">
    <div class="page-body m-5">
        <%-- main content --%>
<%--        page header--%>
            <div class="page-header d-print-none mb-5">
                <div class="container-xl">
                    <div class="row g-2 align-items-center">
                        <div class="col">
                            <!-- Page pre-title -->
                            <div class="row">
                                <div class="col-lg-1 ">
                                    <span class=" avatar avatar-xl mx-3"> <i class="ti ti-user"></i></span>
                                </div>
                                <div class="col-lg-5 mx-5">
                                    <div class="page-title"><%=userModel.getNomPrenom()%> <a href="/user/userProfile?modifier" class="btn btn-ghost-secondary mx-3 ti ti-edit-circle"></a></div>

                                    <div class="mb-2">
                                        <i class="icon ti ti-home"></i> <span>Lives at :</span> <strong><%=userModel.getAdresse()%></strong>
                                    </div>
                                    <div class="mb-2">
                                        <i class="icon ti ti-phone"></i> <span>Phone : </span> <strong><%=userModel.getTelephone()%></strong>
                                    </div>
                                    <div class="mb-2">
                                        <i class="icon ti ti-mail"></i> <span>email : </span> <strong><%=userModel.getUsername()%></strong>
                                    </div>
                                    <div class="mb-2">
                                        <i class="icon ti ti-mail"></i> <span>profile : </span> <strong><%=userModel.getRoles()%></strong>
                                    </div>

                                </div>
                            </div>


                        </div>

                    </div>
                </div>
            </div>
<%--       end  page header--%>


        <%-- end main content --%>
    </div>
</div>

<%@include file="../1-basic_setup/footer.jsp"%>
