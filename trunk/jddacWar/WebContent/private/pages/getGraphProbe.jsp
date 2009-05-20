<%@ taglib uri="http://struts.apache.org/tags-html" prefix="h" %>
<%@ page isELIgnored="false" %>


<h:form method="POST" action="/jddac/forms/addGraphReport.do">
<h:hidden property="m(reportType)" value="${m.reportType}" />

	<table  cellpadding=10 cellspacing=0 border=0 width=80%><tr><td>

	<h1>${addUpdate} Report</h1>
	<font color="red"><h4 class="error">${errMsg}</h4></font>

	<p><h2><b>Select Probe for Report</b></h2></p>
        <table border="1" width="100%">
          <tr>
            <td width="36%" bgcolor="#CCFFFF"><b>Probe</b></td>
            <td width="64%" bgcolor="#CCFFFF" colspan="2">
              <h:select property="m(prbId)" size="1">
                <h:options name="prbIds" labelName="prbNames"/>
              </h:select>
            </td>
          </tr>

        </table>
      <p><h:submit property="submitProbe" value="Next Page"/><h:reset value="Reset"/></p>
  </td></tr></table>
</h:form>
