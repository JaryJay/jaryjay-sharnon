package game.actor;

public interface HasLevel {

	public int getLevel();

	public void setLevel(int level);

	public int getExperience();

	public void setExperience(int experience);

	/**
	 * Adds experience and returns the number of times the {@link GameActor} leveled
	 * up.
	 * 
	 * @param experience the amount of experience to add (can be negative)
	 * @return the number of times the actor levelled up
	 */
	public int addExperience(int experience);

}
