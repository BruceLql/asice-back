package com.mbfw.controller.business.historyemailrecord;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.mbfw.service.system.appuser.AppuserService;
import com.mbfw.service.system.user.UserService;
import com.mbfw.util.mail.SimpleMailSender;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mbfw.controller.base.BaseController;
import com.mbfw.entity.Page;
import com.mbfw.util.AppUtil;
import com.mbfw.util.ObjectExcelView;
import com.mbfw.util.Const;
import com.mbfw.util.PageData;
import com.mbfw.util.Tools;
import com.mbfw.util.Jurisdiction;
import com.mbfw.service.business.historyemailrecord.HistoryEmailRecordService;

/**
 * 类名称：HistoryEmailRecordController
 * 创建人：研发中心
 * 创建时间：2019-12-20
 */
@Controller
@RequestMapping(value = "/historyemailrecord")
public class HistoryEmailRecordController extends BaseController {

    String menuUrl = "historyemailrecord/list.do"; //菜单地址(权限用)
    @Resource(name = "historyemailrecordService")
    private HistoryEmailRecordService historyemailrecordService;
    @Resource(name = "userService")
    private UserService userService;
    @Resource(name = "appuserService")
    private AppuserService appuserService;
    /**
     * go 写邮件 界面
     */
    @RequestMapping(value = "/goWriteEmail")
    public ModelAndView writeEmail() throws Exception {
        logBefore(logger, "==== writeEmail 写邮件 ====");
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "add")) {
            return null;
        } //校验权限
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        pd.put("HISTORYEMAILRECORD_ID", this.get32UUID());    //主键
        pd.put("SENDER_USERID", "");    //发件人ID
        pd.put("SENDER_EMAIL", "");    //发件人邮箱账户
        pd.put("RECIPIENT_EMAIL", "");    //收件人邮箱账户
        pd.put("EMAIL_TITLE", "");    //邮件标题
        pd.put("EMAIL_CONTENT", "");    //邮件正文
        pd.put("EMAIL_SENDTIME", Tools.date2Str(new Date()));    //邮件发送时间
        pd.put("EMAIL_SENDSTATE", "");    //邮件发送状态(0：发送失败；1:发送成功 2：其他状态)
        pd.put("CREATETIME", Tools.date2Str(new Date()));    //创建时间
        pd.put("STATUS", "");    //状态(0:正常 -1：失效)
