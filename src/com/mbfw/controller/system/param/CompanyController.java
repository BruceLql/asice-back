package com.mbfw.controller.system.param;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

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
import com.mbfw.service.system.param.CompanyService;
import com.mbfw.util.AppUtil;
import com.mbfw.util.Const;
import com.mbfw.util.Jurisdiction;
import com.mbfw.util.ObjectExcelView;
import com.mbfw.util.PageData;

/** 
 * 类名称：CompanyController
 * 创建人：研发中心 
 * 创建时间：2018-01-03
 */
@Controller
@RequestMapping(value="/company")
public class CompanyController extends BaseController {

	private org.apache.log4j.Logger log =org.apache.log4j.Logger.getLogger(this.getClass());
	
	String menuUrl = "company/deplist.do"; //菜单地址(权限用)
	@Resource(name="companyService")
	private CompanyService companyService;
	

	/**
	 * 去请求流水详情界面测试页面
	 */
	@RequestMapping(value="/info")
	public ModelAndView info() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		PageData pd1;
		List list=new ArrayList();
		pd = this.getPageData();
		PageData str1=companyService.findByComId(pd);
		if(str1!=null && !"".equals(str1)){
		String str=(String) str1.get("sys_conpany_002");
		//String str=(String) pd.get("temp");
		if(str.length()>0){
			String[] cd=str.split("!");
			for(int a=0;a<cd.length;a++){
				String[] bd=cd[a].split(",");
				for(int b=0;b<bd.length;b+=bd.length){
					pd1=new PageData();
					pd1.put("thing", bd[b]);
					pd1.put("thing", companyService.findname(pd1));
					pd1.put("type", bd[b+1]);
					pd1.put("money", bd[b+2]);
					list.add(pd1);
				}
			}
		}
		}
		pd.put("list", list);
		mv.setViewName("system/interfaces/billingData");
		mv.addObject("pd", pd);
		return mv;
	}
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		log.info("新增Company");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
	
		pd.put("COMPANY_ID", this.get32UUID());	//主键
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		User user = (User) session.getAttribute(Const.SESSION_USER);
	
		pd.put("RISK_A_EXT_SYS_CP_P001", user.getUSER_ID());	//预留
		pd.put("RISK_A_EXT_SYS_CP_P002", "");	//预留
		pd.put("RISK_A_EXT_SYS_CP_P003", "");	//预留
		companyService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		log.info("删除Company");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			companyService.delete(pd);
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
		log.info("修改Company");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		User user = (User) session.getAttribute(Const.SESSION_USER);

		pd.put("RISK_A_EXT_SYS_CP_P001", user.getUSER_ID());	//预留
		companyService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 计费列表
	 */
	@RequestMapping(value="/deplist")
	public ModelAndView list(Page page){
		log.info("列表Company");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = companyService.list(page);	//列出Company列表
			mv.setViewName("system/billing/company_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 费用管理跳转
	 * 操作人：zx
	 * 最后更新时间：2018/1/30
	 */
	@RequestMapping(value = "/report")
	public ModelAndView testcompany() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData pd1 = new PageData();
		PageData pd2 = new PageData();
		List<PageData> varList = companyService.selectall(pd);
		Map<Object, Object> map=new HashMap<Object, Object>();
		Map maptype =new HashMap<Object, Object>();
		String str=new String();
		List<PageData> list=new ArrayList();
		BigDecimal money=new BigDecimal("0");
		for(int a=0;a<varList.size();a++){
			pd1.put("company", varList.get(a).get("RISK_A_EXT_SYS_CP_NAME"));
			pd1.put("department", varList.get(a).get("RISK_A_EXT_SYS_CP_DEP"));
			List<PageData> u=companyService.select(pd1);
			for(int c=0;c<u.size();c++){                                     
				if(u.get(c)!=null){
				map.put(u.get(c).get("RISK_A_EXT_SYS_PARAM_ID"), u.get(c).get("RISK_A_EXT_SYS_FINALPRICE"));
				maptype.put(u.get(c).get("RISK_A_EXT_SYS_PARAM_ID")+"type", u.get(c).get("RISK_A_EXT_SYS_TYPEINFO"));
				}else{
					continue;
				}
			}
			//遍历出每一个key的值
			Set keys=map.keySet();
			if(keys!=null){
				Iterator iterator=keys.iterator();
				while(iterator.hasNext()){
					Object key=iterator.next();
					str+=key.toString()+",";
					str+=maptype.get(key+"type").toString()+",";
					//Double va=Double.parseDouble(detail.get(key).toString());
					//BigDecimal value = BigDecimal.valueOf(va);
					BigDecimal value = new BigDecimal((String)map.get(key));
					money=money.add(value);
					str+=map.get(key).toString()+"!";
				}
			} 
			pd1.put("cost", money);
			pd2.put("postscript", str);
			pd2.put("id", UUID.randomUUID().toString());
			companyService.saveinfo(pd2);
			pd1.put("postscript", pd2.get("id"));
			list.add(pd1);
			pd1 = new PageData();
			money=new BigDecimal("0");
			str="";
			map=new HashMap<Object, Object>();
			maptype=new HashMap<Object, Object>();
		}
		pd.put("list", list);
		mv.addObject("pd",pd);
		mv.setViewName("system/billing/company_cost");
		return mv;
	}
	
	/**
	 * 费用管理查询
	 * 操作人：zx
	 * 最后更新时间：2018/1/30
	 */
	@RequestMapping(value = "/costreport")
	public ModelAndView selectcompany(Page page) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		PageData pd1 = new PageData();
		PageData pd2 = new PageData();
		Map<Object, Object> map=new HashMap<Object, Object>();
		Map maptype =new HashMap<Object, Object>();
		BigDecimal money=new BigDecimal("0");
		List<PageData> list=new ArrayList();
		String str=new String();
		pd = this.getPageData();
		Object company=pd.get("company");
		Object department=pd.get("department");
		pd.put("com", company);
		pd.put("depart", department);
		List<PageData> varList = companyService.selectall(pd);
		List<PageData> List = companyService.selectall();
		if(varList.size()==0){
			//公司不为空
			if(company!=null && !"".equals((String)pd.get("company"))){
			boolean test=false;
			//查询数据库是有该公司
			for(int a=0;a<List.size();a++){ 
				if(company.equals(List.get(a).get("RISK_A_EXT_SYS_CP_NAME"))){
					pd.put("company", company);
					test=true;
					break;
				}
			}
			if(test){
				pd.put("department", "该部门不存在");
			}else{
				pd.put("company", "该公司不存在");
				pd.put("department", "");
			}
			pd.put("cost", 0);
			}
			//公司为空  部门不为空 产生空的原因是因为部门不存在
			else{
				pd.put("company", "");
				pd.put("department", "该部门不存在");
				pd.put("cost", 0);
			}
			list.add(pd);
		}else{
			for(int a=0;a<varList.size();a++){
				pd1.put("company", varList.get(a).get("RISK_A_EXT_SYS_CP_NAME"));
				pd1.put("department", varList.get(a).get("RISK_A_EXT_SYS_CP_DEP"));
				List<PageData> u=companyService.select(pd1);
				for(int c=0;c<u.size();c++){                                     
					if(u.get(c)!=null){
					map.put(u.get(c).get("RISK_A_EXT_SYS_PARAM_ID"), u.get(c).get("RISK_A_EXT_SYS_FINALPRICE"));
					maptype.put(u.get(c).get("RISK_A_EXT_SYS_PARAM_ID")+"type", u.get(c).get("RISK_A_EXT_SYS_TYPEINFO"));
					}else{
						continue;
					}
				}
				//遍历出每一个key的值
				Set keys=map.keySet();
				if(keys!=null){
					Iterator iterator=keys.iterator();
					while(iterator.hasNext()){
						Object key=iterator.next();
						str+=key.toString()+",";
						str+=maptype.get(key+"type").toString()+",";
						//Double va=Double.parseDouble(detail.get(key).toString());
						//BigDecimal value = BigDecimal.valueOf(va);
						BigDecimal value = new BigDecimal((String)map.get(key));
						money=money.add(value);
						str+=map.get(key).toString()+"!";
					}
				}
				pd1.put("cost", money);
				pd2.put("postscript", str);
				pd2.put("id", UUID.randomUUID().toString());
				companyService.saveinfo(pd2);
				pd1.put("postscript", pd2.get("id"));
				list.add(pd1);
				pd1 = new PageData();
				money=new BigDecimal("0");
				str="";
				map=new HashMap<Object, Object>();
				maptype=new HashMap<Object, Object>();
			}
		}
		pd.put("list", list);
		mv.addObject("pd",pd);
		mv.setViewName("system/billing/company_cost");
		return mv;
	}
	
	
	/**
	 * 公司管理列表
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/list")
	public ModelAndView listdep(Page page){
		log.info("列表Company");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = companyService.list(page);	//列出Company列表
			mv.setViewName("system/param/company_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="findCompanyDep")
	public Object findCompanyDep(){
		PageData pd = new PageData();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = companyService.findListByCpId(pd);
			
			map.put("list", pdList);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			logAfter(logger);
		}
		return AppUtil.returnObject(pd, map);
	
	}
	
	
	
	/**
	 * 去新增页面
	 * @throws Exception 
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd() throws Exception{
		log.info("去新增Company页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("system/param/company_edit");
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
		log.info("去修改Company页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = companyService.findById(pd);	//根据ID读取
			mv.setViewName("system/param/company_edit");
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
		log.info("批量删除Company");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				companyService.deleteAll(ArrayDATA_IDS);
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
		log.info("导出Company到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("客户（公司id）");	//1
			titles.add("客户（公司）");	//2
			titles.add("公司下属部门ID");	//3
			titles.add("公司下属部门");	//4
			titles.add("部门下属人员ID");	//5
			titles.add("部门下属人员");	//6
			titles.add("人员在职状态");	//7
			titles.add("预留");	//8
			titles.add("预留");	//9
			titles.add("预留");	//10
			dataMap.put("titles", titles);
			List<PageData> varOList = companyService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("RISK_A_EXT_SYS_CP_ID"));	//1
				vpd.put("var2", varOList.get(i).getString("RISK_A_EXT_SYS_CP_NAME"));	//2
				vpd.put("var3", varOList.get(i).getString("RISK_A_EXT_SYS_CP_DEPID"));	//3
				vpd.put("var4", varOList.get(i).getString("RISK_A_EXT_SYS_CP_DEP"));	//4
				vpd.put("var5", varOList.get(i).getString("RISK_A_EXT_SYS_CP_PERSONID"));	//5
				vpd.put("var6", varOList.get(i).getString("RISK_A_EXT_SYS_CP_PERSON"));	//6
				vpd.put("var7", varOList.get(i).getString("RISK_A_EXT_SYS_CP_STATE"));	//7
				vpd.put("var8", varOList.get(i).getString("RISK_A_EXT_SYS_CP_P001"));	//8
				vpd.put("var9", varOList.get(i).getString("RISK_A_EXT_SYS_CP_P002"));	//9
				vpd.put("var10", varOList.get(i).getString("RISK_A_EXT_SYS_CP_P003"));	//10
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
