package controller;
import repository.UserRepo;
import entity.Users;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserServlet", value = {
        "/user-view",
        "/add",
        "/detail",
        "/update",
        "/delete",
        "/search"

})
public class UserServlet extends HttpServlet {

    private UserRepo repo = new UserRepo();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case "/user-view":{
                viewList(request,response);
                break;
            }
            case "/detail":{
                detail(request, response);
                break;
            }
            case "/delete":{
                deleteUser(request, response);
                break;
            }
            case "/search":{
//                userSearch(request, response);
                break;
            }
        }
    }



    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Users user = repo.getUser(id);
        request.setAttribute("user", user);
        List<Users> list = repo.getList();
        request.setAttribute("list", list);
        request.getRequestDispatcher("/view/listUser.jsp").forward(request,response);
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        Users user = repo.getUser(id);
        repo.deleteUser(user);
        response.sendRedirect("/user-view");

    }

    private void viewList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Users> list = repo.getList();
        request.setAttribute("list", list);
        request.getRequestDispatcher("/view/listUser.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri=request.getRequestURI();
        if(uri.equals("/add")){
            String id = request.getParameter("id");
            String password = request.getParameter("password");
            String fullname = request.getParameter("fullName");
            String email = request.getParameter("email");
            Boolean admin = Boolean.getBoolean(request.getParameter("admin")) ;
            Users users = new Users(id,password,fullname,email,admin);
            repo.add(users);
            response.sendRedirect("/user-view");
        }
        if(uri.equals("/update")){
            String id = request.getParameter("id");
            String password = request.getParameter("password");
            String fullname = request.getParameter("fullName");
            String email = request.getParameter("email");
            Boolean admin = Boolean.getBoolean(request.getParameter("admin")) ;
            Users users = new Users(id,password,fullname,email,admin);
            repo.update(users, id);
            response.sendRedirect("/user-view");
        }
        if(uri.equals("/search")){
                String search = request.getParameter("search");
                String loai = request.getParameter("loai");
                System.out.println(search);
                System.out.println(loai);
                List<Users> list = repo.getUserBySearch(loai, search);
            System.out.println(list.toString());
                request.setAttribute("list", list);
                request.getRequestDispatcher("/view/listUser.jsp").forward(request,response);
            }
        }
    }