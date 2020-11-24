public class HtmlImage {
    private String imageName;

    private String alternateText;

    public HtmlImage(String paramString1, String paramString2) {
        this.imageName = paramString1;
        this.alternateText = paramString2;
    }

    public String buildHtml() {
        return "<IMG src='" + this.imageName + "' alt='" + this.alternateText + "'>";
    }
}
