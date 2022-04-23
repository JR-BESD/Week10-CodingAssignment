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
			"Return to Previous Menu");

	public void start() throws SQLException {
		String selection = "";
		String endProgram = categories.size() + 1 +"";
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

	private void createManufacturer() throws SQLException {
		System.out.print("Enter New Manufacturer Name: ");
		String name = scanner.nextLine();
		mfrDao.createNewMfr(name);
	}
	
	private void deleteManufacturer() throws SQLException {
		System.out.print("Enter id of manufacturer to delete: ");
		int id = Integer.parseInt(scanner.nextLine());
		mfrDao.deleteMfrById(id);
	}
	
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
	
	private void deleteCamera() throws SQLException {
		System.out.print("Enter camera id to delete: ");
		int id = Integer.parseInt(scanner.nextLine());
		camDao.deleteCamByID(id);
	}

	private void displayManufacturer() throws SQLException {
		System.out.print("Enter Manufacturer ID: ");
		int id = Integer.parseInt(scanner.nextLine());
		Manufacturer mfr = mfrDao.getMfrById(id);
		System.out.println("\n" + mfr.getMfrId() + ": " + mfr.getMfrName()+ "\n");
		for (Camera cam : mfr.getCameras()) {
			System.out.println("\t" + cam.getCamId()+ ": " + cam.getCamName());
		}
	}

	private void displayManufacturers() throws SQLException {
		List<Manufacturer> mfrs = mfrDao.getMfrs();
		System.out.println("\nManufacturers:\n");
		for (Manufacturer mfr : mfrs) {
			System.out.println("\t" + mfr.getMfrId() + ": " + mfr.getMfrName());
		}
	}

	private void printCamMenu() {
		String header = "Select an Option";
		System.out.println(header);
		for (int i = 0; i < header.length(); i++) {
			System.out.print("~");
		}
		System.out.println();

		for (int i = 0; i < camOptions.size(); i++) {
			System.out.println(i + 1 + ": " + camOptions.get(i));
		}
	}

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

	private void printInvalidErr() {
		System.err.println("\n***********************************\nInvalid Category, Please Try Again.\n***********************************\n");
	}
}
