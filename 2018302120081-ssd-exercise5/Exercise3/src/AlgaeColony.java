//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

public class AlgaeColony extends LivingBeing {
    private static final String ALIVE = "alive";
    private static final String DEAD = "dead";
    private static final int BABY_MIN_ENERGY = 5;
    private static final int BABY_MAX_ENERGY = 255;
    private static final int ENERGY_TO_LIVE = 1;
    private static final String SPECIES = "Algae";
    private int row;
    private int column;
    private String deadOrAlive;
    private int energy;
    private int age = 0;
    private final String name;
    private Simulation simulation;
    private int minEnergy;
    private int maxEnergy;
    private static int nAlgaeCreated = 0;

    public AlgaeColony(int initialRow, int initialColumn, Simulation initialSimulation) {
        this.simulation = initialSimulation;
        this.deadOrAlive = "alive";
        if (initialRow > initialSimulation.getLastRow()) {
            this.row = initialSimulation.getLastRow();
        } else if (initialRow < initialSimulation.getFirstRow()) {
            this.row = initialSimulation.getFirstRow();
        } else {
            this.row = initialRow;
        }

        if (initialColumn > initialSimulation.getLastColumn()) {
            this.column = initialSimulation.getLastColumn();
        } else if (initialColumn < initialSimulation.getFirstColumn()) {
            this.column = initialSimulation.getFirstColumn();
        } else {
            this.column = initialColumn;
        }

        this.minEnergy = 5;
        this.maxEnergy = 255;
        this.energy = this.simulation.getRand().nextInt(this.maxEnergy - this.minEnergy) + this.minEnergy;
        this.age = 0;
        this.name = "Algae" + nAlgaeCreated;
        ++nAlgaeCreated;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public int getAge() {
        return this.age;
    }

    public String getName() {
        return this.name;
    }

    public String getImage() {
        return "/blank.gif";
    }

    private int getMinEnergy() {
        return this.minEnergy;
    }

    private int getMaxEnergy() {
        return this.maxEnergy;
    }

    public int getEnergy() {
        return this.energy;
    }

    private void setEnergy(int newEnergy) {
        if (newEnergy < this.getMinEnergy()) {
            this.energy = newEnergy;
            this.die();
        } else if (newEnergy > this.getMaxEnergy()) {
            this.energy = this.getMaxEnergy();
        } else {
            this.energy = newEnergy;
        }

    }

    private void die() {
        this.deadOrAlive = "dead";
    }

    public boolean isDead() {
        return this.deadOrAlive == "dead";
    }

    public String getDisplayMechanism() {
        return "color";
    }

    public String getSpecies() {
        return "Algae";
    }

    public String getColor() {
        int energy = this.getEnergy();
        if (energy < 0) {
            energy = 0;
        }

        if (energy > 255) {
            energy = 255;
        }

        String greenLevel = Integer.toHexString(energy);
        if (energy < 16) {
            greenLevel = "0" + greenLevel;
        }

        return "#00" + greenLevel + "00";
    }

    public int giveUpEnergy(int energyWanted) {
        int energy = this.getEnergy();
        if (energyWanted < 0) {
            return 0;
        } else {
            if (energyWanted < energy) {
                this.setEnergy(energy - energyWanted);
            } else {
                energyWanted = energy;
                this.setEnergy(this.getMinEnergy() - 1);
            }

            return energyWanted;
        }
    }

    public void liveALittle() {
        if (!this.isDead()) {
            ++this.age;
            int row = this.getRow();
            int column = this.getColumn();
            int sun = this.simulation.getSunlight(row, column);
            this.setEnergy((int)((double)sun * 0.5D) + this.getEnergy() - 1);
        }
    }
}
