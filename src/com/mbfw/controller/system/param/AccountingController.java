package com.mbfw.controller.system.param;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
import com.mbfw.entity.system.User;
import com.mbfw.util.AppUtil;
import com.mbfw.util.ObjectExcelView;
import com.mbfw.util.Const;
import com.mbfw.util.PageData;
import com.mbfw.util.Tools;
import com.mbfw.util.Jurisdiction;
import com.mbfw.service.system.param.AccountingService;

/** 
 * 类名称：AccountingController
 * 创建人：研发中心 
 * 创建时间：2018-01-05
 */
@Controller
@RequestMapping(value="/accounting")
public class AccountingController extends BaseController {

	private org.apache.log4j.Logger log =org.apache.log4j.Logger.getLogger(this.getClass());
	
	String menuUrl = "accounting/list.do"; //菜单地址(权限用)
	@Resource(name="accountingService")
	private AccountingService accountingService;
	
	
	   @ResponseBody
		@RequestMapping(value="/saveConfData")
		public Object saveConfData() throws Exception{
		   Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			User user = (User) session.getAttribute(Const.SESSION_USER);
		
			PageData pd = new PageData();
			pd = this.getPageData();
			

			List<PageData>  pdlist= accountingService.findByParamID(pd);
			if(pdlist!=null&&pdlist.size()>0){//该公司在以购买一份产品
				PageData pd_=pdlist.get(0);
				pd_.put("ACCOUNTING_ID", this.get32UUID());	//主键d
				pd_.put("RISK_A_EXT_SYS_CURRENTUSERID", user.getUSER_ID());//存储当前用户id
				pd_.put("RISK_A_EXT_SYS_IS_PARENT", "1");
				pd_.put("RISK_A_EXT_SYS_CREATED", Tools.date2Str(new Date()));	//创建时间
				pd_.put("RISK_A_EXT_SYS_UPDATED", Tools.date2Str(new Date()));	//更新时间
				pd_.put("RISK_A_EXT_SYS_CLIENT", pd.get("RISK_A_EXT_SYS_CLIENT"));	//对应公司部门
					accountingService.saveByClient(pd_);
				
			}
		
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("msg","success");
			return AppUtil.returnObject(new PageData(), map);
		}
		
	
	
	
	
	
	
	
	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		log.info("新增Accounting");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("ACCOUNTING_ID", this.get32UUID());	//主键
		pd.put("RISK_A_EXT_SYS_CREATED", Tools.date2Str(new Date()));	//创建时间
		pd.put("RISK_A_EXT_SYS_UPDATED", Tools.date2Str(new Date()));	//更新时间
		pd.put("RISK_A_EXT_SYS_CLIENT", "");	//客户
		pd.put("RISK_A_EXT_SYS_IS_PARENT", "0");	//计费父类
		pd.put("RISK_A_EXT_SYS_ACCOUNT1", "");	//预留字段
		pd.put("RISK_A_EXT_SYS_ACCOUNT2", "");	//预留2
		pd.put("RISK_A_EXT_SYS_ACCOUNT3", "");	//预留3
		pd.put("RISK_A_EXT_SYS_BZ", "");	//备注
		accountingService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		log.info("删除Accounting");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			accountingService.delete(pd);
			out.write("success");
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		
	}
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		log.info("修改Accounting");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		accountingService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 参数管理下列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		log.info("列表Accounting");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = accountingService.list(page);	//列出Accounting列表
			mv.setViewName("system/param/accounting_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	
	/**
	 * 计费管理下列表
	 */
	@RequestMapping(value="/accountList")
	public ModelAndView list1(Page page){
		log.info("列表Accounting");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = accountingService.accountList(page);	//列出Accounting列表
			List<PageData>	typeList = accountingService.accountTypeList(pd);	//列出数据源对应计费类型列表
			pd.put("typeList", typeList);
			mv.setViewName("system/billing/confData");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	
	
	
	
	
	
	
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd(){
		log.info("去新增Accounting页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("system/param/accounting_edit");
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
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit(){
		log.info("去修改Accounting页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = accountingService.findById(pd);	//根据ID读取
			mv.setViewName("system/param/accounting_edit");
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
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() {
		log.info("批量删除Accounting");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				accountingService.deleteAll(ArrayDATA_IDS);
				pd.put("msg", "ok");
			}else{
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
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel(){
		log.info("导出Accounting到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("对应数据源");	//1
			titles.add("当前操作用户id");	//2
			titles.add("成本原价");	//3
			titles.add("成本系数（折扣）");	//4
			titles.add("内部成交价");	//5
			titles.add("原价");	//6
			titles.add("折扣");	//7
			titles.add("成交价");	//8
			titles.add("创建时间");	//9
			titles.add("更新时间");	//10
			titles.add("收费类型");	//11
			titles.add("收费类型说明");	//12
			titles.add("客户");	//13
			titles.add("计费父类");	//14
			titles.add("预留字段");	//15
			titles.add("预留2");	//16
			titles.add("预留3");	//17
			titles.add("备注");	//18
			dataMap.put("titles", titles);
			List<PageData> varOList = accountingService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("RISK_A_EXT_SYS_PARAM_ID"));	//1
				vpd.put("var2", varOList.get(i).getString("RISK_A_EXT_SYS_CURRENTUSERID"));	//2
				vpd.put("var3", varOList.get(i).getString("RISK_A_EXT_SYS_INTER_PRICE"));	//3
				vpd.put("var4", varOList.get(i).getString("RISK_A_EXT_SYS_INTER_DISCOUNT"));	//4
				vpd.put("var5", varOList.get(i).getString("RISK_A_EXT_SYS_INTER_FINALPRICE"));	//5
				vpd.put("var6", varOList.get(i).getString("RISK_A_EXT_SYS_PRICE"));	//6
				vpd.put("var7", varOList.get(i).getString("RISK_A_EXT_SYS_DISCOUNT"));	//7
				vpd.put("var8", varOList.get(i).getString("RISK_A_EXT_SYS_FINALPRICE"));	//8
				vpd.put("var9", varOList.get(i).getString("RISK_A_EXT_SYS_CREATED"));	//9
				vpd.put("var10", varOList.get(i).getString("RISK_A_EXT_SYS_UPDATED"));	//10
				vpd.put("var11", varOList.get(i).getString("RISK_A_EXT_SYS_TYPE"));	//11
				vpd.put("var12", varOList.get(i).getString("RISK_A_EXT_SYS_TYPEINFO"));	//12
				vpd.put("var13", varOList.get(i).getString("RISK_A_EXT_SYS_CLIENT"));	//13
				vpd.put("var14", varOList.get(i).getString("RISK_A_EXT_SYS_IS_PARENT"));	//14
				vpd.put("var15", varOList.get(i).getString("RISK_A_EXT_SYS_ACCOUNT1"));	//15
				vpd.put("var16", varOList.get(i).getString("RISK_A_EXT_SYS_ACCOUNT2"));	//16
				vpd.put("var17", varOList.get(i).getString("RISK_A_EXT_SYS_ACCOUNT3"));	//17
				vpd.put("var18", varOList.get(i).getString("RISK_A_EXT_SYS_BZ"));	//18
				varList.add(vpd);
			}
			dataMap.put("varList", varList);
			ObjectExcelView erv = new ObjectExcelView();
			mv = new ModelAndView(erv,dataMap);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/* ===============================权限================================== */
	public Map<String, String> getHC(){
		Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
		Session session = currentUser.getSession();
		return (Map<String, String>)session.getAttribute(Const.SESSION_QX);
	}
	/* ===============================权限================================== */
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
