package commands;

import ents.Category;
import ents.Product;
import manager.CatalogManager;
import manager.CategoryManager;
import manager.ProductManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProductAction extends Action {


    private ProductManager productManager = new ProductManager();
    private CatalogManager catalorManager = new CatalogManager();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String result = "product.jsp";

        int productId = Integer.parseInt(request.getParameter("id"));


        try {
            List<Category> categories = catalorManager.getAll();
            Product product = productManager.getById(productId);
            request.setAttribute("categories", categories);
            request.setAttribute("product", product);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
