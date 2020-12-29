//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.Random;
import java.util.Vector;

public class Simulation {
    private static final int MAX_SUNLIGHT = 10;
    public static final String IMAGE = "image";
    public static final String COLOR = "color";
    private final int firstRow;
    private final int firstColumn;
    private final int lastRow;
    private final int lastColumn;
    private Random rand;
    private Vector livingBeings;
    private int time = 0;

    public Simulation(int initialFirstRow, int initialFirstColumn, int initialLastRow, int initialLastColumn) {
        this.firstRow = initialFirstRow;
        this.firstColumn = initialFirstColumn;
        this.lastRow = initialLastRow;
        this.lastColumn = initialLastColumn;
        this.livingBeings = new Vector(50);
        this.rand = new Random(7L);
        this.time = 0;
    }

    public int getFirstRow() {
        return this.firstRow;
    }

    public int getFirstColumn() {
        return this.firstColumn;
    }

    public int getLastRow() {
        return this.lastRow;
    }

    public int getLastColumn() {
        return this.lastColumn;
    }

    public int getSunlight(int row, int column) {
        return row >= this.getFirstRow() && row <= this.getLastRow() && column >= this.getFirstColumn() && column <= this.getLastColumn() ? this.rand.nextInt(10) : 0;
    }

    public Random getRand() {
        return this.rand;
    }

    public int getTime() {
        return this.time;
    }

    public void addLivingBeing(LivingBeing newLivingBeing) {
        if (newLivingBeing != null) {
            if (!this.livingBeings.contains(newLivingBeing)) {
                this.livingBeings.add(newLivingBeing);
            }
        }
    }

    public Vector getNeighbors(int row, int column, int distance) {
        Vector neighbors = new Vector();

        for(int beingIndex = 0; beingIndex < this.livingBeings.size(); ++beingIndex) {
            LivingBeing being = (LivingBeing)this.livingBeings.get(beingIndex);
            if (being != null && being.getRow() <= row + distance && being.getRow() >= row - distance && being.getColumn() <= column + distance && being.getColumn() >= column - distance) {
                neighbors.add(being);
            }
        }

        return neighbors;
    }

    public void simulateATimeBlock() {
        ++this.time;

        int beingIndex;
        LivingBeing being;
        for(beingIndex = 0; beingIndex < this.livingBeings.size(); ++beingIndex) {
            being = (LivingBeing)this.livingBeings.get(beingIndex);
            if (being != null && !being.isDead()) {
                being.liveALittle();
            }
        }

        beingIndex = 0;

        while(beingIndex < this.livingBeings.size()) {
            being = (LivingBeing)this.livingBeings.get(beingIndex);
            if (being.isDead()) {
                this.livingBeings.remove(being);
            } else {
                ++beingIndex;
            }
        }

    }
}
