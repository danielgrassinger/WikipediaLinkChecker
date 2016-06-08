package de.grassinger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class CheckLink extends Thread {

	private String link;
	private boolean isObsoleteVar = false;

	public CheckLink(String url) {
		this.link = url;
	}

	public boolean isObsolete() {

		return isObsoleteVar;
	}

	public String getURL() {
		return link;
	}

	@Override
	public void run() {
		// TODO implement this method
		try {
			// HttpURLConnection con;
			// URL url = new URL(link);
			// while (true) {
			// con = (HttpURLConnection) url.openConnection();
			// con.setInstanceFollowRedirects(false);
			// switch (con.getResponseCode()) {
			// case HttpURLConnection.HTTP_MOVED_PERM:
			// case HttpURLConnection.HTTP_MOVED_TEMP:
			// String next = con.getHeaderField(" Location ");
			// url = new URL(url, next);
			// continue;
			// }
			// break;
			// }
//			String source = downloadSource(link);
			
			String source = HTMLDownloader.downloadSource(link);

			
			if (source.toLowerCase().contains("error") || source.toLowerCase().contains(" 404")) {
				isObsoleteVar = true;
			}

		} catch (Exception e) {
			
			// set the link obsolet
			isObsoleteVar=true;
			
			//e.printStackTrace();

		}
	}

	public String downloadSource(String inputURL) throws Exception {

		// Create URL from input
		URL myurl = new URL(inputURL);
		// Open connection
		HttpURLConnection con = (HttpURLConnection) myurl.openConnection();
		// Set property that this is a wikipedia bot

//		con.setConnectTimeout(1000);
//		con.setReadTimeout(1000);
		con.connect();
		
		if (con.getResponseCode() != HttpURLConnection.HTTP_OK) {
			return "";
		}
		// HttpURLConnection con;
		// URL url = new URL(inputURL);
		// while (true) {
		// con = (HttpURLConnection) url.openConnection();
		// con.setInstanceFollowRedirects(false);
		// switch (con.getResponseCode()) {
		// case HttpURLConnection.HTTP_MOVED_PERM:
		// case HttpURLConnection.HTTP_MOVED_TEMP:
		// String next = con.getHeaderField(" Location ");
		// url = new URL(url, next);
		// continue;
		// }
		// break;
		// }
		// Read text from web
		InputStream ins = con.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(ins));

		StringBuilder sb = new StringBuilder();
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			sb.append(inputLine);
		}
		in.close();

		return sb.toString();

	}

}
