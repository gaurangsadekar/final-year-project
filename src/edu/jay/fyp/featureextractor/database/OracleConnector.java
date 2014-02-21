package edu.jay.fyp.featureextractor.database;

import java.sql.DriverManager;
import java.sql.SQLException;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;
import oracle.ord.im.OrdVideo;
import oracle.sql.BLOB;
import oracle.sql.ORAData;

public class OracleConnector {

	private static final String dbUrl = "jdbc:oracle:thin:@jay_tank-pc:1521:fyp";
	private static final String user = "SYSTEM";
	private static final String pwd = "donknuth";
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			OracleConnection connection = (OracleConnection) DriverManager.getConnection(dbUrl,user,pwd);
			System.out.println("Connected");
			String query = "select video_name, video_content from system.videos where sr_no = 1";
			OraclePreparedStatement ps = (OraclePreparedStatement) connection.prepareStatement(query);
			OracleResultSet rs = (OracleResultSet) ps.executeQuery();
			
			if(rs.next()){
				
//				BLOB blob = rs.getBLOB("video_content");
//				OrdVideo videoProxy = (OrdVideo)rs.getORAData(2, OrdVideo.getORADataFactory());
//				System.out.println(blob.length());
				byte[] videoBlob = rs.getBytes("video_content");
			}
			//System.out.println(videoProxy.getBitRate());
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
