<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="h" %>
<%@ page isELIgnored="false" %>

<table width="95%" class="outline">
 <tr><td class="title">Server Summary</td></tr>
 <tr class="space"><td>&nbsp;</td></tr>
 <tr class="space"><td>&nbsp;</td></tr>
 <tr><td>
  <table width="100%" class="inner">
   <tr>
    <th>Name</th>
    <th class='center' nowrap>Last Checkin</th>
    <th>Action</th>
   </tr>

<% int row=0; %>
<c:forEach var="i" items="${table}">
   <tr class="row<%= (row++) % 2%>">
    <td nowrap>${i.hostname}</td>
    <td class='center'>${i.last}</td>
    <td>
     <form method='get' action='/jddac/forms/viewFederation.do'>
      <a href="${i.url}"><img src="/images/nrss.jpg"></a><br>
      <input type='hidden' name='id' value='${i.id}'>
     </form>
    </td>
   </tr>
</c:forEach>
  </table>
 </td></tr>
</table> 
${dateTime}
<br>	   
