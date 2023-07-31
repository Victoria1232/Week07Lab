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
class InvalidArgumentException extends Exception {
    
     public  InvalidArgumentException() {
         super("Invalid argument passed");
    }
     
   public  InvalidArgumentException(String msg) {
       System.out.println(msg);
    }
}
