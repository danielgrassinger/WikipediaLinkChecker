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

		try {

			// download source code of the link
			String source = HTMLDownloader.downloadSource(link);

			// search for the words error and 404
			if (source.toLowerCase().contains("error") || source.toLowerCase().contains(" 404")) {
				isObsoleteVar = true;
			}

		} catch (Exception e) {

			// in the case of exception set the link obsolete
			isObsoleteVar = true;

			// e.printStackTrace();

		}
	}

}
