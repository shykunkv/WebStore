package commands;

import ents.Category;
import manager.CategoryManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Kostya on 26.11.2015.
 */
public class CatalogAction extends Action {

    private CategoryManager categoryManager = new CategoryManager();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String res = "catalog.jsp";

        try {
            List<Category> categoryList = categoryManager.getAll();
            if (categoryList != null) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }
}
