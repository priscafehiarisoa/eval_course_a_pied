<%--
  Created by IntelliJ IDEA.
  User: priscafehiarisoadama
  Date: 08/05/2024
  Time: 23:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../1-basic_setup/head.jsp"%>
<%@include file="../1-basic_setup/side_menu.jsp"%>
<%@include file="../1-basic_setup/pageHeader.jsp"%>
<%
    String pageTitle= (String) request.getAttribute("pageTitle");


%>
<div class="page-wrapper">
    <div class="page-body m-5">
<%--        header --%>
        <div class="page-header d-print-none mb-5">
            <div class="container-xl">
                <div class="row g-2 align-items-center">
                    <div class="col">
                        <!-- Page pre-title -->
                        <h2 class="page-title">
                            <c:out value="${pageTitle}"> </c:out>
                        </h2>
                    </div>
                </div>
            </div>
        </div>
<%--        end header --%>

    <div class="page-body">
        <div class="container-xl">
            <div class="row row-cards">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-body">
                            <h3 class="card-title">drop any file</h3>
                            <form:form class="dropzone" id="dropzone-custom" action="/pages/upload" method="post" enctype="multipart/form-data" autocomplete="off" >
                                <div class="fallback">
                                    <input name="file" type="file"  />
                                </div>
                                <div class="dz-message">
                                    <h3 class="dropzone-msg-title">drop your image here</h3>
                                </div>
                                <button type="submit" class="mt-5 btn btn-outline-primary">valider</button>

                            </form:form>

                        </div>
                    </div>

                    <div class="card mt-5">
                        <div class="card-body">
                            <h3 class="card-title">drop any images</h3>
                            <form:form class="dropzone" id="dropzone-custom" action="/pages/uploadImages" method="post" enctype="multipart/form-data" autocomplete="off" >
                                <div class="fallback">
                                    <input name="file" type="file"  />
                                </div>
                                <div class="dz-message">
                                    <h3 class="dropzone-msg-title">jpg, jpeg, png, giv, bmp</h3>
                                </div>
                                <button type="submit" class="mt-5 btn btn-outline-primary">valider</button>

                            </form:form>

                        </div>
                    </div>

<%--                    formulaire image checkbox --%>
                    <form:form action="/pages/forms" method="post">
                        <div class="mb-3">
                            <label class="form-label">Image Check</label>
                            <div class="row g-2">
                                <div class="col-6 col-sm-4">
                                    <label class="form-imagecheck mb-2">
                                        <input name="form-imagecheck" type="checkbox" value="1" class="form-imagecheck-input" />
                                        <span class="form-imagecheck-figure">
                                    <img src="./static/photos/beautiful-blonde-woman-relaxing-with-a-can-of-coke-on-a-tree-stump-by-the-beach.jpg" alt="Beautiful blonde woman relaxing with a can of coke on a tree stump by the beach" class="form-imagecheck-image">
                                  </span>
                                    </label>
                                </div>
                                <div class="col-6 col-sm-4">
                                    <label class="form-imagecheck mb-2">
                                        <input name="form-imagecheck" type="checkbox" value="2" class="form-imagecheck-input"  checked/>
                                        <span class="form-imagecheck-figure">
                                    <img src="./static/photos/brainstorming-session-with-creative-designers.jpg" alt="Brainstorming session with creative designers" class="form-imagecheck-image">
                                  </span>
                                    </label>
                                </div>
                                <div class="col-6 col-sm-4">
                                    <label class="form-imagecheck mb-2">
                                        <input name="form-imagecheck" type="checkbox" value="3" class="form-imagecheck-input" />
                                        <span class="form-imagecheck-figure">
                                    <img src="./static/photos/finances-us-dollars-and-bitcoins-currency-money.jpg" alt="Finances - US Dollars and Bitcoins - Currency - Money" class="form-imagecheck-image">
                                  </span>
                                    </label>
                                </div>

                            </div>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Image Check Radio</label>
                            <div class="row g-2">
                                <div class="col-6 col-sm-4">
                                    <label class="form-imagecheck mb-2">
                                        <input name="form-imagecheck-radio" type="radio" value="radio 1" class="form-imagecheck-input" />
                                        <span class="form-imagecheck-figure">
                                    <img src="./static/photos/group-of-people-sightseeing-in-the-city.jpg" alt="Group of people sightseeing in the city" class="form-imagecheck-image">
                                  </span>
                                    </label>
                                </div>
                                <div class="col-6 col-sm-4">
                                    <label class="form-imagecheck mb-2">
                                        <input name="form-imagecheck-radio" type="radio" value="radio 2" class="form-imagecheck-input"  checked/>
                                        <span class="form-imagecheck-figure">
                                    <img src="./static/photos/color-palette-guide-sample-colors-catalog-.jpg" alt="Color Palette Guide. Sample Colors Catalog." class="form-imagecheck-image">
                                  </span>
                                    </label>
                                </div>
                                <div class="col-6 col-sm-4">
                                    <label class="form-imagecheck mb-2">
                                        <input name="form-imagecheck-radio" type="radio" value="radio 3" class="form-imagecheck-input" />
                                        <span class="form-imagecheck-figure">
                                    <img src="./static/photos/stylish-workplace-with-computer-at-home.jpg" alt="Stylish workplace with computer at home" class="form-imagecheck-image">
                                  </span>
                                    </label>
                                </div>
                            </div>
                        </div>

