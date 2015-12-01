package tags;

import ents.Category;
import ents.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;


/**
 * Tag that print table with all categories to jsp page
 */
public class ShowCategories extends TagSupport {


    public int doStartTag() throws JspException {

        // get all available categories
        List<Category> categoryList = (List<Category>) pageContext.getRequest().getAttribute("categories");

        //get current user
        User user = (User) pageContext.getSession().getAttribute("user");
        JspWriter out = pageContext.getOut();
        if (categoryList != null) {
            try {
                // print every category
                for (Category c : categoryList) {
                    out.println("<div class = \"row\">");
                    out.println("<h3 class=\"masthead-brand\">");
                    out.println("<a href = \"/main?action=category&id=" + c.getId() + "\">");
                    out.println(c.getName());
                    out.println("</a>");
                    if (user != null && user.getRole() == User.Role.ADMIN) {
                        out.print("<button type=\"button\" class=\"btn btn-default btn-xs\" data-toggle=\"modal\" " +
                            "data-target=\"#edit\" data-name=\""+c.getName()+"\" data-description=\""+c.getDescription()+"\">" +
                            "<span class = \"glyphicon glyphicon-pencil\"/>" +
                            "</button>");
                    }
                    out.print("</h3></div>");
                }

                // is user is ADMIN - print buttons to create and edit categories
                if (user != null && user.getRole() == User.Role.ADMIN) {
                    out.print("<div class=\"row\"><div class=\"col-sm-4\">");
                    out.print("<button type=\"button\" class=\"btn btn-default\" data-toggle=\"modal\" data-target=\"#add\">" +
                            "<span class = \"glyphicon glyphicon-plus\"/> category" +
                            "</button>");
                    out.print("</div></div>");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // tag with empty body
        return SKIP_BODY;
    }
}
