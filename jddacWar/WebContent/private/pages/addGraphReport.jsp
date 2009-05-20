<%@ taglib uri="http://struts.apache.org/tags-html" prefix="h" %>
<%@ page isELIgnored="false" %>

<h1>${addUpdate} Report</h1>

<h2>${errMsg}</h2>
<h:form method="POST" action="/jddac/forms/addGraphReport.do">

<h:hidden property="reportType" value="graph" />
<table  cellpadding=10 cellspacing=0 border=0 width=100%><tr><td>
 <table border="1" width="100%">
          <tr>
            <td width="36%" bgcolor="#CCFFFF"><b>Name</b></td>
            <td width="64%" bgcolor="#CCFFFF" colspan="2">
               <h:text property="m(reportName)" size="53" 
                 disabled="${rid ne -1}"/> 
            </td>
          </tr>
          <tr>
            <td width="36%" bgcolor="#CCFFFF"><b>Viewable by:</b></td>
            <td with="64%" bgcolor="#CCFFFF" colspan="2">
<h:select size="1" property="m(viewable)">
  <h:options name="viewValues" labelName="viewLabels"/>
</h:select>
            </td>
          </tr>
          <tr>
            <td width="36%" bgcolor="#CCFFFF"><b>Measurement</b></td>
            <td width="64%" bgcolor="#CCFFFF" colspan="2">
<h:select size="1" property="m(mid)">
  <h:options name="measValues" labelName="measNames"/>
</h:select>
            </td>
          </tr>
          <tr>
            <td width="36%" bgcolor="#CCFFFF"><b>Time Axis</b><br><br>Specify Time Scale<br> OR <br>Start/Stop times.</td>
            <td width="64%" bgcolor="#CCFFFF" colspan="2">
               &nbsp; 
Time Scale: <h:select size="1" property="m(timeScale)">
<h:option value="-1">Arbitrary Time Range</h:option>
<h:option value="0">All Time</h:option>
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
</h:select>
<p>
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
<b>OR</b>
<p>
               &nbsp; 
Start Time: <h:text property="m(startTime)" size="25" /> (i.e. 2004/07/06 11:23:44)<p>
               &nbsp; 
Stop Time: <h:text property="m(stopTime)" size="25" /> (i.e. 2004/07/06 12:23:44)<p>
            </td>
          </tr>
            </td>
          </tr>
        </table>
<p>
<h:submit property="addGraphSubmit" value="Submit" />
<h:cancel value="Cancel" />
 </td></tr></table>
</h:form>	


