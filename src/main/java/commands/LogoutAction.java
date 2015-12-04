package commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Clean session and redirect to index.jsp
 */
public class LogoutAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession curSession = request.getSession(false);
        if (curSession != null)
            curSession.invalidate();

        return "index.jsp";
    }
}
