<%@ page import="testtask.LogEntry" %>



<div class="fieldcontain ${hasErrors(bean: logEntryInstance, field: 'message', 'error')} required">
	<label for="message">
		<g:message code="logEntry.message.label" default="Message" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="message" required="" value="${logEntryInstance?.message}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: logEntryInstance, field: 'line', 'error')} required">
	<label for="line">
		<g:message code="logEntry.line.label" default="Line" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="line" required="" value="${logEntryInstance?.line}"/>

</div>

