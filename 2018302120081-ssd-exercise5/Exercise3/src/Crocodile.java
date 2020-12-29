import java.util.Vector;
/*
 * Created on Jul 6, 2003
 *
 */

/**
 * Crocodile - to simulate artificial life. Eats small fish.
 * 
 * @author iCarnegie av
 *
 */
public class Crocodile extends LivingBeing {
	
	/**
	 * The crocodile is born "alive". 
	 * Then it dies, becoming a corpse. 
	 */
	private static final String ALIVE = "alive";

	/**
	 * The crocodile is born "alive". 
	 * Then it dies, becoming a "dead" corpse. 
	 */
	private static final String DEAD = "dead";


	/**
	 * Energy expended to wade during a block of time.
	 */
	private static final int ENERGY_TO_WADE = 10;
	
	/**
	 * Energy expended to eat once.
	 */
	private static final int ENERGY_TO_EAT = 10;
		
	/**
	 * Lowest possible energy needed for a baby to survive. 
	 */
	private static final int BABY_MIN_ENERGY = 1000;
	
	/**
	 * Maximum energy that a baby can store. 
	 */
	private static final int BABY_MAX_ENERGY = 2000;

	/**
	 * For each block of time, the min energy grows by a certain amount
	 */
	private static final int MIN_ENERGY_GROWTH_INCREMENT = 5;
	
	/**
	 * For each block of time, the max energy grows by a certain amount
	 */
	private static final int MAX_ENERGY_GROWTH_INCREMENT = 10; 

	// Concept example: final. since it is a constant 
	// Concept example: static. since only one value is needed 
	// 						irrespective of number of object instances 
	/**
	 * String constant - used to indicate the direction crocodile is facing.
	 */
	private static final String RIGHT = "right";

	/**
	 * String constant - used to indicate the direction crocodile is facing.
	 */
	private static final String LEFT = "left";

	/**
	 * String constant - used to indicate the direction crocodile is facing.
	 */
	private static final String UP = "up";

	/**
	 * String constant - used to indicate the direction crocodile is facing.
	 */
	private static final String DOWN = "down";

	/**
	 * Name of species
	 */
	private static final String SPECIES = "Crocodile";

	/**
	 * Row-wise location of the crocodile
	 */
	private int row;

	/**
	 * Column-wise location of the crocodile
	 */
	private int column;

	/**
	 * Is the crocodile dead or alive?
	 */
	private String deadOrAlive;
	
	/**
	 * Amount of energy the crocodile has.
	 */
	private int energy;

	/**
	 * Age expressed as blocks of time lived
	 */
	private int age = 0;

	/**
	 * Name of this crocodile.
	 */
	private final String name;

	/**
	 * The simulation to which this crocodile belongs.
	 * This is needed so the crocodile can send a message 
	 * to simulation and ask
	 * for prey (or predator) in the neighboring locations. 
	 * Prey is food. Food is good!
	 */
	private Simulation simulation;

	/**
	 * Minimum energy level needed to survive.
	 * The minimum could increase as the individual grows.
	 */
	private int minEnergy;
	
	/**
	 * Maximum energy level that the crocodile could carry.
	 * The maximum could change as the individual grows.
	 */
	private int maxEnergy;
	
	/**
	 * Which direction am I facing.
	 */
	private String direction; 

	/**
	 * Number of Crocodiles created so far
	 */
	private static int nCrocodilesCreated = 0;

	/**
	 * Construct and initialize a Crocodile.
	 * 
	 * @param initialRow - the row at which the crocodile is located
	 * @param initialColumn - the column at which the crocodile is located
	 * @param initialSimulation - the simulation that the crocodile belongs to
	 */
	public Crocodile(
		int initialRow,
		int initialColumn,
		Simulation initialSimulation) {

		simulation = initialSimulation;
		nCrocodilesCreated=nCrocodilesCreated+1;
		name = SPECIES + nCrocodilesCreated;
		minEnergy = 500;
		maxEnergy = 2000;
		energy = 100;
		direction=RIGHT;
		if(initialRow<=0){
			row=1;
		}else if(initialRow>10){
			row=10;
		}else{
			row=initialRow;
		}

		if(initialColumn<1){
			column=1;
		}else if(initialRow>10){
			column=10;
		}else{
			column=initialColumn;
		}

	}

	/**
	 * Get the row at which the crocodile is located 
	 * 
	 * @return - the row of the crocodile's location. 
	 */		
	public int getRow() {
		return row;
	}

	/**
	 * Get the column at which the crocodile is located
	 * 
	 * @return - the column of the crocodile's location. 
	 */		
	public int getColumn() {
		return column;
	}

	/**
	 * Get the crocodile's age
	 * 
	 * @return the age of the crocodile expressed in blocks of time
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Color of the crocodile expressed in hex notation.
	 * For example, the "green-est" color is "#00FF00",
	 * "blue-est" is "#0000FF", the "red-est" is "#FF0000".
	 * 
	 * @return the rgb color in hex notation. preceded by a pound character '#'
	 */
	public String getColor() {
		return "#FFFFFF"; // default is white.
	}

	/**
	 * Get the name of this crocodile
	 * 
	 * @return the name of the crocodile.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the minimum energy needed to live.
	 * 
	 * @return the minimum energy needed for the crocodile to live.
	 */
	private int getMinEnergy() {
		return minEnergy;
	}
	
	/**
	 * get the maximum energy that the crocodile can carry.
	 * 
	 * @return the maximum energy the crocodile can carry.
	 */
	private int getMaxEnergy() {
		return maxEnergy;
	}
	
	/**
	 * Get the energy currently carried by the crocodile.
	 * 
	 * @return current energy level of the organism
	 */
	public int getEnergy() {
		return energy;
	}

