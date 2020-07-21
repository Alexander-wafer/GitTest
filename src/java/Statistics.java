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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Statistics")
public class Statistics extends HttpServlet {
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, FileNotFoundException {
        FileManager fm = new FileManager();
        response.setContentType ("text/html; charset=UTF-8");
        PrintWriter writer = response.getWriter();
    try {
        User[] users = fm.getAllFiles();
        for(int i = 0;i<users.length;i++){
            writer.print("<p>`"+users[i].getName()+"` среднее число попыток до угадывания числа "+users[i].getAttempts()/2+"</p>");
        }
        writer.print("<a href='index.html'>Главная</a>");
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
}
