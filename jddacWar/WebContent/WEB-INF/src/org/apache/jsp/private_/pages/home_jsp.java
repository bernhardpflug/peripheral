package org.apache.jsp.private_.pages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class home_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.Vector _jspx_dependants;

  public java.util.List getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    JspFactory _jspxFactory = null;
    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      _jspxFactory = JspFactory.getDefaultFactory();
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n\n\n\n<table width=95% class=\"outline\">\n<tr><td class=\"title\">Status as of ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dateTime}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</td></tr>\n<tr class=\"space\"><td>&nbsp;</td></tr><tr><td>\n<table width=100% class=\"inner\">\n<tr class=\"row0\"><td>Number of Probes</td><td colspan=2>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${numProbes}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</td></tr>\n<tr class=\"row1\"><td>Number of Measurements</td><td colspan=2>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${numMeasChannels}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</td></tr>\n<tr class=\"row0\"><td>Number of Measurement Samples</td><td colspan=2>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${numMeasSamples}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</td></tr>\n</td></tr></table></table>\n<br>\n<h3>Quick Reference Guide</h3>\n<ul>\n<li><b>System Home</b> - Back to this page.\n</li>\n<li><b>Settings</b> - Change your e-mail or password.\n</li>\n<li><b>Logout</b> - Sign out from the current user account.\n</li>\n<li><b>Probes</b> - List the probes associated with this account. From this page, you can view the measurements reported by this probe.  This is where the <i>activation key</i> for this account can be found.\n\n<ul>\n<li><b>View</b> - Lists all the measurements reported by this probe.\n</li>\n<ul>\n<li><b>NRSS</b> - Retrieve the NRSS feed for this measurement. The URL for this feed can be used in a RSS 2.0 compliant RSS reader.\n</li>\n<li><b>View Graph</b> - View the data in a time-series graph.\n</li>\n<li><b>View Histogram</b> - View the data in a histogram.\n</li>\n<li><b>Properties</b> - View the metadata for this measurement.\n</li>\n</ul>\n<li><b>Properties</b> - View the metadata for this probe.\n</li>\n<li><b>Delete</b> - Delete this probe from the database.\n</li>\n</ul>\n");
      out.write("</li>\n<li><b>Summary Reports</b> - Define and view reports generated from your measurements.\n</li>\n</ul>\n<br>\nIf you have any questions or comments, please post them in the JDDAC Server <a href=\"https://jddac.dev.java.net/servlets/ForumMessageList?forumID=1524\">Discussion Forum</a> hosted on java.net. Thanks!\n\n\n\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      if (_jspxFactory != null) _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
