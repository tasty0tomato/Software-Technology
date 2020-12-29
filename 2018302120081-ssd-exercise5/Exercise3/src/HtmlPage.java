//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

public class HtmlPage {
    private String pageTitle = "";
    private String pageBody = "";
    private String bgImage = "";
    private String bgColor = "";

    HtmlPage() {
    }

    private String getHeader() {
        return "<HTML><HEAD><TITLE>" + this.pageTitle + "</TITLE></HEAD>";
    }

    private String getFooter() {
        return "</HTML>";
    }

    public void setTitle(String pTitle) {
        this.pageTitle = pTitle;
    }

    public void setBackgroundImage(String imageName) {
        this.bgImage = imageName;
    }

    public void setBackgroundColor(String colorValue) {
        this.bgColor = colorValue;
    }

    private String getBody() {
        return "<BODY background='" + this.bgImage + "' bgcolor='" + this.bgColor + "'>" + this.pageBody + "</BODY>";
    }

    private String getDoctype() {
        return "<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>";
    }

    public void addText(String textString) {
        this.pageBody = this.pageBody + textString;
    }

    public String buildHtml() {
        String pageString = this.getDoctype() + this.getHeader() + this.getBody() + this.getFooter();
        return pageString;
    }
}
