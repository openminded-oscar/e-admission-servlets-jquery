package com.erealty.dbutils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UniversalTransformer {
	public static Object fromResultSetToObject(ResultSet rs, Class<?> toClass) throws SQLException {
		Object model = null;
		if (rs.next()) {
			try {
				model = toClass.newInstance();
				Field[] fields = toClass.getDeclaredFields();
				for (int i = 0; i < fields.length; ++i) {
					ColumnReflection columnReflection = fields[i].getDeclaredAnnotation(ColumnReflection.class);
					if (columnReflection != null) {
						String columnName = columnReflection.columnName();
						if (fields[i].isAccessible() == false) {
							fields[i].setAccessible(true);
						}
						setValue(rs, columnName, model, fields[i]);
					} else
						throw new Exception("Unknown column name occured!");
				}
			} catch (IllegalArgumentException | IllegalAccessException | InstantiationException e) {
				System.out.println(e + "Error when transforming ");
			} catch (Exception e) {
				System.out.println(e+"What an exception occured");
			}
		}
		return model;
	}

	public static void setValue(ResultSet rs, String columnName, Object object, Field field)
			throws IllegalArgumentException, IllegalAccessException, SQLException {
		String typeString = field.getType().toString();
		switch (typeString) {
		case ("class java.lang.String"):
			field.set(object, rs.getString(columnName));
			break;
		case ("class java.lang.Integer"):
			field.set(object, rs.getInt(columnName));
			break;
		case ("class java.sql.Date"):
			field.set(object, rs.getDate(columnName));
			break;
		case ("class java.lang.Boolean"):
			field.set(object, rs.getBoolean(columnName));
			break;
		case ("class java.lang.Double"):
			field.set(object, rs.getDouble(columnName));
			break;
		case ("class java.sql.Time"):
			field.set(object, rs.getTime(columnName));
			break;
		}

	}
}
