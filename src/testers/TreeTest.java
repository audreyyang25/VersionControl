package testers;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import source.Tree;

class TreeTest {
	private static String actualHash;
	private static String upper;
	private static File actualFile;
	private static File upperFile;

	@BeforeAll
	static void setUp () throws NoSuchAlgorithmException, IOException {
		ArrayList <String> list = new ArrayList <> ();
		list.add("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f file.txt");
		list.add("blob : 01d82591292494afd1602d175e165f94992f6f5f otherFile.txt");
		list.add("blob: f1d82236ab908c86ed095023b1d2e6ddf78a6d83 oneMore.txt");
		Tree treee = new Tree (list);
		actualHash = "2b7e01c372b056bd36950d3d8a19d8178803f63d";
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
		String actualContent = ("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f file.txt" + "\n" + "blob : 01d82591292494afd1602d175e165f94992f6f5f otherFile.txt"+"\n"+"blob: f1d82236ab908c86ed095023b1d2e6ddf78a6d83 oneMore.txt" + "\n");
		String content = "";
		
		if (actualFile.exists()) {
			content = this.content("./objects/" + actualHash);
		}
		else {
			content = this.content("./objects/" + upper);
		}
		assertTrue (actualContent.equals(content));
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

