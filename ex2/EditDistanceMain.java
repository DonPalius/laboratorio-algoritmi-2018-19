package ex2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// 
public class EditDistanceMain {
	
	public static void main(String[] args) {
    // paths to the datasets:
    String pathDictionary = "ex2/dictionary.txt";
    String paText = "ex2/correctme.txt";           
		
		ArrayList<String> dictionary = new ArrayList<String>();
    ArrayList<String> text = new ArrayList<String>();
		try {
      dictionary = loadDictionary(pathDictionary);
      text = loadText(paText);
    } catch (FileNotFoundException e) {
        System.out.println("Error while loading the files!");
    }
		
		edit_distance_dyn_exe(dictionary, text);
	}
	
	
	private static void edit_distance_dyn_exe(ArrayList<String> dictionary,ArrayList<String> text){
		
    System.out.println("Searching...");
		 
		for(String wordsText: text){ 
      ArrayList<String> minEditD = new ArrayList<String>();
      int min = Integer.MAX_VALUE;

      for(String wordsDic: dictionary){
        int editDistance = EditDistance.edit_distance_dyn(wordsDic, wordsText);

        if(editDistance < min){
          min = editDistance;
          minEditD.clear();
          minEditD.add(wordsDic);
        }
          else if(editDistance == min){
            minEditD.add(wordsDic);
          }
        }
        if(min != 0){ // >0
          System.out.println("\nError: " + wordsText + ", possible corrections:");
          for(String corrections: minEditD)
          System.out.println("\t" + corrections);
        }
        }
    }

	private static ArrayList<String> loadDictionary(String dataset) throws FileNotFoundException {
    ArrayList<String> dictionary = new ArrayList<String>();
		
		Scanner sc = new Scanner(new FileInputStream(dataset));
    sc.useDelimiter("\n");
		
		while (sc.hasNextLine()) {
      dictionary.add(sc.nextLine());
    }
    sc.close();
		
		return dictionary;
    }

    private static ArrayList<String> loadText(String pathToFile) throws FileNotFoundException {
      String token;
      Scanner sc = new Scanner(new File(pathToFile)).useDelimiter("\\s"); // space
      ArrayList<String> text = new ArrayList<String>();
      while (sc.hasNext()) {
        token = sc.next();
        text.add(token.toLowerCase().replaceAll("[,.:]", ""));
      }
        sc.close();
      return text;
    }
}






	
	
	
	
	
	
	
	
	
	
