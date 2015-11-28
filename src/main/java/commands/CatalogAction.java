package commands;

import ents.Category;
import manager.CatalogManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CatalogAction extends Action {

    private CatalogManager catalogManager = new CatalogManager();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String res = "catalog.jsp";

        try {
            List<Category> categoryList = catalogManager.getAll();
            if (categoryList != null) {
                req.setAttribute("categories", categoryList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }
}
