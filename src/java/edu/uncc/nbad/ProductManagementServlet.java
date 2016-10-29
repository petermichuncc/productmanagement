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
public class ProductManagementServlet extends HttpServlet {

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
            out.println("<title>Servlet ProductManagementServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductManagementServlet at " + request.getContextPath() + "</h1>");
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
        String url;

        //If user is not logged in, forward to the login page
        if (!isUserLoggedIn(request, response)) {
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }

        //User is logged in
        //Make a big decision
        switch (action) {
            case "displayProducts":
                //Call getProducts method to grab products from text file and put inside ArrayList of Products to put into session attribute
                getProducts(request, response);
                getServletContext().getRequestDispatcher("/products.jsp").forward(request, response);
                break;
            case "addProduct":
                //Just simple redirect to the blank newProduct.jsp
                getServletContext().getRequestDispatcher("/newProduct.jsp").forward(request, response);
                break;
            case "displayProduct":
                //Call getProduct method to grab product from text file and put inside request attribute
                getProduct(request, response);
                System.out.println("In case displayProudct Parameter action: " + request.getParameter("action"));
                System.out.println("In case displayProduct Parameter productCode: " + request.getParameter("productCode"));
                getServletContext().getRequestDispatcher("/product.jsp").forward(request, response);
                break;
            case "deleteProduct":
                //Call getProduct method to grab product from text file and put inside request attribute
                getProduct(request, response);
                getServletContext().getRequestDispatcher("/confirmDelete.jsp").forward(request, response);
                break;
            case "actuallyDelete":
                System.out.println("Requested to delete" + request.getParameter("productCode"));
                deleteProduct(request, response);
                getServletContext().getRequestDispatcher("/productManagement?action=displayProducts").forward(request, response);
                //response.sendRedirect("/productManagement?action=displayProducts");
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

        //Get action parameter
        String action = request.getParameter("action");

        //If user is not logged in, forward to the login page
        if (!isUserLoggedIn(request, response)) {
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }

        //User is logged in, proceed to updateProduct if appropriate action parameter
        switch (action) {
            case "updateProduct":
                //Check if product info is valid

                //Call updateProduct method to update the product in the text file
                updateProduct(request, response);
                //Redirect back to products page
                //getServletContext().getRequestDispatcher("/productManagement?action=displayProducts").forward(request, response);
                response.sendRedirect("/Project_Group_20_P2/productManagement?action=displayProducts");
                break;
            case "addProduct":
                //Call addProduct method to gett product to text file
                addProduct(request, response);
                //Redirect back to products page
                response.sendRedirect("/Project_Group_20_P2/productManagement?action=displayProducts");
                break;
            default:
                System.err.println("He's dead, Jim!");
                break;
        }
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

    private boolean isUserLoggedIn(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //Assume user is logged in
        boolean isLoggedIn = true;

        //Check session for usr object
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        //Check session cookie if no user in session attribute
        if (user == null) {
            Cookie cookies[] = request.getCookies();
            String emailAddress = CookieUtil.getCookieValue(cookies, "emailCookie");

            if (emailAddress == null) {
                //There was no cookie and no session user object, so user is not logged in
                System.out.println("There was no cookie and no session user object, so set url to login page");
                isLoggedIn = false;
            } else {
                //There was a cookie, so get the user object from it and let user proceed
                System.out.println("There was a cookie, so get the user object from it and let user proceed");
                ServletContext sc = getServletContext();
                String path = sc.getRealPath("/WEB-INF/users.txt");
                user = UserIO.getUser(emailAddress, path);
                session.setAttribute("user", user);
                isLoggedIn = true;
            }
        } else {
            System.out.println("User attribute was not null, so let user proceed");
            System.out.println("User's email: " + user.getEmail());
            //user attribute was not null, so let user proceed
            isLoggedIn = true;
        }
        return isLoggedIn;
    }

    private void getProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //Get path to products text file
        String path = getServletContext().getRealPath("/WEB-INF/products.txt");

        //Get product code of product to be display from parameter
        String codeToDisplay = request.getParameter("productCode");
        System.out.println("In getProduct method, user wants to display code: " + codeToDisplay);

        //Get the product from ProductIO 
        Product productToDisplay = ProductIO.selectProduct(codeToDisplay, path);

        //Put the product into request attribute
        request.setAttribute("product", productToDisplay);

    }

