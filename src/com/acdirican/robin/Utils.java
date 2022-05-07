package com.acdirican.robin;

import java.awt.Color;
import java.time.LocalDate;
import java.util.Random;

/**
 * Utility class
 * 
 * @author Ahmet Cengizhan Dirican
 * @version v1.0 May 2022
 *
 */
public final class Utils {

	private static Random rnd =  new Random();

	private Utils() {};
	
	/**
	 * Returns the toString of the given object. If the object is a {@link Number}, 
	 * returns its string value by preserving only two digits after point.
	 * Example: 45.12465465 -> "45.12"
	 */
	public static String toStringTwoZeroAfterPointIfNumber(Object value) {
		if (!(value instanceof Number)) {
			return value.toString();
		}
		String valueStr = value.toString();
		if (valueStr.contains(".")) {
			int p = valueStr.indexOf(".");
			valueStr = valueStr.substring(0, Math.min(p+3 ,valueStr.length()));
		}
		return valueStr;
	}

	public static Color randomColor() {
		return new Color(rnd.nextInt(255), rnd .nextInt(255), rnd.nextInt(255));
	}

	public static String stringContaningComma(String str) {
		return str.contains(",") ? '"' + str + '"' : str;
	}

	public static <T> String intoDblQuotation(T object) {
		return  (object instanceof String) || (object instanceof LocalDate) 
				? '"' + object.toString() + '"' 
				: object.toString();
	}

}
