package commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Invoke this action when invoker can't map any other action
 * Handle 'logout' button
 */
public class DefaultAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession curSession = request.getSession(false);
        if (curSession != null)
            curSession.invalidate();

        return "index.jsp";
    }
}
