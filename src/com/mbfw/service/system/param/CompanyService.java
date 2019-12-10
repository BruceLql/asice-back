package com.mbfw.service.system.param;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mbfw.dao.DaoSupport;
import com.mbfw.entity.Page;
import com.mbfw.util.PageData;


@Service("companyService")
public class CompanyService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		
	
		pd.put("RISK_A_EXT_SYS_CP_STATE", "0");
		dao.save("CompanyMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("CompanyMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("CompanyMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("CompanyMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("CompanyMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("CompanyMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("CompanyMapper.deleteAll", ArrayDATA_IDS);
	}

	public List<PageData> companylistAll(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("CompanyMapper.companylistAll", pd);
		}

	public List<PageData> findListByCpId(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("CompanyMapper.findListByCpIdlistAll", pd);
			}

	public PageData findByDEPId(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (PageData)dao.findForObject("CompanyMapper.findByDEPID", pd);

	}

	/*
	 * 查询购买数据的情况
	 */
	public List<PageData> select(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("CompanyMapper.findbycompany", pd);
	}

	/*
	 * 查询业务的汉文名称
	 */
	public String findname(PageData pd) throws Exception {
		return (String) dao.findForObject("CompanyMapper.findname", pd);
	}
	

	/**
	 * 按条件查询公司的数据收费情况
	 * @param pd
	 * @return
	 * @throws Exception 
	 */
	public List<PageData> selectall(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("CompanyMapper.findall", pd);
	}

	/**
	 * 查询公司和部门的信息
	 * @param pd
	 * @return
	 * @throws Exception 
	 */
	public java.util.List<PageData> selectall() throws Exception {
		return (List<PageData>) dao.findForList("CompanyMapper.findcdall");
	}

	public void saveinfo(PageData pd) throws Exception {
		dao.save("CompanyMapper.saveinfo",pd);
		
	}

	public PageData findByComId(PageData pd) throws Exception {
		return (PageData) dao.findForObject("CompanyMapper.findcominfo",pd);
	}


}

