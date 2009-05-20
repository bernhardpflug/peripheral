package org.apache.jsp.private_.pages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class jddacMenus_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\n\n\n<table class='menu' width=\"100%\">\n  <tr>\n    <td nowrap><a href=\"/jddac/index.do\">System Home</a></td>\n  </tr>\n  <tr>\n    <td class=\"menuTitle\">Measurement Data</td>\n  </tr>\n  <tr>\n    <td><a href=\"/jddac/forms/probes.do\">Probes</a></td>\n  </tr>\n  <tr>\n    <td><a href=\"/jddac/forms/reports.do\">Summary Reports</td>\n  </tr>\n  <tr>\n    <td class=\"menuTitle\">Configuration</td>\n  </tr>\n  <tr>\n    <td><a href=\"/jddac/forms/configure.do\">Configure</a></td>\n  </tr>\n  <tr>\n    <td><a href=\"/jddac/forms/configure.do?status=1\">Probe Status</a></td>\n  </tr>\n  <tr>\n    <td class=\"menuTitle\">Documentation</td>\n  </tr>\n  <tr>\n    <td><a href='/docs/JDDACUsersGuide.pdf'>User's Guide</a></td>\n  </tr>\n  <tr>\n    <td><a href='/docs/api/index.html'>Javadocs</a></td>\n  </tr>\n  <tr>\n    <td class=\"menuTitle\">Debug Pages</td>\n  </tr>\n  <tr>\n    <td width=\"100%\"><a href=\"/admin/servlets/genericStore/a.do\">Generic Tables</a></td>\n  </tr>\n  <tr>\n    <td width=\"100%\"><a href=\"/admin/servlets/registry.do?mode=objects\">Object\n      Registry</a></td>\n");
      out.write("  </tr>\n  <tr>\n    <td width=\"100%\"><a href=\"/admin/servlets/mq.do\">Message Queue</a></td>\n  </tr>\n  <tr>\n    <td width=\"100%\"><a href=\"/admin/servlets/viewThreads.do\">Thread\n      Monitor</a></td>\n  </tr>\n  <tr>\n    <td width=\"100%\"><a href=\"/admin/servlets/dbSetup/a.do\">Query DB</a></td>\n  </tr>\n</table>\n");
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
