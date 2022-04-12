package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.User;
import services.AccountService;
import services.GmailService;


public class ForgotPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/forgot.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String path = getServletContext().getRealPath("/WEB-INF");
        String msg ="";
        
        AccountService as = new AccountService();
        User user = as.forgotPassword(email);
        
        if (user != null) 
        {
            String subject = "Get my password back";
            String template = path + "/emailtemplates/userinfo.html";

            HashMap<String, String> tags = new HashMap<>();
            tags.put("firstname", user.getFirstName());
            tags.put("lastname", user.getLastName());
            tags.put("email", user.getEmail());
            tags.put("password", user.getPassword());

            try 
            {
                GmailService.sendMail(email, subject, template, tags);
            } catch (Exception ex) {
                Logger.getLogger(ForgotPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            msg = "The email was successfully sent!";
            
        }else{
            msg = "Email is not registered, Please enter a vaild email";
        }
        request.setAttribute("msg", msg);
        getServletContext().getRequestDispatcher("/WEB-INF/forgot.jsp").forward(request, response);
    }

}
