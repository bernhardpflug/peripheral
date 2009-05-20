<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="h" %>
<%@ page isELIgnored="false" %>

<table width=95% class="outline">
 <tr><td class="title">Summary Reports</td></tr>
 <tr><td><a href="/jddac/forms/addGraphReport.do">Add Graph Report</td></tr>
 <tr><td><a href="/jddac/forms/addHistReport.do">Add Histogram Report</td></tr>
 <tr class="space"><td>&nbsp;</td></tr>
 <tr><td>
  <table width=100% class="inner">
   <tr>
    <th>Name</th>
    <th>Report Type</th>
    <th>Action</th>
   </tr>

<% int row=0; %>
<c:forEach var="i" items="${table}">
   <tr class="row<%= (row++) % 2%>">
    <td><a href='/jddac/forms/reports.do?id=${i.id}&cmd=View'>${i.name}</a></td>
    <td>${i.attrs.reportType}</td>
    <td>
      <form method='get' href='/jddac/forms/reports.do'>
       <input type='hidden' name='id' value='${i.id}'>
       <input type='submit' name='cmd' value='View'>
<c:if test="${(securityGroup eq 'admin') || i.isOwner}">
       <input type='submit' name='cmd' value='Edit'>
       <input type='submit' name='cmd' value='Delete'>
</c:if>
      </form>
    </td>
   </tr>
</c:forEach>
  </table>
 </td></tr>
</table> 
	<!-- tag goes here -->
    <!-- <rome:reportList />  -->
<br>	   
