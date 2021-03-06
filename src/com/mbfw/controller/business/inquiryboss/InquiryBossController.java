package com.mbfw.controller.business.inquiryboss;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.mbfw.entity.system.Role;
import com.mbfw.entity.system.User;
import com.mbfw.service.business.email.EmailService;
import com.mbfw.service.system.user.UserService;
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
import com.mbfw.service.business.inquiryboss.InquiryBossService;

/**
 * 类名称：InquiryBossController
 * 创建人：研发中心
 * 创建时间：2019-12-10
 */
@Controller
@RequestMapping(value = "/inquiryboss")
public class InquiryBossController extends BaseController {

    String menuUrl = "inquiryboss/list.do"; //菜单地址(权限用)
    @Resource(name = "inquirybossService")
    private InquiryBossService inquirybossService;
    @Resource(name = "userService")
    private UserService userService;
    @Resource(name = "emailService")
    private EmailService emailService;

    /**
     * 新增
     */
    @RequestMapping(value = "/test")
    public PageData test() throws Exception {
        logBefore(logger, "新增InquiryBoss");

        PageData pd = new PageData();

        pd = emailService.testJetx();
        System.out.println("==================:" + pd.toJsonStr());
        return pd;
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/save")
    public ModelAndView save() throws Exception {
        logBefore(logger, "新增InquiryBoss");
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "add")) {
            return null;
        } //校验权限
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();

//		pd.put("INQUIRY_BOSS_ID", "");	//客户id
        pd.put("INQUIRY_BOSS_CREATETIME", Tools.date2Str(new Date()));    //客户创建时间
        pd.put("INQUIRY_BOSS_UPDATETIME", Tools.date2Str(new Date()));    //客户更新时间
        pd.put("STATUS", "0");    //状态
        pd.put("USER_ID", pd.getUser().getUSER_ID());    //状态
        inquirybossService.save(pd);
        mv.addObject("msg", "success");
        mv.setViewName("save_result");
        return mv;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    public void delete(PrintWriter out) {
        logBefore(logger, "删除InquiryBoss");
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "del")) {
            return;
        } //校验权限
        PageData pd = new PageData();
        try {
            pd = this.getPageData();
            inquirybossService.delete(pd);
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
        logBefore(logger, "修改InquiryBoss");
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "edit")) {
            return null;
        } //校验权限
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        inquirybossService.edit(pd);
        mv.addObject("msg", "success");
        mv.setViewName("save_result");
        return mv;
    }

    /**
     * 列表
     */
    @RequestMapping(value = "/list")
    public ModelAndView list(Page page) {
        logBefore(logger, "列表InquiryBoss");
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "cha")) {
            return null;
        } //校验权限
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        try {
            pd = this.getPageData();
            List<PageData> userList = new ArrayList<PageData>();
            String role_id = pd.getUser().getROLE_ID();
            if (Tools.notEmpty(role_id)) {
                if ("1".equals(role_id) || "2".equals(role_id)) {
                    // 系统管理员 和 超级管理员角色 前端传啥就是啥
                    userList = userService.listAll();
                } else {
                    // 其他角色只能查自己的数据
                    String user_id = pd.getUser().getUSER_ID();
                    pd.put("USER_ID", user_id);
                    PageData user = userService.findByUiId(pd);
                    userList.add(user);
                }
            } else {
                return null;
            }
            page.setPd(pd);
            List<PageData> varList = inquirybossService.list(page);    //列出InquiryBoss列表
            mv.setViewName("business/inquiryboss/inquiryboss_list");
            mv.addObject("varList", varList);
            mv.addObject("pd", pd);
            mv.addObject("userList", userList); // 用户下拉列表
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
        logBefore(logger, "去新增InquiryBoss页面");
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        try {
            mv.setViewName("business/inquiryboss/inquiryboss_edit");
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
        logBefore(logger, "去修改InquiryBoss页面");
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        try {
            pd = inquirybossService.findById(pd);    //根据ID读取
            mv.setViewName("business/inquiryboss/inquiryboss_edit");
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
        logBefore(logger, "批量删除InquiryBoss");
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
                inquirybossService.deleteAll(ArrayDATA_IDS);
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
        logBefore(logger, "导出InquiryBoss到excel");
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "cha")) {
            return null;
        }
        ModelAndView mv = new ModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        try {
            Map<String, Object> dataMap = new HashMap<String, Object>();
            List<String> titles = new ArrayList<String>();
            titles.add("客户id");    //1
            titles.add("公司名称");    //2
            titles.add("联系人名称");    //3
            titles.add("联系电话");    //4
            titles.add("联系邮箱");    //5
            titles.add("微信号");    //6
            titles.add("客户创建时间");    //7
            titles.add("客户更新时间");    //8
            titles.add("状态");    //9
            dataMap.put("titles", titles);
            List<PageData> varOList = inquirybossService.listAll(pd);
            List<PageData> varList = new ArrayList<PageData>();
            for (int i = 0; i < varOList.size(); i++) {
                PageData vpd = new PageData();
                vpd.put("var1", varOList.get(i).getString("INQUIRY_BOSS_ID"));    //1
                vpd.put("var2", varOList.get(i).getString("INQUIRY_BOSS_COMPAN"));    //2
                vpd.put("var3", varOList.get(i).getString("INQUIRY_BOSS"));    //3
                vpd.put("var4", varOList.get(i).getString("INQUIRY_BOSS_TELEPHONE"));    //4
                vpd.put("var5", varOList.get(i).getString("INQUIRY_BOSS_QQ"));    //5
                vpd.put("var6", varOList.get(i).getString("INQUIRY_BOSS_WECHAT"));    //6
                vpd.put("var7", varOList.get(i).getString("INQUIRY_BOSS_CREATETIME"));    //7
                vpd.put("var8", varOList.get(i).getString("INQUIRY_BOSS_UPDATETIME"));    //8
                vpd.put("var9", varOList.get(i).getString("STATUS"));    //9
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
