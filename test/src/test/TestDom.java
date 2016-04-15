/*
 * 文 件 名:  TestDom.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  james
 * 创建时间:  2016年4月11日
 */
package test;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * <一句话功能简述>
 * 
 * @author james
 * @version [V1.00, 2016年4月11日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class TestDom {

	public static void readDocumentXML(String XMLPathAndName) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
			org.w3c.dom.Document document = builder.parse(XMLPathAndName);
			document.getDocumentElement().getTagName().getClass().getFields();

		} catch (Exception e) {

		}
	}

	/**
	 * DMO4J写入XML
	 * 
	 * @param obj
	 *            泛型对象
	 * @param entityPropertys
	 *            泛型对象的List集合
	 * @param Encode
	 *            XML自定义编码类型(推荐使用GBK)
	 * @param XMLPathAndName
	 *            XML文件的路径及文件名
	 */
	public static void writeXmlDocument(Object obj, String Encode, String XMLPathAndName) {
		long lasting = System.currentTimeMillis();// 效率检测

		try {
			XMLWriter writer = null;// 声明写XML的对象
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding(Encode);// 设置XML文件的编码格式

			String filePath = XMLPathAndName;// 获得文件地址
			File file = new File(filePath);// 获得文件

			if (file.exists()) {
				file.delete();

			}
			// 新建student.xml文件并新增内容

			Document document = DocumentHelper.createDocument();
			String rootname = obj.getClass().getSimpleName();// 获得类名
			Element root = document.addElement(rootname);// 添加根节点
			// doField(obj, root);

			// 生成XML文件
			writer = new XMLWriter(new FileWriter(file), format);
			writer.write(document);
			writer.close();
			long lasting2 = System.currentTimeMillis();
			System.out.println("写入XML文件结束,用时" + (lasting2 - lasting) + "ms");
		} catch (Exception e) {
			System.out.println("XML文件写入失败");
		}

	}

	public static void main(String[] args) throws InstantiationException, InvocationTargetException {
		MtUserInfoRule mt = new MtUserInfoRule();
		/*mt.setName("mtName");
		mt.setPassport("mtPassport");
		mt.setAddress("mtAddress");
		mt.setCredentials("mtCredentials");
		mt.setEmail("mtEmail");
		mt.setHkmlp("mtHkmlp");
		mt.setId("mtId");
		mt.setMobile("mtMobile");
		mt.setMtp("mtMtp");
		mt.setTlp("mtTlp");
		mt.setPinyin("mtPinyin");*/
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

		doField(mt, map);

		MtUserInfoRule info = new MtUserInfoRule();
		convert(map,info);
		
		systemoutMap(map);

		// writeXmlDocument(mt, "GBK", "src\\student.xml");
		// readDocumentXML("src\\student.xml");
	}

	/** 
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param map
	 * @param info
	 * @see [类、类#方法、类#成员]
	 */
	private static void convert(Map<String, Object> map, Object info) {
		Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Object> next = iterator.next();
			Object obj = next.getValue();
			if (obj.getClass().getName().equals("java.util.HashMap")) {
				systemoutMap((Map) obj);
			} else {
				System.out.println(next.getKey() + ":" + obj);
			}
		}
				
	}

	/**
	 * <一句话功能简述> <功能详细描述>
	 * 
	 * @param map
	 * @see [类、类#方法、类#成员]
	 */
	private static void systemoutMap(Map<String, Object> map) {
		Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Object> next = iterator.next();
			Object obj = next.getValue();
			if (obj.getClass().getName().equals("java.util.HashMap")) {
				systemoutMap((Map) obj);
			} else {
				System.out.println(next.getKey() + ":" + obj);
			}
		}

	}

	/**
	 * <一句话功能简述> <功能详细描述>
	 * 
	 * @param properties
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @see [类、类#方法、类#成员]
	 */
	private static void doField(Object obj, Map<String, Object> map)
			throws InstantiationException, InvocationTargetException {
		try {
			Field[] properties = obj.getClass().getDeclaredFields();
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
				if (returnBoolean(p)) {
					// Element root2 = root.addElement(p.getName());
					Map<String, Object> map2 = new HashMap<String, Object>();
					map.put(p.getName(), map2);
					doField(object, map2);
				} else {
					// root.addElement(p.getName()).setText(object + "");
					map.put("mt." + p.getName(), object);
				}
				// root.addElement(p.getName()).setText(p.get(obj)+"");
				// System.out.println(p.getName());
				p.setAccessible(accessible);
			}
			// }
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
		if (String.class.equals(t) || byte.class.equals(t) || short.class.equals(t) || float.class.equals(t)
				|| long.class.equals(t) || int.class.equals(t) || double.class.equals(t) || boolean.class.equals(t)
				|| Byte.class.equals(t) || Short.class.equals(t) || Float.class.equals(t) || Long.class.equals(t)
				|| Double.class.equals(t) || Integer.class.equals(t) || Boolean.class.equals(t) || Date.class.equals(t)
				|| Character.class.equals(t) || char.class.equals(t)) {
			return false;
		}
		return true;
	}
}
