package org.apache.jsp.private_.pages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class probes_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.Vector _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_h_form_method_action;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_forEach_var_items;

  public java.util.List getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_h_form_method_action = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_forEach_var_items = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_h_form_method_action.release();
    _jspx_tagPool_c_forEach_var_items.release();
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

      out.write("\n\n\n\n<SCRIPT LANGUAGE=\"JavaScript\">\nfunction verify(pName) {\n if (confirm('OK to delete probe '+pName+'?')) {\n  return true\n }\n return false\n}\n\n</SCRIPT>\n");
      if (_jspx_meth_h_form_0(_jspx_page_context))
        return;
      out.write("\n\n<table width=\"95%\" class=\"outline\">\n <tr><td class=\"title\">Probes</td></tr>\n <tr><td>\n  <table width=\"100%\" class=\"inner\">\n   <tr><th>Configuration</th></tr>\n   <tr class=\"row0\"><td>\nTo add a new probe, enter the following information into it's configuration interface:<br><br>\nActivation Key: ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${regCode}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("<br>\nServer: ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${serverUrl}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("<br>\n<br><br>\n   </td></tr>\n  </table>\n </td></tr>\n <tr class=\"space\"><td>&nbsp;</td></tr>\n <tr><td>\n  <table width=100% class=\"inner\">\n   <tr>\n    <th>Probe Name</th>\n    <th class='center'>Owner</th>\n    <th class='center'>Revision</th>\n    <th class='center'>Time Since<br>Last Checkin</th>\n    <th>&nbsp;Action</th>\n   </tr>\n\n");
 int row=0; 
      out.write('\n');
      //  c:forEach
      org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_forEach_0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _jspx_tagPool_c_forEach_var_items.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
      _jspx_th_c_forEach_0.setPageContext(_jspx_page_context);
      _jspx_th_c_forEach_0.setParent(null);
      _jspx_th_c_forEach_0.setVar("i");
      _jspx_th_c_forEach_0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${table}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
      int[] _jspx_push_body_count_c_forEach_0 = new int[] { 0 };
      try {
        int _jspx_eval_c_forEach_0 = _jspx_th_c_forEach_0.doStartTag();
        if (_jspx_eval_c_forEach_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\n   <tr class=\"row");
            out.print( (row++) % 2);
            out.write("\">\n    <td><a href='/jddac/forms/probes.do?pid=");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("&cmd=View'>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i.name}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</a></td>\n    <td class='center'>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i.parentName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</td>\n    <td class='center'>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i.revision}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</td>\n    <td class='center'>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i.lastUpdate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</td>\n    <td>\n      <form method='get' action='/jddac/forms/probes.do'>\n       <input type='hidden' name='pid' value='");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("'>\n       <input type='submit' name='cmd' value='View'>\n       <input type='submit' name='cmd' value='Properties'>\n       <input type='submit' name='cmd' value='Delete' onclick='return verify(\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i.name}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("\")'>\n      </form>\n    </td>\n   </tr>\n");
            int evalDoAfterBody = _jspx_th_c_forEach_0.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
        }
        if (_jspx_th_c_forEach_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
          return;
      } catch (Throwable _jspx_exception) {
        while (_jspx_push_body_count_c_forEach_0[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_c_forEach_0.doCatch(_jspx_exception);
      } finally {
        _jspx_th_c_forEach_0.doFinally();
        _jspx_tagPool_c_forEach_var_items.reuse(_jspx_th_c_forEach_0);
      }
      out.write("\n  </table>\n </td></tr>\n</table> \n");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dateTime}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\n<br>\t   \n");
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

  private boolean _jspx_meth_h_form_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:form
    org.apache.struts.taglib.html.FormTag _jspx_th_h_form_0 = (org.apache.struts.taglib.html.FormTag) _jspx_tagPool_h_form_method_action.get(org.apache.struts.taglib.html.FormTag.class);
    _jspx_th_h_form_0.setPageContext(_jspx_page_context);
    _jspx_th_h_form_0.setParent(null);
    _jspx_th_h_form_0.setMethod("POST");
    _jspx_th_h_form_0.setAction("/jddac/forms/probes.do");
    int _jspx_eval_h_form_0 = _jspx_th_h_form_0.doStartTag();
    if (_jspx_eval_h_form_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write('\n');
        int evalDoAfterBody = _jspx_th_h_form_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_h_form_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_h_form_method_action.reuse(_jspx_th_h_form_0);
    return false;
  }
}
