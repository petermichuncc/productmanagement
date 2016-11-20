package edu.uncc.nbad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import edu.uncc.nbad.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;


public class ProductTable {
 static String url = "jdbc:mysql://localhost:3306/store";
    static String username = "user";
    static String password = "123";

    static Connection connection = null;
    static PreparedStatement selectProduct = null;
    static ResultSet resultset = null;

	
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

    }

    public static List<Product> selectProducts() {
		throw new NotImplementedException(); // remove this line and implement the logic
    /*
       I need to make a list of products         
                
                
                */
    
    
    
    }

    public static Product selectProduct(String productCode) {
        
         try
    {
                   String url = "jdbc:mysql://localhost:3306/store";
    Connection conn = DriverManager.getConnection(url, "user", "123");
      
      // our SQL SELECT query. 
      
      // if you only need a few columns, specify them by name instead of using "*"
      String query = "SELECT * FROM product WHERE id = "+id;

      // create the java statement
      Statement st = conn.createStatement();
      
      // execute the query, and get a java resultset
      ResultSet rs = st.executeQuery(query);
      Product product = new Product();     
       while (rs.next())
     {
           
         /*
         So here I need to set the object that is holding the product data
         
         
         
         */
          product.setId(rs.getInt("id"));
   product.setCode(rs.getString("code"));
  
   product.setPrice(rs.getInt("price"));
   product.setDescription(rs.getString("description"));
   
    //return user;
     System.out.print("ID: " + rs.getString("id"));
         
    
      // Product.add(product);
        
        // print the results
        
     }
      st.close();
      return product;
      
    }catch(Exception e) {
        return null;
    }
        
        
        
        
        
        
        
        
        
        
        
        
        
		//throw new NotImplementedException(); // remove this line and implement the logic
    }

    public static boolean exists(String productCode) {
		throw new NotImplementedException(); // remove this line and implement the logic
    }    
    
    private static void saveProducts(List<Product> products) {
		throw new NotImplementedException(); // remove this line and implement the logic
    }

    public static void insertProduct(Product product) {
		throw new NotImplementedException(); // remove this line and implement the logic
    }

    public static void updateProduct(Product product) {
		throw new NotImplementedException(); // remove this line and implement the logic
    }

    public static void deleteProduct(Product product) {
		throw new NotImplementedException(); // remove this line and implement the logic
    }    
}
