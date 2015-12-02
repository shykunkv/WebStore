package commands;

import ents.Category;
import manager.CategoryManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Show catalog (list of the categories and 'hot' products)
 * Handle 'Catalog' button. Used by all users.
 */
public class CatalogAction extends Action {


    /**
     * Manager that provide work with database (category table)
     */
    private CategoryManager categoryManager = new CategoryManager();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        String res = "catalog.jsp";

        try {
            List<Category> categoryList = categoryManager.getAllCategories(); // get all categories from database
            if (categoryList != null) {
                req.setAttribute("categories", categoryList); // and put them into request
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(getClass()).error(e.getMessage());
            res = "/error";
        }

        return res;
    }
}
