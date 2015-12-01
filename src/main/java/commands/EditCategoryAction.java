package commands;

import ents.Category;
import manager.CategoryManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditCategoryAction extends Action {

    private CategoryManager categoryManager = new CategoryManager();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String res = "/main?action=catalog";

        String name = request.getParameter("old_name");
        String newName = request.getParameter("name");
        String newDescription = request.getParameter("description");

        Category category = categoryManager.getCategoryByName(name);
        if (category != null) {
            category.setName(newName);
            category.setDescription(newDescription);
            categoryManager.update(category);
        }

        return res;
    }
}
