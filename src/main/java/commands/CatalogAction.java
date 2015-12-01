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

public class CatalogAction extends Action {

    private CategoryManager categoryManager = new CategoryManager();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        String res = "catalog.jsp";

        try {
            List<Category> categoryList = categoryManager.getAllCategories();
            if (categoryList != null) {
                req.setAttribute("categories", categoryList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(getClass()).error(e.getMessage());
            res = "/error";
        }

        return res;
    }
}
