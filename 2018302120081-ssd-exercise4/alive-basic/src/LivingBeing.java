public abstract class LivingBeing {
    public static void createLivingBeing(Simulation paramSimulation, String paramString1, String paramString2) {
        int i = Integer.parseInt(paramString2);
        int j = i / 100;
        int k = i - 100 * j;
        if (paramString1.equals("catfish"))
            paramSimulation.addLivingBeing(new Catfish(j, k, paramSimulation));
    }

    public abstract int getRow();

    public abstract int getColumn();

    public abstract int getAge();

    public abstract String getImage();

    public abstract int getEnergy();

    public abstract boolean isDead();

    public abstract void liveALittle();
}
