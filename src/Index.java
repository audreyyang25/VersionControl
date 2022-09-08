import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class Index {
	
	private  String sha1Code;
	
	public Index() {
		
	}
	
	public void init() {
		new File("/Users/remynavarre/eclipse-workspace/VersionControl/object").mkdirs();
		File f = new File("/Users/remynavarre/eclipse-workspace/VersionControl/object/"+"index");
		
	}
	
	//adds the blob of the file name to the object folder
	//also adds to the index file: the key value pair of file name and the corresponding sha1
	public void add(String fileName) throws IOException {
		sha1Code=getSha1Name(fileName);
		
		File sha1File = new File("/Users/remynavarre/eclipse-workspace/VersionControl/object/"+sha1Code);//is it ok to add it here?
        sha1File.createNewFile();
        
        FileWriter myWriter = new FileWriter("index");
        myWriter.write(fileName+ " : "+ sha1Code +"\n");
        myWriter.close();
	}
	
	
	
	private static String getSha1Name(String password)
	{
	    String sha1 = "";
	    try
	    {
	        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	        crypt.reset();
	        crypt.update(password.getBytes("UTF-8"));
	        sha1 = byteToHex(crypt.digest());
	    }
	    catch(NoSuchAlgorithmException e)
	    {
	        e.printStackTrace();
	    }
	    catch(UnsupportedEncodingException e)
	    {
	        e.printStackTrace();
	    }
	    return sha1;
	}

	private static String byteToHex(final byte[] hash)
	{
	    Formatter formatter = new Formatter();
	    for (byte b : hash)
	    {
	        formatter.format("%02x", b);
	    }
	    String result = formatter.toString();
	    formatter.close();
	    return result;
	}
}
