/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/GameServer")
public class GameServer extends HttpServlet {
        
       String number;//mysterious number
       ArrayList<String> track = new ArrayList<String>();//history
       int attempts;//attempts
       Cookie cookieUser;//Which player is playing
       Cookie cookieAttempts;//attempts are saved here
       
        
        
       protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        track.clear();
        number = "";
        attempts = 0;
        
       
        
        Set<Integer> generated = new HashSet<Integer>();
        Random r = new Random();
        while (generated.size() < 4) {
        generated.add(r.nextInt(9) + 1);
        }
        for(Integer item:generated){
            number += item;
        }
        track.add(number);
        
        Cookie[] cookies = request.getCookies();
        String cookieName = "user";
        if(cookies !=null) {
            for(Cookie c: cookies) {
                if(cookieName.equals(c.getName())) {
                    cookieUser = c;
                    break;
                }
            }
        }
        track.add("играет - "+cookieUser.getValue());
        request.setAttribute("number", number);
        request.setAttribute("track", track);
        getServletContext().getRequestDispatcher("/Game.jsp").forward(request, response);
    }
       
       
       protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, FileNotFoundException{
        response.setContentType ("text/html; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        String reply = request.getParameter("reply");
        
        int bull = 0;
        int cow  = 0;
        attempts++;
        
        Cookie cookieAttempts = new Cookie("attempts", Integer.toString(attempts));
        response.addCookie(cookieAttempts);
        cookieAttempts.setMaxAge(-1);
        
        
        Cookie[] cookies = request.getCookies();
        String cookieName = "attempts";
        if(cookies !=null) {
            for(Cookie c: cookies) {
                if(cookieName.equals(c.getName())) {
                    cookieAttempts = c;
                    break;
                }
            }
        }else{
            response.sendRedirect("Door");
        }
        
        String[] replyArr = reply.split("");
        String[] numberArr = number.split("");
        
        for(int i = 0;i<replyArr.length;i++){
            if (replyArr[i].equals(numberArr[i])) {
                bull++;
            }
            for (int j = 0; j < replyArr.length; j++) {
                if (replyArr[i].equals(numberArr[j]) && i != j) {
                    cow++;
                }
            }
        }
           
           if (bull==4) {
            try {
                track.add(reply+"=Б"+bull+"|К"+cow);
                FileManager fm = new FileManager();
                User user = fm.fileLoad(cookieUser.getValue()+".bin");
                user.setAttempts(Integer.parseInt(cookieAttempts.getValue()));
                fm.fileSave(user, cookieUser.getValue());
                writer.print("<h1>Победа!</h1>");
                writer.print("<h2>среднее число попыток до угадывания числа:"+user.getAttempts()/2+"</h2>");
                writer.print("<a href='index.html'>главная</a><br>");
                writer.print("<a href='GameServer'>занова</a>");
                writer.print("<h3>история</h3>");
                for (int i = 0; i < track.size(); i++) {
                    writer.print("<p>"+track.get(i)+"</p>");
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
            }
           }else{
                track.add(reply+"=Б"+bull+"|К"+cow);
                request.setAttribute("track", track);
                getServletContext().getRequestDispatcher("/Game.jsp").forward(request, response);
           }
        
       }
}