/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Victo
 */
public class Role {
    
    
    private int roleID; 
    private String roleName; 
    
    
    public Role(int id , String roleName) {
    
        roleID = id; 
        this.roleName = roleName; 
    }

    public int getRoleID() {
        return roleID;
    }

    public String getRoleName() {
        return roleName;
    }
    
    
      public String toString() {
        return this.roleName;
    }
}
