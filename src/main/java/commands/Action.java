package commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Abstarct action, that will perform some business logic
 * */
abstract public class Action {

    /**
     * Execute some business logic (depending on concrete action)
     * @param request from servlet
     * @param response from servlet
     * @return page to show after concrete action
     * @throws ServletException
     * @throws IOException
     */
    public abstract String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
}