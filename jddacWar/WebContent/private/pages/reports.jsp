<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="h" %>
<%@ page isELIgnored="false" %>

<h:form method="POST" action="/jddac/forms/reports.do">

<table width=95% class="outline">
 <tr><td class="title">Reports</td></tr>
 <tr><td><a href="/jddac/forms/addMapReport.do">Add Map Report</td></tr>
 <tr class="space"><td>&nbsp;</td></tr>
 <tr><td>
  <table width=100% class="inner">
   <tr>
    <th>Name</th>
    <th>Report Type</th>
    <th>Content</th>
    <th>&nbsp;</th>
   </tr>

<% int row=0; %>
<c:forEach var="i" items="${table}">
   <tr class="row<%= (row++) % 2%>">
    <td>${i.name}</td>
    <td>${i.attrs.reportType}</td>
    <td>${i.attrs.content}</td>
    <td>
      <a href='/jddac/forms/mapReport.do?id=${i.id}'>View</a>
      <a href='/jddac/forms/addMapReport.do?		id=${i.id}'>Edit</a>
    </td>
   </tr>
</c:forEach>
  </table>
 </td></tr>
</table> 
	<!-- tag goes here -->
    <!-- <rome:reportList />  -->
<br>	   
</h:form>