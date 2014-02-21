import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Random;


public class TescCOmpare {

	
	public static void main(String[] args) throws IOException {
		HashSet<String> small = new HashSet<String>();
		HashSet<String> big = new HashSet<String>();
		FileInputStream fstream = new FileInputStream("D:\\fyp\\clipExtractor\\smallclips\\output.txt");
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		while ((strLine = br.readLine()) != null) {
			String[] sb = new String[200];
			sb = strLine.split("\t");
			String tmp = "";
			for(int i = 7;i < sb.length;i++)
				tmp += sb[i];
			small.add(getHash(tmp));
		}
		in.close();
		br.close();
		fstream.close();
		fstream = new FileInputStream("D:\\fyp\\clipExtractor\\bigclips\\clip1.txt");
		in = new DataInputStream(fstream);
		br = new BufferedReader(new InputStreamReader(in));
		String line;
		while ((line = br.readLine()) != null) {
			String[] sb = new String[200];
			sb = line.split("\t");
			String tmp = "";
			for(int i = 7;i < sb.length;i++)
				tmp += sb[i];
			big.add(getHash(tmp));
		}
		Random random = new Random();
		int faultCount = 0, correctCount = 0;;
		while(true)
		{
			String curr = (String) small.toArray()[random.nextInt(small.size())];
			if(big.contains(curr))
				correctCount++;
			else
				faultCount++;
			if(correctCount == 10 || faultCount == 60)
				break;
		}
		if(correctCount == 10)
			System.out.println("done");
		if(faultCount == 60)
			System.out.println("no");
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
