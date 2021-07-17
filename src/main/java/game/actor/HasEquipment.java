package game.actor;

import game.inventory.Item;

public interface HasEquipment extends HasInventory {

	public Item getPrimaryWeapon();

	public void setPrimaryWeapon(Item item);

	public Item getSecondaryWeapon();

	public void setSecondaryWeapon(Item item);

	public Item getArmour();

	public void setArmour(Item item);

	public Item getHelmet();

	public void setHelmet(Item item);

	public Item getFootwear();

	public void setFootwear(Item item);

	public Item[] getAccessories();

	public default Item[] getAllEquipment() {
		Item[] accessories = getAccessories();
		return new Item[] { getPrimaryWeapon(), getSecondaryWeapon(), getArmour(), getHelmet(), getFootwear(), accessories[0], accessories[1], accessories[2] };
	}

	public default int getMaxHealthModifier() {
		int maxHealthModifier = 0;
		Item[] allEquipment = getAllEquipment();
		for (int i = 0; i < allEquipment.length; i++) {
			Item item = allEquipment[i];
			maxHealthModifier += item == null ? 0 : item.getMaxHealthModifier();
		}
		return maxHealthModifier;
	}

	public default int getMaxEnergyModifier() {
		int maxEnergyModifier = 0;
		Item[] allEquipment = getAllEquipment();
		for (int i = 0; i < allEquipment.length; i++) {
			Item item = allEquipment[i];
			maxEnergyModifier += item == null ? 0 : item.getMaxEnergyModifier();
		}
		return maxEnergyModifier;
	}

	public default int getAttackModifier() {
		int attackModifier = 0;
		Item[] allEquipment = getAllEquipment();
		for (int i = 0; i < allEquipment.length; i++) {
			Item item = allEquipment[i];
			attackModifier += item == null ? 0 : item.getAttackModifier();
		}
		return attackModifier;
	}

	public default int getDefenceModifier() {
		int attackModifier = 0;
		Item[] allEquipment = getAllEquipment();
		for (int i = 0; i < allEquipment.length; i++) {
			Item item = allEquipment[i];
			attackModifier += item == null ? 0 : item.getDefenceModifier();
		}
		return attackModifier;
	}

	public default int getAgilityModifier() {
		int attackModifier = 0;
		Item[] allEquipment = getAllEquipment();
		for (int i = 0; i < allEquipment.length; i++) {
			Item item = allEquipment[i];
			attackModifier += item == null ? 0 : item.getAgilityModifier();
		}
		return attackModifier;
	}

	public default boolean set(String attr, Item item) {
		switch (attr) {
			case "primary_weapon":
			case "primary":
				setPrimaryWeapon(item);
				return true;
			case "secondary_weapon":
			case "secondary":
				setSecondaryWeapon(item);
				return true;
			case "armour":
				setArmour(item);
				return true;
			case "helmet":
				setHelmet(item);
				return true;
			case "boots":
			case "footwear":
				setFootwear(item);
				return true;
			case "accessory_1":
				getAccessories()[0] = item;
				return true;
			case "accessory_2":
				getAccessories()[1] = item;
				return true;
			case "accessory_3":
				getAccessories()[2] = item;
				return true;
			default:
				return false;
		}
	}

}
