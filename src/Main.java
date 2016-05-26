import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	static ArrayList<Mineral> minerals = new ArrayList<Mineral>();

	public static void main(String[] args) throws NumberFormatException, IOException {

		String fileName = "Minerals.csv";
		BufferedReader fr = null;
		String line = new String();

		try {
			fr = new BufferedReader(new FileReader(fileName));
			line = fr.readLine(); // Clears the title row
			while ((line = fr.readLine()) != null) {
				minerals.add(addMineral(line, minerals));
			}
			for (Mineral mineral : minerals) {
				mineral.printProperties();
			}
		} catch (IOException e) {
			System.out.println("Minerals.csv File not found...");
			e.printStackTrace();
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		ArrayList<Mineral> searchResults = new ArrayList<Mineral>();
		
		int searchChoice;
		String searchTerm = new String();
		Float searchValue = 0f;

		boolean firstRun = true;

		while (true) {
			if (firstRun) {
				showMenu();
				System.out.println("Enter the mineral property you wish to search by:");
			} else {
				System.out.println("Would you like to continue refining your search by another property? (Y/N)");
				if (br.readLine().toUpperCase().equals("N")) {
					break;
				} else {
					showMenu();
					System.out.println("Enter the mineral property you wish to search by:");
				}
			}
			searchChoice = Integer.parseInt(br.readLine());

			switch (searchChoice) {

			case 1:
				System.out.println("Enter the name of the mineral to search for");

				searchTerm = br.readLine().toLowerCase();
				searchTerm = searchTerm.substring(0, 1).toUpperCase() + searchTerm.substring(1, searchTerm.length());

				if (firstRun) {
					searchResults = searchByName(searchTerm, minerals);
				} else {
					searchResults = searchByName(searchTerm, searchResults);
				}
				firstRun = false;
				break;

			case 2:
				System.out.println("Enter the streak color of the mineral you wish to search for");

				searchTerm = br.readLine().toLowerCase();

				if (firstRun) {
					searchResults = searchByStreak(searchTerm, minerals);
				} else {
					searchResults = searchByStreak(searchTerm, searchResults);
				}
				firstRun = false;
				break;

			case 3:
				System.out.println("Enter the color of the mineral you wish to search for");

				searchTerm = br.readLine().toLowerCase();

				if (firstRun) {
					searchResults = searchByColor(searchTerm, minerals);
				} else {
					searchResults = searchByColor(searchTerm, searchResults);
				}
				firstRun = false;
				break;

			case 4:
				System.out.println("Enter the hardness of the mineral you wish to search for (0.0 - 10.0");

				searchValue = Float.parseFloat(br.readLine());

				if (firstRun) {
					searchResults = searchByHardness(searchValue, minerals);
				} else {
					searchResults = searchByHardness(searchValue, searchResults);
				}
				firstRun = false;
				break;

			case 5:
				System.out.println("Narrow results by minerals which display fracture(1) or cleavage(2)?");
				int fractureSearch = Integer.parseInt(br.readLine());
				boolean choice;
				if (fractureSearch == 1) {
					choice = true;
				} else if (fractureSearch == 2) {
					choice = false;
				} else {
					System.out.println(fractureSearch + " is not a valid option. Please try again");
					break;
				}

				if (firstRun) {
					searchResults = searchByFracture(choice, minerals);
				} else {
					searchResults = searchByFracture(choice, searchResults);
				}
				firstRun = false;
				break;

			case 6:
				System.out.println("Enter the luster you wish to search by");
				searchTerm = br.readLine().toLowerCase();

				if (firstRun) {
					searchResults = searchByLuster(searchTerm, minerals);
				} else {
					searchResults = searchByLuster(searchTerm, searchResults);
				}
				firstRun = false;
				break;

			case 7:
				System.out.println("Enter the diaphaneity you wish to search by");
				searchTerm = br.readLine().toLowerCase();

				if (firstRun) {
					searchResults = searchByDiaphaneity(searchTerm, minerals);
				} else {
					searchResults = searchByDiaphaneity(searchTerm, searchResults);
				}
				firstRun = false;
				break;

			case 8:
				System.out.println("Enter the texture you wish to search by (ENTER EXAMPLES)");
				searchTerm = br.readLine().toLowerCase();

				if (firstRun) {
					searchResults = searchByTexture(searchTerm, minerals);
				} else {
					searchResults = searchByTexture(searchTerm, searchResults);
				}
				firstRun = false;
				break;

			case 9:
				System.out.println("Enter the specific gravity you wish to search by (0.0 - 10.0)");
				searchValue = Float.parseFloat(br.readLine());

				if (firstRun) {
					searchResults = searchBySpecGrav(searchValue, minerals);
				} else {
					searchResults = searchBySpecGrav(searchValue, searchResults);
				}
				firstRun = false;
				break;

			case 10: 
				System.out.println("Narrow results by minerals which DO(1) or DO NOT(2) display double refraction?");
				int doubRefSearch = Integer.parseInt(br.readLine());
				if (doubRefSearch == 1) {
					choice = true;
				} else if (doubRefSearch == 2) {
					choice = false;
				} else {
					System.out.println(doubRefSearch + " is not a valid option. Please try again.");
					break;
				}

				if (firstRun) {
					searchResults = searchByDoubleRef(choice, minerals);
				} else {
					searchResults = searchByDoubleRef(choice, searchResults);
				}
				firstRun = false;
				break;

			default:
				System.out.println(searchChoice + " is not a valid choice. Please try again.");
				break;
			}
			if (!firstRun) {
				System.out.println("Found " + searchResults.size()
						+ " matching mineral results. Would you like to display their properties? (Y/N)");
				if (br.readLine().toUpperCase().equals("Y")) {
					for (Mineral mineral : searchResults) {
						mineral.printProperties();
					}

				}
			}

		}

	}

	public static ArrayList<Mineral> searchByName(String searchTerm, ArrayList<Mineral> mineralList) {
		ArrayList<Mineral> tempArray = new ArrayList<Mineral>();
		for (Mineral mineral : mineralList) {
			if (mineral.getName().contains(searchTerm)) {
				tempArray.add(mineral);
			}
		}
		return tempArray;
	}

	public static ArrayList<Mineral> searchByStreak(String searchTerm, ArrayList<Mineral> mineralList) {
		ArrayList<Mineral> tempArray = new ArrayList<Mineral>();
		for (Mineral mineral : mineralList) {
			for (String streak : mineral.getStreak()) {
				if (streak.equals(searchTerm)) {
					tempArray.add(mineral);
				}
			}
		}
		return tempArray;
	}

	public static ArrayList<Mineral> searchByColor(String searchTerm, ArrayList<Mineral> mineralList) {
		ArrayList<Mineral> tempArray = new ArrayList<Mineral>();
		for (Mineral mineral : mineralList) {
			for (String color : mineral.getStreak()) {
				if (color.equals(searchTerm)) {
					tempArray.add(mineral);
				}
			}
		}
		return tempArray;
	}

	public static ArrayList<Mineral> searchByHardness(Float hardness, ArrayList<Mineral> mineralList) {
		ArrayList<Mineral> tempArray = new ArrayList<Mineral>();
		for (Mineral mineral : mineralList) {
			if (hardness >= mineral.getHardness()[0] && hardness <= mineral.getHardness()[1]) {
				tempArray.add(mineral);
			}
		}
		return tempArray;
	}

	public static ArrayList<Mineral> searchByFracture(boolean fracture, ArrayList<Mineral> mineralList) {
		ArrayList<Mineral> tempArray = new ArrayList<Mineral>();
		if (fracture) {
			for (Mineral mineral : mineralList) {
				if (mineral.getFracture()) {
					tempArray.add(mineral);
				}
			}
		} else if (!fracture) {
			for (Mineral mineral : mineralList) {
				if (!mineral.getFracture()) {
					tempArray.add(mineral);
				}
			}
		}
		return tempArray;
	}

	public static ArrayList<Mineral> searchByLuster(String luster, ArrayList<Mineral> mineralList) {
		ArrayList<Mineral> tempArray = new ArrayList<Mineral>();
		for (Mineral mineral : mineralList) {
			if (mineral.getLuster()[0].equals(luster) || mineral.getLuster()[1].equals(luster)) {
				tempArray.add(mineral);
			}
		}
		return tempArray;
	}

	public static ArrayList<Mineral> searchByDiaphaneity(String diaphaneity, ArrayList<Mineral> mineralList) {
		ArrayList<Mineral> tempArray = new ArrayList<Mineral>();
		for (Mineral mineral : mineralList) {
			if (mineral.getDiaphaneity().equals(diaphaneity)) {
				tempArray.add(mineral);
			}
		}
		return tempArray;
	}

	public static ArrayList<Mineral> searchByTexture(String texture, ArrayList<Mineral> mineralList) {
		ArrayList<Mineral> tempArray = new ArrayList<Mineral>();
		for (Mineral mineral : mineralList) {
			if (mineral.getTexture().equals(texture)) {
				tempArray.add(mineral);
			}
		}
		return tempArray;
	}

	public static ArrayList<Mineral> searchBySpecGrav(Float specGrav, ArrayList<Mineral> mineralList) {
		ArrayList<Mineral> tempArray = new ArrayList<Mineral>();
		for (Mineral mineral : mineralList) {
			if (specGrav >= mineral.getSpecGrav()[0] && specGrav <= mineral.getSpecGrav()[1]) {
				tempArray.add(mineral);
			}
		}
		return tempArray;
	}

	public static ArrayList<Mineral> searchByDoubleRef(boolean doubleRef, ArrayList<Mineral> mineralList) {
		ArrayList<Mineral> tempArray = new ArrayList<Mineral>();
		for (Mineral mineral : mineralList) {
			if (doubleRef && mineral.getDoubRef()) {
				tempArray.add(mineral);
			} else if (!doubleRef && !mineral.getDoubRef()) {
				tempArray.add(mineral);
			}
			// If different do nothing?
		}
		return tempArray;
	}

	public static Mineral addMineral(String line, ArrayList<Mineral> minerals) {
		String name;
		ArrayList<String> streak = new ArrayList<String>();
		ArrayList<String> color = new ArrayList<String>();
		Float[] hardness = { 0f, 0f };
		Boolean fracture;
		Integer cleavage;
		String[] luster = { new String(), new String() };
		String diaphaneity;
		String texture;
		Float[] specGrav = { 0f, 0f };
		Boolean doubRef;

		String[] values = line.split(",");

		name = values[0];

		for (int i = 1; i < 4; i++) {
			if (!values[i].isEmpty()) {
				streak.add(values[i]);
			}
		}

		for (int i = 4; i < 10; i++) {
			if (!values[i].isEmpty()) {
				color.add(values[i]);
			}
		}

		hardness[0] = Float.parseFloat(values[10]);
		hardness[1] = Float.parseFloat(values[11]);
		fracture = Boolean.parseBoolean(values[12]);
		cleavage = Integer.parseInt(values[13]);
		luster[0] = values[14];
		luster[1] = values[15];
		diaphaneity = values[16];
		texture = values[17];
		specGrav[0] = Float.parseFloat(values[18]);
		specGrav[1] = Float.parseFloat(values[19]);
		doubRef = Boolean.parseBoolean(values[20]);

		return new Mineral(name, streak, color, hardness, fracture, cleavage, luster, diaphaneity, texture, specGrav,
				doubRef);
	}

	public static void showMenu() {
		System.out.println("(1) Name");
		System.out.println("(2) Streak");
		System.out.println("(3) Color");
		System.out.println("(4) Hardness");
		System.out.println("(5) Fracture or Cleavage");
		System.out.println("(6) Luster");
		System.out.println("(7) Diaphaneity");
		System.out.println("(8) Texture");
		System.out.println("(9) Specific Gravity");
		System.out.println("(10) Double Refraction");
	}

}
