package com.acdirican.robin.parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import com.acdirican.robin.dataset.entities.Property;
import com.acdirican.robin.parser.http.HTTPRequest;
import com.acdirican.robin.parser.http.HTTPStatus;
import com.acdirican.robin.parser.http.HTTPUtils;

/**
 * The parser that read a given data source and produce the {@link List} of
 * {@link Property}.
 * 
 * @author Ahmet Cengizhan Dirican
 * @version v1.0 May 2022
 *
 */
public class Parser {

	/**
	 * Determine if the debug messages will be printed or not
	 */
	private static boolean DEBUG_STATE = false;
	
	/**
	 * Gets and parses Airbnb data from a web source
	 * 
	 * @param url
	 * @param port
	 * @return
	 * @throws IOException
	 */
	public static List<Property> parseFromURL(String url, int port) throws IOException {
		Socket socket = null;
		PrintWriter out = null;
		BufferedReader in = null;

		try {

			String host = HTTPUtils.getHost(url);
			String path = HTTPUtils.getPath(url);
			
			DEBUG("Opening IRL:" + url);
			
			socket = new Socket(host, port);

			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));

			out.println(new HTTPRequest(host, path).toString());
			out.println();
			out.flush();

			if (out.checkError()) {
				DEBUG("Socket Client:  java.io.PrintWriter error");
				return null;
			}

			/* read response */
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			List<String> table = new ArrayList<>();
			boolean ok = false;
			String inputLine = in.readLine();
			
			HTTPStatus status = HTTPStatus.parseFromResponseHeader(inputLine);
			if ( status != HTTPStatus.OK) {
				if (status!=null) {
					DEBUG(status.getText());
				}
				else {
					DEBUG("Cannot open url: " + url);
				}
				return null;
			}
			
			while ((inputLine = in.readLine()) != null) {
				if (HTTPStatus.parseFromResponseHeader(inputLine) != null) {
					break;
				}
				if (ok) {
					table.add(inputLine);
				}
				if (inputLine.equals("")) {
					ok = !ok;
				}
			}
			
			return parse(table);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (socket != null)
				socket.close();
			if (out != null)
				out.close();
			if (in != null)
				in.close();
		}
		
		return null;
	}

	private static void DEBUG(String ...messages ) {
		if (DEBUG_STATE) {
			for (String m : messages) {
				System.out.println(m);
			}
		}
			
		
	}

	/**
	 * Gets and parses Airbnb data from a file
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static List<Property> parse(String fileName) throws IOException {
		Path file = Path.of(fileName);
		List<String> table = Files.readAllLines(file);
		DEBUG("File:" + fileName  + "  has " + table.size() + " lines.");
		return parse(table);
	}

	/**
	 * Parses Airbnb data collections made of string rows
	 * @param table
	 * @return
	 */
	public static List<Property> parse(List<String> table) {
		if (table == null || table.size()==0) {
			return null;
		}
		// remove the title row
		table.remove(0);

		List<Property> dataset = new ArrayList<>();
		int i = 0;
		for (String row : table) {
			List<String> fields = splitRow(row.trim());
			Property property = Property.create(fields);
			if (property != null) {
				i++;
				dataset.add(property);
			}
			else {
				DEBUG("Data ignored: " + fields);
			}
		}
		DEBUG(i + " row parsed out of " + table.size());
		return dataset;
	}

	/**
	 * Parses Airbnb data rows to string lists.
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static List<List<String>> parseRaw(String fileName) throws IOException {
		Path file = Path.of(fileName);
		List<String> table = Files.readAllLines(file);
		// remove the title row
		table.remove(0);

		List<List<String>> dataset = new ArrayList<>();
		for (String row : table) {
			List<String> fields = splitRow(row.trim());
			dataset.add(fields);
		}

		return dataset;
	}

	/**
	 * Print partial data
	 * 
	 * @param fileName
	 * @param rowStartIndex
	 * @param rowFinishIndex
	 * @param fieldStartIndex
	 * @param fieldFinishIndex
	 * @throws IOException
	 */
	public static void summarize(String fileName, int rowStartIndex, int rowFinishIndex, int fieldStartIndex,
			int fieldFinishIndex) throws IOException {
		Path file = Path.of(fileName);
		List<String> table = Files.readAllLines(file);

		// To print field titles
		List<String> titles = splitRow(table.get(0));
		printFields(titles, fieldStartIndex, fieldFinishIndex, 10);
		// to the loop skipped the titles
		if (rowStartIndex == 0) {
			rowStartIndex++;
		}
		for (int i = rowStartIndex; i <= rowFinishIndex && i < table.size(); i++) {
			List<String> fields = splitRow(table.get(i).trim());
			printFields(fields, fieldStartIndex, fieldFinishIndex, 10);

		}
	}

	/**
	 * Parses only titles.
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static List<String> parseTitles(String fileName) throws IOException {
		Path file = Path.of(fileName);
		Scanner sc = new Scanner(file);
		// To print field titles
		List<String> titles = splitRow(sc.nextLine());
		sc.close();
		System.out.println(titles);
		return titles;
	}

	/**
	 * Splits the given string comma as delimiter by ignoring the commas between
	 * quotation marks. The method does not check the input for validity.
	 * 
	 * @param str the given string
	 * @return the split string
	 */
	private static List<String> splitRow(String str) {
		List<String> fields = new ArrayList<>();
		StringBuilder fieldBuilder = new StringBuilder();
		boolean discardComma = false;
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			switch (ch) {
			case ',': {
				if (discardComma) {
					fieldBuilder.append(ch);
				} else {
					fields.add(fieldBuilder.toString().trim());

					fieldBuilder.delete(0, fieldBuilder.length());
				}
			}
				break;

			case '"': {
				discardComma = !discardComma;
			}
				break;

			default:
				fieldBuilder.append(ch);
			}
		}
		fields.add(fieldBuilder.toString().trim());

		return fields;
	}

	public static Set<String> getColumnSet(String fileName, int columnIndex) throws IOException {
		List<List<String>> dataset = parseRaw(fileName);
		Set<String> set = new HashSet<>();
		for (List<String> fields : dataset) {
			if (fields.size() >= columnIndex)
				set.add(fields.get(columnIndex));
		}
		return set;
	}

	private static void printFields(List<String> fields, int fieldStartIndex, int fieldFinishIndex,
			int maxFieldLength) {
		String format = "%-" + maxFieldLength + "s";
		for (int j = fieldStartIndex; j <= fieldFinishIndex && j < fields.size(); j++) {
			String field = fields.get(j).trim();
			if (field.length() >= maxFieldLength) {
				System.out.printf(format, field.substring(0, maxFieldLength));
				System.out.print("...  ");
			} else {
				System.out.printf(format, field);
				System.out.print("     ");
			}
		}
		System.out.println();

	}

	public static void main(String[] args) throws IOException {
		List<Property> properties = Parser.parseFromURL(
				"http://data.insideairbnb.com/the-netherlands/north-holland/amsterdam/2021-12-05/visualisations/listings.csv",
				80);

		System.out.println(properties.get(0));
		System.out.println(properties.get(properties.size() - 1));
		System.out.println(properties.size());
	}

	/**
	 * Set if the debug messages will be printed or not
	 */
	public static void allowDebugPrint(boolean b) {
		DEBUG_STATE= b;
	}

}
