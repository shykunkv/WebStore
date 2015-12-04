package commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Class that provides mapping action with it name in jsp pages.
 * Implemented as Singleton pattern
 */
public class ActionInvoker {

    private static ActionInvoker instance = null;
    private Map<String, Action> actionMap = new HashMap();


    /**
     * Default constructor
     */
    private ActionInvoker() {
        /**
         * Put all available actions to map
         */
        actionMap.put("default", new DefaultAction());
        actionMap.put("login", new LoginAction());
        actionMap.put("register", new RegisterAction());
        actionMap.put("catalog", new CatalogAction());
        actionMap.put("category", new CategoryAction());
        actionMap.put("product", new ProductAction());
        actionMap.put("addToCart", new AddToCartAction());
        actionMap.put("updateCart", new UpdateCartAction());
        actionMap.put("deleteItem", new DeleteItemAction());
        actionMap.put("checkout", new CheckoutAction());
        actionMap.put("addCategory", new AddCategoryAction());
        actionMap.put("deleteCategory", new DeleteCategoryAction());
        actionMap.put("editCategory", new EditCategoryAction());
        actionMap.put("addProduct", new AddProductAction());
        actionMap.put("deleteProduct", new DeleteProductAction());
        actionMap.put("editProduct", new EditProductAction());
        actionMap.put("searchUser", new SearchUserAction());
        actionMap.put("ban", new BanAction());
        actionMap.put("unban", new UnbanAction());
        actionMap.put("logout", new LogoutAction());
    }


    /**
     * Call some specific action with name from request.
     * If no such action name in map - call default action
     * @param req  request from servlet
     * @param resp response from servlet
     * @return page to show after concrete action
     * @throws ServletException
     * @throws IOException
     */
    public String invoke(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String actionName = req.getParameter("action");
        Action action = actionMap.get(actionName);

        if (action == null) {
            action = new DefaultAction();
        }

        return action.execute(req, resp);
    }

    /**
     * Get instance of this class
     * @return singleton instance of this class
     */
    public static ActionInvoker getInstance() {
        if (instance == null) {
            instance = new ActionInvoker();
        }
        return instance;
    }
}
