<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="h" %>
<%@ page isELIgnored="false" %>

<h:form method="POST" action="/jddac/forms/configure.do">
</h:form>

<h2>${msg}</h2>
<table width="95%" class="outline">
 <tr><td class="title">Probe Configuration Status</td></tr>
 <tr class="space"><td>&nbsp;</td></tr>
 <tr><td>
  <table width=100% class="inner">
   <tr>
    <th>Probe Name</th>
    <th>Probe Type</th>
    <th>Status</th>
   </tr>

<% int row=0; %>
<c:forEach var="i" items="${table}">
   <tr class="row<%= (row++) % 2%>">
    <td>${i.name}</td>
    <td>${i.ptName}</td>
    <td>${i.status}</td>
   </tr>
</c:forEach>
  </table>
 </td></tr>
</table> 
${dateTime}
<br>	   
