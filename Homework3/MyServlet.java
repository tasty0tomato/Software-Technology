import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/MyServlet")  //Please complete
public class MyServlet extends HttpServlet {
    /**
     * The doGet method of the servlet. <br>
     *
     * This method is called when a form has its tag value method equals to get.
     *
     * @param request the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException if an error occurred
     */
    public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        doPost(request,response);
    }
    /**
     * The doPost method of the servlet. <br>
     *
     * This method is called when a form has its tag value method equals to post.
     *
     * @param request the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException if an error occurred
     */
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        PrintWriter out=response.getWriter();
        //Please Complete the Code Here
        //this part you need get info from html form(~2lines)
        
        String name=request.getParameter("username");
        String password=request.getParameter("password");
        String sex=request.getParameter("sex");
        String habits=request.getParameter("habits");
        String photo=habits+".png";
        //END

        out.println("<HTML>");
        out.println("<body>");
        out.println("<h1>My Info</h1>");
        //Please complete the code here
        //this part you need print info (~2-4lines)
        out.println("<br>");
        out.println("<br>");
        out.println("Username:"+name);
        out.println("<br>");
        out.println("<br>");
        out.println("Password:"+password);
        out.println("<br>");
        out.println("<br>");
        out.println("Sex:"+sex);
        out.println("<br>");
        out.println("<br>");
        out.println("habits:"+habits);
        out.println("<br>");
        out.println("<img src='"+photo+"' />");

        //END
        out.println("<br>");
        
        out.println("</body>");
        out.println("</html>");
        out.flush();
        out.close();
    }
}
