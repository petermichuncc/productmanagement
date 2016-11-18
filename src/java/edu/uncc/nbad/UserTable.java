package edu.uncc.nbad;

import java.io.*;
import java.util.*;
import edu.uncc.nbad.User;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class UserTable {
	
	//Static initializer, it runs when the class is intialized (it is executed once)
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

    }
    
    public static void addRecord(User user) throws IOException {
		throw new NotImplementedException(); // remove this line and implement the logic

    }

    public static User getUser(String emailAddress) throws IOException {
        throw new NotImplementedException(); // remove this line and implement the logic

    }

    public static ArrayList<User> getUsers() throws IOException {
		throw new NotImplementedException(); // remove this line and implement the logic
    }

    public static HashMap<String, User> getUsersMap() throws IOException {
		throw new NotImplementedException(); // remove this line and implement the logic
    }
}
