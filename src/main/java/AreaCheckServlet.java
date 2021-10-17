import custom.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@WebServlet("/area")
public class AreaCheckServlet extends HttpServlet {
    private double x, y, r;
    private boolean graphUse;

    boolean validateX(String reqStrX){
        Set <Double> xValues = new HashSet<>();
        for(double i = -5d; i <= 3d; i++)
            xValues.add(i);
        try{
            x = Double.parseDouble(reqStrX);
            return xValues.contains(x) || graphUse;
        } catch (NumberFormatException e){
            return false;
        }
    }

    boolean validateY(String reqStrY){
       try {
            y = Double.parseDouble(reqStrY);
            return -5d < y && y < 5d;
        } catch (NumberFormatException e){
            System.out.println(reqStrY);
            return false;
        }
    }

    boolean validateR(String reqStrR){
        Set <Double> rValues = new HashSet<>();
        for(double i = 1d; i <= 3d; i += 0.5)
            rValues.add(i);
        try{
            r = Double.parseDouble(reqStrR);
            return rValues.contains(r);
        } catch (NumberFormatException e){
            return false;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ArrayList <Result> results = (ArrayList <Result>) session.getAttribute("results");
        if(results == null)
            results = new ArrayList<>();

        //System.out.println(session.getId());
        long startTime = System.nanoTime();
        graphUse = Boolean.parseBoolean(req.getParameter("graphUse"));

        if(validateX(req.getParameter("x")) &&
        validateY(req.getParameter("y")) &&
        validateR(req.getParameter("r")) ){

            String currentTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
            double diff = (System.nanoTime() - startTime) / 1000000d;
            String executionTime = String.format("%.1f", diff) + " ms";

            Result result = new Result(x, y, r, currentTime, executionTime, belongsS());
            results.add(result);
            session.setAttribute("results", results);
            resp.getWriter().write(result.generateRow());
//            getServletContext()
//                    .getRequestDispatcher("/index.jsp")
//                    .forward(req, resp);


//            for(custom.Result result : results){
//                resp.getWriter().write(generateLine(result));
//                session.setAttribute("line", generateLine(result));
//            }
          //  session.setAttribute("results", results);
           //resp.getWriter().write("1 " + x + " " + y + " " + r + " " + currentTime + " " + executionTime + " " + (belongsS() ? " 1" : " 0"));

        }
        else{
            resp.sendError(HttpServletResponse.SC_BAD_GATEWAY);
        }

    }


    boolean belongsSector(){
        return (x >= 0) && (y <= 0) && (x*x+y*y <= r*r);
    }
    boolean belongsRectangle(){
        return (x >= 0) && (y >= 0) && (x <= r && y <= r/2);
    }
    boolean belongsTriangle(){
        return (x <= 0) && (y >= 0) && (y - x <= r/2);
    }
    boolean belongsS(){
        return belongsRectangle() || belongsSector() || belongsTriangle();
    }

}
