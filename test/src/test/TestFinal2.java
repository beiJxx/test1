/*
 * 文 件 名:  TestLast.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  james
 * 创建时间:  2016年4月12日
 */
package test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * <一句话功能简述>
 * 
 * @author james
 * @version [V1.00, 2016年4月12日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class TestFinal2 {

	private static String value;
	private static Properties prop = new Properties();

	static {
		InputStream in;
		try {
			in = new BufferedInputStream(new FileInputStream("src/test/test.properties"));
			prop.load(in);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InstantiationException, InvocationTargetException {

		long lasting = System.currentTimeMillis();// 效率检测

		List<MtUserInfoRule> list = new ArrayList<MtUserInfoRule>();
		for (int i = 0; i < 500; i++) {
		//int i = 1;
			MtUserInfoRule mt = new MtUserInfoRule();
			mt.setName(i + "");
			mt.setPassport(i + "");
			mt.setAddress(i + "");
			mt.setCredentials(i + "");
			mt.setEmail(i + "");
			mt.setHkmlp(i + "");
			mt.setId(i + "");
			mt.setMobile(i + "");
			mt.setMtp(i + "");
			mt.setTlp(i + "");
			mt.setPinyin(i + "");
			mt.setName2(i + "");
			mt.setPassport2(i + "");
			mt.setAddress2(i + "");
			mt.setCredentials2(i + "");
			mt.setEmail2(i + "");
			mt.setHkmlp2(i + "");
			mt.setId2(i + "");
			mt.setMobile2(i + "");
			mt.setMtp2(i + "");
			mt.setTlp2(i + "");
			mt.setPinyin2(i + "");
			mt.setName3(i + "");
			mt.setPassport3(i + "");
			mt.setAddress3(i + "");
			mt.setCredentials3(i + "");
			mt.setEmail3(i + "");
			mt.setHkmlp3(i + "");
			mt.setId3(i + "");
			mt.setMobile3(i + "");
			mt.setMtp3(i + "");
			mt.setTlp3(i + "");
			mt.setPinyin3(i + "");

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
			list.add(mt);
		}

		UserInfoRule info = new UserInfoRule();
		for (MtUserInfoRule mt : list) {
			doField(mt, info);
		}
		long lasting2 = System.currentTimeMillis();
		System.out.println("MtUserInfoRule--》UserInfoRule转换完成,用时" + (lasting2 - lasting) + "ms");
	}

	private static void doField(Object obj, Object obj2) throws InstantiationException, InvocationTargetException {
		try {
			Field[] field1 = obj.getClass().getDeclaredFields();
			Field[] field2 = obj2.getClass().getDeclaredFields();
			for (Field p2 : field2) {
				boolean accessible = p2.isAccessible();
				p2.setAccessible(true);
				String fieldName2 = p2.getName();
				for (Field p1 : field1) {
					p1.setAccessible(true);
					String fieldName1 = p1.getName();
					// 两个对象名字相同（如果两个对象名字有差异，则可以转换进行匹配）
					if (isMatch(fieldName1, fieldName2)) {
					//if (fieldName1.equals(fieldName2)) {
						// boolean类型的isMen的get方法名就是isMen
						String strGet = p1.getType().equals(Boolean.class) || p1.getType().equals(boolean.class) ? fieldName1
								: ("get" + fieldName1.substring(0, 1).toUpperCase()
										+ fieldName1.substring(1, fieldName1.length()));
						Method methodGet = obj.getClass().getDeclaredMethod(strGet);
						Object object1 = methodGet.invoke(obj);
						if (returnBoolean(p2)) {
							//System.out.println("======================"+p2.getType().newInstance()+"  start ======================");
							doField(object1, p2.getType().newInstance());
							//System.out.println("======================"+p2.getType().newInstance()+"  end   ======================");
						} else {
							if (boolean.class.equals(p2.getType()) || Boolean.class.equals(p2.getType())) {
								p2.set(obj2, "1".equals(object1) ? true : false);
							} else {
								p2.set(obj2, object1);
							}
							//System.out.println(p2.getName() + ":" + p2.get(obj2));
						}
						break;
					}
					p1.setAccessible(accessible);
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

	}

	/**
	 * // marketPrice market_price <功能详细描述>
	 * 
	 * @param name
	 * @param name2
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	private static boolean isMatch(String name1, String name2) {

		value = prop.getProperty(name2);
		if (name1.equals(value)) {
			return true;
		}
		return false;
	}

	// 判断是否是基本数据类型或者是String、Date（可能不全面，可能还有其他类型）
	public static boolean returnBoolean(Field f) {
		Class t = f.getType();
		if (String.class.equals(t) || Date.class.equals(t) || byte.class.equals(t) || Byte.class.equals(t)
				|| short.class.equals(t) || Short.class.equals(t) || float.class.equals(t) || Float.class.equals(t)
				|| long.class.equals(t) || Long.class.equals(t) || int.class.equals(t) || Integer.class.equals(t)
				|| double.class.equals(t) || Double.class.equals(t) || boolean.class.equals(t)
				|| Boolean.class.equals(t) || char.class.equals(t) || Character.class.equals(t)) {
			return false;
		}
		return true;
	}
}