    private void getProducts(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //Get session
        HttpSession session = request.getSession();

        //Get path to products text file
        String path = getServletContext().getRealPath("/WEB-INF/products.txt");

        //Put products into ArrayList
        ArrayList<Product> products = (ArrayList<Product>) ProductIO.selectProducts(path);

        //Put ArrayList of product objects into session attribute
        session.setAttribute("products", products);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) {

        //Get the product code of product to be deleted from parameter
        String codeToDelete = request.getParameter("productCode");
        System.out.println("In deleteProduct method, user wants to delete code: " + codeToDelete);

        //Get the session and file path of the products text file
        HttpSession session = request.getSession();
        String path = getServletContext().getRealPath("/WEB-INF/products.txt");

        //Get the product object we want to delete
        Product productToDelete = ProductIO.selectProduct(codeToDelete, path);

        //Tell ProductIO to delete that product
        ProductIO.deleteProduct(productToDelete, path);

    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //Validate Method will print out error if any of the input from parameters is invalid
        if (!isProductInfoValid(request, response)) {
            return;
        }
        //Get the product code of product to be updated from parameter
        String codeToUpdate = request.getParameter("productCode");
        System.out.println("In updateProduct method, user wants to update code: " + codeToUpdate);

        //Get file path of products text file
        String path = getServletContext().getRealPath("/WEB-INF/products.txt");

        //Get the product object we want to update
        Product productToUpdate = ProductIO.selectProduct(codeToUpdate, path);

        //Get new values from request parameters
        String code = productToUpdate.getCode(); //Can't change code, otherwise ProductIO won't know what to update
        //String code = request.getParameter("code");
        String desc = request.getParameter("desc");
        double price = Double.parseDouble(request.getParameter("price"));

        //Update the product's field's
        productToUpdate.setCode(code);
        productToUpdate.setDescription(desc);
        productToUpdate.setPrice(price);

        //Tell ProductIO to update that product
        ProductIO.updateProduct(productToUpdate, path);
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        //Validate Method will print out error if any of the input from parameters is invalid
        if (!isProductInfoValid(request, response)) {
            return;
        }
        //Get the the values to put into the new product
        String code = request.getParameter("code");
        String desc = request.getParameter("desc");
        double price = Double.parseDouble(request.getParameter("price"));

        //Create new product object and put in the values
        Product newProduct = new Product();
        newProduct.setCode(code);
        newProduct.setDescription(desc);
        newProduct.setPrice(price);

        //Get file path of products text file
        String path = getServletContext().getRealPath("/WEB-INF/products.txt");

        //Tell ProductIO to put new product into text file
        ProductIO.insertProduct(newProduct, path);
    }

    private boolean isProductInfoValid(HttpServletRequest request, HttpServletResponse response) throws IOException {

        boolean badCode = false;
        boolean badDesc = false;
        boolean badPrice = false;

        //Validate code
        String code = request.getParameter("code");
        if (code == null) {
            badCode = true;
        } else if (code.length() > 40) {
            badCode = true;
        }

        //Validate description
        String desc = request.getParameter("desc");
        if (desc == null) {
            badDesc = true;
        } else if (desc.length() > 160) {
            badDesc = true;
        }

        //Validate price
        double price = 0;
        try {
            price = Double.parseDouble(request.getParameter("price"));
        } catch (NumberFormatException nfe) {
            //Was not a valid double because parser threw exception
            badPrice = true;
        }

        if (!badPrice) {
            if (price <= 0) {
                //Was not a valid price
                badPrice = true;
            }
        }

        //If at least one input was invalid, print out the error
        if (badCode || badDesc || badPrice) {

            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet ProductManagementServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Product Info Validation at Servlet ProductManagementServlet at " + request.getContextPath() + "</h1>");

                if (badCode) {
                    out.println("<p>Product Code was invalid. Product code must not be blank and cannot exceed 40 characters.</p>");
                }
                if (badDesc) {
                    out.println("<p>Description was invalid. Description must not be blank and cannot exceed 160 characters.</p>");
                }
                if (badPrice) {
                    out.println("<p>Price was invalid. Price must be a valid number greater than $0.</p>");
                }
                out.println("Use your browser's back button to return.");
                out.println("</body>");
                out.println("</html>");
            }
            return false;
        }
        return true;
    }

}
