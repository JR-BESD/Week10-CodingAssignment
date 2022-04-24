package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.CamDao;
import dao.MfrDao;
import entity.Camera;
import entity.Manufacturer;

public class Menu {

	private Scanner scanner = new Scanner(System.in);
	private List<String> categories = Arrays.asList("Manufacturers", "Cameras");
	private MfrDao mfrDao = new MfrDao();
	private CamDao camDao = new CamDao();
	private List<String> mfrOptions = Arrays.asList("Show All Manufacturers", "Add New manufacturer",
			"Delete Manufacturer", "Return to Previous Menu");
	private List<String> camOptions = Arrays.asList("Show Cameras by Manufacturer", "Add New Camera", "Delete Camera",
			"Modify Camera Details", "Return to Previous Menu");

	/*
	 * Start Application: Call to Show Categories and Control Menu Selections
	 */
	public void start() throws SQLException {
		String selection = "";
		String endProgram = categories.size() + 1 + "";
		do {
			printCategories();
			selection = scanner.nextLine();

			if (selection.equals("1")) {
				listMfrOptions();
			} else if (selection.equals("2")) {
				listCamOptions();
			} else if (selection.equals(endProgram)) {
				System.out.println("Application Closed!");
				System.exit(0);
			} else {
				printInvalidErr();
			}

		} while (!selection.equals(-1));
	}

	/*
	 * Show Categories
	 */
	private void printCategories() {
		String header = "Select Category";
		System.out.println(header);
		for (int i = 0; i < header.length(); i++) {
			System.out.print("~");
		}
		System.out.println();

		for (int i = 0; i < categories.size(); i++) {
			System.out.println(i + 1 + ": " + categories.get(i));
		}
		System.out.println(categories.size() + 1 + ": Exit Application");
	}

