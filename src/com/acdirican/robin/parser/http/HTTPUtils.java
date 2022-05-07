package com.acdirican.robin.parser.http;


/**
 * Utility class for HTTP operations
 * 
 * @author Ahmet Cengizhan Dirican
 * @version v1.0 May 2022
 *
 */

public final class HTTPUtils {
	private HTTPUtils() {}
	
	public static String removeHTTP(String url) {
		if (url.contains("//")) {
			return url.substring(url.indexOf("//") + 2);
		}
		return url;
	}
	public static String getHost(String url) {
		url = removeHTTP(url);
		int indexSlash = url.indexOf('/');
		if (indexSlash == -1 || indexSlash == url.length() - 1) {
			return url;
		}
		
		return url.substring(0, indexSlash);
	}
	
	public static String getPath(String url) {
		url = removeHTTP(url);
		int indexSlash = url.indexOf('/');
		if (indexSlash == -1 || indexSlash == url.length() - 1) {
			return "";
		}
		return url.substring(indexSlash + 1, url.length());
	}
}
