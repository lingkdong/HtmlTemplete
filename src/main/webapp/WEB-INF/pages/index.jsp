<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <title>htmltemp</title>
    <meta charset="utf-8">
    <script type="text/javascript"  src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript"  src="${pageContext.request.contextPath}/js/index.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css"/>
</head>
<body>
    <div class="main-div">
        <ul>
            <c:if test="${not empty records}">
                <c:forEach items="${records}" var="record">
                    <li class="main-li">
                        <div class="main-li-div">
                            <a class="main-li-div-a" target="_blank" href="${pageContext.request.contextPath}/${record.url}">${record.name}</a>
                            <img class="main-li-div-img" src="${pageContext.request.contextPath}/${record.icon}"
                                 data-url="${pageContext.request.contextPath}/${record.url}">
                        </div>
                    </li>
                </c:forEach>
           </c:if>

        </ul>
    </div>

</body>
</html>