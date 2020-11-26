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

    public Simulation(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        this.firstRow = paramInt1;
        this.firstColumn = paramInt2;
        this.lastRow = paramInt3;
        this.lastColumn = paramInt4;
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

    public int getSunlight(int paramInt1, int paramInt2) {
        if (paramInt1 >= getFirstRow() && paramInt1 <= getLastRow() && paramInt2 >= getFirstColumn() && paramInt2 <= getLastColumn())
            return this.rand.nextInt(10);
        return 0;
    }

    public Random getRand() {
        return this.rand;
    }

    public int getTime() {
        return this.time;
    }

    public void addLivingBeing(LivingBeing paramLivingBeing) {
        if (paramLivingBeing == null)
            return;
        if (this.livingBeings.contains(paramLivingBeing))
            return;
        this.livingBeings.add(paramLivingBeing);
    }

    public Vector getNeighbors(int paramInt1, int paramInt2, int paramInt3) {
        Vector vector = new Vector();
        for (byte b = 0; b < this.livingBeings.size(); b++) {
            LivingBeing livingBeing = (LivingBeing)this.livingBeings.get(b);
            if (livingBeing != null && livingBeing.getRow() <= paramInt1 + paramInt3 && livingBeing.getRow() >= paramInt1 - paramInt3 && livingBeing.getColumn() <= paramInt2 + paramInt3 && livingBeing.getColumn() >= paramInt2 - paramInt3)
                vector.add(livingBeing);
        }
        return vector;
    }

    public void simulateATimeBlock() {
        this.time++;
        byte b;
        for (b = 0; b < this.livingBeings.size(); b++) {
            LivingBeing livingBeing = (LivingBeing)this.livingBeings.get(b);
            if (livingBeing != null && !livingBeing.isDead())
                livingBeing.liveALittle();
        }
        b = 0;
        while (b < this.livingBeings.size()) {
            LivingBeing livingBeing = (LivingBeing)this.livingBeings.get(b);
            if (livingBeing.isDead()) {
                this.livingBeings.remove(livingBeing);
                continue;
            }
            b++;
        }
    }
}
