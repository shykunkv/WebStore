package commands;

import ents.Category;
import ents.Product;
import manager.CategoryManager;
import manager.ProductManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ProductAction extends Action {


    private ProductManager productManager = new ProductManager();
    private CategoryManager categoryManager = new CategoryManager();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String res= "product.jsp";

        int productId = Integer.parseInt(request.getParameter("id"));

        try {
            List<Category> categories = categoryManager.getAllCategories();
            Product product = productManager.getById(productId);
            request.setAttribute("categories", categories);
            request.setAttribute("product", product);

        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(getClass()).error(e.getMessage());
            res = "/error";
        }

        return res;
    }
}
