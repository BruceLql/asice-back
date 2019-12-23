<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8"/>
    <title></title>
    <meta name="description" content="overview & stats"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link href="static/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="static/css/bootstrap-responsive.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="static/css/font-awesome.min.css"/>
    <!-- 下拉框 -->
    <link rel="stylesheet" href="static/css/chosen.css"/>
    <link rel="stylesheet" href="static/css/email.css"/>

    <link rel="stylesheet" href="static/css/ace.min.css"/>
    <link rel="stylesheet" href="static/css/ace-responsive.min.css"/>
    <link rel="stylesheet" href="static/css/ace-skins.min.css"/>

    <link rel="stylesheet" href="static/css/datepicker.css"/><!-- 日期框 -->
    <script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="static/js/jquery.tips.js"></script>

    <script type="text/javascript"></script>
</head>
<body>
<form action="" name="Form" id="Form" method="post">
    <input type="hidden" name="HISTORYEMAILRECORD_ID" id="HISTORYEMAILRECORD_ID" value="${pd.HISTORYEMAILRECORD_ID}"/>
    <div id="zhongxin" style="margin:0 auto; width:95%; height:100%; border:1px solid #F00; ">

        <div class="readmailinfo" style="position:relative;z-index:2;zoom:1; background: #ceb2ea;">
            <span id="subjectTip"></span>
            <table style="height:24px" border="0" cellspacing="0" cellpadding="0">
                <tbody>
                <tr>
                    <td height="24" valign="middle" style="word-break:break-all;padding:9px 8px 2px 14px;"
                        class="txt_left settingtable readmail_subject">
                        <div class="qm_left" style="padding-bottom:3px;">
                            <b><span id="subject" class="sub_title ">${pd.EMAIL_TITLE}</span></b>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
            <table border="0" cellspacing="0" cellpadding="0">
                <tbody>
                <tr>
                    <td style="padding-left:14px;" class="settingtable txt_left">
                        <span class="addrtitle">发件人：</span>
                        <span>
							<img name="qqplusimg" key="-3711466260"
                                 addr="2466133172@qq.com" mailnick="2466133172"
                                 fmt="true" align="absmiddle"
                                 style="display:none;margin-right:3px;">
							<span rejecthtml="rejectionhtml" t="1" e="2466133172@qq.com" u="-3711466260" n="2466133172"
                                  addrvip="" filinghtml="filinghtml" mailhtml="operhidepanel"
                                  mailid="ZC2320-8_4cuvV80IN_DyxxADF4e9c" xqs="0" fid="1" cfid="1" dm="qq.com"
                                  ibm="false"
                                  iba="0" bcd="0" ctd="103" isad="0" mop="1" sa="0" addrid="-1" se="1">

								<b t="u" class="grn">${pd.SENDER_EMAIL}</b>&nbsp;
							</span>&nbsp;
						</span>

                    </td>

                </tr>
                </tbody>
            </table>
            <table border="0" cellspacing="0" cellpadding="0">
                <tbody>
                <tr>
                    <td style="word-break:break-all;padding:2px 12px 0 14px;line-height:19px;" width="99%"
                        class="settingtable txt_left">
                        <span class="addrtitle">时&nbsp;&nbsp;&nbsp;间：</span>
                        <b class="tcolor">${pd.EMAIL_SENDTIME} </b>
                    </td>
                </tr>
                <tr>
                    <td style="padding:0 0 0 14px;line-height:19px;" class="settingtable txt_left">
                        <div>
                            <div class="addrtitle nowrap" style="position:absolute;">收件人：</div>
                            <div style="padding-left:48px;font-size:12px;overflow:hidden; zoom:1;">
								<span style="white-space:nowrap;height:18px;line-height:18px; " class="left"
                                      rejecthtml="rejectionhtml" filinghtml="filinghtml" mailhtml="operhidepanel"
                                      mailid="ZC2320-8_4cuvV80IN_DyxxADF4e9c" xqs="0" fid="1" cfid="1" dm="qq.com"
                                      ibm="false" iba="0" bcd="0" ctd="103" sd="0" se="0">
									${pd.RECIPIENT_EMAIL}&nbsp;
									<b t="u" class="tcolor">
										&lt;${pd.RECIPIENT_EMAIL}&gt;
									</b>
								</span>
                            </div>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
            <div class="txt_left">
                <div class="attbg" id="reminderContainer_mailid:ZC2320-8_4cuvV80IN_DyxxADF4e9c"
                     style="padding:5px 14px;display:none;"></div>
                <div id="spam" module="qmAntiSpam" name="spam" style="zoom:1;">
                    <div class="alert_tips_red">该邮件可能包含危害您银行卡、网络帐户或其他虚拟财产安全的内容，建议您不要轻信。
                        <a href="javascript:;" style="font-weight:normal" ck="reportSpam" mimefrom="2466133172@qq.com"
                           mailfrom="" yellow="true">举报垃圾邮件</a>
                    </div>
                </div>
            </div>
        </div>
        <div id="mailContentContainer" class="qmbox qm_con_body_content qqmail_webmail_only"
             style="height: auto;word-wrap: break-word;font-size: 14px;">
            ${pd.EMAIL_CONTENT}
        </div>
    </div>

</form>


<!-- 引入 -->
<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
<script src="static/js/bootstrap.min.js"></script>
<script src="static/js/ace-elements.min.js"></script>
<script src="static/js/ace.min.js"></script>
<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
<script type="text/javascript">
    $(top.hangge());
    $(function () {

        //单选框
        $(".chzn-select").chosen();
        $(".chzn-select-deselect").chosen({allow_single_deselect: true});

        //日期框
        $('.date-picker').datepicker();

    });

</script>
</body>
</html>