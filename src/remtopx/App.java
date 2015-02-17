package remtopx;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

public class App {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner userInput = new Scanner(System.in);

		System.out.println("Welcome to the RemToPx project");
		System.out.println("-------------------------------\n");

		
		System.out.println("Type the css file absolute path:");
		String filePath = userInput.nextLine();
		
		String[] pathSplit;
		String[] filenameSplit;
		String filename;
		String newFilename = "";
		
		Integer hasSlash = filePath.indexOf("/");
		if (hasSlash != -1) 
		{
			pathSplit = filePath.split("/");
			filename = pathSplit[pathSplit.length-1];
		}
		else 
		{
			filename = filePath;
		}

		Integer hasExtention = filePath.indexOf(".");
		if (hasExtention != -1) 
		{
			filenameSplit = filename.split("\\.");
			newFilename = filename.replace(filenameSplit[0], filenameSplit[0] + "-px");
		}
		else 
		{
			newFilename = filename + "-px";
		}
		
		String newFilePath = filePath.replace(filename, newFilename);

		File fRaw = new File(filePath);
		File fOutput = new File(newFilePath);
		Parser parser = new Parser();
		

		System.out.println("Choose a multiplicator :");
		parser.setMultiplicator(Float.parseFloat(userInput.nextLine()));
		
		if(fRaw.exists()) 
		{
			Scanner reader = new Scanner(fRaw);
			PrintWriter writer = new PrintWriter(fOutput);
			while (reader.hasNext()) 
			{
				String line = reader.nextLine();
				String newLine = parser.remToPx(line);
				writer.println(newLine);
			}

			System.out.println("... DONE !");
			reader.close();
			writer.close();
		}
	}
}
