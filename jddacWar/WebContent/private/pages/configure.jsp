<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="h" %>
<%@ page isELIgnored="false" %>

<h:form method="POST" action="/jddac/forms/configure.do">
<h:hidden property="m(ptid)"/>
<h:hidden property="m(numRecords)"/>

	<table  cellpadding=10 cellspacing=0 border=0 width=80%><tr><td>

	<h1>Configure ${name}</h1>
	<font color="red"><h4 class="error">${errMsg}</h4></font>

        <table border="1" class="configBehavior">
          <tr>
            <th class="configColTitle">&nbsp;</th>
<c:forEach var="i" begin="1" end="${m.numRecords}">
            <th class="center">Record #${i}</th>
</c:forEach>
          </tr>
<c:forEach var="c" items="${chAttrs}">
 <c:if test="${(c.displayType == 'checkbox') || (c.displayType == 'textbox')}">
          <tr>
            <td class="configColTitle">${c.displayName}</td>
  <c:if test="${! ((c.displayType == 'textbox') && c.allRecords)}">
   <c:forEach var="i" begin="1" end="${m.numRecords}">
            <td class="center">
    <c:if test="${c.displayType == 'checkbox'}">
  				<h:checkbox property="m(${i}_${c.attrName})" value="true"/>
    </c:if>
    <c:if test="${c.displayType == 'textbox'}">
  				<h:text property="m(${i}_${c.attrName})" size="${(empty c.textboxSize) ? '10' : c.textboxSize}"/>
    </c:if>
	        </td>
   </c:forEach>
  </c:if>
  <c:if test="${((c.displayType == 'textbox') && c.allRecords)}">
            <td colspan="${m.numRecords}">
            	<h:text property="m(${c.attrName})" size="${(empty c.textboxSize) ? '10' : c.textboxSize}"/>
            </td>
  </c:if>
          </tr>
 </c:if>
 <c:if test="${c.displayType == 'title'}">
          <tr>
            <td class="configRowTitle" colspan="${m.numRecords+1}">${c.displayName}</td>
          </tr>
 </c:if> 
</c:forEach>
<c:if test="${m.numRecords > 1}">
          <tr>
            <td class="configColTitle">&nbsp;</td>
 <c:forEach var="i" begin="1" end="${m.numRecords}">
            <td class="center">
  <c:if test="${m.numRecords>1}">
             <h:submit property="deleteRecord${i}" value="Delete"/>
  </c:if>
			</td>
 </c:forEach>
</c:if> 
          </tr>
        </table>
      <p><h:submit property="addNewRecord" value="Add Record"/></p>
      <p><h:submit property="submitConfig" value="Submit"/><h:cancel value="Cancel"/></p>
  </td></tr></table>
</h:form>

