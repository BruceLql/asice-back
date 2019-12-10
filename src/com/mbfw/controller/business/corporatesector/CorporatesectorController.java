package com.mbfw.controller.business.corporatesector;

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
import com.mbfw.service.business.corporatesector.CorporatesectorService;

/** 
 * 类名称：CorporatesectorController
 * @Description 公司和部门
 */
@Controller
@RequestMapping(value="/corporatesector")
public class CorporatesectorController extends BaseController {
	
	private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(this.getClass());
	
	String menuUrl = "corporatesector/list.do"; //菜单地址(权限用)
	@Resource(name="corporatesectorService")
	private CorporatesectorService corporatesectorService;
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		log.info("新增Corporatesector");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("mrc_uuid", this.get32UUID());//主键
		corporatesectorService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		log.info("删除Corporatesector");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			corporatesectorService.delete(pd);
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
		log.info("修改Corporatesector");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		corporatesectorService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		log.info("列表Corporatesector");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			//查询所有公司
			PageData gs_=new PageData();
			gs_.put("mrc_type", "1");//1为公司
			List<PageData>	list_se_gs = corporatesectorService.listIf(gs_);
			mv.addObject("list_se_gs", list_se_gs);
			//
			List<PageData>	varList = corporatesectorService.list2Page(page);//列出Corporatesector列表
			mv.setViewName("business/corporatesector/corporatesector_list");
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
		log.info("去新增Corporatesector页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			//查询所有可用的公司
			PageData gs=new PageData();
			gs.put("mrc_type", "1");//1为公司
			gs.put("mrc_state", "enable");//enable为可用
			List<PageData> list_gs=corporatesectorService.listIf(gs);
			mv.addObject("list_gs", list_gs);
			
			mv.setViewName("business/corporatesector/corporatesector_edit");
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
		log.info("去修改Corporatesector页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = corporatesectorService.findById(pd);//根据ID读取
			//查询所有可用的公司
			PageData gs=new PageData();
			gs.put("mrc_type", "1");//1为公司
			gs.put("mrc_state", "enable");//enable为可用
			List<PageData> list_gs=corporatesectorService.listIf(gs);
			mv.addObject("list_gs", list_gs);
			
			mv.setViewName("business/corporatesector/corporatesector_edit");
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
		log.info("批量删除Corporatesector");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				corporatesectorService.deleteAll(ArrayDATA_IDS);
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
		log.info("导出Corporatesector到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("名称");	//1
			titles.add("编号");	//2
			titles.add("父id");	//3
			titles.add("类型（1公司、2部门）");	//4
			titles.add("备注说明");	//5
			titles.add("状态(enable可用、disable禁用)");	//6
			titles.add("级别");	//7
			titles.add("创建时间");	//8
			titles.add("修改时间");	//9
			titles.add("排序号");	//10
			titles.add("预留");	//11
			titles.add("预留");	//12
			titles.add("预留");	//13
			titles.add("预留");	//14
			titles.add("预留");	//15
			titles.add("预留");	//16
			titles.add("预留");	//17
			titles.add("预留");	//18
			titles.add("预留");	//19
			titles.add("预留");	//20
			titles.add("预留");	//21
			titles.add("预留");	//22
			titles.add("预留");	//23
			titles.add("预留");	//24
			titles.add("预留");	//25
			titles.add("预留");	//26
			titles.add("预留");	//27
			titles.add("预留");	//28
			titles.add("预留");	//29
			titles.add("预留");	//30
			titles.add("公司");	//31
			dataMap.put("titles", titles);
			List<PageData> varOList = corporatesectorService.listAll();
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("mrc_name"));	//1
				vpd.put("var2", varOList.get(i).getString("mrc_code"));	//2
				vpd.put("var3", varOList.get(i).getString("mrc_pid"));	//3
				vpd.put("var4", varOList.get(i).getString("mrc_type"));	//4
				vpd.put("var5", varOList.get(i).getString("mrc_bz"));	//5
				vpd.put("var6", varOList.get(i).getString("mrc_state"));	//6
				vpd.put("var7", varOList.get(i).get("mrc_jb").toString());	//7
				vpd.put("var8", varOList.get(i).getString("mrc_cdate"));	//8
				vpd.put("var9", varOList.get(i).getString("mrc_udate"));	//9
				vpd.put("var10", varOList.get(i).get("mrc_order").toString());	//10
				vpd.put("var11", varOList.get(i).getString("mrc_001"));	//11
				vpd.put("var12", varOList.get(i).getString("mrc_002"));	//12
				vpd.put("var13", varOList.get(i).getString("mrc_003"));	//13
				vpd.put("var14", varOList.get(i).getString("mrc_004"));	//14
				vpd.put("var15", varOList.get(i).getString("mrc_005"));	//15
				vpd.put("var16", varOList.get(i).getString("mrc_006"));	//16
				vpd.put("var17", varOList.get(i).getString("mrc_007"));	//17
				vpd.put("var18", varOList.get(i).getString("mrc_008"));	//18
				vpd.put("var19", varOList.get(i).getString("mrc_009"));	//19
				vpd.put("var20", varOList.get(i).getString("mrc_010"));	//20
				vpd.put("var21", varOList.get(i).getString("mrc_011"));	//21
				vpd.put("var22", varOList.get(i).getString("mrc_012"));	//22
				vpd.put("var23", varOList.get(i).getString("mrc_013"));	//23
				vpd.put("var24", varOList.get(i).getString("mrc_014"));	//24
				vpd.put("var25", varOList.get(i).getString("mrc_015"));	//25
				vpd.put("var26", varOList.get(i).get("mrc_016").toString());	//26
				vpd.put("var27", varOList.get(i).get("mrc_017").toString());	//27
				vpd.put("var28", varOList.get(i).get("mrc_018").toString());	//28
				vpd.put("var29", varOList.get(i).get("mrc_019").toString());	//29
				vpd.put("var30", varOList.get(i).get("mrc_020").toString());	//30
				vpd.put("var31", varOList.get(i).getString("mrc_gsid"));	//31
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
	
	/**
	 * 系统登录账户-根据公司查询其下的可用部门
	 * @Description 
	 * @return
	 * @author suj
	 */
	@ResponseBody
	@RequestMapping(value="findBMbyGS")
	public Object findBMbyGS(){
		PageData pd = new PageData();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			pd = this.getPageData();
			map.put("list", corporatesectorService.listBMbyGS(pd));
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return AppUtil.returnObject(pd, map);
	
	}
	
}
