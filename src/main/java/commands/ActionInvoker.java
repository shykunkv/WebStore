package commands;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ActionInvoker {

    private static ActionInvoker instance = null;
    private Map<String, Action> actionMap = new HashMap();

    private ActionInvoker() {
        actionMap.put("default", new DefaultAction());
        actionMap.put("login", new LoginAction());
        actionMap.put("register", new RegisterAction());
    }

    public String invoke(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String actionName = req.getParameter("action");
        Action action = actionMap.get(actionName);

        if (action == null) {
            action = new DefaultAction();
        }

        return action.execute(req, resp);
    }


    public static ActionInvoker getInstance() {
        if (instance == null) {
            instance = new ActionInvoker();
        }
        return instance;
    }
}
