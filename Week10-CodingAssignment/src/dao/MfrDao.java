package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Manufacturer;

public class MfrDao {
	
	private Connection connection;
	private CamDao camDao = new CamDao();
	private final String GET_MFR = "SELECT * FROM manufacturers";
	private final String GET_MFR_BY_ID = "SELECT * FROM manufacturers WHERE mfr_id = ?";
	private final String CREATE_NEW_MFR = "INSERT INTO manufacturers(mfr_name) VALUES (?)";
	private final String DELETE_MFR_BY_ID = "DELETE FROM manufacturers WHERE mfr_id = ?";
	
	public MfrDao() {
		connection = DBConnection.getConnection();
	}
	
	public List<Manufacturer> getMfrs() throws SQLException{
		ResultSet rs= connection.prepareStatement(GET_MFR).executeQuery();
		List<Manufacturer> mfrs = new ArrayList<Manufacturer>();
		
		while (rs.next()) {
			mfrs.add(populateMfr(rs.getInt(1), rs.getString(2)));
		}
		return mfrs; 
	}
	
	public Manufacturer getMfrById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_MFR_BY_ID);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return populateMfr(rs.getInt(1), rs.getString(2));
		
	}
	
	public void deleteMfrById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_MFR_BY_ID);
		ps.setInt(1, id);
		ps.executeUpdate();
	}
	
	public void createNewMfr(String name) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_MFR);
		ps.setString(1, name);
		ps.executeUpdate();
		
	}

	private Manufacturer populateMfr(int id, String name) throws SQLException {
		return new Manufacturer(id, name, camDao.getCamsByMfrId(id));
	}

}
