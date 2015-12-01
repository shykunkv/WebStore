package commands;

import ents.Category;
import ents.Product;
import manager.CategoryManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CategoryAction extends Action {

    private CategoryManager categoryManager = new CategoryManager();


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String res = "category.jsp";

        try {
            int categoryId = Integer.parseInt(request.getParameter("id"));
            String name = categoryManager.getById(categoryId).getName();
            List<Category> categoryList = categoryManager.getAllCategories();
            List<Product> productsList = categoryManager.getAllFromCategory(categoryId);
            if (productsList != null) {
                request.setAttribute("products", productsList);
                request.setAttribute("categories", categoryList);
                request.setAttribute("name", name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(getClass()).error(e.getMessage());
            res = "/error";
        }

        return res;
    }
}
