<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page isELIgnored="false" %>

<table class='menu' width="100%">
  <tr>
    <td nowrap><a href="/jddac/index.do">System Home</a></td>
  </tr>
  <tr>
    <td class="menuTitle">Measurement Data</td>
  </tr>
  <tr>
    <td><a href="/jddac/forms/probes.do">Probes</a></td>
  </tr>
  <tr>
    <td><a href="/jddac/forms/reports.do">Summary Reports</td>
  </tr>
  <tr>
    <td class="menuTitle">Configuration</td>
  </tr>
  <tr>
    <td><a href="/jddac/forms/configure.do">Configure</a></td>
  </tr>
  <tr>
    <td><a href="/jddac/forms/configure.do?status=1">Probe Status</a></td>
  </tr>
  <tr>
    <td class="menuTitle">Documentation</td>
  </tr>
  <tr>
    <td><a href='/docs/JDDACUsersGuide.pdf'>User's Guide</a></td>
  </tr>
  <tr>
    <td><a href='/docs/api/index.html'>Javadocs</a></td>
  </tr>
  <tr>
    <td class="menuTitle">Debug Pages</td>
  </tr>
  <tr>
    <td width="100%"><a href="/admin/servlets/genericStore/a.do">Generic Tables</a></td>
  </tr>
  <tr>
    <td width="100%"><a href="/admin/servlets/registry.do?mode=objects">Object
      Registry</a></td>
  </tr>
  <tr>
    <td width="100%"><a href="/admin/servlets/mq.do">Message Queue</a></td>
  </tr>
  <tr>
    <td width="100%"><a href="/admin/servlets/viewThreads.do">Thread
      Monitor</a></td>
  </tr>
  <tr>
    <td width="100%"><a href="/admin/servlets/dbSetup/a.do">Query DB</a></td>
  </tr>
</table>
