<%@ page import="testtask.College" %>



<div class="fieldcontain ${hasErrors(bean: collegeInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="college.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${collegeInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: collegeInstance, field: 'students', 'error')} ">
	<label for="students">
		<g:message code="college.students.label" default="Students" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${collegeInstance?.students?}" var="s">
    <li><g:link controller="student" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="student" action="create" params="['college.id': collegeInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'student.label', default: 'Student')])}</g:link>
</li>
</ul>


</div>

