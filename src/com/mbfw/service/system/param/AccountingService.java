package com.mbfw.service.system.param;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mbfw.dao.DaoSupport;
import com.mbfw.entity.Page;
import com.mbfw.util.PageData;


@Service("accountingService")
public class AccountingService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/***
	 * 新增客户
	 * @param pd
	 * @throws Exception
	 */
	public void saveByClient(PageData pd) throws Exception {
		 dao.save("AccountingMapper.save", pd);
		
	}
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		String type=(String) pd.get("RISK_A_EXT_SYS_TYPE");
		if("1".equals(type)){
			pd.put("RISK_A_EXT_SYS_TYPEINFO", "按条收费");
		}else if("2".equals(type)){
			pd.put("RISK_A_EXT_SYS_TYPEINFO", "按天收费");
		}else if("3".equals(type)){
			pd.put("RISK_A_EXT_SYS_TYPEINFO", "按月收费");
		}else if("4".equals(type)){
			pd.put("RISK_A_EXT_SYS_TYPEINFO", "按季收费");
		}else if("5".equals(type)){
			pd.put("RISK_A_EXT_SYS_TYPEINFO", "按年收费");
		}else{
			pd.put("RISK_A_EXT_SYS_TYPEINFO", "不收费");
			
		}
		dao.save("AccountingMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("AccountingMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		String type=(String) pd.get("RISK_A_EXT_SYS_TYPE");
		if("1".equals(type)){
			pd.put("RISK_A_EXT_SYS_TYPEINFO", "按条收费");
		}else if("2".equals(type)){
			pd.put("RISK_A_EXT_SYS_TYPEINFO", "按天收费");
		}else if("3".equals(type)){
			pd.put("RISK_A_EXT_SYS_TYPEINFO", "按月收费");
		}else if("4".equals(type)){
			pd.put("RISK_A_EXT_SYS_TYPEINFO", "按季收费");
		}else if("5".equals(type)){
			pd.put("RISK_A_EXT_SYS_TYPEINFO", "按年收费");
		}else{
			pd.put("RISK_A_EXT_SYS_TYPEINFO", "不收费");
			
		}
		dao.update("AccountingMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("AccountingMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("AccountingMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("AccountingMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("AccountingMapper.deleteAll", ArrayDATA_IDS);
	}

	public List<PageData> accountList(Page page) throws Exception {
		return (List<PageData>)dao.findForList("AccountingMapper.accountlistPage", page);
		
	}

	public List<PageData> accountTypeList(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("AccountingMapper.accountTypelistAll",pd);
		}

	public List<PageData>findByParamID(PageData pd) throws Exception {
		
		return (List<PageData>)dao.findForList("AccountingMapper.findByParamIdAndType", pd);
	}

	
	
}

