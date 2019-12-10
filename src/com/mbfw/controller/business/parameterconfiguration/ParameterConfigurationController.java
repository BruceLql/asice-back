package com.mbfw.controller.business.parameterconfiguration;

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
import com.mbfw.service.business.parameterconfiguration.ParameterConfigurationService;

/** 
 * 类名称：ParameterConfigurationController
 */
@Controller
@RequestMapping(value="/parameterconfiguration")
public class ParameterConfigurationController extends BaseController {

	private org.apache.log4j.Logger log =org.apache.log4j.Logger.getLogger(this.getClass());
	
	String menuUrl = "parameterconfiguration/list.do"; //菜单地址(权限用)
	@Resource(name="parameterconfigurationService")
	private ParameterConfigurationService parameterconfigurationService;
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		log.info("新增ParameterConfiguration");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("UUID", this.get32UUID());	//主键
		pd.put("NICK1", null);	//nick1
		pd.put("NICK2", null);	//NICK2
		pd.put("NICK3", null);	//NICK3
		pd.put("TYPE", null);	//类型
		String data=Tools.date2Str(new Date());
		pd.put("CDATE", data);	//创建时间
		pd.put("UDATE", data);	//修改时间
		pd.put("MSP_001", null);	//msp_001
		pd.put("MSP_002", null);	//msp_002
		pd.put("MSP_003", null);	//msp_003
		pd.put("MSP_004", null);	//msp_004
		pd.put("MSP_005", null);	//msp_005
		pd.put("MSP_006", null);	//msp_006
		pd.put("MSP_007", null);	//msp_007
		pd.put("MSP_008", null);	//msp_008
		pd.put("MSP_009", null);	//msp_009
		pd.put("MSP_010", null);	//msp_010
		String name_=pd.getString("NAME");
		if(Tools.notEmpty(name_))pd.put("NAME", name_.trim());
		String key1=pd.getString("KEY1");
		if(Tools.notEmpty(key1))pd.put("KEY1", key1.trim());
		String key2=pd.getString("KEY2");
		if(Tools.notEmpty(key2))pd.put("KEY2", key2.trim());
		String key3=pd.getString("KEY3");
		if(Tools.notEmpty(key3))pd.put("KEY3", key3.trim());
		String type_name=pd.getString("TYPE_NAME");
		if(Tools.notEmpty(type_name))pd.put("TYPE_NAME", type_name.trim());
		String bz=pd.getString("BZ");
		if(Tools.notEmpty(bz))pd.put("BZ", bz.trim());
		parameterconfigurationService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		log.info("删除ParameterConfiguration");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			parameterconfigurationService.delete(pd);
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
		log.info("修改ParameterConfiguration");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String name_=pd.getString("NAME");
		if(Tools.notEmpty(name_))pd.put("NAME", name_.trim());
		String key1=pd.getString("KEY1");
		if(Tools.notEmpty(key1))pd.put("KEY1", key1.trim());
		String key2=pd.getString("KEY2");
		if(Tools.notEmpty(key2))pd.put("KEY2", key2.trim());
		String key3=pd.getString("KEY3");
		if(Tools.notEmpty(key3))pd.put("KEY3", key3.trim());
		String type_name=pd.getString("TYPE_NAME");
		if(Tools.notEmpty(type_name))pd.put("TYPE_NAME", type_name.trim());
		String bz=pd.getString("BZ");
		if(Tools.notEmpty(bz))pd.put("BZ", bz.trim());
		parameterconfigurationService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		log.info("列表ParameterConfiguration");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			pd.put("MSP_002", "1");//是否显示(1显示)
			String name_=pd.getString("NAME");
			if(Tools.notEmpty(name_))pd.put("NAME", name_.trim());
			String lastLoginEnd=pd.getString("lastLoginEnd");
			if(lastLoginEnd!=null && !"".equals(lastLoginEnd)){
				 pd.put("lastLoginEnd", lastLoginEnd+" 23:59:59");
			}
			List<PageData>	typesList = parameterconfigurationService.getTypeNames(pd);	//
			mv.addObject("typesList", typesList);
			page.setPd(pd);
			List<PageData>	varList = parameterconfigurationService.list(page);	//列出ParameterConfiguration列表
			mv.setViewName("business/parameterconfiguration/parameterconfiguration_list");
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
		log.info("去新增ParameterConfiguration页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			List<PageData>	typesList = parameterconfigurationService.getTypeNames(pd);	//
			mv.addObject("typesList", typesList);
			mv.setViewName("business/parameterconfiguration/parameterconfiguration_edit");
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
		log.info("去修改ParameterConfiguration页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			List<PageData>	typesList = parameterconfigurationService.getTypeNames(pd);	//
			mv.addObject("typesList", typesList);
			pd = parameterconfigurationService.findById(pd);	//根据ID读取
			mv.setViewName("business/parameterconfiguration/parameterconfiguration_edit");
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
		log.info("批量删除ParameterConfiguration");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				parameterconfigurationService.deleteAll(ArrayDATA_IDS);
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
		log.info("导出ParameterConfiguration到excel");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("名称");	//1
			titles.add("nick1");	//2
			titles.add("值1");	//3
			titles.add("值2");	//4
			titles.add("值3");	//5
			titles.add("值4");	//6
			titles.add("值5");	//7
			titles.add("值6");	//8
			titles.add("NICK2");	//9
			titles.add("NICK3");	//10
			titles.add("类型");	//11
			titles.add("类型名称");	//12
			titles.add("备注");	//13
			titles.add("创建时间");	//14
			titles.add("修改时间");	//15
			titles.add("msp_001");	//16
			titles.add("msp_002");	//17
			titles.add("msp_003");	//18
			titles.add("msp_004");	//19
			titles.add("msp_005");	//20
			titles.add("msp_006");	//21
			titles.add("msp_007");	//22
			titles.add("msp_008");	//23
			titles.add("msp_009");	//24
			titles.add("msp_010");	//25
			dataMap.put("titles", titles);
			List<PageData> varOList = parameterconfigurationService.listAll();
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("NAME"));	//1
				vpd.put("var2", varOList.get(i).getString("NICK1"));	//2
				vpd.put("var3", varOList.get(i).getString("KEY1"));	//3
				vpd.put("var4", varOList.get(i).getString("KEY2"));	//4
				vpd.put("var5", varOList.get(i).getString("KEY3"));	//5
				vpd.put("var6", varOList.get(i).get("KEY4").toString());	//6
				vpd.put("var7", varOList.get(i).get("KEY5").toString());	//7
				vpd.put("var8", varOList.get(i).get("KEY6").toString());	//8
				vpd.put("var9", varOList.get(i).getString("NICK2"));	//9
				vpd.put("var10", varOList.get(i).getString("NICK3"));	//10
				vpd.put("var11", varOList.get(i).getString("TYPE"));	//11
				vpd.put("var12", varOList.get(i).getString("TYPE_NAME"));	//12
				vpd.put("var13", varOList.get(i).getString("BZ"));	//13
				vpd.put("var14", varOList.get(i).getString("CDATE"));	//14
				vpd.put("var15", varOList.get(i).getString("UDATE"));	//15
				vpd.put("var16", varOList.get(i).getString("MSP_001"));	//16
				vpd.put("var17", varOList.get(i).getString("MSP_002"));	//17
				vpd.put("var18", varOList.get(i).getString("MSP_003"));	//18
				vpd.put("var19", varOList.get(i).get("MSP_004").toString());	//19
				vpd.put("var20", varOList.get(i).get("MSP_005").toString());	//20
				vpd.put("var21", varOList.get(i).get("MSP_006").toString());	//21
				vpd.put("var22", varOList.get(i).getString("MSP_007"));	//22
				vpd.put("var23", varOList.get(i).getString("MSP_008"));	//23
				vpd.put("var24", varOList.get(i).getString("MSP_009"));	//24
				vpd.put("var25", varOList.get(i).getString("MSP_010"));	//25
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
