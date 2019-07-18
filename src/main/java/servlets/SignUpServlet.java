package servlets;

import accounts.AccountService;
import accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {
    private final AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        String login=req.getParameter("login");
        String password=req.getParameter("password");
        if(login==null || password==null){
            resp.setContentType("text/html:charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        UserProfile profile = accountService.getUserByLogin(login);
        if(profile!=null){
            resp.setContentType("text/html:charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        profile = new UserProfile(login, password, "");
        accountService.AddNewUser(profile);
        resp.setContentType("text/html:charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
