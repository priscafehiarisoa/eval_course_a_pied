<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.userdetails.UserDetails" %>

<%
    String firstName="";
    UserModel usermodel= UserService.getAuthenticatedUser();
        firstName = usermodel.getUsername();
        request.setAttribute("menuItem",usermodel.getMenuList());

        try{
            usermodel.setMenuList();
        }catch (Exception e){

        }
        if(firstName==null)
            firstName="";

%>


<header class="navbar navbar-expand-md d-print-none" >
    <div class="container-xl">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbar-menu" aria-controls="navbar-menu" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="my-2 my-md-0 flex-grow-1 flex-md-grow-0 order-first order-md-last">
            <form action="./" method="get" autocomplete="off" novalidate>
                <div class="input-icon m-2">
                    <span class="input-icon-addon">
                        <i class="icon ti ti-search"></i>
                    </span>
                    <input type="text" value="" class="form-control" placeholder="Search" aria-label="Search in website">
                </div>
            </form>
        </div>
        <div class="navbar-nav flex-row order-md-last">
            <div class="d-none d-md-flex">
                <a href="?theme=dark" class="nav-link px-0 hide-theme-dark" title="Enable dark mode" data-bs-toggle="tooltip"
                   data-bs-placement="bottom">
                    <!-- Download SVG icon from http://tabler-icons.io/i/moon -->
                    <i class="icon ti ti-moon-stars" ></i>
                </a>
                <a href="?theme=light" class="nav-link px-0 hide-theme-light" title="Enable light mode" data-bs-toggle="tooltip"
                   data-bs-placement="bottom">
                    <!-- Download SVG icon from http://tabler-icons.io/i/sun -->
                    <i class="icon ti ti-sun" ></i>
                </a>

