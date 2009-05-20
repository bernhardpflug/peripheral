package org.apache.jsp.private_.pages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class probeTypeEdit_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.Vector _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_h_form_method_action;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_h_hidden_value_property_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_h_textarea_styleClass_rows_property_cols_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_h_submit_value_property_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_h_cancel_value_nobody;

  public java.util.List getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_h_form_method_action = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_h_hidden_value_property_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_h_textarea_styleClass_rows_property_cols_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_h_submit_value_property_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_h_cancel_value_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_h_form_method_action.release();
    _jspx_tagPool_h_hidden_value_property_nobody.release();
    _jspx_tagPool_h_textarea_styleClass_rows_property_cols_nobody.release();
    _jspx_tagPool_h_submit_value_property_nobody.release();
    _jspx_tagPool_h_cancel_value_nobody.release();
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
    _jspx_th_h_form_0.setAction("/jddac/forms/configure.do");
    int _jspx_eval_h_form_0 = _jspx_th_h_form_0.doStartTag();
    if (_jspx_eval_h_form_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n\n<h2>");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${msg}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</h2>\n<table width=\"95%\" class=\"outline\">\n <tr><td class=\"title\">Probe Type ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${name}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</td></tr>\n <tr><td>\n  ");
        if (_jspx_meth_h_hidden_0(_jspx_th_h_form_0, _jspx_page_context))
          return true;
        out.write('\n');
        out.write(' ');
        out.write(' ');
        if (_jspx_meth_h_textarea_0(_jspx_th_h_form_0, _jspx_page_context))
          return true;
        out.write("<br>\n  ");
        if (_jspx_meth_h_submit_0(_jspx_th_h_form_0, _jspx_page_context))
          return true;
        out.write('\n');
        out.write(' ');
        out.write(' ');
        if (_jspx_meth_h_cancel_0(_jspx_th_h_form_0, _jspx_page_context))
          return true;
        out.write("\n </td></tr>\n</table> \n");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dateTime}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
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

  private boolean _jspx_meth_h_hidden_0(javax.servlet.jsp.tagext.JspTag _jspx_th_h_form_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:hidden
    org.apache.struts.taglib.html.HiddenTag _jspx_th_h_hidden_0 = (org.apache.struts.taglib.html.HiddenTag) _jspx_tagPool_h_hidden_value_property_nobody.get(org.apache.struts.taglib.html.HiddenTag.class);
    _jspx_th_h_hidden_0.setPageContext(_jspx_page_context);
    _jspx_th_h_hidden_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_form_0);
    _jspx_th_h_hidden_0.setProperty("m(ptid)");
    _jspx_th_h_hidden_0.setValue((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ptid}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_h_hidden_0 = _jspx_th_h_hidden_0.doStartTag();
    if (_jspx_th_h_hidden_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_h_hidden_value_property_nobody.reuse(_jspx_th_h_hidden_0);
    return false;
  }

  private boolean _jspx_meth_h_textarea_0(javax.servlet.jsp.tagext.JspTag _jspx_th_h_form_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:textarea
    org.apache.struts.taglib.html.TextareaTag _jspx_th_h_textarea_0 = (org.apache.struts.taglib.html.TextareaTag) _jspx_tagPool_h_textarea_styleClass_rows_property_cols_nobody.get(org.apache.struts.taglib.html.TextareaTag.class);
    _jspx_th_h_textarea_0.setPageContext(_jspx_page_context);
    _jspx_th_h_textarea_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_form_0);
    _jspx_th_h_textarea_0.setProperty("m(xml)");
    _jspx_th_h_textarea_0.setCols("80");
    _jspx_th_h_textarea_0.setRows("25");
    _jspx_th_h_textarea_0.setStyleClass("code");
    int _jspx_eval_h_textarea_0 = _jspx_th_h_textarea_0.doStartTag();
    if (_jspx_th_h_textarea_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_h_textarea_styleClass_rows_property_cols_nobody.reuse(_jspx_th_h_textarea_0);
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
    _jspx_th_h_submit_0.setProperty("submitProbeTypeXml");
    _jspx_th_h_submit_0.setValue("Submit Changes");
    int _jspx_eval_h_submit_0 = _jspx_th_h_submit_0.doStartTag();
    if (_jspx_th_h_submit_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_h_submit_value_property_nobody.reuse(_jspx_th_h_submit_0);
    return false;
  }

  private boolean _jspx_meth_h_cancel_0(javax.servlet.jsp.tagext.JspTag _jspx_th_h_form_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:cancel
    org.apache.struts.taglib.html.CancelTag _jspx_th_h_cancel_0 = (org.apache.struts.taglib.html.CancelTag) _jspx_tagPool_h_cancel_value_nobody.get(org.apache.struts.taglib.html.CancelTag.class);
    _jspx_th_h_cancel_0.setPageContext(_jspx_page_context);
    _jspx_th_h_cancel_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_form_0);
    _jspx_th_h_cancel_0.setValue("Cancel");
    int _jspx_eval_h_cancel_0 = _jspx_th_h_cancel_0.doStartTag();
    if (_jspx_th_h_cancel_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_h_cancel_value_nobody.reuse(_jspx_th_h_cancel_0);
    return false;
  }
}
