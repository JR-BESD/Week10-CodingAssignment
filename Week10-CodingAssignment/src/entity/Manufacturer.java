package entity;

import java.util.List;

public class Manufacturer {

	private int mfrId;
	private String mfrName;
	private List<Camera> cameras;

	public Manufacturer(int id, String name, List<Camera> cameras) {
		this.setMfrId(id);
		this.setMfrName(name);
		this.setCameras(cameras);
	}

	public int getMfrId() {
		return mfrId;
	}

	public void setMfrId(int mfrId) {
		this.mfrId = mfrId;
	}

	public String getMfrName() {
		return mfrName;
	}

	public void setMfrName(String mfrName) {
		this.mfrName = mfrName;
	}

	public List<Camera> getCameras() {
		return cameras;
	}

	public void setCameras(List<Camera> cameras) {
		this.cameras = cameras;
	}

}
