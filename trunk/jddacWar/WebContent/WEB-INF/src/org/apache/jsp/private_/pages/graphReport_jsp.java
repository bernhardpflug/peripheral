package org.apache.jsp.private_.pages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class graphReport_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.Vector _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_h_form_styleId_method_action;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_h_hidden_property_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_h_select_property;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_h_option_value;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_h_text_size_property_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_h_submit_value_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;

  public java.util.List getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_h_form_styleId_method_action = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_h_hidden_property_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_h_select_property = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_h_option_value = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_h_text_size_property_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_h_submit_value_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_h_form_styleId_method_action.release();
    _jspx_tagPool_h_hidden_property_nobody.release();
    _jspx_tagPool_h_select_property.release();
    _jspx_tagPool_h_option_value.release();
    _jspx_tagPool_h_text_size_property_nobody.release();
    _jspx_tagPool_h_submit_value_nobody.release();
    _jspx_tagPool_c_if_test.release();
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

      out.write("\n\n\n\n<h2>Measurement History</h2>\n");
      if (_jspx_meth_h_form_0(_jspx_page_context))
        return;
      out.write("\n</td>\n<td align='right'>\n&nbsp;\n");
      if (_jspx_meth_c_if_2(_jspx_page_context))
        return;
      out.write("\n</td>\n</tr></table>\n<p/>\n<table class=\"outline\">\n <tr>\n  <td height='400' width='600'>\n   <img name='i' src='../reports/jchart?mid=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${m.mid}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("&title=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${m.title}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("&time=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${m.time}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("&start=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${m.start}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("&stop=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${m.stop}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("&vlabel=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${m.vLabel}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("&hlabel=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${m.hLabel}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("&type=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${m.type}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("&x=600&y=400&dummy=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${time}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("'>\n  </td>\n </tr>\n</table>\n");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dateTime}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\n\n\n\n");
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
    org.apache.struts.taglib.html.FormTag _jspx_th_h_form_0 = (org.apache.struts.taglib.html.FormTag) _jspx_tagPool_h_form_styleId_method_action.get(org.apache.struts.taglib.html.FormTag.class);
    _jspx_th_h_form_0.setPageContext(_jspx_page_context);
    _jspx_th_h_form_0.setParent(null);
    _jspx_th_h_form_0.setStyleId("mapform");
    _jspx_th_h_form_0.setAction("/jddac/forms/graphReport.do");
    _jspx_th_h_form_0.setMethod("POST");
    int _jspx_eval_h_form_0 = _jspx_th_h_form_0.doStartTag();
    if (_jspx_eval_h_form_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write('\n');
        if (_jspx_meth_h_hidden_0(_jspx_th_h_form_0, _jspx_page_context))
          return true;
        out.write('\n');
        if (_jspx_meth_h_hidden_1(_jspx_th_h_form_0, _jspx_page_context))
          return true;
        out.write('\n');
        if (_jspx_meth_h_hidden_2(_jspx_th_h_form_0, _jspx_page_context))
          return true;
        out.write('\n');
        if (_jspx_meth_h_hidden_3(_jspx_th_h_form_0, _jspx_page_context))
          return true;
        out.write("\n\n<table border='0' width='600' cellpadding='0' cellspacing='0'><tr><td colspan='2'>\nTimeScale: ");
        if (_jspx_meth_h_select_0(_jspx_th_h_form_0, _jspx_page_context))
          return true;
        out.write("<br>\nStart Time: ");
        if (_jspx_meth_h_text_0(_jspx_th_h_form_0, _jspx_page_context))
          return true;
        out.write(" <span class=\"error\">");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${startErrMsg}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</span><br>\nStop Time: ");
        if (_jspx_meth_h_text_1(_jspx_th_h_form_0, _jspx_page_context))
          return true;
        out.write(" <span class=\"error\">");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${stopErrMsg}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</span><br>\n</td></tr>\n<tr><td>\n");
        if (_jspx_meth_h_submit_0(_jspx_th_h_form_0, _jspx_page_context))
          return true;
        out.write('\n');
        if (_jspx_meth_c_if_0(_jspx_th_h_form_0, _jspx_page_context))
          return true;
        out.write('\n');
        if (_jspx_meth_c_if_1(_jspx_th_h_form_0, _jspx_page_context))
          return true;
        out.write('\n');
        int evalDoAfterBody = _jspx_th_h_form_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_h_form_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_h_form_styleId_method_action.reuse(_jspx_th_h_form_0);
    return false;
  }

  private boolean _jspx_meth_h_hidden_0(javax.servlet.jsp.tagext.JspTag _jspx_th_h_form_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:hidden
    org.apache.struts.taglib.html.HiddenTag _jspx_th_h_hidden_0 = (org.apache.struts.taglib.html.HiddenTag) _jspx_tagPool_h_hidden_property_nobody.get(org.apache.struts.taglib.html.HiddenTag.class);
    _jspx_th_h_hidden_0.setPageContext(_jspx_page_context);
    _jspx_th_h_hidden_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_form_0);
    _jspx_th_h_hidden_0.setProperty("m(pid)");
    int _jspx_eval_h_hidden_0 = _jspx_th_h_hidden_0.doStartTag();
    if (_jspx_th_h_hidden_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_h_hidden_property_nobody.reuse(_jspx_th_h_hidden_0);
    return false;
  }

  private boolean _jspx_meth_h_hidden_1(javax.servlet.jsp.tagext.JspTag _jspx_th_h_form_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:hidden
    org.apache.struts.taglib.html.HiddenTag _jspx_th_h_hidden_1 = (org.apache.struts.taglib.html.HiddenTag) _jspx_tagPool_h_hidden_property_nobody.get(org.apache.struts.taglib.html.HiddenTag.class);
    _jspx_th_h_hidden_1.setPageContext(_jspx_page_context);
    _jspx_th_h_hidden_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_form_0);
    _jspx_th_h_hidden_1.setProperty("m(mid)");
    int _jspx_eval_h_hidden_1 = _jspx_th_h_hidden_1.doStartTag();
    if (_jspx_th_h_hidden_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_h_hidden_property_nobody.reuse(_jspx_th_h_hidden_1);
    return false;
  }

  private boolean _jspx_meth_h_hidden_2(javax.servlet.jsp.tagext.JspTag _jspx_th_h_form_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:hidden
    org.apache.struts.taglib.html.HiddenTag _jspx_th_h_hidden_2 = (org.apache.struts.taglib.html.HiddenTag) _jspx_tagPool_h_hidden_property_nobody.get(org.apache.struts.taglib.html.HiddenTag.class);
    _jspx_th_h_hidden_2.setPageContext(_jspx_page_context);
    _jspx_th_h_hidden_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_form_0);
    _jspx_th_h_hidden_2.setProperty("m(type)");
    int _jspx_eval_h_hidden_2 = _jspx_th_h_hidden_2.doStartTag();
    if (_jspx_th_h_hidden_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_h_hidden_property_nobody.reuse(_jspx_th_h_hidden_2);
    return false;
  }

  private boolean _jspx_meth_h_hidden_3(javax.servlet.jsp.tagext.JspTag _jspx_th_h_form_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:hidden
    org.apache.struts.taglib.html.HiddenTag _jspx_th_h_hidden_3 = (org.apache.struts.taglib.html.HiddenTag) _jspx_tagPool_h_hidden_property_nobody.get(org.apache.struts.taglib.html.HiddenTag.class);
    _jspx_th_h_hidden_3.setPageContext(_jspx_page_context);
    _jspx_th_h_hidden_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_form_0);
    _jspx_th_h_hidden_3.setProperty("m(fromReports)");
    int _jspx_eval_h_hidden_3 = _jspx_th_h_hidden_3.doStartTag();
    if (_jspx_th_h_hidden_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_h_hidden_property_nobody.reuse(_jspx_th_h_hidden_3);
    return false;
  }

  private boolean _jspx_meth_h_select_0(javax.servlet.jsp.tagext.JspTag _jspx_th_h_form_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:select
    org.apache.struts.taglib.html.SelectTag _jspx_th_h_select_0 = (org.apache.struts.taglib.html.SelectTag) _jspx_tagPool_h_select_property.get(org.apache.struts.taglib.html.SelectTag.class);
    _jspx_th_h_select_0.setPageContext(_jspx_page_context);
    _jspx_th_h_select_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_form_0);
    _jspx_th_h_select_0.setProperty("m(time)");
    int _jspx_eval_h_select_0 = _jspx_th_h_select_0.doStartTag();
    if (_jspx_eval_h_select_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_h_select_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_h_select_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_h_select_0.doInitBody();
      }
      do {
        out.write('\n');
        if (_jspx_meth_h_option_0(_jspx_th_h_select_0, _jspx_page_context))
          return true;
        out.write('\n');
        if (_jspx_meth_h_option_1(_jspx_th_h_select_0, _jspx_page_context))
          return true;
        out.write('\n');
        if (_jspx_meth_h_option_2(_jspx_th_h_select_0, _jspx_page_context))
          return true;
        out.write('\n');
        if (_jspx_meth_h_option_3(_jspx_th_h_select_0, _jspx_page_context))
          return true;
        out.write('\n');
        if (_jspx_meth_h_option_4(_jspx_th_h_select_0, _jspx_page_context))
          return true;
        out.write('\n');
        if (_jspx_meth_h_option_5(_jspx_th_h_select_0, _jspx_page_context))
          return true;
        out.write('\n');
        if (_jspx_meth_h_option_6(_jspx_th_h_select_0, _jspx_page_context))
          return true;
        out.write('\n');
        if (_jspx_meth_h_option_7(_jspx_th_h_select_0, _jspx_page_context))
          return true;
        out.write('\n');
        if (_jspx_meth_h_option_8(_jspx_th_h_select_0, _jspx_page_context))
          return true;
        out.write('\n');
        if (_jspx_meth_h_option_9(_jspx_th_h_select_0, _jspx_page_context))
          return true;
        out.write('\n');
        if (_jspx_meth_h_option_10(_jspx_th_h_select_0, _jspx_page_context))
          return true;
        out.write('\n');
        if (_jspx_meth_h_option_11(_jspx_th_h_select_0, _jspx_page_context))
          return true;
        out.write('\n');
        if (_jspx_meth_h_option_12(_jspx_th_h_select_0, _jspx_page_context))
          return true;
        out.write('\n');
        int evalDoAfterBody = _jspx_th_h_select_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_h_select_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_h_select_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_h_select_property.reuse(_jspx_th_h_select_0);
    return false;
  }

  private boolean _jspx_meth_h_option_0(javax.servlet.jsp.tagext.JspTag _jspx_th_h_select_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:option
    org.apache.struts.taglib.html.OptionTag _jspx_th_h_option_0 = (org.apache.struts.taglib.html.OptionTag) _jspx_tagPool_h_option_value.get(org.apache.struts.taglib.html.OptionTag.class);
    _jspx_th_h_option_0.setPageContext(_jspx_page_context);
    _jspx_th_h_option_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_select_0);
    _jspx_th_h_option_0.setValue("-1");
    int _jspx_eval_h_option_0 = _jspx_th_h_option_0.doStartTag();
    if (_jspx_eval_h_option_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_h_option_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_h_option_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_h_option_0.doInitBody();
      }
      do {
        out.write("Arbitrary Time Range");
        int evalDoAfterBody = _jspx_th_h_option_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_h_option_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_h_option_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_h_option_value.reuse(_jspx_th_h_option_0);
    return false;
  }

  private boolean _jspx_meth_h_option_1(javax.servlet.jsp.tagext.JspTag _jspx_th_h_select_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:option
    org.apache.struts.taglib.html.OptionTag _jspx_th_h_option_1 = (org.apache.struts.taglib.html.OptionTag) _jspx_tagPool_h_option_value.get(org.apache.struts.taglib.html.OptionTag.class);
    _jspx_th_h_option_1.setPageContext(_jspx_page_context);
    _jspx_th_h_option_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_select_0);
    _jspx_th_h_option_1.setValue("60");
    int _jspx_eval_h_option_1 = _jspx_th_h_option_1.doStartTag();
    if (_jspx_eval_h_option_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_h_option_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_h_option_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_h_option_1.doInitBody();
      }
      do {
        out.write("1 min");
        int evalDoAfterBody = _jspx_th_h_option_1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_h_option_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_h_option_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_h_option_value.reuse(_jspx_th_h_option_1);
    return false;
  }

  private boolean _jspx_meth_h_option_2(javax.servlet.jsp.tagext.JspTag _jspx_th_h_select_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:option
    org.apache.struts.taglib.html.OptionTag _jspx_th_h_option_2 = (org.apache.struts.taglib.html.OptionTag) _jspx_tagPool_h_option_value.get(org.apache.struts.taglib.html.OptionTag.class);
    _jspx_th_h_option_2.setPageContext(_jspx_page_context);
    _jspx_th_h_option_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_select_0);
    _jspx_th_h_option_2.setValue("180");
    int _jspx_eval_h_option_2 = _jspx_th_h_option_2.doStartTag();
    if (_jspx_eval_h_option_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_h_option_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_h_option_2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_h_option_2.doInitBody();
      }
      do {
        out.write("3 min");
        int evalDoAfterBody = _jspx_th_h_option_2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_h_option_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_h_option_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_h_option_value.reuse(_jspx_th_h_option_2);
    return false;
  }

  private boolean _jspx_meth_h_option_3(javax.servlet.jsp.tagext.JspTag _jspx_th_h_select_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:option
    org.apache.struts.taglib.html.OptionTag _jspx_th_h_option_3 = (org.apache.struts.taglib.html.OptionTag) _jspx_tagPool_h_option_value.get(org.apache.struts.taglib.html.OptionTag.class);
    _jspx_th_h_option_3.setPageContext(_jspx_page_context);
    _jspx_th_h_option_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_select_0);
    _jspx_th_h_option_3.setValue("600");
    int _jspx_eval_h_option_3 = _jspx_th_h_option_3.doStartTag();
    if (_jspx_eval_h_option_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_h_option_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_h_option_3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_h_option_3.doInitBody();
      }
      do {
        out.write("10 min");
        int evalDoAfterBody = _jspx_th_h_option_3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_h_option_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_h_option_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_h_option_value.reuse(_jspx_th_h_option_3);
    return false;
  }

  private boolean _jspx_meth_h_option_4(javax.servlet.jsp.tagext.JspTag _jspx_th_h_select_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:option
    org.apache.struts.taglib.html.OptionTag _jspx_th_h_option_4 = (org.apache.struts.taglib.html.OptionTag) _jspx_tagPool_h_option_value.get(org.apache.struts.taglib.html.OptionTag.class);
    _jspx_th_h_option_4.setPageContext(_jspx_page_context);
    _jspx_th_h_option_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_select_0);
    _jspx_th_h_option_4.setValue("1800");
    int _jspx_eval_h_option_4 = _jspx_th_h_option_4.doStartTag();
    if (_jspx_eval_h_option_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_h_option_4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_h_option_4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_h_option_4.doInitBody();
      }
      do {
        out.write("30 min");
        int evalDoAfterBody = _jspx_th_h_option_4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_h_option_4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_h_option_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_h_option_value.reuse(_jspx_th_h_option_4);
    return false;
  }

  private boolean _jspx_meth_h_option_5(javax.servlet.jsp.tagext.JspTag _jspx_th_h_select_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:option
    org.apache.struts.taglib.html.OptionTag _jspx_th_h_option_5 = (org.apache.struts.taglib.html.OptionTag) _jspx_tagPool_h_option_value.get(org.apache.struts.taglib.html.OptionTag.class);
    _jspx_th_h_option_5.setPageContext(_jspx_page_context);
    _jspx_th_h_option_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_select_0);
    _jspx_th_h_option_5.setValue("3600");
    int _jspx_eval_h_option_5 = _jspx_th_h_option_5.doStartTag();
    if (_jspx_eval_h_option_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_h_option_5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_h_option_5.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_h_option_5.doInitBody();
      }
      do {
        out.write("1 hr");
        int evalDoAfterBody = _jspx_th_h_option_5.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_h_option_5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_h_option_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_h_option_value.reuse(_jspx_th_h_option_5);
    return false;
  }

  private boolean _jspx_meth_h_option_6(javax.servlet.jsp.tagext.JspTag _jspx_th_h_select_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:option
    org.apache.struts.taglib.html.OptionTag _jspx_th_h_option_6 = (org.apache.struts.taglib.html.OptionTag) _jspx_tagPool_h_option_value.get(org.apache.struts.taglib.html.OptionTag.class);
    _jspx_th_h_option_6.setPageContext(_jspx_page_context);
    _jspx_th_h_option_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_select_0);
    _jspx_th_h_option_6.setValue("21600");
    int _jspx_eval_h_option_6 = _jspx_th_h_option_6.doStartTag();
    if (_jspx_eval_h_option_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_h_option_6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_h_option_6.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_h_option_6.doInitBody();
      }
      do {
        out.write("6 hr");
        int evalDoAfterBody = _jspx_th_h_option_6.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_h_option_6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_h_option_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_h_option_value.reuse(_jspx_th_h_option_6);
    return false;
  }

  private boolean _jspx_meth_h_option_7(javax.servlet.jsp.tagext.JspTag _jspx_th_h_select_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:option
    org.apache.struts.taglib.html.OptionTag _jspx_th_h_option_7 = (org.apache.struts.taglib.html.OptionTag) _jspx_tagPool_h_option_value.get(org.apache.struts.taglib.html.OptionTag.class);
    _jspx_th_h_option_7.setPageContext(_jspx_page_context);
    _jspx_th_h_option_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_select_0);
    _jspx_th_h_option_7.setValue("43200");
    int _jspx_eval_h_option_7 = _jspx_th_h_option_7.doStartTag();
    if (_jspx_eval_h_option_7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_h_option_7 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_h_option_7.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_h_option_7.doInitBody();
      }
      do {
        out.write("12 hr");
        int evalDoAfterBody = _jspx_th_h_option_7.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_h_option_7 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_h_option_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_h_option_value.reuse(_jspx_th_h_option_7);
    return false;
  }

  private boolean _jspx_meth_h_option_8(javax.servlet.jsp.tagext.JspTag _jspx_th_h_select_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:option
    org.apache.struts.taglib.html.OptionTag _jspx_th_h_option_8 = (org.apache.struts.taglib.html.OptionTag) _jspx_tagPool_h_option_value.get(org.apache.struts.taglib.html.OptionTag.class);
    _jspx_th_h_option_8.setPageContext(_jspx_page_context);
    _jspx_th_h_option_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_select_0);
    _jspx_th_h_option_8.setValue("86400");
    int _jspx_eval_h_option_8 = _jspx_th_h_option_8.doStartTag();
    if (_jspx_eval_h_option_8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_h_option_8 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_h_option_8.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_h_option_8.doInitBody();
      }
      do {
        out.write("1 day");
        int evalDoAfterBody = _jspx_th_h_option_8.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_h_option_8 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_h_option_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_h_option_value.reuse(_jspx_th_h_option_8);
    return false;
  }

  private boolean _jspx_meth_h_option_9(javax.servlet.jsp.tagext.JspTag _jspx_th_h_select_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:option
    org.apache.struts.taglib.html.OptionTag _jspx_th_h_option_9 = (org.apache.struts.taglib.html.OptionTag) _jspx_tagPool_h_option_value.get(org.apache.struts.taglib.html.OptionTag.class);
    _jspx_th_h_option_9.setPageContext(_jspx_page_context);
    _jspx_th_h_option_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_select_0);
    _jspx_th_h_option_9.setValue("259200");
    int _jspx_eval_h_option_9 = _jspx_th_h_option_9.doStartTag();
    if (_jspx_eval_h_option_9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_h_option_9 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_h_option_9.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_h_option_9.doInitBody();
      }
      do {
        out.write("3 days");
        int evalDoAfterBody = _jspx_th_h_option_9.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_h_option_9 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_h_option_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_h_option_value.reuse(_jspx_th_h_option_9);
    return false;
  }

  private boolean _jspx_meth_h_option_10(javax.servlet.jsp.tagext.JspTag _jspx_th_h_select_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:option
    org.apache.struts.taglib.html.OptionTag _jspx_th_h_option_10 = (org.apache.struts.taglib.html.OptionTag) _jspx_tagPool_h_option_value.get(org.apache.struts.taglib.html.OptionTag.class);
    _jspx_th_h_option_10.setPageContext(_jspx_page_context);
    _jspx_th_h_option_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_select_0);
    _jspx_th_h_option_10.setValue("604800");
    int _jspx_eval_h_option_10 = _jspx_th_h_option_10.doStartTag();
    if (_jspx_eval_h_option_10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_h_option_10 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_h_option_10.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_h_option_10.doInitBody();
      }
      do {
        out.write("1 week");
        int evalDoAfterBody = _jspx_th_h_option_10.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_h_option_10 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_h_option_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_h_option_value.reuse(_jspx_th_h_option_10);
    return false;
  }

  private boolean _jspx_meth_h_option_11(javax.servlet.jsp.tagext.JspTag _jspx_th_h_select_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:option
    org.apache.struts.taglib.html.OptionTag _jspx_th_h_option_11 = (org.apache.struts.taglib.html.OptionTag) _jspx_tagPool_h_option_value.get(org.apache.struts.taglib.html.OptionTag.class);
    _jspx_th_h_option_11.setPageContext(_jspx_page_context);
    _jspx_th_h_option_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_select_0);
    _jspx_th_h_option_11.setValue("1209600");
    int _jspx_eval_h_option_11 = _jspx_th_h_option_11.doStartTag();
    if (_jspx_eval_h_option_11 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_h_option_11 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_h_option_11.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_h_option_11.doInitBody();
      }
      do {
        out.write("2 weeks");
        int evalDoAfterBody = _jspx_th_h_option_11.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_h_option_11 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_h_option_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_h_option_value.reuse(_jspx_th_h_option_11);
    return false;
  }

  private boolean _jspx_meth_h_option_12(javax.servlet.jsp.tagext.JspTag _jspx_th_h_select_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:option
    org.apache.struts.taglib.html.OptionTag _jspx_th_h_option_12 = (org.apache.struts.taglib.html.OptionTag) _jspx_tagPool_h_option_value.get(org.apache.struts.taglib.html.OptionTag.class);
    _jspx_th_h_option_12.setPageContext(_jspx_page_context);
    _jspx_th_h_option_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_select_0);
    _jspx_th_h_option_12.setValue("2419200");
    int _jspx_eval_h_option_12 = _jspx_th_h_option_12.doStartTag();
    if (_jspx_eval_h_option_12 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_h_option_12 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_h_option_12.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_h_option_12.doInitBody();
      }
      do {
        out.write("4 weeks");
        int evalDoAfterBody = _jspx_th_h_option_12.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_h_option_12 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_h_option_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_h_option_value.reuse(_jspx_th_h_option_12);
    return false;
  }

  private boolean _jspx_meth_h_text_0(javax.servlet.jsp.tagext.JspTag _jspx_th_h_form_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:text
    org.apache.struts.taglib.html.TextTag _jspx_th_h_text_0 = (org.apache.struts.taglib.html.TextTag) _jspx_tagPool_h_text_size_property_nobody.get(org.apache.struts.taglib.html.TextTag.class);
    _jspx_th_h_text_0.setPageContext(_jspx_page_context);
    _jspx_th_h_text_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_form_0);
    _jspx_th_h_text_0.setProperty("m(startTime)");
    _jspx_th_h_text_0.setSize("25");
    int _jspx_eval_h_text_0 = _jspx_th_h_text_0.doStartTag();
    if (_jspx_th_h_text_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_h_text_size_property_nobody.reuse(_jspx_th_h_text_0);
    return false;
  }

  private boolean _jspx_meth_h_text_1(javax.servlet.jsp.tagext.JspTag _jspx_th_h_form_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:text
    org.apache.struts.taglib.html.TextTag _jspx_th_h_text_1 = (org.apache.struts.taglib.html.TextTag) _jspx_tagPool_h_text_size_property_nobody.get(org.apache.struts.taglib.html.TextTag.class);
    _jspx_th_h_text_1.setPageContext(_jspx_page_context);
    _jspx_th_h_text_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_form_0);
    _jspx_th_h_text_1.setProperty("m(stopTime)");
    _jspx_th_h_text_1.setSize("25");
    int _jspx_eval_h_text_1 = _jspx_th_h_text_1.doStartTag();
    if (_jspx_th_h_text_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_h_text_size_property_nobody.reuse(_jspx_th_h_text_1);
    return false;
  }

  private boolean _jspx_meth_h_submit_0(javax.servlet.jsp.tagext.JspTag _jspx_th_h_form_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:submit
    org.apache.struts.taglib.html.SubmitTag _jspx_th_h_submit_0 = (org.apache.struts.taglib.html.SubmitTag) _jspx_tagPool_h_submit_value_nobody.get(org.apache.struts.taglib.html.SubmitTag.class);
    _jspx_th_h_submit_0.setPageContext(_jspx_page_context);
    _jspx_th_h_submit_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_form_0);
    _jspx_th_h_submit_0.setValue("Redraw Graph");
    int _jspx_eval_h_submit_0 = _jspx_th_h_submit_0.doStartTag();
    if (_jspx_th_h_submit_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_h_submit_value_nobody.reuse(_jspx_th_h_submit_0);
    return false;
  }

  private boolean _jspx_meth_c_if_0(javax.servlet.jsp.tagext.JspTag _jspx_th_h_form_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_0.setPageContext(_jspx_page_context);
    _jspx_th_c_if_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_form_0);
    _jspx_th_c_if_0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fromReports}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
    if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n <input type='button' value=\"Back\" onClick='location=\"/jddac/forms/reports.do\"'>\n");
        int evalDoAfterBody = _jspx_th_c_if_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
    return false;
  }

  private boolean _jspx_meth_c_if_1(javax.servlet.jsp.tagext.JspTag _jspx_th_h_form_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_1.setPageContext(_jspx_page_context);
    _jspx_th_c_if_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_form_0);
    _jspx_th_c_if_1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${! fromReports}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
    if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n <input type='button' value=\"Back\" onClick='location=\"/jddac/forms/probes.do?cmd=View&pid=");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pid}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\"'>\n");
        int evalDoAfterBody = _jspx_th_c_if_1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
    return false;
  }

  private boolean _jspx_meth_c_if_2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_2 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_2.setPageContext(_jspx_page_context);
    _jspx_th_c_if_2.setParent(null);
    _jspx_th_c_if_2.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${isPublic}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
    if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n  <a href='/reports/graph.do?mid=");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${mid}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("&type=");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${m.type}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("'>Publicly viewable link</a>\n");
        int evalDoAfterBody = _jspx_th_c_if_2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
    return false;
  }
}
