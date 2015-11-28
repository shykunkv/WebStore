package tags;

import ents.Category;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

public class ShowCategories extends TagSupport {



    public int doStartTag() throws JspException {


        List<Category> categoryList = (List<Category>) pageContext.getRequest().getAttribute("categories");
        JspWriter out = pageContext.getOut();
        if (categoryList != null) {
            try {
                for (Category c : categoryList) {
                    out.println("<div class = \"row\">");
                    out.println("<h3 class=\"masthead-brand\">");
                    out.println("<a href = \"/main?action=category&id=" + c.getId() + "\">"); //TODO
                    out.println(c.getName());
                    out.println("</a></h3></div>");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return SKIP_BODY;
    }
}
