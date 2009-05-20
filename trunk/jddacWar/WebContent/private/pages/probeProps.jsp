<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="h" %>
<%@ page isELIgnored="false" %>

<h:form method="GET" action="/jddac/forms/probes.do">
<input type=hidden name="pid" value="${pid}"/>

<table width="95%" class="outline">
 <tr><td class="title">Probe ${m.name}</td></tr>
 <tr class="space"><td>&nbsp;</td></tr>
 <tr><td class="title">Metadata</td></tr> <tr><td>
  <table width=100% class="inner">
   <tr>
    <th class='center'>Name</th>
    <th class='center'>Value</th>
   </tr>

<% int row=0; %>
<c:forEach var="i" items="${table}">
   <tr class="row<%= (row++) % 2%>">
    <td>${i.name}</td>
    <td><pre>${i.value}</pre></td>
   </tr>
</c:forEach>
  </table>
 </td></tr>
</table> 
${dateTime}
<br>	   
</h:form>
