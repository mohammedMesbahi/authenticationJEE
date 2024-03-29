<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${not empty requestScope.successMessage}">
    <ul>
        <c:forEach var="success" items="${requestScope.successMessage}">
            <li>${success}</li>
        </c:forEach>
    </ul>
</c:if>
