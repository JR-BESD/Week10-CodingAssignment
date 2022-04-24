package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Manufacturer;

/*
 * manufacturers database columns:
 * mfr_id(INT, PRIMARY), mfr_name(VARCHAR(25))
 */

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

	/*
	 * Select and Return all Manufacturers (ID and Name). Used in
	 * Menu.displayManufacturers()
	 */
	public List<Manufacturer> getMfrs() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_MFR).executeQuery();
		List<Manufacturer> mfrs = new ArrayList<Manufacturer>();

		while (rs.next()) {
			mfrs.add(populateMfr(rs.getInt(1), rs.getString(2)));
		}
		return mfrs;
	}

	/*
	 * Select a Specific Manufacturer from ID and Return ID and Name. Used in
	 * Menu.displayManufacturer()
	 */
	public Manufacturer getMfrById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_MFR_BY_ID);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return populateMfr(rs.getInt(1), rs.getString(2));
	}

	/*
	 * Add a New Manufacturer to Database by Name. Used in Menu.createManufacturer()
	 */
	public void createNewMfr(String name) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_MFR);
		ps.setString(1, name);
		ps.executeUpdate();
	}

	/*
	 * Remove an Existing Manufacturer from Database by ID. Used in
	 * Menu.deleteManufacturer()
	 */
	public void deleteMfrById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_MFR_BY_ID);
		ps.setInt(1, id);
		ps.executeUpdate();
	}

	/*
	 * Create a new Manufacturer Object in Java. Used in this.getMfrs() and
	 * this.getMfrById()
	 */
	private Manufacturer populateMfr(int id, String name) throws SQLException {
		return new Manufacturer(id, name, camDao.getCamsByMfrId(id));
	}

}
