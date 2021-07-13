package game.actor;

public class GameActor {

	protected String name;

	protected int maxHealth;
	protected int maxEnergy;
	protected int health;
	protected int energy;
	protected int attack;
	protected int defence;
	protected int agility;

	public GameActor(String name, int maxHealth, int maxEnergy, int attack, int defence, int agility) {
		this.name = name;
		this.maxHealth = maxHealth;
		this.maxEnergy = maxEnergy;
		this.health = maxHealth;
		this.energy = maxEnergy;
		this.attack = attack;
		this.defence = defence;
		this.agility = agility;
	}

	@Override
	public String toString() {
		return name
				+ "\nHealth:  " + health + "/" + maxHealth
				+ "\nEnergy:  " + energy + "/" + maxEnergy
				+ "\nAttack:  " + attack
				+ "\nDefence: " + defence
				+ "\nAgility: " + agility;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getMaxEnergy() {
		return maxEnergy;
	}

	public void setMaxEnergy(int maxEnergy) {
		this.maxEnergy = maxEnergy;
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

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefence() {
		return defence;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}

	public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

}
