/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import data_access.UserDB;
import java.util.ArrayList;

import models.User;

/**
 *
 * @author Victo
 */
public class UserService {

    private final UserDB USER_DB = new UserDB();

    public UserService() {

    }

    public ArrayList<User> getAll() throws Exception {

        ArrayList<User> users = USER_DB.getAllUsers();
        return users;
    }

    public void insert(User user) throws Exception {

        USER_DB.insert(user);
    }



    public void update(User user, String firstName) throws Exception {

        USER_DB.update(user , firstName);
    }


    
      public void delete(String userName) throws Exception {

        USER_DB.delete(userName);
    }
}
