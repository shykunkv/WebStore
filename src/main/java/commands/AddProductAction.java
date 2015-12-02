package commands;

import ents.Category;
import manager.CategoryManager;
import manager.ProductManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Add new product to web store with specified values from jsp page
 * Handle '+ product' button. Used only by ADMIN users
 */
public class AddProductAction extends Action {

    /**
     * Manager that provide work with database (category table)
     */
    private CategoryManager categoryManager = new CategoryManager();

    /**
     * Manager that provide work with database (product table)
     */
    private ProductManager productManager = new ProductManager();


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String res = "main?action=category&id=";

        try {
            String categoryName = request.getParameter("category");
            Category category = categoryManager.getCategoryByName(categoryName);

            if (category != null) {
                res += category.getId();

                //get parameter from jsp inputs
                String productName = request.getParameter("name");
                String productBrand = request.getParameter("brand");
                Double productPrice = Double.parseDouble(request.getParameter("price"));
                String productImage = request.getParameter("image");
                String productDescription = request.getParameter("description");

                productManager.create(productName, productBrand, productPrice, productDescription, category.getId(), productImage);
            } else {
                res = "main?action=catalog";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(getClass()).error(e.getMessage());
        }

        return res;
    }
}
