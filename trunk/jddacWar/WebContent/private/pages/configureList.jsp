<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="h" %>
<%@ page isELIgnored="false" %>

<SCRIPT LANGUAGE="JavaScript">
function verify(pName) {
 if (confirm('OK to delete probe type '+pName+'?')) {
  return true
 }
 return false
}

</SCRIPT>
<h:form method="POST" action="/jddac/forms/configure.do">
</h:form>

<h2>${msg}</h2>
<table width="95%" class="outline">
 <tr><td class="title">Configurations</td></tr>
 <tr class="space"><td>&nbsp;</td></tr>
 <tr><td>
  <table width=100% class="inner">
   <tr>
    <th>Configuration</th>
    <th>Last Changed</th>
    <th>Action</th>
   </tr>

<% int row=0; %>
<c:forEach var="i" items="${table}">
   <tr class="row<%= (row++) % 2%>">
    <td><a href='/jddac/forms/configure.do?configure=1&ptid=${i.id}'>${i.name}</a></td>
    <td>${i.lastUpdate}</td>
    <td>
      <form method='get' action='/jddac/forms/configure.do'>
       <input type='hidden' name='ptid' value='${i.id}'>
       <input type='submit' name='configure' value='Configure'><br>
       <input type='submit' name='probeTypeEdit' value='Edit Probe Type'>
<c:if test="${i.canDelete}">
       <br><input type='submit' name='deleteProbeType' value='Delete' onClick='return verify("${i.name}")'>
</c:if>
      </form>
    </td>
   </tr>
</c:forEach>
   <tr class="row<%= (row++) % 2%>">
    <form method='get' action='/jddac/forms/configure.do'>
     <td><input type='text' name='ptName' size='20'></td>
     <td>&nbsp;</td>
     <td>
       <input type='submit' name='addProbeType' value='Add Probe Type'>
     </td>
    </form>
   </tr>
  </table>
 </td></tr>
</table> 
${dateTime}
<br>	   
