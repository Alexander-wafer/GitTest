/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Door")
public class Door extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType ("text/html; charset=UTF-8");
        String path = request.getContextPath();
        PrintWriter writer = response.getWriter();
        
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        FileManager fm = new FileManager();
        
        
        try {
            User user = fm.fileLoad(name+".bin");
            if(!user.getPassword().equals(password)){
                response.sendRedirect(path+"/index.html");
            }else{
                Cookie cookie = new Cookie("user", name);
                cookie.setMaxAge(-1);
                response.addCookie(cookie);
                response.sendRedirect("GameServer");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Door.class.getName()).log(Level.SEVERE, null, ex);
        }catch(FileNotFoundException fne){
            response.sendRedirect(path+"/index.html");
        }
        
        
    }
}
