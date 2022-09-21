package testers;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import source.Tree;

class TreeTester {
	private static String actualHash;
	private static String upper;
	private static File actualFile;
	private static File upperFile;
	 
	@BeforeAll
	static void setUp () throws NoSuchAlgorithmException, IOException {
		ArrayList <String> list = new ArrayList <String> ();
		list.add("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f");
		list.add("blob : 01d82591292494afd1602d175e165f94992f6f5f");
		list.add("blob: f1d82236ab908c86ed095023b1d2e6ddf78a6d83");
		Tree treee = new Tree (list);
		actualHash = "d9961d8a462c0d384d7343b7460fbccd4ea928b9";
		upper = actualHash.toUpperCase();
		actualFile = new File ("./objects/" + actualHash);
		upperFile = new File ("./objects/" + upper);
	}
	
	@Test
	void testFileNameAccuracy () {
		assertTrue (actualFile.exists()||upperFile.exists());
	}
	
	@Test
	void testContents () throws IOException {
		String actualContent = ("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f" + "\n" + "blob : 01d82591292494afd1602d175e165f94992f6f5f"+"\n"+"blob: f1d82236ab908c86ed095023b1d2e6ddf78a6d83");
		String content = "";
		System.out.println(actualContent);
		if (actualFile.exists()) {
			content = this.content("./objects/" + actualHash);
			assertTrue (actualContent.equals(content));
		}
		else if (upperFile.exists()){
			content = this.content("./objects/" + upper);
			assertTrue (actualContent.equals(content));
		}
		
	}

	
	private String content (String filepath) throws IOException {
		File file = new File (filepath);
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		StringBuilder sb = new StringBuilder(); 
		String line = br.readLine(); 
		while (line != null) { 
			sb.append(line).append("\n"); 
			line = br.readLine(); 
		} 
		String fileAsString = sb.toString();
		return fileAsString;
	}

}

