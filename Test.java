import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import com.google.api.gax.paging.Page;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Document.Type;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.common.collect.Lists;

public class Test {
  public static void main(String[] args) throws Exception {
    
	String text = "";
	String[] sentences;
	
	Scanner std = new Scanner(new File("entry.txt")); //get the txt file
	while (std.hasNextLine()) {
		text += std.nextLine();
	}
	sentences = text.split(".");
	
	// Instantiates a client
    try (LanguageServiceClient language = LanguageServiceClient.create()) {

      // The text to analyze
      //String text = "Hello, world!";
      //Document doc = Document.newBuilder().setContent(text).setType(Type.PLAIN_TEXT).build();

      // Detects the sentiment of the text
      //Sentiment sentiment = language.analyzeSentiment(doc).getDocumentSentiment();

      //System.out.printf("Text: %s%n", text);
      //System.out.printf("Sentiment: %s, %s%n", sentiment.getScore(), sentiment.getMagnitude());
    }
  }
  
}
