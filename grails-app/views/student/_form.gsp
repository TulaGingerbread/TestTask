<%@ page import="testtask.Student" %>



<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'firstName', 'error')} required">
	<label for="firstName">
		<g:message code="student.firstName.label" default="First Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="firstName" required="" value="${studentInstance?.firstName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'lastName', 'error')} required">
	<label for="lastName">
		<g:message code="student.lastName.label" default="Last Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="lastName" required="" value="${studentInstance?.lastName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'documentID', 'error')} ">
	<label for="documentID">
		<g:message code="student.documentID.label" default="Document ID" />
		
	</label>
	<g:textField name="documentID" value="${studentInstance?.documentID}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="student.email.label" default="Email" />
		
	</label>
	<g:textField name="email" value="${studentInstance?.email}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'college', 'error')} required">
	<label for="college">
		<g:message code="student.college.label" default="College" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="college" name="college.id" from="${testtask.College.list()}" optionKey="id" required="" value="${studentInstance?.college?.id}" class="many-to-one"/>

</div>

