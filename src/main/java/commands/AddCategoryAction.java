package commands;

import ents.Category;
import manager.CategoryManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


public class AddCategoryAction extends Action {

    private CategoryManager categoryManager = new CategoryManager();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String res = "main?action=catalog";

        try {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            categoryManager.create(name, description);
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(getClass()).error(e);
        }

        return res;
    }
}
