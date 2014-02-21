import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import edu.jay.fyp.featureextractor.FileExtractor;

public class Test {
   public static void main(String args[]) {
     FileExtractor fe = new FileExtractor("D:\\fyp\\clipExtractor\\bigclips", ".txt");
     List<String> files = fe.getFiles();
     int i = 1;
     for(String file : files){
    	 FileInputStream fstream;
		try {
			fstream = new FileInputStream(file);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line;
			FileWriter fw = new FileWriter(new File("D:\\fyp\\clipExtractor\\stips\\clip"+i+".txt"));
			while((line = br.readLine()) != null){
				String[] sb = new String[200];
				sb = line.split("\t");
				String str = "";
				for(int j = 7;j < sb.length;j++){
					str += sb[j];
				}
				fw.write(getHash(str)+"\n");
			}
			i++;
			fw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
     }
   }
   
   private static String getHash(String str){
		String hash = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] bytepath = str.getBytes("UTF-8");
			byte[] ans = digest.digest(bytepath);
			StringBuilder sb = new StringBuilder(2*ans.length);
			for(byte b : ans){
				sb.append(String.format("%02x",b&0xff));
			}
			hash = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return hash;
	}
}