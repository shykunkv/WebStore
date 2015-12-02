package commands;

import manager.CategoryManager;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;


/**
 * Add new category to web store, handle '+ category' button
 * Used only by ADMIN users
 */
public class AddCategoryAction extends Action {

    /**
     * Manager that provide work with database (category table)
     */
    private CategoryManager categoryManager = new CategoryManager();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String res = "main?action=catalog";

        try {
            String name = request.getParameter("name");
            String description = request.getParameter("description");

            categoryManager.create(name, description);
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(getClass()).error(e);
        }

        return res;
    }
}
