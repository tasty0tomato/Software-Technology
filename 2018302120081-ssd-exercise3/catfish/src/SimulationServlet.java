import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(name = "SimulationServlet" ,urlPatterns = "/SimulationServlet")
public class SimulationServlet extends HttpServlet {
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        doPost(httpServletRequest,httpServletResponse);
    }
    public void doPost(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws ServletException,IOException{
        HttpSession httpSession=httpServletRequest.getSession();
        httpServletResponse.setContentType("text/html");
        PrintWriter printWriter=httpServletResponse.getWriter();
        String str=httpServletRequest.getParameter("Submit");
        if((str!=null)&&(str.equals("Create a fish"))){
            Simulation simulation=new Simulation(1,10);
            Catfish catfish=new Catfish();
            simulation.addCatfish(catfish);
            printWriter.print(SimulationView.getHtml(simulation));
            httpSession.setAttribute("simulation", simulation);
            return;
        }
        Simulation simulation = (Simulation)httpSession.getAttribute("simulation");
        Catfish catfish = simulation.getCatfish(0);
        if (str != null && str.equals("right1"))
            catfish.swimRight();
        printWriter.print(SimulationView.getHtml(simulation));
    }
}
