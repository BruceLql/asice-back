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
import com.mbfw.util.AppUtil;
import com.mbfw.util.ObjectExcelView;
import com.mbfw.util.Const;
import com.mbfw.util.PageData;
import com.mbfw.util.Tools;
import com.mbfw.util.Jurisdiction;
import com.mbfw.service.system.param.ParamService;

/** 
 * 类名称：ParamController
 * 创建人：研发中心 
 * 创建时间：2017-12-29
 */
@Controller
@RequestMapping(value="/param")
public class ParamController extends BaseController {

	private org.apache.log4j.Logger log =org.apache.log4j.Logger.getLogger(this.getClass());
	
	String menuUrl = "param/list.do"; //菜单地址(权限用)
	@Resource(name="paramService")
	private ParamService paramService;
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		log.info("新增Param");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("PARAM_ID", this.get32UUID());	//主键
		pd.put("BILLING_ID", this.get32UUID());	
		/*pd.put("BIZ_SYS_PROD_C006", "");	//业务接口说明
		pd.put("BIZ_SYS_PROD_C007", "");	//业务接口英文名
		pd.put("BIZ_SYS_PROD_C008", "");	//业务接口代码*/
		pd.put("BIZ_SYS_PROD_C019", Tools.getDateStr());
		paramService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		log.info("删除Param");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			paramService.delete(pd);
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
		log.info("修改Param");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("BIZ_SYS_PROD_C019", Tools.getDateStr());
		paramService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		log.info("列表Param");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = paramService.list(page);	//列出Param列表
			List<PageData>	gsList = paramService.listGsType(pd);
			mv.addObject("varList", varList);
			mv.addObject("gsList", gsList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
			mv.setViewName("system/param/param_list");
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
		log.info("去新增Param页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("system/param/param_edit");
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
		log.info("去修改Param页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = paramService.findById(pd);	//根据ID读取
			mv.setViewName("system/param/param_edit");
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
		log.info("批量删除Param");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				paramService.deleteAll(ArrayDATA_IDS);
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
		log.info("导出Param到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("编号");	//1
			titles.add("业务类型");	//2
			titles.add("业务类型说明");	//3
			titles.add("业务接口ID");	//4
			titles.add("业务接口版本");	//5
			titles.add("业务接口说明");	//6
			titles.add("业务接口英文名");	//7
			titles.add("业务接口代码");	//8
			titles.add("业务接口标识");	//9
			titles.add("内部网关地址");	//10
			titles.add("外部网关地址");	//11
			titles.add("连接方式");	//12
			titles.add("行方RSA私钥");	//13
			titles.add("行方RSA公钥");	//14
			titles.add("外部RSA公钥");	//15
			titles.add("外部请求JAVA类");	//16
			titles.add("外部返回JAVA类");	//17
			titles.add("对接数据源公司");	//18
			titles.add("扩展说明1");	//19
			titles.add("扩展说明2");	//20
			titles.add("扩展说明3");	//21
			titles.add("扩展说明4");	//22
			titles.add("备注");	//23
			dataMap.put("titles", titles);
			List<PageData> varOList = paramService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("BIZ_SYS_PROD_C001"));	//1
				vpd.put("var2", varOList.get(i).getString("BIZ_SYS_PROD_C002"));	//2
				vpd.put("var3", varOList.get(i).getString("BIZ_SYS_PROD_C003"));	//3
				vpd.put("var4", varOList.get(i).getString("BIZ_SYS_PROD_C004"));	//4
				vpd.put("var5", varOList.get(i).getString("BIZ_SYS_PROD_C005"));	//5
				vpd.put("var6", varOList.get(i).getString("BIZ_SYS_PROD_C006"));	//6
				vpd.put("var7", varOList.get(i).getString("BIZ_SYS_PROD_C007"));	//7
				vpd.put("var8", varOList.get(i).getString("BIZ_SYS_PROD_C008"));	//8
				vpd.put("var9", varOList.get(i).getString("BIZ_SYS_PROD_C009"));	//9
				vpd.put("var10", varOList.get(i).getString("BIZ_SYS_PROD_C010"));	//10
				vpd.put("var11", varOList.get(i).getString("BIZ_SYS_PROD_C011"));	//11
				vpd.put("var12", varOList.get(i).getString("BIZ_SYS_PROD_C012"));	//12
				vpd.put("var13", varOList.get(i).getString("BIZ_SYS_PROD_C013"));	//13
				vpd.put("var14", varOList.get(i).getString("BIZ_SYS_PROD_C014"));	//14
				vpd.put("var15", varOList.get(i).getString("BIZ_SYS_PROD_C015"));	//15
				vpd.put("var16", varOList.get(i).getString("BIZ_SYS_PROD_C016"));	//16
				vpd.put("var17", varOList.get(i).getString("BIZ_SYS_PROD_C017"));	//17
				vpd.put("var18", varOList.get(i).getString("BIZ_SYS_PROD_C018"));	//18
				vpd.put("var19", varOList.get(i).getString("BIZ_SYS_PROD_C019"));	//19
				vpd.put("var20", varOList.get(i).getString("BIZ_SYS_PROD_C020"));	//20
				vpd.put("var21", varOList.get(i).getString("BIZ_SYS_PROD_C021"));	//21
				vpd.put("var22", varOList.get(i).getString("BIZ_SYS_PROD_C022"));	//22
				vpd.put("var23", varOList.get(i).getString("BIZ_SYS_PROD_C023"));	//23
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
