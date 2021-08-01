package game.actor;

public abstract class GameActor {

	protected String name;

	protected int baseMaxHealth;
	protected int baseMaxEnergy;
	protected int health;
	protected int energy;
	protected int baseAttack;
	protected int baseDefence;
	protected int baseAgility;

	public GameActor(String name, int maxHealth, int maxEnergy, int attack, int defence, int agility) {
		this.name = name;
		this.baseMaxHealth = maxHealth;
		this.baseMaxEnergy = maxEnergy;
		this.health = maxHealth;
		this.energy = maxEnergy;
		this.baseAttack = attack;
		this.baseDefence = defence;
		this.baseAgility = agility;
	}

	@Override
	public String toString() {
		return name
				+ "\nHealth:  " + health + "/" + baseMaxHealth
				+ "\nEnergy:  " + energy + "/" + baseMaxEnergy
				+ "\nAttack:  " + baseAttack
				+ "\nDefence: " + baseDefence
				+ "\nAgility: " + baseAgility;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxHealth() {
		return baseMaxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.baseMaxHealth = maxHealth;
	}

	public int getMaxEnergy() {
		return baseMaxEnergy;
	}

	public void setMaxEnergy(int maxEnergy) {
		this.baseMaxEnergy = maxEnergy;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getBaseAttack() {
		return baseAttack;
	}

	public void setBaseAttack(int attack) {
		this.baseAttack = attack;
	}

	public abstract int getAttack();

	public int getBaseDefence() {
		return baseDefence;
	}

	public void setBaseDefence(int defence) {
		this.baseDefence = defence;
	}

	public abstract int getDefence();

	public int getBaseAgility() {
		return baseAgility;
	}

	public void setBaseAgility(int agility) {
		this.baseAgility = agility;
	}

	public abstract int getAgility();

}
