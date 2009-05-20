<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="h" %>
<%@ page isELIgnored="false" %>

<h2>Measurement History</h2>
<h:form styleId="mapform" action='/jddac/forms/graphReport.do' method='POST'>
<h:hidden property="m(pid)"/>
<h:hidden property="m(mid)"/>
<h:hidden property="m(type)"/>
<h:hidden property="m(fromReports)"/>

<table border='0' width='600' cellpadding='0' cellspacing='0'><tr><td colspan='2'>
TimeScale: <h:select property="m(time)">
<h:option value="-1">Arbitrary Time Range</h:option>
<h:option value="60" >1 min</h:option>
<h:option value="180" >3 min</h:option>
<h:option value="600" >10 min</h:option>
<h:option value="1800" >30 min</h:option>
<h:option value="3600" >1 hr</h:option>
<h:option value="21600" >6 hr</h:option>
<h:option value="43200" >12 hr</h:option>
<h:option value="86400" >1 day</h:option>
<h:option value="259200" >3 days</h:option>
<h:option value="604800" >1 week</h:option>
<h:option value="1209600" >2 weeks</h:option>
<h:option value="2419200" >4 weeks</h:option>
</h:select><br>
Start Time: <h:text property="m(startTime)" size="25"/> <span class="error">${startErrMsg}</span><br>
Stop Time: <h:text property="m(stopTime)" size="25"/> <span class="error">${stopErrMsg}</span><br>
</td></tr>
<tr><td>
<h:submit value="Redraw Graph"/>
<c:if test="${fromReports}">
 <input type='button' value="Back" onClick='location="/jddac/forms/reports.do"'>
</c:if>
<c:if test="${! fromReports}">
 <input type='button' value="Back" onClick='location="/jddac/forms/probes.do?cmd=View&pid=${pid}"'>
</c:if>
</h:form>
</td>
<td align='right'>
&nbsp;
<c:if test='${isPublic}'>
  <a href='/reports/graph.do?mid=${mid}&type=${m.type}'>Publicly viewable link</a>
</c:if>
</td>
</tr></table>
<p/>
<table class="outline">
 <tr>
  <td height='400' width='600'>
   <img name='i' src='../reports/jchart?mid=${m.mid}&title=${m.title}&time=${m.time}&start=${m.start}&stop=${m.stop}&vlabel=${m.vLabel}&hlabel=${m.hLabel}&type=${m.type}&x=600&y=400&dummy=${time}'>
  </td>
 </tr>
</table>
${dateTime}



