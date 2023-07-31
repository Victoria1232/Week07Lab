/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import data_access.ConnectionPool;
import data_access.DBUtil;
import data_access.RoleDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import models.Role;
import models.User;


/**
 *
 * @author Victo
 */
public class RoleService {
    
    
    
        private final RoleDB ROLE_DB = new RoleDB();

    public RoleService() {

    }

    public ArrayList<Role> getAll() throws Exception {

        ArrayList<Role> roles = ROLE_DB.getAllRoles();
        return roles;
    }
    
   public void delete(String userName) throws Exception {

        ROLE_DB.delete(userName);
    }
   
   
     public void insert(Role role) throws Exception {

        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
       //String sql = "INSERT INTO user (email, first_name, last_name , password) VALUES (?, ?, ?, ?)";
       String sql = "INSERT INTO role (role_id, role_name) VALUES (?, ?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, role.getRoleID());
            ps.setString(2, role.getRoleName());
         
    
           ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
}
