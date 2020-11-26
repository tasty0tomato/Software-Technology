public class HtmlTable {
    private boolean heading;

    private String tableBody;

    public HtmlTable() {
        this.tableBody = "<TABLE>";
        this.heading = false;
    }

    public HtmlTable(int paramInt) {
        this.tableBody = "<TABLE cellpadding=" + paramInt + ">";
        this.heading = false;
    }

    public void addHeader(String paramString) {
        this.tableBody += "<THEAD bgcolor='" + paramString + "'>";
        this.heading = true;
    }

    public void startRow() {
        this.tableBody += "<TR>";
    }

    public void endRow() {
        this.tableBody += "</TR>";
    }

    public void addCell(String paramString) {
        this.tableBody += "<TD>";
        this.tableBody += paramString;
        this.tableBody += "</TD>";
    }

    public void addCell(String paramString1, String paramString2) {
        this.tableBody += "<TD bgcolor='" + paramString1 + "'>";
        this.tableBody += paramString2;
        this.tableBody += "</TD>";
    }

    public String buildHtml() {
        if (this.heading)
            this.tableBody += "</THEAD>";
        this.tableBody += "</TABLE>";
        return this.tableBody;
    }
}
