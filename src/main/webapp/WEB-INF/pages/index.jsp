<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <title>html Templetes</title>
    <meta charset="utf-8">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/logo.png" />
    <script type="text/javascript"  src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript"  src="${pageContext.request.contextPath}/js/index.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css"/>
</head>
<body>
   <div class="welcome">
        <div class="welcome-website">
            www.htmltemp.com
        </div>
        <div class="welcome-slogan">
            <h1 style="text-align: center;" class="font-slogan">Choose Your Beautiful Website Template</h1>
        </div>
    </div>
    <div class="content" id='content'>
        <div class="main-div">
            <ul class="main-div-ul">
                <c:if test="${not empty records}">
                    <c:forEach items="${records}" var="record">
                        <li class="main-li">
                            <div class="main-li-div">
                                <a title="${record.name}" class="main-li-div-a" target="_blank" href="${pageContext.request.contextPath}/Templetes/${record.url}">${record.name}</a>
                                <img class="main-li-div-img" onclick="imgClick(this)" src="${pageContext.request.contextPath}/${record.icon}"
                                     data-url="${pageContext.request.contextPath}/Templetes/${record.url}">
                            </div>
                        </li>
                    </c:forEach>
                </c:if>

            </ul>
        </div>
    </div>
    <div class="go-top" id='go-top' hidden="hidden">TOP</div>
</body>
</html>