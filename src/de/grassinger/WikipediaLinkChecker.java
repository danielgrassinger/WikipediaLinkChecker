package de.grassinger;

import java.util.Scanner;

public class WikipediaLinkChecker {

	public static void main(String[] args) {

		// Read in the url via programm parameter or sdtin
		String inputURL = "";
		if (args.length > 1) {
			// program parameter
			inputURL = args[1];
		} else {

			// read via stdin
			System.out.println("Bitte geben Sie die URL der Wikipedia Seite ein, die Sie \u00fcberpr\u00fcfen wollen:");
			Scanner input = new Scanner(System.in);
			inputURL = input.nextLine();
			input.close();
		}

		// Start the link checker
		String[] obsoleteLinks = checkWikipediaLink(inputURL);

		// Print out the obsolete links
		System.out.println("Diese Links auf der angegeben Seite funktionieren nicht mehr:");
		for (int i = 0; i < obsoleteLinks.length; i++) {
			System.out.println("\t" + obsoleteLinks[i]);
		}

	}

	public static String[] checkWikipediaLink(String inputURL) {

		// download the HTML source code
		String htmlSource = "";
		try {
			htmlSource = HTMLDownloader.downloadSource(inputURL);
		} catch (Exception e) {
			System.err.println("Der Link konnte nicht ge\u00f6ffnet werden!");
			System.exit(404);
			//e.printStackTrace();
		}
		
		// get the links from the HTML source code
		String[] links = LinkExtractor.getLinks(htmlSource);
		
		// check the links if they work
		String[] obsoleteLinks = LinkChecker.checkAllLinks(links);

		// return the links which are not working
		return obsoleteLinks;

	}

}