//        historyemailrecordService.save(pd);
        mv.addObject("msg", "success");
        mv.setViewName("business/email/writeEmail");
        return mv;
    }

    /**
     * 点击发送后保存邮件记录
     */
    @RequestMapping(value = "/saveEmail")
    public PageData saveEmail() {
        PageData result = new PageData();
        result.put("result", true);
        logBefore(logger, "==== 点击发送按钮后，保存邮件记录 ====");

        if (!Jurisdiction.buttonJurisdiction(menuUrl, "add")) {
            return null;
        } //校验权限
        try {
            PageData pd = new PageData();
            pd = this.getPageData();
//            pd.put("HISTORYEMAILRECORD_ID", this.get32UUID());    //主键
            pd.put("SENDER_USERID", "");    //发件人ID
            pd.put("SENDER_EMAIL", "");    //发件人邮箱账户
            pd.put("RECIPIENT_EMAIL", "");    //收件人邮箱账户
            pd.put("EMAIL_TITLE", "");    //邮件标题
            pd.put("EMAIL_CONTENT", "");    //邮件正文
            pd.put("EMAIL_SENDTIME", Tools.date2Str(new Date()));    //邮件发送时间
            pd.put("EMAIL_SENDSTATE", "");    //邮件发送状态(0：发送失败；1:发送成功 2：其他状态)
            pd.put("CREATETIME", Tools.date2Str(new Date()));    //创建时间
            pd.put("STATUS", "");    //状态(0:正常 -1：失效)
            historyemailrecordService.save(pd);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result", false);
            result.put("msg", "发送成功，保存成功");
        }
        return result;
    }
    /**
     * 发送电子邮件
     */
    @RequestMapping(value = "/sendEmail")
    @ResponseBody
    public Object sendEmail() {
        PageData pd = new PageData();
        pd = this.getPageData();
        Map<String, Object> map = new HashMap<String, Object>();
        String msg = "ok"; // 发送状态
        int count = 0; // 统计发送成功条数
        int zcount = 0; // 理论条数

        String strEMAIL = Tools.readTxtFile(Const.EMAIL); // 读取邮件配置

        List<PageData> pdList = new ArrayList<PageData>();
        List<PageData> recordList = new ArrayList<PageData>(); // 邮件记录

        String toEMAIL = pd.getString("EMAIL"); // 对方邮箱
        String TITLE = pd.getString("TITLE"); // 标题
        String CONTENT = pd.getString("CONTENT"); // 内容
        String TYPE = pd.getString("TYPE"); // 类型
        String isAll = pd.getString("isAll"); // 是否发送给全体成员 yes or no

        String fmsg = pd.getString("fmsg"); // 判断是系统用户还是会员 "appuser"为会员用户

        if (null != strEMAIL && !"".equals(strEMAIL)) {
            String strEM[] = strEMAIL.split(",mbfw,");
            if (strEM.length == 4) {
                if ("yes".endsWith(isAll)) {
                    try {
                        List<PageData> userList = new ArrayList<PageData>();

                        userList = "appuser".equals(fmsg) ? appuserService.listAllUser(pd) : userService.listAllUser(pd);

                        zcount = userList.size();
                        try {
                            for (int i = 0; i < userList.size(); i++) {
                                if (Tools.checkEmail(userList.get(i).getString("EMAIL"))) { // 邮箱格式不对就跳过
                                    // String SMTP, String PORT, String EMAIL, String PAW, String toEMAIL, String TITLE, String CONTENT, String TYPE
                                    SimpleMailSender.sendEmail(strEM[0], strEM[1], strEM[2], strEM[3], userList.get(i).getString("EMAIL"), TITLE, CONTENT, TYPE);// 调用发送邮件函数
                                    count++;
                                    PageData pdEmail = new PageData();
//                                    pdEmail.put("HISTORYEMAILRECORD_ID", this.get32UUID());    //主键
                                    pdEmail.put("SENDER_USERID", pd.getUser().getUSER_ID());    //发件人ID
                                    pdEmail.put("SENDER_EMAIL", strEM[2]);    //发件人邮箱账户
                                    pdEmail.put("RECIPIENT_EMAIL", toEMAIL);    //收件人邮箱账户
                                    pdEmail.put("EMAIL_TITLE", TITLE);    //邮件标题
                                    pdEmail.put("EMAIL_CONTENT", CONTENT);    //邮件正文
                                    pdEmail.put("EMAIL_SENDTIME", Tools.date2Str(new Date()));    //邮件发送时间
                                    pdEmail.put("EMAIL_SENDSTATE", "1");    //邮件发送状态(0：发送失败；1:发送成功 2：其他状态)
                                    pdEmail.put("CREATETIME", Tools.date2Str(new Date()));    //创建时间
                                    pdEmail.put("STATUS", "0");    //状态(0:正常 -1：失效)
                                    recordList.add(pdEmail);
                                } else {
                                    continue;
                                }
                            }
                            msg = "ok";
                        } catch (Exception e) {
                            msg = "error";
                        }

                    } catch (Exception e) {
                        msg = "error";
                    }
                } else {
                    toEMAIL = toEMAIL.replaceAll("；", ";");
                    toEMAIL = toEMAIL.replaceAll(" ", "");
                    String[] arrTITLE = toEMAIL.split(";");
                    zcount = arrTITLE.length;
                    try {
                        for (int i = 0; i < arrTITLE.length; i++) {
                            if (Tools.checkEmail(arrTITLE[i])) { // 邮箱格式不对就跳过
                                // String SMTP, String PORT, String EMAIL, String PAW, String toEMAIL, String TITLE, String CONTENT, String TYPE
                                SimpleMailSender.sendEmail(strEM[0], strEM[1], strEM[2], strEM[3], arrTITLE[i], TITLE, CONTENT, TYPE);// 调用发送邮件函数
                                count++;
                                PageData pdEmail = new PageData();
//                                    pdEmail.put("HISTORYEMAILRECORD_ID", this.get32UUID());    //主键
                                pdEmail.put("SENDER_USERID", pd.getUser().getUSER_ID());    //发件人ID
                                pdEmail.put("SENDER_EMAIL", strEM[2]);    //发件人邮箱账户
                                pdEmail.put("RECIPIENT_EMAIL", arrTITLE[i]);    //收件人邮箱账户
                                pdEmail.put("EMAIL_TITLE", TITLE);    //邮件标题
                                pdEmail.put("EMAIL_CONTENT", CONTENT);    //邮件正文
                                pdEmail.put("EMAIL_SENDTIME", Tools.date2Str(new Date()));    //邮件发送时间
                                pdEmail.put("EMAIL_SENDSTATE", "1");    //邮件发送状态(0：发送失败；1:发送成功 2：其他状态)
                                pdEmail.put("CREATETIME", Tools.date2Str(new Date()));    //创建时间
                                pdEmail.put("STATUS", "0");    //状态(0:正常 -1：失效)
                                recordList.add(pdEmail);
                            } else {
                                continue;
                            }
                        }
                        msg = "ok";
                    } catch (Exception e) {
                        e.printStackTrace();
                        msg = "error";
                    }
                }
                try{
                    if(recordList != null && recordList.size() >0 ){
                        // 批量保存邮件记录
                        historyemailrecordService.saveBatch(recordList);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            } else {
                msg = "error";
            }
        } else {
            msg = "error";
        }
        pd.put("msg", msg);
        pd.put("count", count); // 成功数
        pd.put("ecount", zcount - count); // 失败数
        pdList.add(pd);
        map.put("list", pdList);
        return AppUtil.returnObject(pd, map);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/save")
    public ModelAndView save() throws Exception {
        logBefore(logger, "新增HistoryEmailRecord");
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "add")) {
            return null;
        } //校验权限
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        pd.put("HISTORYEMAILRECORD_ID", this.get32UUID());    //主键
        pd.put("SENDER_USERID", "");    //发件人ID
        pd.put("SENDER_EMAIL", "");    //发件人邮箱账户
        pd.put("RECIPIENT_EMAIL", "");    //收件人邮箱账户
        pd.put("EMAIL_TITLE", "");    //邮件标题
        pd.put("EMAIL_CONTENT", "");    //邮件正文
        pd.put("EMAIL_SENDTIME", Tools.date2Str(new Date()));    //邮件发送时间
        pd.put("EMAIL_SENDSTATE", "");    //邮件发送状态(0：发送失败；1:发送成功 2：其他状态)
        pd.put("CREATETIME", Tools.date2Str(new Date()));    //创建时间
        pd.put("STATUS", "");    //状态(0:正常 -1：失效)
        historyemailrecordService.save(pd);
        mv.addObject("msg", "success");
        mv.setViewName("save_result");
        return mv;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    public void delete(PrintWriter out) {
        logBefore(logger, "删除HistoryEmailRecord");
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "del")) {
            return;
        } //校验权限
        PageData pd = new PageData();
        try {
            pd = this.getPageData();
            historyemailrecordService.delete(pd);
            out.write("success");
            out.close();
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }

    }

    /**
     * 修改
     */
    @RequestMapping(value = "/edit")
    public ModelAndView edit() throws Exception {
        logBefore(logger, "修改HistoryEmailRecord");
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "edit")) {
            return null;
        } //校验权限
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        historyemailrecordService.edit(pd);
        mv.addObject("msg", "success");
        mv.setViewName("save_result");
        return mv;
    }

    /**
     * 列表
     */
    @RequestMapping(value = "/list")
    public ModelAndView list(Page page) {
        logBefore(logger, "列表HistoryEmailRecord");
        //if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        try {
            pd = this.getPageData();
            page.setPd(pd);
            List<PageData> varList = historyemailrecordService.list(page);    //列出HistoryEmailRecord列表
            mv.setViewName("business/historyemailrecord/historyemailrecord_list");
            mv.addObject("varList", varList);
            mv.addObject("pd", pd);
            mv.addObject(Const.SESSION_QX, this.getHC());    //按钮权限
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        return mv;
    }

    /**
     * 去新增页面
     */
    @RequestMapping(value = "/goAdd")
    public ModelAndView goAdd() {
        logBefore(logger, "去新增HistoryEmailRecord页面");
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        try {
            mv.setViewName("business/historyemailrecord/historyemailrecord_edit");
            mv.addObject("msg", "save");
            mv.addObject("pd", pd);
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        return mv;
    }

    /**
     * 去修改页面
     */
    @RequestMapping(value = "/goEdit")
    public ModelAndView goEdit() {
        logBefore(logger, "去修改HistoryEmailRecord页面");
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        try {
            pd = historyemailrecordService.findById(pd);    //根据ID读取
            mv.setViewName("business/historyemailrecord/historyemailrecord_edit");
            mv.addObject("msg", "edit");
            mv.addObject("pd", pd);
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        return mv;
    }

    /**
     * 查看邮件详情
     */
    @RequestMapping(value = "/showEmailContent")
    public ModelAndView showEmailContent() {
        logBefore(logger, "在HistoryEmailRecord页面 点击查看按钮 查看邮件详情");
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        try {
            pd = historyemailrecordService.findById(pd);    //根据ID读取
            mv.setViewName("business/historyemailrecord/historyemailrecord_emailDetatil");
            mv.addObject("msg", "edit");
            mv.addObject("pd", pd);
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        return mv;
    }












    /**
     * 批量删除
     */
    @RequestMapping(value = "/deleteAll")
    @ResponseBody
    public Object deleteAll() {
        logBefore(logger, "批量删除HistoryEmailRecord");
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "dell")) {
            return null;
        } //校验权限
        PageData pd = new PageData();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            pd = this.getPageData();
            List<PageData> pdList = new ArrayList<PageData>();
            String DATA_IDS = pd.getString("DATA_IDS");
            if (null != DATA_IDS && !"".equals(DATA_IDS)) {
                String ArrayDATA_IDS[] = DATA_IDS.split(",");
                historyemailrecordService.deleteAll(ArrayDATA_IDS);
                pd.put("msg", "ok");
            } else {
                pd.put("msg", "no");
            }
            pdList.add(pd);
            map.put("list", pdList);
        } catch (Exception e) {
            logger.error(e.toString(), e);
        } finally {
            logAfter(logger);
        }
        return AppUtil.returnObject(pd, map);
    }

    /*
     * 导出到excel
     * @return
     */
    @RequestMapping(value = "/excel")
    public ModelAndView exportExcel() {
        logBefore(logger, "导出HistoryEmailRecord到excel");
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "cha")) {
            return null;
        }
        ModelAndView mv = new ModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        try {
            Map<String, Object> dataMap = new HashMap<String, Object>();
            List<String> titles = new ArrayList<String>();
            titles.add("发件人ID");    //1
            titles.add("发件人邮箱账户");    //2
            titles.add("收件人邮箱账户");    //3
            titles.add("邮件标题");    //4
            titles.add("邮件正文");    //5
            titles.add("邮件发送时间");    //6
            titles.add("邮件发送状态(0：发送失败；1:发送成功 2：其他状态)");    //7
            titles.add("创建时间");    //8
            titles.add("状态(0:正常 -1：失效)");    //9
            titles.add("备用");    //10
            titles.add("备用2");    //11
            dataMap.put("titles", titles);
            List<PageData> varOList = historyemailrecordService.listAll(pd);
            List<PageData> varList = new ArrayList<PageData>();
            for (int i = 0; i < varOList.size(); i++) {
                PageData vpd = new PageData();
                vpd.put("var1", varOList.get(i).getString("SENDER_USERID"));    //1
                vpd.put("var2", varOList.get(i).getString("SENDER_EMAIL"));    //2
                vpd.put("var3", varOList.get(i).getString("RECIPIENT_EMAIL"));    //3
                vpd.put("var4", varOList.get(i).getString("EMAIL_TITLE"));    //4
                vpd.put("var5", varOList.get(i).getString("EMAIL_CONTENT"));    //5
                vpd.put("var6", varOList.get(i).getString("EMAIL_SENDTIME"));    //6
                vpd.put("var7", varOList.get(i).getString("EMAIL_SENDSTATE"));    //7
                vpd.put("var8", varOList.get(i).getString("CREATETIME"));    //8
                vpd.put("var9", varOList.get(i).getString("STATUS"));    //9
                vpd.put("var10", varOList.get(i).getString("HISTORY_001"));    //10
                vpd.put("var11", varOList.get(i).getString("HISTORY_002"));    //11
                varList.add(vpd);
            }
            dataMap.put("varList", varList);
            ObjectExcelView erv = new ObjectExcelView();
            mv = new ModelAndView(erv, dataMap);
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        return mv;
    }

    /* ===============================权限================================== */
    public Map<String, String> getHC() {
        Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
        Session session = currentUser.getSession();
        return (Map<String, String>) session.getAttribute(Const.SESSION_QX);
    }
    /* ===============================权限================================== */

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
    }
}
