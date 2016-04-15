/*
 * 文 件 名:  TestLast.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  james
 * 创建时间:  2016年4月12日
 */
package test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
public class TestLast {

	public static void main(String[] args) throws InstantiationException, InvocationTargetException {
		MtUserInfoRule mt = new MtUserInfoRule();
		/*
		 * mt.setName("mtName"); mt.setPassport("mtPassport");
		 * mt.setAddress("mtAddress"); mt.setCredentials("mtCredentials");
		 * mt.setEmail("mtEmail"); mt.setHkmlp("mtHkmlp"); mt.setId("mtId");
		 * mt.setMobile("mtMobile"); mt.setMtp("mtMtp"); mt.setTlp("mtTlp");
		 * mt.setPinyin("mtPinyin");
		 */
		mt.setName("1");
		mt.setPassport("1");
		mt.setAddress("1");
		mt.setCredentials("2");
		mt.setEmail("2");
		mt.setHkmlp("2");
		mt.setId("2");
		mt.setMobile("1");
		mt.setMtp("1");
		mt.setTlp("1");
		mt.setPinyin("1");
		// Person p = new
		// Person(1,"name",true,'a',1f,2.00,(Long)2l,(short)1,(byte) 1);
		Person p = new Person();
		p.setId_p(1);
		p.setName_p("personName");
		p.setMen_p(true);
		p.setCh_p('a');
		p.setFloat_p(1f);
		p.setDouble_p(2.0);
		p.setLong_p(2l);
		p.setShort_p((short) 3);
		p.setByte_p((byte) 4);

		Student s = new Student();
		s.setId_s(11);
		s.setName_s("stuName");

		p.setStudent_p(s);
		mt.setPerson(p);

		Map<String, Object> map = new HashMap<String, Object>();

		UserInfoRule info = new UserInfoRule();
		doField(mt, map, info);

		// convert(map,info);

		// systemoutMap(map);

		// writeXmlDocument(mt, "GBK", "src\\student.xml");
		// readDocumentXML("src\\student.xml");
	}

	private static void doField(Object obj, Map<String, Object> map, Object obj2)
			throws InstantiationException, InvocationTargetException {
		try {
			Field[] properties = obj.getClass().getDeclaredFields();
			Field[] field2 = obj2.getClass().getDeclaredFields();
			for (Field p : properties) {

				boolean accessible = p.isAccessible();
				p.setAccessible(true);
				/*
				 * if (returnBoolean(p)) { Element root2 =
				 * root.addElement(p.getName()); doField( p.getType(), root2); }
				 * else if(!returnBoolean(p)){
				 */
				String fieldName = p.getName();
				// boolean类型的isMen的get方法名就是isMen
				String strGet = p.getType().equals(Boolean.class) || p.getType().equals(boolean.class) ? fieldName
						: ("get" + fieldName.substring(0, 1).toUpperCase()
								+ fieldName.substring(1, fieldName.length()));
				Method methodGet = obj.getClass().getDeclaredMethod(strGet);
				Object object = methodGet.invoke(obj);
				for (Field p2 : field2) {
					p2.setAccessible(true);
					if (p.getName().equals(p2.getName())) {
						if (returnBoolean(p)) {
							// Element root2 = root.addElement(p.getName());
							Map<String, Object> map2 = new HashMap<String, Object>();
							map.put(p.getName(), map2);
							/*
							 * String fieldName2 = p2.getName(); String
							 * methodGet2 = (p2.getType().equals(Boolean.class)
							 * || p2.getType().equals(boolean.class) ? "is" :
							 * "get") + fieldName2.substring(0, 1).toUpperCase()
							 * + fieldName2.substring(1, fieldName2.length());
							 * p2.getType().newInstance(); Object invoke =
							 * obj2.getClass().getDeclaredMethod(methodGet2).
							 * invoke(obj2);
							 */
							doField(object, map2, p2.getType().newInstance());
						} else {
							if (boolean.class.equals(p2.getType()) || Boolean.class.equals(p2.getType())) {
								p2.set(obj2, "1".equals(object) ? true : false);
							} else {
								p2.set(obj2, object);
							}

							System.out.println(p2.getName() + ":" + p2.get(obj2));
						}

						// root.addElement(p.getName()).setText(object + "");
						map.put("mt." + p.getName(), object);
					}
					// root.addElement(p.getName()).setText(p.get(obj)+"");
					// System.out.println(p.getName());
					p.setAccessible(accessible);
				}
				// }
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static boolean returnBoolean(Field f) {
		Class t = f.getType();
		if (String.class.equals(t) 		  	|| Date.class.equals(t)
				|| byte.class.equals(t)   	|| Byte.class.equals(t) 
				|| short.class.equals(t)  	|| Short.class.equals(t)  
				|| float.class.equals(t)  	|| Float.class.equals(t)
				|| long.class.equals(t)   	|| Long.class.equals(t)
				|| int.class.equals(t)    	|| Integer.class.equals(t)
				|| double.class.equals(t)	|| Double.class.equals(t) 
				|| boolean.class.equals(t)	|| Boolean.class.equals(t)
				|| char.class.equals(t)     || Character.class.equals(t))
		{
			return false;
		}
		return true;
	}
}
