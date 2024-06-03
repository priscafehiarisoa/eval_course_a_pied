<%--
  Created by IntelliJ IDEA.
  User: priscafehiarisoadama
  Date: 17/04/2024
  Time: 21:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta charset="utf-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, viewport-fit=cover"/>
<%--  <meta http-equiv="X-UA-Compatible" content="ie=edge"/>--%>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />

  <title><%=(request.getAttribute("pageTitle")!=null)?request.getAttribute("pageTitle"): "faith"%></title>
  <!-- CSS files -->
  <link href="../../../public/css/tabler.min.css?1684106062" rel="stylesheet"/>
  <link href="../../../public/css/tabler-flags.min.css?1684106062" rel="stylesheet"/>
  <link href="../../../public/css/tabler-payments.min.css?1684106062" rel="stylesheet"/>
  <link href="../../../public/css/tabler-vendors.min.css?1684106062" rel="stylesheet"/>
  <link href="../../../public/css/demo.min.css?1684106062" rel="stylesheet"/>
  <link href="../../../public/libs/dropzone/dist/dropzone.css?1684106062" rel="stylesheet"/>


  <link rel="stylesheet" href="../../../public/webfont/tabler-icons.min.css">
  <link rel="stylesheet" href="../../../public/js/sweetalert2.min.css">
  <style>
    /*@import url('https://rsms.me/inter/inter.css');*/
    :root {
      --tblr-font-sans-serif:Roboto,  San Francisco,  -apple-system, BlinkMacSystemFont, Segoe UI, Helvetica Neue, sans-serif;
    }
    body {
      font-feature-settings: "cv03", "cv04", "cv11";
    }
  </style>
</head>
<body  class=" layout-fluid">
<script src="../../../public/js/demo-theme.min.js?1684106062"></script>
