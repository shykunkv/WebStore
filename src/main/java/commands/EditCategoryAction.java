package commands;

import ents.Category;
import manager.CategoryManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Edit information about some category.
 * Handle 'Edit' button from catalog page.
 * User only by ADMIN users.
 */
public class EditCategoryAction extends Action {


    /**
     * Manager that provide work with database (category table)
     */
    private CategoryManager categoryManager = new CategoryManager();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String res = "/main?action=catalog";

        try {
            //get parameters for category from inputs
            String name = request.getParameter("old_name");
            String newName = request.getParameter("name");
            String newDescription = request.getParameter("description");

            Category category = categoryManager.getCategoryByName(name);

            if (category != null) {
                category.setName(newName);
                category.setDescription(newDescription);
                categoryManager.update(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(getClass()).error(e.getMessage());
        }
        return res;
    }
}
