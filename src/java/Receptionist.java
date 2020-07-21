/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Receptionist")
public class Receptionist extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setContentType ("text/html; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        
        User user = new User(name,password);
        FileManager fm = new FileManager();
        fm.fileSave(user, name);
        
        try{
            writer.print("<h1 style='text-align: center'>Вы зарегистрированы</h1>");
            writer.print("<h3 style='text-align: center'>Ваше имя: "+name+"</h3>");
            writer.print("<h3 style='text-align: center'>Ваш пароль: "+password+"</h3>");
            writer.print("<a href='index.html' style='text-align: center'>страница входа</a>");
        }finally{
            writer.close();
        }
    }

}
