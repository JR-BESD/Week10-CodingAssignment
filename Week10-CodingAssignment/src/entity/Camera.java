package entity;

public class Camera {

	private int camId;
	private int mfrId;
	private String camName;
	private String camType;
	private String imgType;
	private int year;

	public Camera(int id, int mfrId, String name, String type, String img, int year) {
		this.setCamId(id);
		this.setMfrId(mfrId);
		this.setCamName(name);
		this.setCamType(type);
		this.setImgType(img);
		this.setYear(year);
	}

	public int getCamId() {
		return camId;
	}

	public void setCamId(int camId) {
		this.camId = camId;
	}

	public String getCamName() {
		return camName;
	}

	public void setCamName(String camName) {
		this.camName = camName;
	}

	public String getCamType() {
		return camType;
	}

	public void setCamType(String camType) {
		this.camType = camType;
	}

	public String getImgType() {
		return imgType;
	}

	public void setImgType(String img) {
		this.imgType = img;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMfrId() {
		return mfrId;
	}

	public void setMfrId(int mfrId) {
		this.mfrId = mfrId;
	}

}
