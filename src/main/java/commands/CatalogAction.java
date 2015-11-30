package commands;

import ents.Category;
import manager.CategoryManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CatalogAction extends Action {

    private CategoryManager categoryManager = new CategoryManager();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String res = "catalog.jsp";

        try {
            List<Category> categoryList = categoryManager.getAllCategories();
            if (categoryList != null) {
                req.setAttribute("categories", categoryList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }
}
