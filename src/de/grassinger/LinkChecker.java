package de.grassinger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class LinkChecker {

	public boolean checkLink(String url) {
		// TODO
		return false;
	}

	public String[] checkAllLinks(String[] urls) {
		String[] links;
		List<String> obsoleteLinks;
		List<CheckLink> threads;

		links = urls;

		obsoleteLinks = (List<String>) Collections.synchronizedCollection(new LinkedList<String>());
		threads = new ArrayList<CheckLink>(urls.length);

		for (int i = 0; i < urls.length; i++) {
			CheckLink thread = new CheckLink(urls[i]);
			thread.start();
			threads.add(thread);
		}

		for (int i = 0; i < threads.size(); i++) {
			if (threads.get(i).isObsolete()) {
				obsoleteLinks.add(threads.get(i).getURL());
			}
		}

		return  obsoleteLinks.toArray(new String[0]);
	}

	private class CheckLink extends Thread {

		private String url;
		private boolean isObsoleteVar = false;

		public CheckLink(String url) {
			this.url = url;
		}

		public boolean isObsolete() {

			return isObsoleteVar;
		}

		public String getURL() {
			return url;
		}

		@Override
		public void run() {
			// TODO implement this method
			
			isObsoleteVar = true;

		}
	}

}
