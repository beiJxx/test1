/*
 * 文 件 名:  Test2.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  james
 * 创建时间:  2016年4月11日
 */
package test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * <一句话功能简述>
 * 
 * @author james
 * @version [V1.00, 2016年4月11日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class Test2 {
	
	
	public static void main(String[] args) throws Exception {
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
		
		UserInfoRule customer2 = (UserInfoRule)copy(mt);
	}
	
	public static Object copy(Object object) throws Exception
    {
        Class<?> classType = object.getClass();


        Object objectCopy = classType.getConstructor(new Class[]{}).newInstance(new Object[]{});
        
        //获得对象的所有成员变量
        Field[] fields = classType.getDeclaredFields();
        for(Field field : fields)
        {
        	field.setAccessible(true);
            //获取成员变量的名字
            String name = field.getName();    //获取成员变量的名字，此处为id，name,age
            //System.out.println(name);

            //获取get和set方法的名字
            String firstLetter = name.substring(0,1).toUpperCase();    //将属性的首字母转换为大写            
            String getMethodName = "get" + firstLetter + name.substring(1);
            String setMethodName = "set" + firstLetter + name.substring(1);            
            //System.out.println(getMethodName + "," + setMethodName);
            
            //获取方法对象
            Method getMethod = classType.getMethod(getMethodName, new Class[]{});
            Method setMethod = classType.getMethod(setMethodName, new Class[]{field.getType()});//注意set方法需要传入参数类型
            
            //调用get方法获取旧的对象的值
            Object value = getMethod.invoke(object, new Object[]{});
            //调用set方法将这个值复制到新的对象中去
            setMethod.invoke(objectCopy, new Object[]{value});

        }
        return objectCopy;
    }
}
