package com.mbfw.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.mbfw.entity.system.User;
import net.sf.json.JSONNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

public class PageData extends HashMap implements Map {

	private static final long serialVersionUID = 1L;

	Map map = null;
	HttpServletRequest request;

	/*public PageData(HttpServletRequest request) {
		this.request = request;
		Map properties = request.getParameterMap();
		Map returnMap = new HashMap();
		Iterator entries = properties.entrySet().iterator();
		Map.Entry entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString();
			}
			returnMap.put(name, value);
		}
		map = returnMap;
	}*/

	public PageData(HttpServletRequest request) {
		this.request = request;
		Map properties = request.getParameterMap();
		Map returnMap = new HashMap();
		Iterator entries = properties.entrySet().iterator();
		Map.Entry entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {//遍历
			entry = (Map.Entry) entries.next();
			name = ((String) entry.getKey()).trim();//键名，（去空格）
			Object valueObj = entry.getValue();//键值
			if (null == valueObj) {//1、键值为null 
				value = "";
			} else if (valueObj instanceof String[]) {//2、键值为字符串数组
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					String t=values[i];
					value = (t!=null?t.trim():t) + ",";//将数组以逗号分隔拼接字符串（去空格）
				}
				value = value.substring(0, value.length() - 1);//去掉末尾的逗号
			} else {
				String s=valueObj.toString();
				value =(s!=null?s.trim():s);//3、其他情况转化为String型（去空格）
			}
			returnMap.put(name, value);
		}
		map = returnMap;
	}
	
	public PageData() {
		map = new HashMap();
	}

	public PageData(Map map_) {
		if(map_==null)map_=new HashMap();
		map = map_;
	}
	
	@Override
	public Object get(Object key) {
		Object obj = null;
		if (map.get(key) instanceof Object[]) {
			Object[] arr = (Object[]) map.get(key);
			obj = request == null ? arr : (request.getParameter((String) key) == null ? arr : arr[0]);
		} else {
			obj = map.get(key);
		}
		return obj;
	}

	public String getString(Object key) {
		//return (String) get(key);
		Object obj=get(key);
		return obj==null?null:obj.toString();
	}

	public String getToString(Object key) {
		Object obj=get(key);
		if(obj!=null){
			return obj.toString();	
		}
		return "";
	}
	/**
	 * 去除空格
	 */
	public String getStrTrim(Object key) {
		String str=(String) get(key);
		return str!=null?str.trim():str;
	}
	
	/**
	 * 过滤特殊字符。保留中间部分的空格和逗号(,)和点号(.)和冒号(:)和百分号(%)和邮箱符(@)和下划线(_)和减号(-)和英文字母和数字和中文字符(不包括中文符号)，其他的都替换为空字符串并去掉前后空格。
	 */
	public String getStrFilter(Object key) {
		String str=(String) get(key);
		if(str==null)return null;
		// 只允许 .:%@_-和字母和数字和中文字符 
		String regEx ="[^ ,.:%@a-zA-Z0-9_-\u4E00-\u9FBF]";
		Pattern p = Pattern.compile(regEx); 
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();//替换为空字符串并去空格
	}
	
	/**
	 * 获取英文、数字和中文，并过滤特殊字符。保留中间部分的空格和逗号(,)和点号(.)和百分号(%)和减号(-)和英文字母和数字和中文字符(不包括中文符号)，其他的都替换为空字符串并去掉前后空格。
	 */
	public String getStr3EnNuZn(Object key) {
		String str=(String) get(key);
		if(str==null)return null;
		// 只允许 .:%@_-和字母和数字和中文字符 
		String regEx ="[^ ,.%a-zA-Z0-9-\u4E00-\u9FBF]";
		Pattern p = Pattern.compile(regEx); 
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();//替换为空字符串并去空格
	}
	/**
	 * 获取英文，并过滤特殊字符。保留中间空格和英文字母，其他的都替换为空字符串并去掉前后空格。
	 */
	public String getStrFiltEn(Object key) {
		String str=(String) get(key);
		if(str==null)return null;
		// 只允许字母
		String regEx ="[^ a-zA-Z]";
		Pattern p = Pattern.compile(regEx); 
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();//替换为空字符串并去空格
	}
	/**
	 * 获取数字，并过滤特殊字符。保留中间空格和.%-和数字，其他的都替换为空字符串并去掉前后空格。
	 */
	public String getStrFiltNu(Object key) {
		String str=(String) get(key);
		if(str==null)return null;
		// 只允许字母
		String regEx ="[^ .%0-9-]";
		Pattern p = Pattern.compile(regEx); 
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();//替换为空字符串并去空格
	}
	/**
	 * 获取中文，并过滤特殊字符。保留中间空格和中文字符(不包括中文符号)，其他的都替换为空字符串并去掉前后空格。
	 */
	public String getStrFiltZn(Object key) {
		String str=(String) get(key);
		if(str==null)return null;
		// 只允许字母
		String regEx ="[^ \u4E00-\u9FBF]";
		Pattern p = Pattern.compile(regEx); 
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();//替换为空字符串并去空格
	}
	/**
	 * 获取日期时间，并过滤特殊字符。保留中间空格和日期时间(可以有冒号(:)和减号(-))，其他的都替换为空字符串并去掉前后空格。
	 */
	public String getStrFiltDa(Object key) {
		String str=(String) get(key);
		if(str==null)return null;
		// 只允许字母
		String regEx ="[^ :0-9-]";
		Pattern p = Pattern.compile(regEx); 
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();//替换为空字符串并去空格
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object put(Object key, Object value) {
		return map.put(key, value);
	}

	@Override
	public Object remove(Object key) {
		return map.remove(key);
	}

	public void clear() {
		map.clear();
	}

	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return map.containsKey(key);
	}

	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return map.containsValue(value);
	}

	public Set entrySet() {
		// TODO Auto-generated method stub
		return map.entrySet();
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return map.isEmpty();
	}

	public Set keySet() {
		// TODO Auto-generated method stub
		return map.keySet();
	}

	@SuppressWarnings("unchecked")
	public void putAll(Map t) {
		// TODO Auto-generated method stub
		map.putAll(t);
	}

	public int size() {
		// TODO Auto-generated method stub
		return map.size();
	}

	public Collection values() {
		// TODO Auto-generated method stub
		return map.values();
	}

	/**
	 * PageData转化成JsonString
	 */
	public String toJsonStr(){
		if(map==null)return null;
		return JSON.toJSONString(map);
	}
	
	/**
	 * PageData转化成com.alibaba.fastjson.JsonObject对象
	 */
	public JSONObject toJsonObj(){
		if(map==null)return null;
		return JSON.parseObject(JSON.toJSONString(map));
	}
	
	/**
	 * 获取字符串型数组。获取指定key键的值，并以逗号为分隔符转成成字符串数组
	 * @Description 
	 * @param key 键名
	 * @return null或String[]
	 */
	public String[] getArrayStr(Object key){
		String[] result=null;
		String str=(String) get(key);
		if(str!=null && !"".equals(str) && !"null".equals(str)){
			result=str.split(",\\s*");
		}
		return result;
	}
	
	/**
	 * 替换JSONNull类型的值为指定的的内容。
	 * @Description 
	 * @param obj 指定的内容。
	 * @author suj
	 */
	public void replaceJSONNull(Object obj){
		try {
			if(map!=null && !map.isEmpty()){
				Set set=map.keySet();
				if(set!=null && !set.isEmpty()){
					Object[] strs=set.toArray();
					if(strs!=null && strs.length>0){
						String name_="";
						for(int i=0;i<strs.length;i++){
							name_=strs[i].toString();
							if(map.get(name_)==JSONNull.getInstance())map.put(name_, obj);
						}
					}
				}
			}
		} catch (Exception e) {
		}
	}
	
	/**
	 * 获取字符串型数组。获取指定key键的值，并以指定的splitRegex为分隔符转成成字符串数组
	 * @Description 
	 * @param key 键名
	 * @param splitRegex 分隔符
	 * @return null或String[]
	 */
	public String[] getArrayStr(Object key,String splitRegex){
		String[] result=null;
		String str=(String) get(key);
		if(str!=null && !"".equals(str) && !"null".equals(str)){
			result=str.split(splitRegex);
		}
		return result;
	}
	
	/**
	 * 设置默认值。<br>
	 * 如果指定键(keyName)的值为null或空字符串，则将它设置成给定的值(obj)。<br>
	 * @Description 
	 * @param keyName 属性名称，即key键名称
	 * @param obj 默认的值
	 */
	public void setDefault(String keyName,Object obj){
		if(map!=null){
			Object value=map.get(keyName);
			if(value==null || "".equals(value.toString())){
				map.put(keyName, obj);
			}
		}
	}

	/**
	 * 直接通过pd.getUser() 获取当前登陆用户的信息
	 * @return
	 */
	public User getUser (){
		//获取当前的登录用户
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		User user = (User) session.getAttribute(Const.SESSION_USER);
		return user;
	}

}
