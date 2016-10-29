/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uncc.nbad;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author madho
 */
public class MembershipServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MembershipServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MembershipServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Get action parameter
        String action = request.getParameter("action");

        //Make a big decision
        switch (action) {
            case "login":
                getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
                break;
            case "signup":
                getServletContext().getRequestDispatcher("/signup.jsp").forward(request, response);
                break;
            case "logout":
                HttpSession session = request.getSession();
                Cookie[] cookies = request.getCookies();
                for (Cookie cookie : cookies) {
                    System.out.println("Deleting cookie: " + cookie.getName());
                    cookie.setMaxAge(0); //delete the cookie
                    cookie.setPath("/"); //allow the download application to access it
                    response.addCookie(cookie);
                }
                session.invalidate();
                getServletContext().getRequestDispatcher("/index.jsp").forward(request, response); //forward back to index          
                break;
            default:
                break;
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Get the action and session
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
 String message; 
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MembershipControllerServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p>Membership Servlet</p>");

            //********** LOGIN **********
            //If action is login, login the user
            if ("login".equals(action)) {
                //Get the parameters
                String username = request.getParameter("username");
                String password = request.getParameter("password");

                //Try to match inputted email to saved user in the text file
                ServletContext sc = getServletContext();
                String path = sc.getRealPath("/WEB-INF/users.txt");
                User user = UserIO.getUser(username, path);

                //Validate the login
                if (username.isEmpty()) {
                    //Didn't provide a username
                    out.println("<p>Username is invalid.</p>");
                } else if (!username.equals(user.getEmail())) {
                    //user didn't match any user in the text file
                    out.println("User does not exist");
                } else if (password == null || !password.equals(user.getPassword())) {
                    //password didn't match
                    out.println("<p>Incorrect Password.</p>");
                } else {
                    //valid user, so put user object in session
                    session.setAttribute("user", user);
                    out.println("<p>Successfully logged in " + user.getFirstName() + " " + user.getLastName() + "</p>");
                    out.println("<p><a href=\"index.jsp\">Return to Index</a></p>");
                }
                //********** SIGNUP **********
                //If action is signup, signup the user
            } else if ("signup".equals(action)) {
                //Get the parameters
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                    String userName = request.getParameter("username");
              String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
           java.util.regex.Matcher m = p.matcher(email);

                    if ( firstName.isEmpty() ||userName.isEmpty()|| lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                
                message = "Please fill out all of the fields<br>";
                message += "You are missing: ";
                if( firstName.isEmpty()){
                    message += "first name, ";
                }
                
                if(lastName.isEmpty()){
                    message += "last name, ";
                }
                if(userName.isEmpty()){
                    message += "username, ";
                }
                if(email.isEmpty()){
                    message += "email, ";
                }
                
               
                
                if(password.isEmpty()){
                    message += "password ";
                }
                
               out.println("<p> " + message + "</p>");
                out.println("<a href=\"signup.jsp\">Sign up</a>");
        } 
                    else if (m.matches()==false )
                    {
                       
                        message = "the e-mail was incorrect ";
                          out.println("<p> " + message + "</p>");
                        
                    }
                    else if (password.length()<=8)
                    {
                         message = "the password must be greater than 8 characters";
                           out.println("<p> " + message + "</p>");
                        
                    }
                     else if (password.length()<=8 &&m.matches()==false)
                    {
                         message = "the password must be greater than 8 characters and the e-mail must be correct";
                           out.println("<p> " + message + "</p>");
                        
                    }
                    else if (m.matches()==true && password.length()>8) {
         //Create new user object and put the information in it
         
   // is valid, do something

         
                User user = new User();
                user.setEmail(email);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setPassword(password);

                //Write user object to the text file
                ServletContext sc = getServletContext();
                String path = sc.getRealPath("/WEB-INF/users.txt");
                UserIO.addRecord(user, path);

                //Put user object into session attribute
                session.setAttribute("user", user);

                //Add a cookie that stores the user's email as a cookie
                Cookie c1 = new Cookie("emailCookie", email);
                c1.setMaxAge(60 * 60 * 24 * 365 * 2); // set age to 2 years
                c1.setPath("/");                      // allow entire app to access it
                response.addCookie(c1);

                //Add a cookie that stores the user's first name as a cookie
                Cookie c2 = new Cookie("firstNameCookie", firstName);
                c2.setMaxAge(60 * 60 * 24 * 365 * 2); // set age to 2 years
                response.addCookie(c2);

                out.println("<p>Successfully created new user " + user.getFirstName() + "</p>");
                out.println("<p><a href=\"index.jsp\">Return to Index</a></p>");
            
           
           
     //      request.getRequestDispatcher("/profile.jsp").include (request,response);
        }
                
                
               
               

            }
            out.println("</body>");
            out.println("</html>");
        }

    }

    private boolean isUserRegistrationInfoValid(HttpServletRequest request, HttpServletResponse response) throws IOException {

        boolean badFN = false;
        boolean badLN = false;
        boolean badEmail = false;
        boolean badPass = false;

        String password = request.getParameter("password");

        //Validate first name
        String firstName = request.getParameter("firstName");
        if (firstName == null) {
            badFN = true;
        } else if (firstName.length() > 60) {
            badFN = true;
        }

        //Validate last name
        String lastName = request.getParameter("lastName");
        if (lastName == null) {
            badLN = true;
        } else if (lastName.length() > 60) {
            badLN = true;
        }

        //Validate email
        String email = request.getParameter("e-mail");
        if (email == null) {
            badEmail = true;
        } else if (email.length() > 60) {
            badEmail = true;
        }

        //Validate password
        //If at least one input was invalid, print out the error
        if (badFN || badLN || badEmail || badPass) {

            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet ProductManagementServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Product Info Validation at Servlet ProductManagementServlet at " + request.getContextPath() + "</h1>");

                if (badFN) {
                    out.println("<p>Product Code was invalid. Product code must not be blank and cannot exceed 40 characters.</p>");
                }
                if (badLN) {
                    out.println("<p>Description was invalid. Description must not be blank and cannot exceed 160 characters.</p>");
                }
                if (badEmail) {
                    out.println("<p>Price was invalid. Price must be a valid number greater than $0.</p>");
                }
                if (badPass) {
                    out.println("<p>Price was invalid. Price must be a valid number greater than $0.</p>");
                }
                out.println("</body>");
                out.println("</html>");
            }
            return false;
        }
        return true;
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