<%--                NOTIFICATION --%>
<%--                <div class="nav-item dropdown d-none d-md-flex me-3">--%>
<%--                    <a href="#" class="nav-link px-0" data-bs-toggle="dropdown" tabindex="-1" aria-label="Show notifications">--%>
<%--                        <!-- Download SVG icon from http://tabler-icons.io/i/bell -->--%>
<%--                        <svg xmlns="http://www.w3.org/2000/svg" class="icon" width="24" height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M10 5a2 2 0 1 1 4 0a7 7 0 0 1 4 6v3a4 4 0 0 0 2 3h-16a4 4 0 0 0 2 -3v-3a7 7 0 0 1 4 -6" /><path d="M9 17v1a3 3 0 0 0 6 0v-1" /></svg>--%>
<%--                        <span class="badge bg-red"></span>--%>
<%--                    </a>--%>
<%--                    <div class="dropdown-menu dropdown-menu-arrow dropdown-menu-end dropdown-menu-card">--%>
<%--                        <div class="card">--%>
<%--                            <div class="card-header">--%>
<%--                                <h3 class="card-title">Last updates</h3>--%>
<%--                            </div>--%>
<%--                            <div class="list-group list-group-flush list-group-hoverable">--%>
<%--                                <div class="list-group-item">--%>
<%--                                    <div class="row align-items-center">--%>
<%--                                        <div class="col-auto"><span class="status-dot status-dot-animated bg-red d-block"></span></div>--%>
<%--                                        <div class="col text-truncate">--%>
<%--                                            <a href="#" class="text-body d-block">Example 1</a>--%>
<%--                                            <div class="d-block text-muted text-truncate mt-n1">--%>
<%--                                                Change deprecated html tags to text decoration classes (#29604)--%>
<%--                                            </div>--%>
<%--                                        </div>--%>
<%--                                        <div class="col-auto">--%>
<%--                                            <a href="#" class="list-group-item-actions">--%>
<%--                                                <!-- Download SVG icon from http://tabler-icons.io/i/star -->--%>
<%--                                                <svg xmlns="http://www.w3.org/2000/svg" class="icon text-muted" width="24" height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M12 17.75l-6.172 3.245l1.179 -6.873l-5 -4.867l6.9 -1l3.086 -6.253l3.086 6.253l6.9 1l-5 4.867l1.179 6.873z" /></svg>--%>
<%--                                            </a>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                                <div class="list-group-item">--%>
<%--                                    <div class="row align-items-center">--%>
<%--                                        <div class="col-auto"><span class="status-dot d-block"></span></div>--%>
<%--                                        <div class="col text-truncate">--%>
<%--                                            <a href="#" class="text-body d-block">Example 2</a>--%>
<%--                                            <div class="d-block text-muted text-truncate mt-n1">--%>
<%--                                                justify-content:between ⇒ justify-content:space-between (#29734)--%>
<%--                                            </div>--%>
<%--                                        </div>--%>
<%--                                        <div class="col-auto">--%>
<%--                                            <a href="#" class="list-group-item-actions show">--%>
<%--                                                <!-- Download SVG icon from http://tabler-icons.io/i/star -->--%>
<%--                                                <svg xmlns="http://www.w3.org/2000/svg" class="icon text-yellow" width="24" height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M12 17.75l-6.172 3.245l1.179 -6.873l-5 -4.867l6.9 -1l3.086 -6.253l3.086 6.253l6.9 1l-5 4.867l1.179 6.873z" /></svg>--%>
<%--                                            </a>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                                <div class="list-group-item">--%>
<%--                                    <div class="row align-items-center">--%>
<%--                                        <div class="col-auto"><span class="status-dot d-block"></span></div>--%>
<%--                                        <div class="col text-truncate">--%>
<%--                                            <a href="#" class="text-body d-block">Example 3</a>--%>
<%--                                            <div class="d-block text-muted text-truncate mt-n1">--%>
<%--                                                Update change-version.js (#29736)--%>
<%--                                            </div>--%>
<%--                                        </div>--%>
<%--                                        <div class="col-auto">--%>
<%--                                            <a href="#" class="list-group-item-actions">--%>
<%--                                                <!-- Download SVG icon from http://tabler-icons.io/i/star -->--%>
<%--                                                <svg xmlns="http://www.w3.org/2000/svg" class="icon text-muted" width="24" height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M12 17.75l-6.172 3.245l1.179 -6.873l-5 -4.867l6.9 -1l3.086 -6.253l3.086 6.253l6.9 1l-5 4.867l1.179 6.873z" /></svg>--%>
<%--                                            </a>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                                <div class="list-group-item">--%>
<%--                                    <div class="row align-items-center">--%>
<%--                                        <div class="col-auto"><span class="status-dot status-dot-animated bg-green d-block"></span></div>--%>
<%--                                        <div class="col text-truncate">--%>
<%--                                            <a href="#" class="text-body d-block">Example 4</a>--%>
<%--                                            <div class="d-block text-muted text-truncate mt-n1">--%>
<%--                                                Regenerate package-lock.json (#29730)--%>
<%--                                            </div>--%>
<%--                                        </div>--%>
<%--                                        <div class="col-auto">--%>
<%--                                            <a href="#" class="list-group-item-actions">--%>
<%--                                                <!-- Download SVG icon from http://tabler-icons.io/i/star -->--%>
<%--                                                <svg xmlns="http://www.w3.org/2000/svg" class="icon text-muted" width="24" height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M12 17.75l-6.172 3.245l1.179 -6.873l-5 -4.867l6.9 -1l3.086 -6.253l3.086 6.253l6.9 1l-5 4.867l1.179 6.873z" /></svg>--%>
<%--                                            </a>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
            </div>
            <% if (usermodel!=null){%>
            <div class="nav-item dropdown">
                <a href="#" class="nav-link d-flex lh-1 text-reset p-0" data-bs-toggle="dropdown" aria-label="Open user menu">
                    <span class="avatar avatar-sm" >
                        <i class="ti ti-user-filled" style="font-size: 170%"></i>
                    </span>
<%--                    user details --%>
                    <div class="d-none d-xl-block ps-2">
                        <c:if test="${not empty pageContext.request.userPrincipal.authorities}">
                            <c:forEach var="authority" items="${pageContext.request.userPrincipal.authorities}">
                                <c:if test="${authority.authority == 'EQUIPE'}">
                                    <div><%=usermodel.getUsername()%></div>
                                    <div class="mt-1 small text-muted text-lowercase align-right"><c:out value="${authority.authority}" /></div>
                                </c:if>

                                <c:if test="${authority.authority == 'ADMIN'}">
                                    <div><%=firstName%></div>
                                    <div class="mt-1 small text-muted"><c:out value="${authority.authority}"/></div>
                                </c:if>
                            </c:forEach>
                        </c:if>

                    </div>
                </a>
                <div class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                    <a href="#" class="dropdown-item">Status</a>
                    <a href="/user/userProfile" class="dropdown-item">Profile</a>
                    <a href="#" class="dropdown-item">Feedback</a>
                    <div class="dropdown-divider"></div>
                    <a href="./settings.html" class="dropdown-item">Settings</a>

                    <form:form action="/logout" method="post">
                        <button type="submit" class="dropdown-item">Logout</button>
                    </form:form>
                </div>
            </div>
            <%}%>
        </div>
    </div>
</header>
