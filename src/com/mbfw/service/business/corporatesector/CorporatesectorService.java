package com.mbfw.service.business.corporatesector;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mbfw.dao.DaoSupport;
import com.mbfw.entity.Page;
import com.mbfw.util.PageData;
import com.mbfw.util.Tools;

/** 
 * @Description 公司和部门
 */
@Service("corporatesectorService")
public class CorporatesectorService {

	private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(this.getClass());
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
    /**
	 * 新增
	 */
	public void save(PageData pd)throws Exception{
		String time=Tools.date2Str(new Date());
		pd.put("mrc_cdate", time);
		pd.put("mrc_udate", time);
		pd=this.getformatPd(pd);
		dao.save("CorporatesectorMapper.save", pd);
	}
	
	//处理排序号
	private PageData getformatPd(PageData pd){
		//排序号
		String mrc_order_=pd.getString("mrc_order");
		if(Tools.notEmpty(mrc_order_)){
			pd.put("mrc_order", Integer.parseInt(mrc_order_));
		}else{
			pd.put("mrc_order", null);	
		}
		//父id
		String mrc_type_=pd.getString("mrc_type");//类型（1公司、2部门）
		if("2".equals(mrc_type_)){//2部门
			pd.put("mrc_gsid", pd.getString("mrc_pid"));//公司id
		}else{
			pd.put("mrc_pid", null);//父id
			pd.put("mrc_gsid", pd.getString("mrc_uuid"));//公司id
		}
		
		return pd;
	}
    /**
	 * 删除(只根据主键id)
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("CorporatesectorMapper.delete", pd);
	}
	
	/**
	 * 删除(根据非空(null,empty)属性值,有cmd)
	 */
	public void deleteIf(PageData pd)throws Exception{
		dao.delete("CorporatesectorMapper.deleteIf", pd);
	}
	
    /**
	 * 修改(根据主键只修改属于前台录入的字段)
	 */
	public void edit(PageData pd)throws Exception{
		String time=Tools.date2Str(new Date());
		pd.put("mrc_udate", time);
		pd=this.getformatPd(pd);
		dao.update("CorporatesectorMapper.edit", pd);
	}
	
    /**
	 * 修改(根据主键修改所有字段)
	 */
	public void updateSetAll(PageData pd)throws Exception{
		String time=Tools.date2Str(new Date());
		pd.put("mrc_udate", time);
		dao.update("CorporatesectorMapper.updateSetAll", pd);
	}
	
    /**
	 * 修改(根据主键修改非空(null,empty)字段)
	 */
	public void updateSetIf(PageData pd)throws Exception{
		String time=Tools.date2Str(new Date());
		pd.put("mrc_udate", time);
		dao.update("CorporatesectorMapper.updateSetIf", pd);
	}
	
	/**
	 * 修改(根据主键修改非空(null)字段；可将字段修改为空字符串)
	 */
	public void updateSetIfNull(PageData pd)throws Exception{
		String time=Tools.date2Str(new Date());
		pd.put("mrc_udate", time);
		dao.update("CorporatesectorMapper.updateSetIfNull", pd);
	}
	
	/**
	 * 修改(根据自定义条件修改非空(null,empty)字段)<br/>
	 * 在pd对象的cmd中加入自定义的条件，如下<br/>
	 * pd.put("cmd","自定义的条件");
	 */
	public void updateSetIfByCmd(PageData pd)throws Exception{
		String time=Tools.date2Str(new Date());
		pd.put("mrc_udate", time);
		dao.update("CorporatesectorMapper.updateSetIfByCmd", pd);
	}
	
    /**
	 * 列表(分页，即 listPage(Page page)方法)
	 */
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("CorporatesectorMapper.datalistPage", page);
	}
	
    /**
	 * 列表(分页，即 list(Page page)方法)
	 */
	public List<PageData> listPage(Page page)throws Exception{
		return (List<PageData>)dao.findForList("CorporatesectorMapper.datalistPage", page);
	}
	
    /**
	 * 列表(分页有where条件，有cmd)
	 */
	public List<PageData> listPageWhere(Page page)throws Exception{
		return (List<PageData>)dao.findForList("CorporatesectorMapper.dataWherelistPage", page);
	}
	
    /**
	 * 列表(查全部)
	 */
	public List<PageData> listAll()throws Exception{
		return (List<PageData>)dao.findForList("CorporatesectorMapper.listAll", null);
	}
	
    /**
	 * 列表(查非空(null,empty)属性值，有cmd)
	 */
	public List<PageData> listIf(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("CorporatesectorMapper.listIf", pd);
	}
	
	/**
	 * 列表(非主键的like查询，有cmd)
	 */
	public List<PageData> listLike(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("CorporatesectorMapper.listLike", pd);
	}

    /**
	 * 列表(查自定义条件)<br/>
	 * 在pd对象的cmd中加入自定义的条件，如下<br/>
	 * pd.put("cmd","自定义的条件");
	 */
	public List<PageData> listCmd(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("CorporatesectorMapper.listCmd", pd);
	}
		
    /**
	 * 通过主键id获取单条数据
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("CorporatesectorMapper.findById", pd);
	}
	
	/**
	 * 批量删除(根据主键)
	 * @param ArrayDATA_IDS 存放主键的数组
	 * @throws Exception 
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("CorporatesectorMapper.deleteAll", ArrayDATA_IDS);
	}
	/* =======================================以上系统自动生成======================================== */
	
	
	
    /**
	 * 列表(分页)
	 * 上级的字段都加p前缀，如上级主键为pmrc_uuid，上级名称为pmrc_name
	 */
	public List<PageData> list2Page(Page page)throws Exception{
		return (List<PageData>)dao.findForList("CorporatesectorMapper.data2listPage", page);
	}
	
    /**
	 * 列表(查非空(null,empty)属性值，有cmd)，关联了上级（所属公司）<br>
	 * 上级的字段都加p前缀，如上级主键为pmrc_uuid，上级名称为pmrc_name
	 */
	public List<PageData> listIf2(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("CorporatesectorMapper.listIf2", pd);
	}
	
	/**
	 * 根据指定公司查询其下的可用（enable）部门
	 * @Description 
	 * @param pd 指定的公司
	 * @return null或结果
	 * @throws Exception
	 * @author suj
	 */
	public List<PageData> listBMbyGS(PageData pd)throws Exception{
		List<PageData> result=null;
		if(pd!=null && !pd.isEmpty()){
			pd.put("mrc_type", "1");//1为公司
			List<PageData>  list_gs= this.listIf(pd);
			if(list_gs!=null && !list_gs.isEmpty()){
				pd=list_gs.get(0);
				PageData temp=new PageData();
				temp.put("mrc_type", "2");//2为部门
				temp.put("mrc_state", "enable");//状态(enable可用、disable禁用)
				temp.put("mrc_pid", pd.getString("mrc_uuid"));//父id
				result=this.listIf(temp);
			}
		}
		return result;
	}
}

