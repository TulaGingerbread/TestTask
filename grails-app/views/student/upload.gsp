<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="testtask.Student" %>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'student.label', default: 'Student')}" />
    <title><g:message code="student.upload.label" args="[entityName]" /></title>
</head>
<body>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="['Student']" /></g:link></li>
    </ul>
</div>
<div role="main" style="padding: 10px">
    <h1>Uploading CSV file with students' information</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:form method="post" action="saveCSV" enctype="multipart/form-data">
        <input type="file" name="studentCSV" multiple>
        <input type="submit"/>
    </g:form>
</div>
</body>
</html>