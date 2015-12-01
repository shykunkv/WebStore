package commands;

import ents.Product;
import manager.ProductManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


public class DeleteProductAction extends Action {

    private ProductManager productManager = new ProductManager();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String res = "/main?action=category&id=";

        try {
            int productId = Integer.parseInt(request.getParameter("product_id"));
            Product product = productManager.getById(productId);

            if (product != null) {
                res += product.getCategoryId();
                productManager.delete(product);
            } else {
                res = "index.jsp";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(getClass()).error(e.getMessage());
        }

        return res;
    }
}
