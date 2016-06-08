package de.grassinger;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class LinkChecker {

	public static String[] checkAllLinks(String[] urls) {

		// list with obsolete links which is returned by this function
		LinkedList<String> obsoleteLinks = new LinkedList<String>();

		// list of threads, every link is checked in an extra thread
		List<CheckLink> threads = new ArrayList<CheckLink>(urls.length);

		// open a new thread for every thread
		for (int i = 0; i < urls.length; i++) {
			CheckLink thread = new CheckLink(urls[i]);
			thread.start();
			threads.add(thread);
		}

		for (CheckLink thread : threads) {

			// wait till every thread is finished
			try {
				thread.join();

			} catch (InterruptedException e) {
				// ignore exceptions
				// e.printStackTrace();
			}

			// get the obsolete parameter from the thread
			if (thread.isObsolete()) {
				// add the obsolete links to the obsolete list
				obsoleteLinks.add(thread.getURL());
			}
		}

		// return the obsolete links
		return obsoleteLinks.toArray(new String[0]);
	}

}
