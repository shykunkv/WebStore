package commands;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



//TODO: log4j
//TODO: error handler
abstract public class Action {

    public abstract String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
}