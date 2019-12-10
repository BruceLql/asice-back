package com.mbfw.service.system.param;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mbfw.dao.DaoSupport;
import com.mbfw.entity.Page;
import com.mbfw.util.PageData;
import com.mbfw.util.Tools;

/**
 * 数据源
 * @Description 
 * @author wdt
 */
@Service("paramService")
public class ParamService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("ParamMapper.save", pd);
		//dao.save("BillingMapper.saveParam", pd);
		//dao.save("InterfaceMapper.save", pd);
		
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("ParamMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("ParamMapper.edit", pd);
		//dao.update("InterfaceMapper.editByC006", pd);
		
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("ParamMapper.datalistPage", page);
	}
	
	public List<PageData> listIf(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ParamMapper.listIf", pd);
	}
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ParamMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("ParamMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("ParamMapper.deleteAll", ArrayDATA_IDS);
	}
	
	/**
	 * 根据指定的主键获取对象。
	 * @Description 
	 * @param id 主键的值
	 * @param field 字段的名称
	 * @return
	 */
	public PageData getIdforField(String id){
		PageData result =new PageData();
		if(Tools.notEmpty(id)){
			PageData pd=new PageData();
			List<PageData> list=null;
			pd.put("PARAM_ID", id.trim());//主键
			try {
				list=this.listIf(pd);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(list!=null && !list.isEmpty()){
				result=list.get(0);
			}
		}
		return result;
	}
	
	/**
	 * 查询所有的来源公司
	 * @Description 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listGsType(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ParamMapper.listGsType", pd);
	} 
}