	/**
	 * Sets energy level.
	 * If new energy level is less than minimum energy level, the organism dies.
	 * New energy level is capped at maximum energy level.
	 */
	private void setEnergy(int newEnergy) {
		if (newEnergy < minEnergy)
		{
			energy=minEnergy;
			die();
		}else if(newEnergy > maxEnergy){
			energy=maxEnergy;
		}else{
			energy=newEnergy;
		}
	}

	/**
	 * Die: Change the deadOrAlive to DEAD.
	 */
	private void die() {
		deadOrAlive = DEAD;
	}

	/**
	 * Is the crocodile dead?
	 * 
	 * @return <code>true</code> if dead. <code>false</code>, otherwise.
	 */
	public boolean isDead() {
		return (deadOrAlive == DEAD);
	}

	/**
	 * Get the direction faced by the crocodile.
	 * 
	 * @return the facing direction.
	 */
	private String getDirection() {
		return direction;
	}

	/** 
	 * Is the crocodile hungry?
	 * 
	 * @return True, if hungry. False, otherwise.
	 */
	private boolean isHungry() {
		
		// Hungry, if current energy level is less than twice the 
		// amount needed for survival.
		if (energy < 2*minEnergy)  // Hungry, if current energy level is less than twice the
		// amount needed for survival.
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Move the crocodile to a new row, if new row is within lake bounds.
	 * 
	 * @param newRow - the row to move to.
	 * @return the row moved to. Lake boundary limits movement. -1, if dead.
	 */
	private int moveToRow(int newRow) {
		if(deadOrAlive == ALIVE){
			if (newRow < 1){
				if(newRow < row){
					direction = UP;
				}else{
					direction = DOWN;
				}
				row = 1;
			}else if(newRow >= 10){
				if(newRow < row){
					direction = UP;
				}else{
					direction = DOWN;
				}
				row = 10;
			}else{
				if(newRow < row){
					direction = UP;
				}else{
					direction = DOWN;
				}
				row = newRow;
			}
		}else if(deadOrAlive == DEAD){
			row=-1;
		}
		return row;
	}

	/**
	 * Move the crocodile to a new column, if new column is within lake bounds.
	 * 
	 * @param newColumn - the column to move to.
	 * @return the column moved to. Lake boundary limits movement.
	 */
	private int moveToColumn(int newColumn) {
		if(deadOrAlive == ALIVE){
			if (newColumn < 1){
				if(newColumn < column){
					direction = LEFT;
				}else{
					direction = RIGHT;
				}
				column = 1;
			}else if(newColumn >= 10){
				if(newColumn < column){
					direction = LEFT;
				}else{
					direction = RIGHT;
				}
				column = 10;
			}else{
				if(newColumn < column){
					direction = LEFT;
				}else{
					direction = RIGHT;
				}
				column = newColumn;
			}
		}else if(deadOrAlive == DEAD){
			column=-1;
		}
		return column;
	}

	/**
	 * Get the species
	 * 
	 * @return a string indicating the species
	 */
	public String getSpecies() {
		return SPECIES;
	}

	/**
	 * The display mechanism to use to display a crocodile.
	 * 
	 * @return a constant defined in {@link Simulation#IMAGE Simulation} class
	 */
	public String getDisplayMechanism() {
		return Simulation.IMAGE;
	}
	
	/**
	 * Get the filename that contains an image of the Crocodile
	 * 
	 * @return filename of Crocodile image
	 */
	public String getImage() {

		if (getDirection()==RIGHT){
			return "resource/img/Crocodile-right.gif";
		}
		if (getDirection()==LEFT){
			return "resource/img/Crocodile-left.gif";
		}
		if (getDirection()==UP){
			return "resource/img/Crocodile-up.gif";
		}
		if ((getDirection())==DOWN){
			return "resource/img/Crocodile-down.gif";
		}
		return null;
	}

	/** 
	 * Wade to a new location if possible.
	 * Consumes some energy.
	 */
	private void wadeIfPossible() {
		energy-=ENERGY_TO_WADE;
		// Consume ENERGY_TO_SWIM units of energy to swim.

		// Check if there is any energy left after consumption(~2/3lines)
		if(energy>=minEnergy){
			int directionNum = simulation.getRand().nextInt(4);
			switch (directionNum) {
				case 0:
					row--;
					this.direction = UP;
					break;
				case 1:
					row++;
					this.direction = DOWN;
					break;
				case 2:
					column++;
					this.direction = RIGHT;
					break;
				case 3:
					column--;
					this.direction = LEFT;
					break;
			}
	}else {
			die();
		}
	}

	/**
	 * Eat if food is available in the current location.
	 */
	private void eatIfPossible() {
		if(isDead()){
			return;
		}
		Vector foodMaybe = simulation.getNeighbors(getRow(), getColumn(), 0);
		for(int neighborIndex = 0; neighborIndex < foodMaybe.size(); neighborIndex++)
			if(foodMaybe.get(neighborIndex) instanceof Catfish)
			{
				Catfish fish = (Catfish)foodMaybe.get(neighborIndex);
				int energy_Gain_From_Meal;
				energy_Gain_From_Meal = fish.getEnergy();
				setEnergy((getEnergy() + energy_Gain_From_Meal) - ENERGY_TO_EAT);
				fish.die();
				return;
			}
	}

	/**
	 * Live for a block of time.
	 */
	public void liveALittle() {
		if(isDead()){
			return;
		}else{
			age++;
			wadeIfPossible();
			eatIfPossible();
			minEnergy = minEnergy + MIN_ENERGY_GROWTH_INCREMENT;
			maxEnergy = maxEnergy + MAX_ENERGY_GROWTH_INCREMENT;
			return;
		}
	}
}
