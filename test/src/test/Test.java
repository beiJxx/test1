/*
 * 文 件 名:  Test.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  james
 * 创建时间:  2016年4月5日
 */
package test;

/**
 * <一句话功能简述>
 *  
 * @author  james
 * @version  [V1.00, 2016年4月5日]
 * @see  [相关类/方法]
 * @since V1.00
 */
import java.lang.reflect.Field;

public class Test {
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		
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

		UserInfoRule userInfoRule = new UserInfoRule();

		try {
			convertObjToObj(userInfoRule,mt);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//
		// Field[] infoFields = userInfoRule.getClass().getDeclaredFields();
		// Field[] mtFields = mt.getClass().getDeclaredFields();
		// for (Field field : infoFields) {
		// field.setAccessible(true);
		// for (Field mtField : mtFields) {
		// mtField.setAccessible(true);
		// if (field.getName().equals(mtField.getName())) {
		// boolean mtValue = "1".equals(mtField.get(mt).toString()) ? true :
		// false;
		// field.set(userInfoRule, mtValue);
		// }
		// }
		// System.out.println(field.getName() + ":" +
		// field.get(userInfoRule).toString());
		// }

	}

	public static void convertObjToObj(Object obj1, Object obj2) throws InstantiationException {
		System.out.println("1111111111111   start    111111111111");
		try {
			Field[] firstFields = obj1.getClass().getDeclaredFields();
			Field[] secondFields = obj2.getClass().getDeclaredFields();
			for (Field field1 : firstFields) {
				boolean accessible1 = field1.isAccessible();
				field1.setAccessible(true);

				for (Field field2 : secondFields) {
					boolean accessible2 = field2.isAccessible();
					field2.setAccessible(true);

					if (field1.getType().getName().equals(field2.getType().getName())
							&& null != field2.getType().getDeclaredFields()
							&& field2.getType().getDeclaredFields().length > 0) {
						convertObjToObj(field1, field2.getType());
					} else if (field1.getName().equals(field2.getName())) {
						boolean b = "1".equals(field2.get(obj2)) ? true : false;
						field1.set(obj1, b);
					}
					field2.setAccessible(accessible2);
					/*
					 * if(!Integer.class.equals(field2.getType())) {
					 * System.out.println("111111111111111"); Field[]
					 * declaredFields =
					 * field2.getType().getClass().getDeclaredFields(); for
					 * (Field f : declaredFields) { if
					 * ("name".equals(f.getName()))
					 * System.out.println(f.getName()); } }
					 */

				}
				System.out.println(field1.getName() + ":" + field1.get(obj1).toString());
				field1.setAccessible(accessible1);
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
