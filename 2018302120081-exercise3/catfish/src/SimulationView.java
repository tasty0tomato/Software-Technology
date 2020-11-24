import java.util.Vector;
public class SimulationView {
    public static String getHtml(Simulation simulation) {
        HtmlPage htmlPage = new HtmlPage();
        htmlPage.setTitle("Move Catfish Right");
        htmlPage.addText("<H2> Move Catfish Right</H2>");
        HtmlImage htmlImage1 = new HtmlImage("resource/img/right-arrow.gif", "right");//the image here is right-arrow.gif
        String str = getImageButton("Submit", "right1", htmlImage1);
        HtmlTable htmlTable1 = new HtmlTable();
        htmlTable1.startRow();
        htmlTable1.addCell(str);
        htmlTable1.endRow();
        HtmlTable htmlTable2 = new HtmlTable(simulation.getMaxColumn());
        HtmlImage htmlImage2 = new HtmlImage("resource/img/blank.gif", "No fish.");//the image here is blank.gif
        for (byte b = 1; b <= simulation.getMaxRow(); b++) {
            htmlTable2.startRow();
            for (byte b1 = 1; b1 <= simulation.getMaxColumn(); b1++) {
                String str1 = "aqua";
                HtmlImage htmlImage = htmlImage2;
                Vector vector = simulation.getNeighbors(b, b1, 0);
                byte b2 = 0;
                for (; b2 < vector.size();b2++) {
                    Catfish catfish = (Catfish)vector.get(b2);
                    htmlImage = new HtmlImage(catfish.getImage(), "catfish" + b2);
                }
                htmlTable2.addCell(str1, htmlImage.buildHtml());
            }
            htmlTable2.endRow();
        }
        htmlPage.addText("<FORM name='Move fish right' method='post' action='SimulationServlet'>");
        htmlPage.addText("<TABLE>");
        htmlPage.addText("<TR><TD>Move fish:</TD><TD>");
        htmlPage.addText(htmlTable1.buildHtml());
        htmlPage.addText("</TD></TR></TABLE></FORM>");
        htmlPage.addText(htmlTable2.buildHtml());
        return htmlPage.buildHtml();
    }
    private static String getImageButton(String paramString1, String paramString2, HtmlImage paramHtmlImage) {
        HtmlAnchor htmlAnchor = new HtmlAnchor(paramHtmlImage.buildHtml(), "SimulationServlet?" + paramString1 + "=" + paramString2);
        return htmlAnchor.buildHtml();
    }
}
