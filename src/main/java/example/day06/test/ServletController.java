package example.day06.test;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/day06/test")
public class ServletController extends HttpServlet {

    // 1. get
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("request Data : "+req.getParameter("data"));
        int result = Integer.parseInt(req.getParameter("data"));
        resp.getWriter().print(result+2);
    }
    // 2. post
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("request Data : "+req.getParameter("data"));
        int result = Integer.parseInt(req.getParameter("data"));
        resp.getWriter().print(result*2);
    }
    // 3. put
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("request Data : "+req.getParameter("data"));
        int result = Integer.parseInt(req.getParameter("data"));
        resp.getWriter().print(result/2);
    }
    // 4. delete
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("request Data : "+req.getParameter("data"));
        int result = Integer.parseInt(req.getParameter("data"));
        resp.getWriter().print(result%2);
    }

}   // class end