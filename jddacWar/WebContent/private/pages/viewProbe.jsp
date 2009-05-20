<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="h" %>
<%@ page isELIgnored="false" %>

<table width="95%" class="outline">
 <tr><td class="title">Probe ${name} Summary</td></tr>
 <tr class="space"><td>&nbsp;</td></tr>
 <tr><td>
  <table width="100%" class="inner">
   <tr>
    <th colspan=2>Statistics</th>
   </tr>
   <tr class='row0'>
    <td nowrap>Last Checkin Time</td><td width='100%'>${lastMeasTime}</td>
   </tr>
  </table>
 </td></tr>
 <tr class="space"><td>&nbsp;</td></tr>
 <tr><td>
  <table width="100%" class="inner">
   <tr>
    <th>Measurement</th>
    <th class='center' nowrap>Description</th>
    <th>Action</th>
   </tr>

<% int row=0; %>
<c:forEach var="i" items="${table}">
   <tr class="row<%= (row++) % 2%>">
    <td nowrap>${i.name}</td>
    <td class='center'>${i.description}</td>
    <td>
     <form method='get' action='/jddac/forms/viewProbe.do'>
      <a href="/nrss/feed?mid=${i.id}"><img src="/images/nrss.jpg"></a><br>
      <input type='hidden' name='mid' value='${i.id}'>
      <input type='hidden' name='pid' value='${pid}'>
      <input type='submit' name='viewGraph' value='View Graph'><br>
      <input type='submit' name='viewHist' value='View Histogram'><br>
      <input type='submit' name='properties' value='Properties'>
     </form>
     
    </td>
   </tr>
</c:forEach>
  </table>
 </td></tr>
</table> 
${dateTime}
<br>	   
