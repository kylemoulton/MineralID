import java.util.ArrayList;

public class Mineral {
	private final String NAME;
	private final ArrayList<String> STREAK;
	private final ArrayList<String> COLOR;
	private final Float[] HARDNESS;
	private final Boolean FRACTURE;
	private final Integer CLEAVAGE;
	private final String[] LUSTER;
	private final String DIAPHANEITY;
	private final String TEXTURE;
	private final Float[] SPECGRAV;
	private final Boolean DOUBREF;
	
	public Mineral(String name, ArrayList<String> streak, ArrayList<String> color, Float[] hardness, Boolean fracture,
			Integer cleavage, String[] luster, String diaphaneity, String texture, Float[] specGrav,
			Boolean doubRef) {
		this.NAME = name;
		this.STREAK = streak;
		this.COLOR = color;
		this.HARDNESS = hardness;
		this.FRACTURE = fracture;
		this.CLEAVAGE = cleavage;
		this.LUSTER = luster;
		this.DIAPHANEITY = diaphaneity;
		this.TEXTURE = texture;
		this.SPECGRAV = specGrav;
		this.DOUBREF = doubRef;
	}

	public String getName() {
		return NAME;
	}

	public ArrayList<String> getStreak() {
		return STREAK;
	}

	public ArrayList<String> getColor() {
		return COLOR;
	}

	public Float[] getHardness() {
		return HARDNESS;
	}

	public Boolean getFracture() {
		return FRACTURE;
	}

	public Integer getCleavage() {
		return CLEAVAGE;
	}

	public String[] getLuster() {
		return LUSTER;
	}

	public String getDiaphaneity() {
		return DIAPHANEITY;
	}

	public String getTexture() {
		return TEXTURE;
	}

	public Float[] getSpecGrav() {
		return SPECGRAV;
	}

	public Boolean getDoubRef() {
		return DOUBREF;
	}
	
	public void printProperties() {
		System.out.println("Mineral name: \t\t" + getName());
		System.out.print("Streak(s): \t\t");
		for (int i = 0; i < getStreak().size(); i++) {
			System.out.print(getStreak().get(i));
			if (i != (getStreak().size() - 1)) {
				System.out.print(", ");
			} else
				System.out.println();
		}
		System.out.print("Color(s): \t\t");
		for (int i = 0; i < getColor().size(); i++) {
			System.out.print(getColor().get(i));
			if (i != (getColor().size() - 1)) {
				System.out.print(", ");
			} else
				System.out.println();
		}
		System.out.println("Hardness: \t\t" + getHardness()[0] + " to " + getHardness()[1]);
		System.out.print("Luster: \t\t" + getLuster()[0]);
		if (!getLuster()[1].isEmpty()) {
			System.out.print(" to " + getLuster()[1]);
		}
		System.out.println();
		System.out.println("Diaphaneity: \t\t" + getDiaphaneity());
		System.out.println("Texture: \t\t" + getTexture());
		System.out.println("Specific Gravity: \t" + getSpecGrav()[0] + " to " + getSpecGrav()[1]);
		if (getFracture()) {
			System.out.println("Mineral displays fracture");
		} else {
			System.out.println("Mineral displays " + getCleavage() + " plane(s) of cleavage");
		}
		if (getDoubRef()) {
			System.out.println("Mineral displays double refraction");
		} else {
			System.out.println("Mineral does not display double refraction");
		}
		System.out.println();
	}
	
	
}






