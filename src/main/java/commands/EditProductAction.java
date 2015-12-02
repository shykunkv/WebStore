package commands;

import ents.Product;
import manager.ProductManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


/**
 * Edit product in web store.
 * Handle 'edit' button on the product page.
 * Used only by ADMIN users
 */
public class EditProductAction extends Action {

    /**
     * Manager that provide work with database (product table)
     */
    private ProductManager productManager = new ProductManager();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String res = "/main?action=category&id=";

        try {
            int productId = Integer.parseInt(request.getParameter("product_id"));
            Product product = productManager.getById(productId);

            if (product != null) {
                res += product.getCategoryId();


                // get new parameters for product
                String newName = request.getParameter("name");
                String newBrand = request.getParameter("brand");
                double newPrice = Double.parseDouble(request.getParameter("price"));
                String newImage = request.getParameter("image");
                String newDescription = request.getParameter("description");

                // set parameters
                product.setName(newName);
                product.setBrand(newBrand);
                product.setPrice(newPrice);
                product.setImage(newImage);
                product.setDescription(newDescription);

                // update product in database
                productManager.update(product);
            } else {
                res = "/main?action=catalog";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(getClass()).error(e.getMessage());
        }

        return res;
    }
}