	/*
	 * Call to Show Options for Manufacturer Category and Control Menu Selections
	 */
	private void listMfrOptions() {
		String selection = "";
		do {
			printMfrMenu();
			selection = scanner.nextLine();

			try {
				if (selection.equals("1")) {
					displayManufacturers();
				} else if (selection.equals("2")) {
					createManufacturer();
				} else if (selection.equals("3")) {
					deleteManufacturer();
				} else if (selection.equals("4")) {
					break;
				} else {
					printInvalidErr();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			System.out.println("\n\nPress enter to continue");
			scanner.nextLine();

		} while (!selection.equals(-1));

	}

	/*
	 * Show Manufacturer Options
	 */
	private void printMfrMenu() {
		String header = "Select an Option";
		System.out.println(header);
		for (int i = 0; i < header.length(); i++) {
			System.out.print("~");
		}
		System.out.println();

		for (int i = 0; i < mfrOptions.size(); i++) {
			System.out.println(i + 1 + ": " + mfrOptions.get(i));
		}
	}

	/*
	 * Call to Show Options for Camera Category and Control Menu Selections
	 */
	private void listCamOptions() throws SQLException {
		String selection = "";
		do {
			printCamMenu();
			selection = scanner.nextLine();
			try {
				if (selection.equals("1")) {
					displayManufacturer();
				} else if (selection.equals("2")) {
					createCamera();
				} else if (selection.equals("3")) {
					deleteCamera();
				} else if (selection.equals("4")) {
					listModOptions();
				} else if (selection.equals("5")) {
					break;
				} else {
					printInvalidErr();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			System.out.println("\n\nPress enter to continue");
			scanner.nextLine();

		} while (!selection.equals(-1));
	}

	/*
	 * Call to shoe Modification Options and Control Menu Selections.
	 */
	private void listModOptions() throws SQLException {
		String selection = "";
		System.out.print("Enter the ID of the Camera to Modify:");
		int id = Integer.parseInt(scanner.nextLine());
		do {
			Camera cam = camDao.selectCam(id);
			modifyCamera(cam);
			selection = scanner.nextLine();
			try {
				if (selection.equals("1")) {
					changeMfr(id);
				} else if (selection.equals("2")) {
					changeCamName(id);
				} else if (selection.equals("3")) {
					changeCamType(id);
				} else if (selection.equals("4")) {
					changeImgType(id);
				} else if (selection.equals("5")) {
					changeYear(id);
				} else if (selection.equals("6")) {
					break;
				} else {
					printInvalidErr();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			System.out.println("\n\nPress enter to continue");
			scanner.nextLine();

		} while (!selection.equals(-1));
	}

	/*
	 * Show Modification Options for specific Camera by ID with current Camera
	 * details.
	 */
	private void modifyCamera(Camera cam) {
		List<String> modOptions = Arrays.asList("Manufacturer ID:\t" + cam.getMfrId(), "Name:\t\t" + cam.getCamName(),
				"Camera Type:\t\t" + cam.getCamType(), "Image Type:\t\t" + cam.getImgType(),
				"Year Released:\t" + cam.getYear() + "\n", "Return to Previous Menu");
		String header = "What would you like to change for Camera ID #" + cam.getCamId() + "?";
		System.out.println();
		System.out.println(header);
		for (int i = 0; i < header.length(); i++) {
			System.out.print("~");
		}
		System.out.println();

		for (int i = 0; i < modOptions.size(); i++) {
			System.out.println(i + 1 + ": " + modOptions.get(i));
		}
	}

	/*
	 * Show Camera Options
	 */
	private void printCamMenu() {
		String header = "Select an Option";
		System.out.println();
		System.out.println(header);
		for (int i = 0; i < header.length(); i++) {
			System.out.print("~");
		}
		System.out.println();

		for (int i = 0; i < camOptions.size(); i++) {
			System.out.println(i + 1 + ": " + camOptions.get(i));
		}
	}

	/*
	 * Show all Manufacturers
	 */
	private void displayManufacturers() throws SQLException {
		List<Manufacturer> mfrs = mfrDao.getMfrs();
		System.out.println("\nManufacturers:\n");
		for (Manufacturer mfr : mfrs) {
			System.out.println("\t" + mfr.getMfrId() + ": " + mfr.getMfrName());
		}
	}

	/*
	 * Create a new manufacturer
	 */
	private void createManufacturer() throws SQLException {
		System.out.print("Enter New Manufacturer Name: ");
		String name = scanner.nextLine();
		mfrDao.createNewMfr(name);
	}

	/*
	 * Delete a Manufacturer by Id
	 */
	private void deleteManufacturer() throws SQLException {
		System.out.print("Enter id of manufacturer to delete: ");
		int id = Integer.parseInt(scanner.nextLine());
		mfrDao.deleteMfrById(id);
	}

	/*
	 * Show all Cameras for a Specified Manufacturer
	 */
	private void displayManufacturer() throws SQLException {
		System.out.print("Enter Manufacturer ID: ");
		int id = Integer.parseInt(scanner.nextLine());
		Manufacturer mfr = mfrDao.getMfrById(id);
		System.out.println("\n" + mfr.getMfrId() + ": " + mfr.getMfrName() + "\n");
		for (Camera cam : mfr.getCameras()) {
			System.out.println("\t" + cam.getCamId() + ": " + cam.getCamName());
		}
	}

	/*
	 * Create a New Camera
	 */
	private void createCamera() throws SQLException {
		System.out.print("Enter the name of the Manufacturer: ");
		String mfrName = scanner.nextLine();
		System.out.print("Enter the name of the Camera: ");
		String camName = scanner.nextLine();
		System.out.print("Enter the camera type: ");
		String camType = scanner.nextLine();
		System.out.print("Enter film or digital: ");
		String imgType = scanner.nextLine();
		System.out.print("Enter the year the camera was released: ");
		int releaseYear = Integer.parseInt(scanner.nextLine());
		camDao.createNewCam(mfrName, camName, camType, imgType, releaseYear);
	}

	/*
	 * Delete a Camera by Id
	 */
	private void deleteCamera() throws SQLException {
		System.out.print("Enter camera id to delete: ");
		int id = Integer.parseInt(scanner.nextLine());
		camDao.deleteCamByID(id);
	}

	/*
	 * Show Invalid Selection
	 */
	private void printInvalidErr() {
		System.err.println(
				"\n***********************************\nInvalid Category, Please Try Again.\n***********************************\n");
	}

	/*
	 * Change the Manufacturer by ID
	 */
	private void changeMfr(int id) throws SQLException {
		int camId = id;
		System.out.print("Enter new Manufacturer id: ");
		int mfrId = Integer.parseInt(scanner.nextLine());
		camDao.modCameraMfrByCamId(camId, mfrId);
	}

	/*
	 * Change the Camera Name by ID
	 */
	private void changeCamName(int id) throws SQLException {
		int camId = id;
		System.out.print("Enter new Name: ");
		String name = scanner.nextLine();
		camDao.modCameraNameByCamId(camId, name);
	}

	/*
	 * Change the Camera Type by ID
	 */
	private void changeCamType(int id) throws SQLException {
		int camId = id;
		System.out.print("Enter new Type: ");
		String type = scanner.nextLine();
		camDao.modCameraTypeById(camId, type);
	}

	/*
	 * Change the Camera Image Type by ID
	 */
	private void changeImgType(int id) throws SQLException {
		int camId = id;
		System.out.print("Enter new Type: ");
		String type = scanner.nextLine();
		camDao.modImageTypeById(camId, type);
	}

	/*
	 * Change Camera Year of Release by ID
	 */
	private void changeYear(int id) throws SQLException {
		int camId = id;
		System.out.print("Enter the new Year of Release: ");
		int yr = Integer.parseInt(scanner.next());
		if (String.valueOf(yr).length() != 4 || yr < 1901 || yr > 2155) {
			System.out.println("Please Enter a valid 4 digit year between 1901 and 2155!");
			changeYear(camId);
		} else {
			camDao.modYearById(camId, yr);
		}
	}
}
