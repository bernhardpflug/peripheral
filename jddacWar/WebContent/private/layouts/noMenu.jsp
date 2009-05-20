<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page isELIgnored="false" %>

<HTML>
  <HEAD>
    <tiles:insert attribute='headerHead'/>
    <link rel="stylesheet" href="/general.css" type="text/css"/>
    <title><tiles:getAsString name="title"/></title>
  </HEAD>
<body>
<table border="0" width="100%" cellspacing="5">
<tr>
  <td><tiles:insert attribute="header" /></td>
</tr>
<tr>
  <td valign="top"  align="left" width="100%">
<c:if test="${not empty body}">
    <tiles:insert page='${body}'/> 
</c:if>
<c:if test="${empty body}">
    <tiles:insert attribute='body' />
</c:if>
  </td>
</tr>
<tr>
  <td>
    <tiles:insert attribute="footer" />
  </td>
</tr>
</table>
</body>
</html>
