import java.util.Vector;
public class SimulationView {
    public static String getHtml(Simulation paramSimulation) {
        HtmlPage htmlPage = new HtmlPage();
        htmlPage.setTitle("Fish Simulation");
        htmlPage.addText("<H2>Fish Simulation</H2>");
        HtmlTable htmlTable = new HtmlTable(10);
        HtmlImage htmlImage = new HtmlImage("resource/img/blank.gif", "Maybe algae. No other life.");//This image is blank.gif
        for (int i = paramSimulation.getFirstRow(); i <= paramSimulation.getLastRow(); i++) {
            htmlTable.startRow();
            for (int j = paramSimulation.getFirstColumn(); j <= paramSimulation.getLastColumn();
                 j++) {
                String str = "aqua";
                HtmlImage htmlImage1 = htmlImage;
                Vector vector = paramSimulation.getNeighbors(i, j, 0);
                byte b = 0;
                for (; b < vector.size();
                     b++) {
                    LivingBeing livingBeing = (LivingBeing)vector.get(b);
                    htmlImage1 = new HtmlImage(livingBeing.getImage(), "Catfish");
                }
                htmlTable.addCell(str, htmlImage1.buildHtml());
            }
            htmlTable.endRow();
        }
        htmlPage.addText(htmlTable.buildHtml());
        return htmlPage.buildHtml();
    }
}
