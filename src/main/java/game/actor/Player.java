package game.actor;

public class Player extends GameActor {

	private String name;
	private int level;
	private int experience;

	public Player(int maxHealth, int maxEnergy, int attack, int defence, int agility, String name) {
		super(maxHealth, maxEnergy, attack, defence, agility);
		this.name = name;
	}

	public boolean addExperience(int experience) {
		boolean leveledUp = false;
		this.experience += experience;
		while (this.experience >= level * 50 + 50) {
			this.experience -= level * 50 + 50;
			level++;
			leveledUp = true;
		}
		return leveledUp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

}
