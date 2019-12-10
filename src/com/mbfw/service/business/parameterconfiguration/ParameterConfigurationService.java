package com.mbfw.service.business.parameterconfiguration;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mbfw.dao.DaoSupport;
import com.mbfw.entity.Page;
import com.mbfw.util.PageData;
import com.mbfw.util.Tools;

/**
 * 系统参数配置
 * @Description 
 */
@Service("parameterconfigurationService")
public class ParameterConfigurationService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
    /**
	 * 新增
	 */
	public void save(PageData pd)throws Exception{
		dao.save("ParameterConfigurationMapper.save", pd);
	}
	
    /**
	 * 删除(只根据主键id)
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("ParameterConfigurationMapper.delete", pd);
	}
	
	/**
	 * 删除(根据非空(null,empty)属性值,有cmd)
	 */
	public void deleteIf(PageData pd)throws Exception{
		dao.delete("ParameterConfigurationMapper.deleteIf", pd);
	}
	
    /**
	 * 修改(根据主键只修改属于前台录入的字段)
	 */
	public void edit(PageData pd)throws Exception{
		pd.put("UDATE", Tools.date2Str(new Date()));//修改时间
		dao.update("ParameterConfigurationMapper.edit", pd);
	}
	
    /**
	 * 修改(根据主键修改所有字段)
	 */
	public void updateSetAll(PageData pd)throws Exception{
		pd.put("UDATE", Tools.date2Str(new Date()));//修改时间
		dao.update("ParameterConfigurationMapper.updateSetAll", pd);
	}
	
    /**
	 * 修改(根据主键修改非空(null,empty)字段)
	 */
	public void updateSetIf(PageData pd)throws Exception{
		pd.put("UDATE", Tools.date2Str(new Date()));//修改时间
		dao.update("ParameterConfigurationMapper.updateSetIf", pd);
	}
	
	/**
	 * 修改(根据主键修改非空(null)字段)
	 */
	public void updateSetIfNull(PageData pd)throws Exception{
		pd.put("UDATE", Tools.date2Str(new Date()));//修改时间
		dao.update("ParameterConfigurationMapper.updateSetIfNull", pd);
	}
	
	/**
	 * 修改(根据自定义条件修改非空(null,empty)字段)<br/>
	 * 在pd对象的cmd中加入自定义的条件，如下<br/>
	 * pd.put("cmd","自定义的条件");
	 */
	public void updateSetIfByCmd(PageData pd)throws Exception{
		pd.put("UDATE", Tools.date2Str(new Date()));//修改时间
		dao.update("ParameterConfigurationMapper.updateSetIfByCmd", pd);
	}
	
    /**
	 * 列表(分页，即 listPage(Page page)方法)
	 */
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("ParameterConfigurationMapper.datalistPage", page);
	}
	
    /**
	 * 列表(分页，即 list(Page page)方法)
	 */
	public List<PageData> listPage(Page page)throws Exception{
		return (List<PageData>)dao.findForList("ParameterConfigurationMapper.datalistPage", page);
	}
	
    /**
	 * 列表(分页有where条件，有cmd)
	 */
	public List<PageData> listPageWhere(Page page)throws Exception{
		return (List<PageData>)dao.findForList("ParameterConfigurationMapper.dataWherelistPage", page);
	}
	
    /**
	 * 列表(查全部)
	 */
	public List<PageData> listAll()throws Exception{
		return (List<PageData>)dao.findForList("ParameterConfigurationMapper.listAll", null);
	}
	
    /**
	 * 列表(查非空(null,empty)属性值，有cmd)
	 */
	public List<PageData> listIf(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ParameterConfigurationMapper.listIf", pd);
	}
	
	/**
	 * 列表(非主键的like查询，有cmd)
	 */
	public List<PageData> listLike(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ParameterConfigurationMapper.listLike", pd);
	}

    /**
	 * 列表(查自定义条件)<br/>
	 * 在pd对象的cmd中加入自定义的条件，如下<br/>
	 * pd.put("cmd","自定义的条件");
	 */
	public List<PageData> listCmd(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ParameterConfigurationMapper.listCmd", pd);
	}
		
    /**
	 * 通过主键id获取单条数据
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("ParameterConfigurationMapper.findById", pd);
	}
	
	/**
	 * 批量删除(根据主键)
	 * @param ArrayDATA_IDS 存放主键的数组
	 * @throws Exception 
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("ParameterConfigurationMapper.deleteAll", ArrayDATA_IDS);
	}
	/* =======================================以上系统自动生成======================================== */
	
	
	
	/**
	 * @Description 获取所有类型名称
	 * @param pd
	 * @return
	 * @throws Exception
	 * @author suj
	 */
	public List<PageData> getTypeNames(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ParameterConfigurationMapper.getTypeNames", pd);
	}
	
	/**
	 * 
	 * @Description 获取指定主键UUID和字段名称field中的值
	 * @param uuid 主键即识别码
	 * @param field 字段名称即列名称
	 * @return 指定的值
	 * @author suj
	 * @throws Exception 
	 */
	public String getValue(String uuid,String field) throws Exception{
		String str=null;
		if(Tools.notEmpty(uuid) && Tools.notEmpty(field)){
			PageData pd=new PageData();
			pd.put("UUID", uuid.trim());//
			List<PageData> list=this.listIf(pd);
			if(list!=null && !list.isEmpty()){
				str= list.get(0).getString(field.trim().toUpperCase());
			}
		}
		return str;
	}
	
	/**
	 * 
	 * @Description 判断指定主键UUID和字段名称field中的值是否是参数value
	 * @param uuid 主键即识别码
	 * @param field 字段名称即列名称
	 * @param value 判断是否是该值
	 * @author suj
	 * @throws Exception 
	 */
	public boolean ifFieldIsValue(String uuid,String field,String value) throws Exception{
		boolean b=false;
		if(Tools.notEmpty(uuid) && Tools.notEmpty(field) && Tools.notEmpty(value)){
			PageData pd=new PageData();
			pd.put("UUID", uuid.trim());//
			pd.put(field.trim().toUpperCase(), value);//
			List<PageData> list=this.listIf(pd);
			if(list!=null && !list.isEmpty()){
				b=true;
			}
		}
		return b;
	}
	
	
}

