package com.annoatation.annotationexec;

import java.lang.reflect.Field;

import com.annoatation.annotation.ISINTEGER;

public class AnnotationHandler {

	public static <T> boolean processAnnotation(T data) {

		Class<?> classObj = null;
		try {
			classObj = Class.forName(data.getClass().getName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Field[] fields = classObj.getDeclaredFields();

		for (Field field : fields) {

			boolean value = field.isAnnotationPresent(ISINTEGER.class);

			if (value) {

				try {
					field.setAccessible(true);
					Object x = field.get(data);
					try {

						Integer.parseInt(x.toString());

						return true;
					} catch (Exception e) {
						return false;
					}

				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

		return false;
	}
}
