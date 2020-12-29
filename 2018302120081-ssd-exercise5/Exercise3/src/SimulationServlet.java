//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

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
public class SimulationServlet extends HttpServlet {
    private static final int DEBUG = 10;

    public SimulationServlet() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int totalTimeBlocksToSimulate = 0;
        HttpSession session = request.getSession();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String numTimeBlocks = request.getParameter("numTimeBlocks");
        Simulation sim;
        if (numTimeBlocks == null) {
            sim = (Simulation)session.getAttribute("simulation");
            String totalTimeBlocksInSession = (String)session.getAttribute("totalTimeBlocksToSimulate");
            if (totalTimeBlocksInSession != null) {
                totalTimeBlocksToSimulate = Integer.parseInt(totalTimeBlocksInSession);
            }

            if (sim.getTime() < totalTimeBlocksToSimulate - 1) {
                response.setHeader("Refresh", "1");
            }

            sim.simulateATimeBlock();
            out.print(SimulationView.getHtml(sim));
        } else {
            totalTimeBlocksToSimulate = Integer.parseInt(numTimeBlocks);
            sim = new Simulation(0, 0, 9, 9);
            Map allParams = request.getParameterMap();
            Set allKeys = allParams.keySet();
            Iterator it = allKeys.iterator();

            while(true) {
                String key;
                do {
                    do {
                        if (!it.hasNext()) {
                            if (totalTimeBlocksToSimulate > 0) {
                                response.setHeader("Refresh", "1");
                            }

                            out.print(SimulationView.getHtml(sim));
                            session.setAttribute("simulation", sim);
                            session.setAttribute("totalTimeBlocksToSimulate", numTimeBlocks);
                            return;
                        }

                        key = (String)it.next();
                    } while(key.equals("Submit"));
                } while(key.equals("numTimeBlocks"));

                String[] value = (String[])allParams.get(key);

                for(int i = 0; i < value.length; ++i) {
                    LivingBeing.createLivingBeing(sim, key, value[i]);
                }
            }
        }
    }
}
