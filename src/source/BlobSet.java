package source;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class BlobSet {
	ArrayList<String> list = new ArrayList<String>();
	String fileName;
	public BlobSet(ArrayList<String> input) throws IOException {
		String temp = "";
		MrTopicsMan help = new MrTopicsMan();
		for(String s : input) {
			temp+=s+"\n";
		}
		
		fileName = help.shaify(temp);
		File f = new File(".\\objects\\"+fileName);
		MrTopicsMan.writeTo(f,temp);
		
		list = input;
	}
	
	public String getSetName() {
		return fileName;
	}
}