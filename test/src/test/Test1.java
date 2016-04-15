/*
 * 文 件 名:  Test1.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  james
 * 创建时间:  2016年4月12日
 */
package test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <一句话功能简述>
 * 
 * @author james
 * @version [V1.00, 2016年4月12日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class Test1 {
	public static <T> T convertClass(Object obj, Class<T> cla)
			throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
		Map<String, Object> maps = new HashMap<String, Object>();
		if (null == obj) {
			return null;
		}
		Class<?> cls = obj.getClass();
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			String strGet = "get" + fieldName.substring(0, 1).toUpperCase()
					+ fieldName.substring(1, fieldName.length());
			Method methodGet = cls.getDeclaredMethod(strGet);
			Object object = methodGet.invoke(obj);
			maps.put(fieldName, object == null ? "" : object);
		}
		
		

		
		return setValue(cla, maps);

	}

	public static void main(String arg0[])
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		MtUserInfoRule mt = new MtUserInfoRule();
		mt.setName("2");
		mt.setPassport("2");
		mt.setAddress("1");
		mt.setCredentials("1");
		mt.setEmail("1");
		mt.setHkmlp("1");
		mt.setId("1");
		mt.setMobile("1");
		mt.setMtp("1");
		mt.setTlp("1");
		mt.setPinyin("1");
		UserInfoRule base = convertClass(mt, UserInfoRule.class);
		System.out.println(base.isName());
	}
	
	public static <T> T setValue(Class<T> cla,Map<String, Object> maps) throws InstantiationException, IllegalAccessException
	{
		T dataBean = cla.newInstance();
		Field[] beanFields = cla.getDeclaredFields();
		try {
			for (Field field : beanFields) {
				field.setAccessible(true);
				String fieldName = field.getName();
				Class<?> fieldType = field.getType();
				String fieldValue = maps.get(fieldName) == null ? null : maps.get(fieldName).toString();
				if (fieldValue != null) {
					try {
						if (String.class.equals(fieldType)) {
							field.set(dataBean, fieldValue);
						} else if (byte.class.equals(fieldType)) {
							field.setByte(dataBean, Byte.parseByte(fieldValue));

						} else if (Byte.class.equals(fieldType)) {
							field.set(dataBean, Byte.valueOf(fieldValue));

						} else if (boolean.class.equals(fieldType)) {
							field.setBoolean(dataBean, Boolean.parseBoolean(fieldValue));

						} else if (Boolean.class.equals(fieldType)) {
							field.set(dataBean, Boolean.valueOf(fieldValue));

						} else if (short.class.equals(fieldType)) {
							field.setShort(dataBean, Short.parseShort(fieldValue));

						} else if (Short.class.equals(fieldType)) {
							field.set(dataBean, Short.valueOf(fieldValue));

						} else if (int.class.equals(fieldType)) {
							field.setInt(dataBean, Integer.parseInt(fieldValue));

						} else if (Integer.class.equals(fieldType)) {
							field.set(dataBean, Integer.valueOf(fieldValue));

						} else if (long.class.equals(fieldType)) {
							field.setLong(dataBean, Long.parseLong(fieldValue));

						} else if (Long.class.equals(fieldType)) {
							field.set(dataBean, Long.valueOf(fieldValue));

						} else if (float.class.equals(fieldType)) {
							field.setFloat(dataBean, Float.parseFloat(fieldValue));

						} else if (Float.class.equals(fieldType)) {
							field.set(dataBean, Float.valueOf(fieldValue));

						} else if (double.class.equals(fieldType)) {
							field.setDouble(dataBean, Double.parseDouble(fieldValue));

						} else if (Double.class.equals(fieldType)) {
							field.set(dataBean, Double.valueOf(fieldValue));

						} else if (Date.class.equals(fieldType)) {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
							field.set(dataBean, sdf.parse(fieldValue));
						}
						else
						{
							System.out.println("1111111111");
						}
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (ParseException e) {
						e.printStackTrace();
					}

				}
			}
			return dataBean;
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataBean;
	}

}
