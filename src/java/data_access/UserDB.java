package data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.Role;
import models.User;
import services.RoleService;

/**
 *
 * @author Victo
 */
public class UserDB {

    private DBUtil dbUtility = new DBUtil();

    public UserDB() {

    }

    public ArrayList<User> getAllUsers() throws Exception {

        Role roleObj = null; 
       // RoleService roleService = new RoleService();
        ArrayList<User> users = new ArrayList<>();
         //ArrayList<Role> roles = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM user";
      // roles =  roleService.getAll();
      // int index =  0; 

        try {
            ps = con.prepareStatement(sql);
            // ps.setString(1, owner); // set ? in query  
            rs = ps.executeQuery();

            while (rs.next()) {

                String email = rs.getString(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                String password = rs.getString(4);
               int roleID = rs.getInt(5);
  
               
               String roleName = "";

               
                if (roleID == 1) {
                    roleName = "System Admin";
                    roleObj = new Role(1, roleName);
                   

                } else if (roleID == 2) {
                    roleName = "Regular User";
                    roleObj = new Role(2, roleName);
                 
                }
              
                 RoleService roleService = new RoleService();
                 //roleService.insert(roleObj);
                 
                 //User user = new User(email, firstName, lastName);
                  User user = new User(email, firstName, lastName, password, roleObj);
               // User user = new User(email, firstName, lastName,  roles.get(index).getRoleName());
               // User user = new User(email, firstName, lastName, roles.get(index));
                users.add(user);
               // index++; 
            }
        } finally {
            DBUtil.closePreparedStatement(ps); // equivalent to ps.close()
            DBUtil.closeResultSet(rs); // equivalent to rs.close()
            cp.freeConnection(con);
        }

        return users;
    }

   
    
    public void insert(User user) throws Exception {

        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
       //String sql = "INSERT INTO user (email, first_name, last_name , password) VALUES (?, ?, ?, ?)";
       String sql = "INSERT INTO user (email, first_name, last_name , password, role) VALUES (?, ?, ?, ?, ?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getPassword());
          // ps.setString(5, user.getRoleName());
          // ps.setInt(4, user.getRoleID());
           ps.setInt(5, 2);
           ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }

    public void update(User user , String firstName) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        //String sql = "UPDATE user SET first_name=?, last_name=?, password=?, role_name=? WHERE email=?";
        String sql = "UPDATE user SET first_name=?, last_name=?, password=?, role=? WHERE first_name=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getRoleID());
            ps.setString(5, firstName);
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }

    public void delete(User user) throws Exception {

        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM user WHERE first_name=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getFirstName());
           
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
    
    
    
    
      public void delete(String userName) throws Exception {

        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM user WHERE first_name=?";

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
