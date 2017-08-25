
<%@ page import="testtask.LogEntry" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'logEntry.label', default: 'LogEntry')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-logEntry" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-logEntry" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="message" title="${message(code: 'logEntry.message.label', default: 'Message')}" />
					
						<g:sortableColumn property="line" title="${message(code: 'logEntry.line.label', default: 'Line')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${logEntryInstanceList}" status="i" var="logEntryInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${logEntryInstance.id}">${fieldValue(bean: logEntryInstance, field: "message")}</g:link></td>
					
						<td>${fieldValue(bean: logEntryInstance, field: "line")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${logEntryInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
