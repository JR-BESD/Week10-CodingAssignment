package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Camera;

public class CamDao {
	
	private Connection connection;
	private final String GET_CAMS_BY_MFR_ID = "SELECT * FROM cameras WHERE mfr_id = ?";
	private final String CREATE_NEW_CAMERA = "INSERT INTO cameras(mfr_id, cam_name, cam_type, image_type, year_of_release)"
			+ "VALUES((SELECT mfr_id FROM manufacturers WHERE mfr_name = ?),?,?,?,?)";
	private final String DELETE_CAMERA_BY_ID = "DELETE FROM cameras WHERE camera_id = ?";
	
	public CamDao() {
		connection = DBConnection.getConnection();
	}

	public List<Camera> getCamsByMfrId(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_CAMS_BY_MFR_ID);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		List<Camera> cameras= new ArrayList<Camera>();
		while (rs.next()) {
			cameras.add(new Camera(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6)));
		}
		
		return cameras;
	}
	
	public void deleteCamByID(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_CAMERA_BY_ID);
		ps.setInt(1, id);
		ps.executeUpdate();
	}
	
	public void createNewCam(String mfrName, String camName, String camType, String imgType,int releaseYear ) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_CAMERA);
		ps.setString(1, mfrName);
		ps.setString(2, camName);
		ps.setString(3, camType);
		ps.setString(4, imgType);
		ps.setInt(5, releaseYear);
		ps.executeUpdate();
		
	}

}
