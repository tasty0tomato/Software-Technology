//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

public abstract class LivingBeing {
    public LivingBeing() {
    }

    public static void createLivingBeing(Simulation sim, String species, String value) {
        int rowAndCol = Integer.parseInt(value);
        int row = rowAndCol / 100;
        int column = rowAndCol - 100 * row;
        if (species.equals("algae")) {
            sim.addLivingBeing(new AlgaeColony(row, column, sim));
        }

        if (species.equals("catfish")) {
            sim.addLivingBeing(new Catfish(row, column, sim));
        }

        if (species.equals("crocodile")) {
            sim.addLivingBeing(new Crocodile(row, column, sim));
        }

    }

    public abstract int getRow();

    public abstract int getColumn();

    public abstract int getAge();

    public abstract String getColor();

    public abstract String getName();

    public abstract String getImage();

    public abstract String getDisplayMechanism();

    public abstract String getSpecies();

    public abstract int getEnergy();

    public abstract boolean isDead();

    public abstract void liveALittle();
}
