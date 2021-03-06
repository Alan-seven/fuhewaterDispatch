/*
 * @(#) Algorithm.java           1.0 2015/1/15/ ChaoWang
 *
 * Copyright(C) 2011-2015 ChaoWang   All Rights Reserved
 */
package util;

import com.thoughtworks.xstream.XStream;

import java.io.*;
import java.lang.reflect.Field;


public class ClassFactory {
	/**
	 * 将类写入XML
	 *
	 * @param object
	 * @param path
	 */
	public static <T> void writeXML(T object, String path) {
		XStream xstream = null;
		try {
			xstream = new XStream();
			xstream.autodetectAnnotations(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String xml = xstream.toXML(object);
		File f = null;
		try {
			f = createXmlFile(path);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		writeDoc(f, xml);
	}

	/**
	 * 将XML转换为类
	 *
	 * @param path
	 *            XML文件路径
	 * @return
	 */
	public static <T> T readXML(String path) {
		XStream xstream = null;
		try {
			xstream = new XStream(); // xstream = new XStream(new
										// DomDriver()); // 需要xpp3 jar
		} catch (Exception e) {
			e.printStackTrace();
		}
		File file = new File(path);

		FileInputStream stream = null;
		try {
			stream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			System.err.println("文件不存在!");
			e.printStackTrace();
			return null;
		}

		try {
			@SuppressWarnings("unchecked")
			T aClass = (T) xstream.fromXML(stream);
			return aClass;
		} catch (ClassCastException e) {
			// TODO 自动生成的 catch 块
			System.out.println("类型转换异常!");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将内容写入文件
	 *
	 * @param file
	 * @param str
	 */
	private static void writeDoc(File file, String str) {
		OutputStream out = null;
		try {
			out = new FileOutputStream(file);
			out.write(str.getBytes());
		} catch (Exception ex) {
			//
			ex.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 创建文件
	 *
	 * @param path
	 * @return
	 * @throws IOException
	 */
	private static File createXmlFile(String path) throws IOException {
		File file = new File(path);
		if (!file.exists()) {
			file.createNewFile();
		}
		return file;
	}

	/**
	 * 将Java类序列化为dat文件
	 *
	 * @param object
	 * @param path
	 */
	public static <T> boolean serialize(T object, String path) {
		// 序列化
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(path));
			out.writeObject(object);
			out.close();
			return true;
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}


	/**
	 * 将对象文件反序列化为类
	 *
	 * @param path
	 */
	@SuppressWarnings("unchecked")
	public static <T> T unSerialize(String path) {
		// 则进行反序列化操作
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					path));
			try {
				T result = (T) in.readObject();
				in.close();
				return result;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				in.close();
				return null;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}



	public static String getTypeByName(Class<?> aClass,String name){

		Field[] fields = aClass.getDeclaredFields();
		for(Field f:fields){

			if(f.getName().equals(name)) {
				String[] s = f.getType().toString().split("\\.");
				return s[s.length-1];
			}
		}
		System.out.println("未找到名为"+name+"的字段!");
		return null;
	}
}
