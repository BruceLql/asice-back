package com.mbfw.controller.business.contacts;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.mbfw.service.system.role.RoleService;
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
import com.mbfw.service.business.contacts.ContactsService;

/**
 * 类名称：ContactsController
 * 创建人：研发中心
 * 创建时间：2019-12-23
 */
@Controller
@RequestMapping(value = "/contacts")
public class ContactsController extends BaseController {

    String menuUrl = "contacts/list.do"; //菜单地址(权限用)
    @Resource(name = "contactsService")
    private ContactsService contactsService;

    /**
     * 新增
     */
    @RequestMapping(value = "/save")
    public ModelAndView save() throws Exception {
        logBefore(logger, "新增Contacts");
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "add")) {
            return null;
        } //校验权限
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
//		pd.put("CONTACTS_ID", this.get32UUID());	//主键
        pd.put("USER_ID", pd.getUser().getUSER_ID());    //创建人
        pd.put("STATUS", 0);    //状态  0 可用 ；-1 不可用
//        pd.put("CREATE_TIME", Tools.date2Str(new Date()));    //创建时间
//        pd.put("UPDATE_TIME", Tools.date2Str(new Date()));    //更新时间
        contactsService.save(pd);
        mv.addObject("msg", "success");
        mv.setViewName("save_result");
        return mv;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    public void delete(PrintWriter out) {
        logBefore(logger, "删除Contacts");
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "del")) {
            return;
        } //校验权限
        PageData pd = new PageData();
        try {
            pd = this.getPageData();
            contactsService.delete(pd);
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
        logBefore(logger, "修改Contacts");
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "edit")) {
            return null;
        } //校验权限
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        contactsService.edit(pd);
        mv.addObject("msg", "success");
        mv.setViewName("save_result");
        return mv;
    }

    /**
     * 列表
     */
    @RequestMapping(value = "/list")
    public ModelAndView list(Page page) {
        logBefore(logger, "列表Contacts");
        //if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        try {
            pd = this.getPageData();
            pd.get("searchKey"); // 前端传过来的搜索关键字
			System.out.println(pd.toJsonStr());
            page.setPd(pd);
            List<PageData> varList = contactsService.list(page);    //列出Contacts列表
            mv.setViewName("business/contacts/contacts_list");
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
        logBefore(logger, "去新增Contacts页面");
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        try {
            mv.setViewName("business/contacts/contacts_edit");
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
        logBefore(logger, "去修改Contacts页面");
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        try {
            pd = contactsService.findById(pd);    //根据ID读取
            mv.setViewName("business/contacts/contacts_edit");
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
        logBefore(logger, "批量删除Contacts");
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
                contactsService.deleteAll(ArrayDATA_IDS);
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
        logBefore(logger, "导出Contacts到excel");
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "cha")) {
            return null;
        }
        ModelAndView mv = new ModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        try {
            Map<String, Object> dataMap = new HashMap<String, Object>();
            List<String> titles = new ArrayList<String>();
            titles.add("联系人姓名");    //1
            titles.add("联系人邮箱");    //1
            titles.add("联系人所属公司");    //2
            titles.add("联系人电话");    //3
            titles.add("联系人地址");    //4
            titles.add("创建人");    //5
            titles.add("创建时间");    //6
            titles.add("更新时间");    //7
            titles.add("备注");    //8
            dataMap.put("titles", titles);
            List<PageData> varOList = contactsService.listAll(pd);
            List<PageData> varList = new ArrayList<PageData>();
            for (int i = 0; i < varOList.size(); i++) {
                PageData vpd = new PageData();
                vpd.put("var1", varOList.get(i).getString("CONTACTS_NAME"));    //1
                vpd.put("var1", varOList.get(i).getString("CONTACTS_EMAIL"));    //1
                vpd.put("var2", varOList.get(i).getString("CONTACTS_COMPANY"));    //2
                vpd.put("var3", varOList.get(i).getString("CONTACTS_MOBILEPHONE"));    //3
                vpd.put("var4", varOList.get(i).getString("CONTACTS_ADDRESS"));    //4
                vpd.put("var5", varOList.get(i).getString("USER_ID"));    //5
                vpd.put("var6", varOList.get(i).getString("CREATE_TIME"));    //6
                vpd.put("var7", varOList.get(i).getString("UPDATE_TIME"));    //7
                vpd.put("var8", varOList.get(i).getString("CONTACTS_NOTE"));    //8
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
