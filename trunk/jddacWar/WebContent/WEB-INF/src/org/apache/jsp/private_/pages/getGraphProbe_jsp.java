package org.apache.jsp.private_.pages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class getGraphProbe_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.Vector _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_h_form_method_action;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_h_hidden_value_property_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_h_select_size_property;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_h_options_name_labelName_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_h_submit_value_property_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_h_reset_value_nobody;

  public java.util.List getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_h_form_method_action = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_h_hidden_value_property_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_h_select_size_property = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_h_options_name_labelName_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_h_submit_value_property_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_h_reset_value_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_h_form_method_action.release();
    _jspx_tagPool_h_hidden_value_property_nobody.release();
    _jspx_tagPool_h_select_size_property.release();
    _jspx_tagPool_h_options_name_labelName_nobody.release();
    _jspx_tagPool_h_submit_value_property_nobody.release();
    _jspx_tagPool_h_reset_value_nobody.release();
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

      out.write("\n\n\n\n");
      if (_jspx_meth_h_form_0(_jspx_page_context))
        return;
      out.write('\n');
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
    _jspx_th_h_form_0.setAction("/jddac/forms/addGraphReport.do");
    int _jspx_eval_h_form_0 = _jspx_th_h_form_0.doStartTag();
    if (_jspx_eval_h_form_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write('\n');
        if (_jspx_meth_h_hidden_0(_jspx_th_h_form_0, _jspx_page_context))
          return true;
        out.write("\n\n\t<table  cellpadding=10 cellspacing=0 border=0 width=80%><tr><td>\n\n\t<h1>");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${addUpdate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write(" Report</h1>\n\t<font color=\"red\"><h4 class=\"error\">");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${errMsg}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</h4></font>\n\n\t<p><h2><b>Select Probe for Report</b></h2></p>\n        <table border=\"1\" width=\"100%\">\n          <tr>\n            <td width=\"36%\" bgcolor=\"#CCFFFF\"><b>Probe</b></td>\n            <td width=\"64%\" bgcolor=\"#CCFFFF\" colspan=\"2\">\n              ");
        if (_jspx_meth_h_select_0(_jspx_th_h_form_0, _jspx_page_context))
          return true;
        out.write("\n            </td>\n          </tr>\n\n        </table>\n      <p>");
        if (_jspx_meth_h_submit_0(_jspx_th_h_form_0, _jspx_page_context))
          return true;
        if (_jspx_meth_h_reset_0(_jspx_th_h_form_0, _jspx_page_context))
          return true;
        out.write("</p>\n  </td></tr></table>\n");
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

  private boolean _jspx_meth_h_hidden_0(javax.servlet.jsp.tagext.JspTag _jspx_th_h_form_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:hidden
    org.apache.struts.taglib.html.HiddenTag _jspx_th_h_hidden_0 = (org.apache.struts.taglib.html.HiddenTag) _jspx_tagPool_h_hidden_value_property_nobody.get(org.apache.struts.taglib.html.HiddenTag.class);
    _jspx_th_h_hidden_0.setPageContext(_jspx_page_context);
    _jspx_th_h_hidden_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_form_0);
    _jspx_th_h_hidden_0.setProperty("m(reportType)");
    _jspx_th_h_hidden_0.setValue((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${m.reportType}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_h_hidden_0 = _jspx_th_h_hidden_0.doStartTag();
    if (_jspx_th_h_hidden_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_h_hidden_value_property_nobody.reuse(_jspx_th_h_hidden_0);
    return false;
  }

  private boolean _jspx_meth_h_select_0(javax.servlet.jsp.tagext.JspTag _jspx_th_h_form_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:select
    org.apache.struts.taglib.html.SelectTag _jspx_th_h_select_0 = (org.apache.struts.taglib.html.SelectTag) _jspx_tagPool_h_select_size_property.get(org.apache.struts.taglib.html.SelectTag.class);
    _jspx_th_h_select_0.setPageContext(_jspx_page_context);
    _jspx_th_h_select_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_form_0);
    _jspx_th_h_select_0.setProperty("m(prbId)");
    _jspx_th_h_select_0.setSize("1");
    int _jspx_eval_h_select_0 = _jspx_th_h_select_0.doStartTag();
    if (_jspx_eval_h_select_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_h_select_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_h_select_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_h_select_0.doInitBody();
      }
      do {
        out.write("\n                ");
        if (_jspx_meth_h_options_0(_jspx_th_h_select_0, _jspx_page_context))
          return true;
        out.write("\n              ");
        int evalDoAfterBody = _jspx_th_h_select_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_h_select_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_h_select_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_h_select_size_property.reuse(_jspx_th_h_select_0);
    return false;
  }

  private boolean _jspx_meth_h_options_0(javax.servlet.jsp.tagext.JspTag _jspx_th_h_select_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:options
    org.apache.struts.taglib.html.OptionsTag _jspx_th_h_options_0 = (org.apache.struts.taglib.html.OptionsTag) _jspx_tagPool_h_options_name_labelName_nobody.get(org.apache.struts.taglib.html.OptionsTag.class);
    _jspx_th_h_options_0.setPageContext(_jspx_page_context);
    _jspx_th_h_options_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_select_0);
    _jspx_th_h_options_0.setName("prbIds");
    _jspx_th_h_options_0.setLabelName("prbNames");
    int _jspx_eval_h_options_0 = _jspx_th_h_options_0.doStartTag();
    if (_jspx_th_h_options_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_h_options_name_labelName_nobody.reuse(_jspx_th_h_options_0);
    return false;
  }

  private boolean _jspx_meth_h_submit_0(javax.servlet.jsp.tagext.JspTag _jspx_th_h_form_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:submit
    org.apache.struts.taglib.html.SubmitTag _jspx_th_h_submit_0 = (org.apache.struts.taglib.html.SubmitTag) _jspx_tagPool_h_submit_value_property_nobody.get(org.apache.struts.taglib.html.SubmitTag.class);
    _jspx_th_h_submit_0.setPageContext(_jspx_page_context);
    _jspx_th_h_submit_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_form_0);
    _jspx_th_h_submit_0.setProperty("submitProbe");
    _jspx_th_h_submit_0.setValue("Next Page");
    int _jspx_eval_h_submit_0 = _jspx_th_h_submit_0.doStartTag();
    if (_jspx_th_h_submit_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_h_submit_value_property_nobody.reuse(_jspx_th_h_submit_0);
    return false;
  }

  private boolean _jspx_meth_h_reset_0(javax.servlet.jsp.tagext.JspTag _jspx_th_h_form_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:reset
    org.apache.struts.taglib.html.ResetTag _jspx_th_h_reset_0 = (org.apache.struts.taglib.html.ResetTag) _jspx_tagPool_h_reset_value_nobody.get(org.apache.struts.taglib.html.ResetTag.class);
    _jspx_th_h_reset_0.setPageContext(_jspx_page_context);
    _jspx_th_h_reset_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_form_0);
    _jspx_th_h_reset_0.setValue("Reset");
    int _jspx_eval_h_reset_0 = _jspx_th_h_reset_0.doStartTag();
    if (_jspx_th_h_reset_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_h_reset_value_nobody.reuse(_jspx_th_h_reset_0);
    return false;
  }
}
