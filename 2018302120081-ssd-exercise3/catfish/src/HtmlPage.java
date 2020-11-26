public class HtmlPage {
    private String pageTitle = "";

    private String pageBody = "";

    private String bgImage = "";

    private String bgColor = "";

    private String getHeader() {
        return "<HTML><HEAD><TITLE>" + this.pageTitle + "</TITLE></HEAD>";
    }

    private String getFooter() {
        return "</HTML>";
    }

    public void setTitle(String paramString) {
        this.pageTitle = paramString;
    }

    public void setBackgroundImage(String paramString) {
        this.bgImage = paramString;
    }

    public void setBackgroundColor(String paramString) {
        this.bgColor = paramString;
    }

    private String getBody() {
        return "<BODY background='" + this.bgImage + "' bgcolor='" + this.bgColor + "'>" + this.pageBody + "</BODY>";
    }

    private String getDoctype() {
        return "<!DOCTYPE HTML>";
    }

    public void addText(String paramString) {
        this.pageBody += paramString;
    }

    public String buildHtml() {
        return getDoctype() + getHeader() + getBody() + getFooter();
    }
}
