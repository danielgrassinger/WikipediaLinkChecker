package de.grassinger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HTMLDownloader {

	public static String downloadSource(String inputURL) throws Exception {

		// Create URL from input
		URL myurl = new URL(inputURL);
		// Open connection
		HttpURLConnection con = (HttpURLConnection) myurl.openConnection();
		// Set property that this is a wikipedia bot
		con.setRequestProperty(" User - Agent ", " LinkTester /0.1 ( grassing@fim.uni-passau.de ) Java /1.8.0 ");

		// set timeout for connecting and reading
		con.setConnectTimeout(1000);
		con.setReadTimeout(2000);
		con.connect();

		if (con.getResponseCode() != HttpURLConnection.HTTP_OK) {
			return "";
		}

		// Read text from web
		InputStream ins = con.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(ins));

		StringBuilder sb = new StringBuilder();
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			sb.append(inputLine);
		}
		in.close();

		// return the source code
		return sb.toString();

	}

}