<%--                        to whom it may concern : MILA JAVASCRIPT ITO  => search for "select-users" --%>

                        <div class="mb-3">
                            <label class="form-label">Advanced select</label>
                            <select type="text" class="form-select" id="select-users" value="" name="advanced-select">
                                <option value="1">Chuck Tesla</option>
                                <option value="2">Elon Musk</option>
                                <option value="3">Paweł Kuna</option>
                                <option value="4">Nikola Tesla</option>
                            </select>
                        </div>

                        <div class="mb-3">
                            <input type="datetime-local" name="dateTime" class="form-control" autocomplete="off"/>
                        </div>

                        <div class="mb-3">
                            <input type="text"
                                   name="telephone"
                                   id="telephone"
                                   data-mask="+261 00 00 000 00"
                                   data-mask-visible="true"
                                   placeholder="+261 00 00 000 00"
                                   class="form-control <c:if test="${telephone!=null}"> is-invalid </c:if> ">
                                <div class="invalid-feedback"><c:out value="${telephone}"></c:out></div>
                        </div>
                        <div class="mb-3">
                            <input type="text" class="form-control is-invalid " name="code" id="zipCode" data-mask="000" data-mask-visible="true" placeholder="___" >
                        </div>

                        <div class="mb-3">
                            <input type="text" class="form-control is-invalid" placeholder="Invalid State..">
                            <div class="invalid-feedback">Invalid feedback</div>
                        </div>


                    <siv class="mb-3">
                            <button class="btn" type="submit">valider</button>
                        </siv>

                    </form:form>

                    <a  <c:url value="/" var="completeURL">
                        <c:param name="trackingId" value="786"/>
                        <c:param name="user" value="Nakul"/>
                    </c:url>
                    >hello</a>




                </div>
            </div>
        </div>
    </div>

    </div>

    <script>
        // @formatter:off
        document.addEventListener("DOMContentLoaded", function () {
            var el;
            window.TomSelect && (new TomSelect(el = document.getElementById('select-users'), {
                copyClassesToDropdown: false,
                dropdownParent: 'body',
                controlInput: '<input>',
                render:{
                    item: function(data,escape) {
                        if( data.customProperties ){
                            return '<div><span class="dropdown-item-indicator">' + data.customProperties + '</span>' + escape(data.text) + '</div>';
                        }
                        return '<div>' + escape(data.text) + '</div>';
                    },
                    option: function(data,escape){
                        if( data.customProperties ){
                            return '<div><span class="dropdown-item-indicator">' + data.customProperties + '</span>' + escape(data.text) + '</div>';
                        }
                        return '<div>' + escape(data.text) + '</div>';
                    },
                },
            }));
        });
        // @formatter:on
    </script>

<%@include file="../1-basic_setup/footer.jsp"%>