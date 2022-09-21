package testers;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import source.Blob;
import source.BlobSet;
import source.Index;
import source.MrTopicsMan;


class BlobIndexTest {
	static File f;
	static File f2;
	static File f3;

	@BeforeAll
	static void setUpBefore() throws IOException {
		f = new File("Erik's test file");
		FileWriter ike = new FileWriter(f);
		ike.write("this is erik's new test file");
		ike.close();
		
		f2 = new File("something");
		FileWriter ike2 = new FileWriter(f2);
		ike2.write("some content");
		ike2.close();
		
		f3 = new File("bar.txt");
		FileWriter ike3 = new FileWriter(f3);
		ike3.write("bye");
		ike3.close();

	}

	@AfterAll
	static void shutDownAfter() throws IOException {
		f.delete();
		f2.delete();
		f3.delete();
	}

	@Test
	void testBlob() throws IOException {

		Blob b = new Blob("Erik's test file");

		String temp = "";
		File test = new File(".\\objects\\"+b.getSha1FileName());
		assertTrue(test.exists());

		BufferedReader mike = new BufferedReader(new FileReader(".\\objects\\"+b.getSha1FileName()));
		while (mike.ready())
			temp += (char)mike.read();
		mike.close();
		
		System.out.print(temp);
		assertTrue(temp.equals("this is erik's new test file"));

	}

	@Test
	void testIndex() throws IOException {
		FileWriter ike = new FileWriter(f);
		Index i = new Index();
		i.init();

		File testIndex = new File("index");
		assertTrue(testIndex.exists());

		File testObject = new File("./objects/");
		assertTrue(testObject.exists());

		i.add("something");
		File testBlob = new File("./objects/94e66df8cd09d410c62d9e0dc59d3a884e458e05");
		assertTrue(testBlob.exists());
		i.add("bar.txt");
		File testBlob2 = new File("./objects/78c9a53e2f28b543ea62c8266acfdf36d5c63e61");
		assertTrue(testBlob.exists());
		String content1 = BlobIndexTest.content (testBlob.getAbsolutePath());
		String content2 = BlobIndexTest.content(testBlob2.getAbsolutePath());
		i.remove("bar.txt");
		assertTrue(!testBlob.exists());
		
		assertTrue (content1.equals(BlobIndexTest.content(f2.getAbsolutePath())));
		assertTrue (content2.equals(BlobIndexTest.content(f3.getAbsolutePath())));
	}

	@Test
	void testBlobSet() throws IOException {
		ArrayList<String> testList = new ArrayList<>();
		MrTopicsMan help = new MrTopicsMan();
		String s = "";
		String temp = "blob : 94e66df8cd09d410c62d9e0dc59d3a884e458e05";
		testList.add(temp);
		s+=temp;
		temp = "blob : 78c9a53e2f28b543ea62c8266acfdf36d5c63e61";
		testList.add(temp);
		s+=temp;
		BlobSet set = new BlobSet(testList);
		File testFile = new File(".\\objects\\"+set.getSetName());
//		assertTrue(help.readContents(testFile).equals(s));
	}

	
	private static String content (String filepath) throws IOException {
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
