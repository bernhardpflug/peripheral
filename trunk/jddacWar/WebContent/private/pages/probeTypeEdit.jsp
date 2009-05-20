<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="h" %>
<%@ page isELIgnored="false" %>

<h:form method="POST" action="/jddac/forms/configure.do">

<h2>${msg}</h2>
<table width="95%" class="outline">
 <tr><td class="title">Probe Type ${name}</td></tr>
 <tr><td>
  <h:hidden property="m(ptid)" value="${ptid}"/>
  <h:textarea property="m(xml)" cols="80" rows="25" styleClass="code"/><br>
  <h:submit property="submitProbeTypeXml" value="Submit Changes"/>
  <h:cancel value="Cancel"/>
 </td></tr>
</table> 
${dateTime}
</h:form>
