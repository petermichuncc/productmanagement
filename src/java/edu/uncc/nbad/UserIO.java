/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uncc.nbad;

import java.io.*;
import java.util.*;

/**
 *
 * @author Murach, ITIS 4166
 */
public class UserIO {

    public static void addRecord(User user, String filename) throws IOException {
        File file = new File(filename);
        PrintWriter out = new PrintWriter(
                new FileWriter(file, true));
        out.println(user.getEmail() + "|"
                + user.getFirstName() + "|"
                + user.getLastName() + "|"
                + user.getPassword());
        out.close();
    }

    public static User getUser(String emailAddress, String filename) throws IOException {
        
        User user = null;
        File file = new File(filename);
        
        if(file.exists())
        {
            BufferedReader in = new BufferedReader(
                    new FileReader(file));
            
            user = new User();

            String line = in.readLine();
            while (line != null) {
                StringTokenizer t = new StringTokenizer(line, "|");
                String email = t.nextToken();
                if (email.equalsIgnoreCase(emailAddress)) {
                    String firstName = t.nextToken();
                    String lastName = t.nextToken();
                    String password = t.nextToken();
                    user.setEmail(emailAddress);
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setPassword(password);
                }
                line = in.readLine();
            }
            in.close();
        }
        return user;
    }

    public static ArrayList<User> getUsers(String filename) throws IOException {
        ArrayList<User> users = new ArrayList<User>();
        File file = new File(filename);
        if(file.exists())
        {
            BufferedReader in = new BufferedReader(
                    new FileReader(file));
            String line = in.readLine();
            while (line != null) {
                try {
                    StringTokenizer t = new StringTokenizer(line, "|");
                    String emailAddress = t.nextToken();
                    String firstName = t.nextToken();
                    String lastName = t.nextToken();
                    String password = t.nextToken();
                    User user = new User(firstName, lastName, emailAddress, password);
                    users.add(user);
                    line = in.readLine();
                } catch (NoSuchElementException e) {
                    line = in.readLine();
                }
            }
            in.close();
        }
        return users;
    }

    public static HashMap<String, User> getUsersMap(String filename) throws IOException {
        HashMap<String, User> users = new HashMap<String, User>();
        File file = new File(filename);
        if(file.exists())
        {
            BufferedReader in = new BufferedReader(
                    new FileReader(file));
            String line = in.readLine();
            while (line != null) {
                try {
                    StringTokenizer t = new StringTokenizer(line, "|");
                    String emailAddress = t.nextToken();
                    String firstName = t.nextToken();
                    String lastName = t.nextToken();
                    String password = t.nextToken();
                    User user = new User(firstName, lastName, emailAddress,password);
                    users.put(emailAddress, user);
                    line = in.readLine();
                } catch (NoSuchElementException e) {
                    line = in.readLine();
                }
            }
            in.close();
        }
        return users;
    }
}
