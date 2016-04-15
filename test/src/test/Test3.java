/*
 * 文 件 名:  Test3.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  james
 * 创建时间:  2016年4月12日
 */
package test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <一句话功能简述>
 * 
 * @author james
 * @version [V1.00, 2016年4月12日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class Test3 {
	public static void main(String[] args) {
		UserInfoRule info = new UserInfoRule();
		Person p = new Person();
		info.setPerson(p);
		
		Field[] properties = info.getClass().getDeclaredFields();
		for(Field f : properties)
		{
			String fieldName = f.getName();
			String strGet = "get" + fieldName.substring(0, 1).toUpperCase()
					+ fieldName.substring(1, fieldName.length());
			Method methodGet;
			Object object = null;
				try {
					methodGet = info.getClass().getDeclaredMethod(strGet);
					object = methodGet.invoke(info);
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
