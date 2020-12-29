//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

public class HtmlTable {
    private boolean heading;
    private String tableBody;

    public HtmlTable() {
        this.tableBody = "<TABLE>";
        this.heading = false;
    }

    public HtmlTable(int cp) {
        this.tableBody = "<TABLE cellpadding=" + cp + ">";
        this.heading = false;
    }

    public void addHeader(String colorValue) {
        this.tableBody = this.tableBody + "<THEAD bgcolor='" + colorValue + "'>";
        this.heading = true;
    }

    public void startRow() {
        this.tableBody = this.tableBody + "<TR>";
    }

    public void endRow() {
        this.tableBody = this.tableBody + "</TR>";
    }

    public void addCell(String cellData) {
        this.tableBody = this.tableBody + "<TD>";
        this.tableBody = this.tableBody + cellData;
        this.tableBody = this.tableBody + "</TD>";
    }

    public void addCell(String cellColor, String cellData) {
        this.tableBody = this.tableBody + "<TD bgcolor='" + cellColor + "'>";
        this.tableBody = this.tableBody + cellData;
        this.tableBody = this.tableBody + "</TD>";
    }

    public String buildHtml() {
        if (this.heading) {
            this.tableBody = this.tableBody + "</THEAD>";
        }

        this.tableBody = this.tableBody + "</TABLE>";
        return this.tableBody;
    }
}
