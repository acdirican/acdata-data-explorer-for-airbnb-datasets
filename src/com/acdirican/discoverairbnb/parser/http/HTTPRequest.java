package com.acdirican.discoverairbnb.parser.http;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.util.List;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import com.acdirican.discoverairbnb.dataset.entities.Property;

/**
 * An HTTP request
 * 
 * @author Ahmet Cengizhan Dirican
 * @version v1.0 May 2022
 *
 */

public class HTTPRequest {

	private String host;
	private String path;

	public HTTPRequest(String host, String path) {
		this.host = host;
		this.path = path;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "GET /" + path + " HTTP/1.1\r\n" + "Host:" + host + "\r\n"
				+ "User-Agent: Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:64.0) Gecko/20100101 Firefox/64.0\r\n"
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,/;q=0.8\r\n"
				+ "Connection: keep-alive\r\n" + "\r\n ";
	}

}