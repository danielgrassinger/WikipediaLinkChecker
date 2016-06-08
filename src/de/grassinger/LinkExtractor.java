package de.grassinger;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkExtractor {

	public static String[] getLinks(String htmlSource) {

		// pattern to find hyperlinks (only links out of wikipedia)
		Pattern p = Pattern.compile("href=\"(http.*?)\"");
		Matcher m = p.matcher(htmlSource);

		List<String> links = new LinkedList<String>();

		// add every match to the links list
		while (m.find()) {
			links.add(m.group(1));
		}

		// return an array of the links
		return links.toArray(new String[0]);
	}

}
