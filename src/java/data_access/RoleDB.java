/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import models.Role;

/**
 *
 * @author Victo
 */
public class RoleDB {
    
    
    Role role; 
    
    public RoleDB() {
    }
    // return an array of role objects 
    // then use role service 
    // then get role service then call method to get role name then add it to user 
    // 

    public ArrayList<Role> getAllRoles() throws Exception {

        ArrayList<Role> roles = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM role"; 

        try {
            ps = con.prepareStatement(sql);
            // ps.setString(1, owner); // set ? in query  
            rs = ps.executeQuery();

            while (rs.next()) {

                int roleID = rs.getInt(1);
                String roleName = rs.getString(2);
               
       

                 role = new Role(roleID , roleName);
                roles.add(role);
            }
        } finally {
            DBUtil.closePreparedStatement(ps); // equivalent to ps.close()
            DBUtil.closeResultSet(rs); // equivalent to rs.close()
            cp.freeConnection(con);
        }

        return roles;
    }
    
   
        
      public void delete(String userName) throws Exception {

        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM role WHERE first_name=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, userName);
           
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
    
    
    
    
}
