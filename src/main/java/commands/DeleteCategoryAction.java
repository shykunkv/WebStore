package commands;

import ents.Category;
import manager.CategoryManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


public class DeleteCategoryAction extends Action {


    private CategoryManager categoryManager = new CategoryManager();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String res = "/main?action=catalog";

        try {
            String name = request.getParameter("name");
            Category category = categoryManager.getCategoryByName(name);
            if (category != null) {
                categoryManager.delete(category);
            }
        } catch (SQLException e) {
            Logger.getLogger(getClass()).error(e.getMessage());
            e.printStackTrace();
        }

        return res;
    }
}
