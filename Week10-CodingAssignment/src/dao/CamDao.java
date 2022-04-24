package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Camera;

/*
 * cameras database columns:
 * camera_id(INT, PRIMARY), mfr_id(INT, FOREIGN), cam_name(VARCHAR(50)), 
 * cam_type(VARCHAR(25), image_type(ENUM('film','digital'), year_of_release(YEAR)
 */

public class CamDao {

	private Connection connection;
	private final String GET_CAMS_BY_MFR_ID = "SELECT * FROM cameras WHERE mfr_id = ?";
	private final String GET_CAM_BY_ID = "SELECT * FROM cameras WHERE camera_id = ?";
	private final String CREATE_NEW_CAMERA = "INSERT INTO cameras(mfr_id, cam_name, cam_type, image_type, year_of_release)"
			+ "VALUES((SELECT mfr_id FROM manufacturers WHERE mfr_name = ?),?,?,?,?)";
	private final String DELETE_CAMERA_BY_ID = "DELETE FROM cameras WHERE camera_id = ?";
	private final String MOD_CAM_MFR_ID = "UPDATE cameras SET mfr_id = ? WHERE camera_id = ?";
	private final String MOD_CAM_NAME = "UPDATE cameras SET cam_name = ? WHERE camera_id = ?";
	private final String MOD_CAM_TYPE = "UPDATE cameras SET cam_type = ? WHERE camera_id = ?";
	private final String MOD_IMG_TYPE = "UPDATE cameras SET image_type = ? WHERE camera_id = ?";
	private final String MOD_YEAR = "UPDATE cameras SET year_of_release = ? WHERE camera_id = ?";
			
	public CamDao() {
		connection = DBConnection.getConnection();
	}

	/*
	 * Select and Return all cameras from a specified Manufacturer by mfrId. Used in
	 * MfrDao.populateMfr()
	 */
	public List<Camera> getCamsByMfrId(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_CAMS_BY_MFR_ID);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		List<Camera> cameras = new ArrayList<Camera>();
		while (rs.next()) {
			cameras.add(new Camera(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6)));
		}

		return cameras;
	}

	/*
	 * Create a New Camera in the Database. Used in Menu.createCamera()
	 */
	public void createNewCam(String mfrName, String camName, String camType, String imgType, int releaseYear)
			throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_CAMERA);
		ps.setString(1, mfrName);
		ps.setString(2, camName);
		ps.setString(3, camType);
		ps.setString(4, imgType);
		ps.setInt(5, releaseYear);
		ps.executeUpdate();
	}

	/*
	 * Delete an existing Camera from the Database. Used in Menu.deleteCamera()
	 */
	public void deleteCamByID(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_CAMERA_BY_ID);
		ps.setInt(1, id);
		ps.executeUpdate();
	}
	
	/*
	 * Select Specific Camera by ID
	 */
	public Camera selectCam(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_CAM_BY_ID);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return new Camera(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));		
	}

	/*
	 * Update camera mfr_id in database. Used in Menu.changeMfr()
	 */
	public void modCameraMfrByCamId(int camId, int mfrId) throws SQLException{
		PreparedStatement ps = connection.prepareStatement(MOD_CAM_MFR_ID);
		ps.setInt(1, mfrId);
		ps.setInt(2, camId);
		ps.executeUpdate();	
	}	
	
	/*
	 * Update cam_name in database. Used in Menu.changeCamName()
	 */
	public void modCameraNameByCamId(int camId, String name) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(MOD_CAM_NAME);
		ps.setString(1, name);
		ps.setInt(2, camId);
		ps.executeUpdate();
	}
	
	/*
	 * Update cam_type in database. Used in Menu.changeCamType()
	 */
	public void modCameraTypeById(int camId, String type) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(MOD_CAM_TYPE);
		ps.setString(1, type);
		ps.setInt(2, camId);
		ps.executeUpdate();
	}
	
	/*
	 * Update image_type in database. Used in Menu.changeImgType()
	 */
	public void modImageTypeById(int camId, String type) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(MOD_IMG_TYPE);
		ps.setString(1, type);
		ps.setInt(2, camId);
		ps.executeUpdate();
	}
	
	/*
	 * Update year_of_release in database. Used in Menu.changeYear()
	 */
	public void modYearById(int camId, int yr) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(MOD_YEAR);
		ps.setInt(1, yr);
		ps.setInt(2, camId);
		ps.executeUpdate();
	}
}
