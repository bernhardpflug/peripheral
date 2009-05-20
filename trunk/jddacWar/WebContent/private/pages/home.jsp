<%@ taglib uri="http://struts.apache.org/tags-html" prefix="h" %>
<%@ page isELIgnored="false" %>


<table width=95% class="outline">
<tr><td class="title">Status as of ${dateTime}</td></tr>
<tr class="space"><td>&nbsp;</td></tr><tr><td>
<table width=100% class="inner">
<tr class="row0"><td>Number of Probes</td><td colspan=2>${numProbes}</td></tr>
<tr class="row1"><td>Number of Measurements</td><td colspan=2>${numMeasChannels}</td></tr>
<tr class="row0"><td>Number of Measurement Samples</td><td colspan=2>${numMeasSamples}</td></tr>
</td></tr></table></table>
<br>
<h3>Quick Reference Guide</h3>
<ul>
<li><b>System Home</b> - Back to this page.
</li>
<li><b>Settings</b> - Change your e-mail or password.
</li>
<li><b>Logout</b> - Sign out from the current user account.
</li>
<li><b>Probes</b> - List the probes associated with this account. From this page, you can view the measurements reported by this probe.  This is where the <i>activation key</i> for this account can be found.

<ul>
<li><b>View</b> - Lists all the measurements reported by this probe.
</li>
<ul>
<li><b>NRSS</b> - Retrieve the NRSS feed for this measurement. The URL for this feed can be used in a RSS 2.0 compliant RSS reader.
</li>
<li><b>View Graph</b> - View the data in a time-series graph.
</li>
<li><b>View Histogram</b> - View the data in a histogram.
</li>
<li><b>Properties</b> - View the metadata for this measurement.
</li>
</ul>
<li><b>Properties</b> - View the metadata for this probe.
</li>
<li><b>Delete</b> - Delete this probe from the database.
</li>
</ul>
</li>
<li><b>Summary Reports</b> - Define and view reports generated from your measurements.
</li>
</ul>
<br>
If you have any questions or comments, please post them in the JDDAC Server <a href="https://jddac.dev.java.net/servlets/ForumMessageList?forumID=1524">Discussion Forum</a> hosted on java.net. Thanks!



