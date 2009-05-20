<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="h" %>
<%@ page isELIgnored="false" %>

<SCRIPT LANGUAGE="JavaScript">
function verify(pName) {
 if (confirm('OK to delete probe '+pName+'?')) {
  return true
 }
 return false
}

</SCRIPT>
<h:form method="POST" action="/jddac/forms/probes.do">
</h:form>

<table width="95%" class="outline">
 <tr><td class="title">Probes</td></tr>
 <tr><td>
  <table width="100%" class="inner">
   <tr><th>Configuration</th></tr>
   <tr class="row0"><td>
To add a new probe, enter the following information into it's configuration interface:<br><br>
Activation Key: ${regCode}<br>
Server: ${serverUrl}<br>
<br><br>
   </td></tr>
  </table>
 </td></tr>
 <tr class="space"><td>&nbsp;</td></tr>
 <tr><td>
  <table width=100% class="inner">
   <tr>
    <th>Probe Name</th>
    <th class='center'>Owner</th>
    <th class='center'>Revision</th>
    <th class='center'>Time Since<br>Last Checkin</th>
    <th>&nbsp;Action</th>
   </tr>

<% int row=0; %>
<c:forEach var="i" items="${table}">
   <tr class="row<%= (row++) % 2%>">
    <td><a href='/jddac/forms/probes.do?pid=${i.id}&cmd=View'>${i.name}</a></td>
    <td class='center'>${i.parentName}</td>
    <td class='center'>${i.revision}</td>
    <td class='center'>${i.lastUpdate}</td>
    <td>
      <form method='get' action='/jddac/forms/probes.do'>
       <input type='hidden' name='pid' value='${i.id}'>
       <input type='submit' name='cmd' value='View'>
       <input type='submit' name='cmd' value='Properties'>
       <input type='submit' name='cmd' value='Delete' onclick='return verify("${i.name}")'>
      </form>
    </td>
   </tr>
</c:forEach>
  </table>
 </td></tr>
</table> 
${dateTime}
<br>	   
