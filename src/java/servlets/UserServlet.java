package servlets;

import java.io.IOException;

import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Role;
import models.User;
import services.RoleService;
import services.UserService;

/**
 *
 * @author Victo
 */
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /* VARIABLES  */
        UserService userService = new UserService();
        RoleService roleService = new RoleService();
        String action = request.getParameter("action");

        /* GET ALL USERS  */
        try {
            ArrayList<User> usersArray = userService.getAll();
             ArrayList<Role> rolesArray = roleService.getAll();

            /* IF NULL THROW ERROR MSG */
            if (usersArray == null) {

                request.setAttribute("error", "No users found, Please add user");
            }

            /* OTHERWISE ADD ARRAY TO USERS IN JSP  */
            request.setAttribute("users", usersArray);
            request.setAttribute("roles", rolesArray);

        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex); // ???
        }

             /* IF EDIT LINK IS PRESSED - EDIT TABLE IS SHOWN  */
        if (action != null && action.equals("edit_link")) {

            String userEmail = request.getParameter("users_email");
            request.setAttribute("edit_table", true);
            request.setAttribute("email", userEmail); // set the email var in jsp to be the user email
            getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
            return;

        }

           /* IF DELETE LINK IS PRESSED - DELETE USER */
        if (action != null && action.equals("delete")) {

            String userFirstName = request.getParameter("user_first_name");
            String userLastName = request.getParameter("user_last_name");
            try {
                userService.delete(userFirstName); // delete that user 
             
                ArrayList<User> usersArray = userService.getAll(); // get users again to get the updated table  
                request.setAttribute("users", usersArray); // send to users 
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.setAttribute("message", "Deleted user " + userFirstName + " " + userLastName); // confirmation msg that user was deleted 
            getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
            return;

        }
           /* OTHERWISE SEND USERS WITH POPULATED USERS TO JSP  */

        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /* VARIABLES */
        UserService userService = new UserService();
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String password = request.getParameter("password");
        String role = request.getParameter("roles");

        //String action = request.getParameter("action");
        String cancelButton = request.getParameter("cancel_button");
        String editButton = request.getParameter(" submit_edits_button");
       // String addButton = request.getParameter(" add_user_button");


        /* IF TABLE IS NOT FILLED OUT  */
        if (firstName == null || firstName.equalsIgnoreCase("") || lastName == null || lastName.equalsIgnoreCase("") || password == null || password.equalsIgnoreCase("")) {

            request.setAttribute("error", "All fields are required");
            request.setAttribute("email", email);

            try {
                ArrayList<User> usersArray = userService.getAll(); // get users again to get the new user
                request.setAttribute("users", usersArray); // send to users 
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
            return;

        }
        /* CREATE USER  */
  
         User newUser = new User(email, firstName, lastName, password, 2);
         
        /* IF CANCEL BUTTON IS PRESSED  */
        if ("Cancel".equalsIgnoreCase(cancelButton)) {
            request.setAttribute("edit_table", false);

            /* IF EDIT BUTTON IS PRESSED  */
        } else if ("Edit User".equalsIgnoreCase(editButton)) {

            String userName = request.getParameter("user_name");

                if (role.equalsIgnoreCase("option 1")) {
               newUser = new User(email, firstName, lastName, password, 1);
        }
        else if (role.equalsIgnoreCase("option 2")) {
              newUser = new User(email, firstName, lastName, password, 2);
        }
            try {
                userService.update(newUser, userName);
                ArrayList<User> usersArray = userService.getAll(); // get users again to get the new user
                request.setAttribute("users", usersArray); // send to users 
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.setAttribute("message", "updated user: " + userName);
            getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
            return;

        } else {

            /* OTHERWISE ADD USER TO TABLE  */
            try {
                userService.insert(newUser);
                ArrayList<User> usersArray = userService.getAll(); // get users again to get the new user
                request.setAttribute("users", usersArray); // send to users 
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("message", "added user: " + firstName + " " + lastName);
            getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
            return;
        }

    }

}
