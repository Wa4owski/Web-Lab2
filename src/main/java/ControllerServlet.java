import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        if(req.getParameter("x") != null && req.getParameter("y") != null && req.getParameter("r") != null) {
            dispatcher = req.getRequestDispatcher("area");
        }
        else{
            dispatcher = req.getRequestDispatcher("index.jsp");
        }
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.getWriter().write("POST!");
        doPost(req, resp);
    }
}
