package com.mbfw.util;

import java.security.MessageDigest;

public class MD5 {

	public static String md5(String str) {
		try {
			//获取加密方式为md5的算法对象
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes("UTF-8"));
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return str;
	}

    public static String jm(String inStr) {   
        if(inStr==null)return null;
        char[] a = inStr.toCharArray();   
        for (int i = 0; i < a.length; i++) {   
         a[i] = (char) (a[i] +'1');   
        }   
        String s = new String(a);   
        return s;   
       }
       
       
       public static String jmh(String inStr) {  
    	if(inStr==null)return null;
        char[] a = inStr.toCharArray();   
        for (int i = 0; i < a.length; i++) {   
         a[i] = (char) (a[i]- '1');   
        }   
        String k = new String(a);   
        return k;   
       } 
       
	public static void main(String[] args) {
		String md5 = md5("56bfP^dbQ01");
		System.out.println(md5);
		String t=System.currentTimeMillis() + "";;
		System.out.println("qt687B"+t.trim()+md5);
		//System.out.println(jm(""));
		//System.out.println(jmh(""));
	}
}
