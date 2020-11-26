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
import javax.servlet.http.HttpSession;
@WebServlet(name = "SimulationServlet",urlPatterns = "/SimulationServlet")
public class SimulationServlet extends HttpServlet{
    private static final int DEBUG = 10;

    public void doGet(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws ServletException, IOException {
        doPost(paramHttpServletRequest, paramHttpServletResponse);
    }

    public void doPost(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws ServletException, IOException {
        int i = 0;
        HttpSession httpSession = paramHttpServletRequest.getSession();
        paramHttpServletResponse.setContentType("text/html");
        PrintWriter printWriter = paramHttpServletResponse.getWriter();
        String str1 = paramHttpServletRequest.getParameter("numTimeBlocks");
        if (str1 != null) {
            i = Integer.parseInt(str1);
            Simulation simulation1 = new Simulation(0, 0, 9, 9);
            Map map = paramHttpServletRequest.getParameterMap();
            Set set = map.keySet();
            Iterator iterator = set.iterator();
            while (iterator.hasNext()) {
                String str = (String)iterator.next();
                if (str.equals("Submit") || str.equals("numTimeBlocks"))
                    continue;
                String[] arrayOfString = (String[])map.get(str);
                for (byte b = 0; b < arrayOfString.length; b++)
                    LivingBeing.createLivingBeing(simulation1, str, arrayOfString[b]);
            }
            if (i > 0)
                paramHttpServletResponse.setHeader("Refresh", "1");
            printWriter.print(SimulationView.getHtml(simulation1));
            httpSession.setAttribute("simulation", simulation1);
            httpSession.setAttribute("totalTimeBlocksToSimulate", str1);
            return;
        }
        Simulation simulation = (Simulation)httpSession.getAttribute("simulation");
        String str2 = (String)httpSession.getAttribute("totalTimeBlocksToSimulate");
        if (str2 != null)
            i = Integer.parseInt(str2);
        if (simulation.getTime() < i - 1)
            paramHttpServletResponse.setHeader("Refresh", "1");
        simulation.simulateATimeBlock();
        printWriter.print(SimulationView.getHtml(simulation));
    }
}
