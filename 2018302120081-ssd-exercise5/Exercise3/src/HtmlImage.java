//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

public class HtmlImage {
    private String imageName;
    private String alternateText;

    public HtmlImage(String iName, String aText) {
        this.imageName = iName;
        this.alternateText = aText;
    }

    public String buildHtml() {
        return "<IMG src='" + this.imageName + "' alt='" + this.alternateText + "'>";
    }
}
