public class HtmlAnchor {
    private String displayText;

    private String targetLocation;

    public HtmlAnchor(String paramString1, String paramString2) {
        this.displayText = paramString1;
        this.targetLocation = paramString2;
    }

    public String buildHtml() {
        return "<A href='" + this.targetLocation + "'>" + this.displayText + "</A>";
    }
}
