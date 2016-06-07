package de.grassinger;

import java.util.Scanner;

public class WikipediaLinkChecker {

	public static void main(String[] args) {
		// Read in the url via command line argument or ci
		String inputURL = "https://en.wikipedia.org/wiki/Test";
//		if (args.length > 1) {
//			inputURL = args[1];
//		} else {
//			System.out.println("Bitte geben Sie die URL der Wikipedia Seite ein, die Sie \u00fcberpr\u00fcfen wollen:");
//			Scanner input = new Scanner(System.in);
//			inputURL = input.nextLine();
//			input.close();
//		}

		// Start the link checker
		String[] obsoleteLinks = checkWikipediaLink(inputURL);
		

		// Print out the obsolete links
		System.out.println("Diese Links auf der angegeben Seite funktionieren nicht mehr:");
		for (int i = 0; i < obsoleteLinks.length; i++) {
			System.out.println("\t" + obsoleteLinks[i]);
		}

	}

	public static String[] checkWikipediaLink(String inputURL) {
		String htmlSource = "";
		try {
			htmlSource = HTMLDownloader.downloadSource(inputURL);
		} catch (Exception e) {
			System.err.println("Der Link konnte nicht geöffnet werden!");
			e.printStackTrace();
		}
		
		String[] links = LinkExtractor.getLinks(htmlSource);
		
		
		

		return links;

	}

}
