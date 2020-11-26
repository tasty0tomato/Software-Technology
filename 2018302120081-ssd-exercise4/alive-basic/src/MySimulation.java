import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "MySimulation", urlPatterns = "/MySimulation")
public class MySimulation extends HttpServlet{
    public void doPost(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws ServletException, IOException {
        int i = 0;
        paramHttpServletResponse.setContentType("text/html");
        PrintWriter printWriter = paramHttpServletResponse.getWriter();
        String str = paramHttpServletRequest.getParameter("numTimeBlocks");
        if (str != null) {
            i = Integer.parseInt(str);
            System.out.println("Blocks:" + i);
        }
        Map map = paramHttpServletRequest.getParameterMap();
        Set set = map.keySet();
        Iterator iterator = set.iterator();
        boolean[][] arrayOfBoolean = new boolean[10][10];
        while (iterator.hasNext()) {
            String str1 = (String)iterator.next();
            if (str1.equals("Save") || str1.equals("numTimeBlocks"))
                continue;
            String[] arrayOfString = (String[])map.get(str1);
            for (byte b1 = 0; b1 < 10; b1++) {
                for (byte b = 0; b < 10; b++)
                    arrayOfBoolean[b1][b] = false;
            }
            for (byte b2 = 0; b2 < arrayOfString.length; b2++) {
                int j = Integer.parseInt(arrayOfString[b2]);
                int k = j / 100;
                int m = j - 100 * k;
                arrayOfBoolean[k][m] = true;
            }
            printWriter.println("<!DOCTYPE HTML>");
            printWriter.println("<HTML>");
            printWriter.println("<HEAD><TITLE>Basic Fish Simulation</TITLE></HEAD>");
            printWriter.println("<BODY>");
            printWriter.println("<FORM name='initialWorld' id='initialWorld' method='post' action='SimulationServlet'>");
            printWriter.println("<TABLE width='100%' border='1' cellpadding='1'>");
            for (byte b3 = 0; b3 < 10; b3++) {
                printWriter.println("<TR>");
                for (byte b = 0; b < 10; b++) {
                    if (arrayOfBoolean[b3][b] == true) {
                        printWriter.println("<TD><INPUT type='checkbox' name='catfish' value='" + b3 + "0" + b + "' checked>catfish<BR></TD>");
                    } else {
                        printWriter.println("<TD><INPUT type='checkbox' name='catfish' value='" + b3 + "0" + b + "'>catfish<BR></TD>");
                    }
                }
                printWriter.println("</TR>");
            }
            printWriter.println("</TABLE>");
            printWriter.println("<P>Number of time blocks to simulate:<INPUT name='numTimeBlocks' type='text' value='" + i + "' size='4' maxlength='4'></P>");
            printWriter.println("<input name='Submit' type='submit' value='Start simulation'>");
            printWriter.println("</FORM>");
            printWriter.println("</BODY>");
            printWriter.println("</HTML>");
        }
    }
}
