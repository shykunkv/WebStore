package commands;

import ents.Category;
import manager.CategoryManager;
import manager.ProductManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



public class AddProductAction extends Action {

    private CategoryManager categoryManager = new CategoryManager();
    private ProductManager productManager = new ProductManager();


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String res = "main?action=category&id=";

        String categoryName = request.getParameter("category");
        Category category = categoryManager.getCategoryByName(categoryName);

        if (category != null) {
            res += category.getId();

            String productName = request.getParameter("name");
            String productBrand = request.getParameter("brand");
            Double productPrice = Double.parseDouble(request.getParameter("price"));
            String productImage = request.getParameter("image");
            String productDescription = request.getParameter("description");
            try {
                productManager.create(productName, productBrand, productPrice, productDescription, category.getId(), productImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return res;
    }
}
