//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.Vector;

public class SimulationView {
    private static final int DEBUG = 50;

    public SimulationView() {
    }

    public static String getHtml(Simulation sim) {
        HtmlPage simulationPage = new HtmlPage();
        simulationPage.setTitle("Fish Simulation");
        simulationPage.addText("<H2>Fish Simulation</H2>");
        HtmlTable lake = new HtmlTable(10);
        HtmlImage blankImage = new HtmlImage("resource/img/blank.gif", "Maybe algae. No other life.");
        int numRows = sim.getLastRow() - sim.getFirstRow() + 1;
        Vector beings = sim.getNeighbors(1 + numRows / 2, 1 + numRows / 2, numRows);
        System.out.println("----------------------");
        System.out.println("Simulation at Time " + sim.getTime());

        int beingIndex;
        for(beingIndex = 0; beingIndex < beings.size(); ++beingIndex) {
            LivingBeing being = (LivingBeing)beings.get(beingIndex);
            System.out.println(being.getName() + " energyLevel = " + being.getEnergy());
        }

        for(int row = sim.getFirstRow(); row <= sim.getLastRow(); ++row) {
            lake.startRow();

            for(int column = sim.getFirstColumn(); column <= sim.getLastColumn(); ++column) {
                String cellBgColor = "aqua";
                HtmlImage cellImage = blankImage;
                Vector cohabitants = sim.getNeighbors(row, column, 0);

                for(beingIndex = 0; beingIndex < cohabitants.size(); ++beingIndex) {
                    LivingBeing being = (LivingBeing)cohabitants.get(beingIndex);
                    if (being.getDisplayMechanism() == "color") {
                        cellBgColor = being.getColor();
                    } else if (being.getDisplayMechanism() == "image") {
                        cellImage = new HtmlImage(being.getImage(), being.getName());
                    }
                }

                lake.addCell(cellBgColor, cellImage.buildHtml());
            }

            lake.endRow();
        }

        simulationPage.addText(lake.buildHtml());
        return simulationPage.buildHtml();
    }
}
