package tags;

import ents.Product;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.List;
import java.util.ResourceBundle;


/**
 * Tag that print category (all products from this category) to jsp page
 */
public class ShowCategory extends TagSupport {


    /**
     * Path to image folder
     */
    private static String IMAGE_URL = "images/";

    public int doStartTag() throws JspException {

        String path = "i18n.webstore";
        String curLan = (String) pageContext.getSession().getAttribute("language");

        if (curLan != null && !curLan.equals("en")) {
            path += "_" + curLan;
        }

        ResourceBundle rb = ResourceBundle.getBundle(path);

        List<Product> productList = (List<Product>) pageContext.getRequest().getAttribute("products");
        JspWriter out = pageContext.getOut();

        if (productList != null) {
            try {
                for (Product p : productList) {
                    out.println("<div class = \"row\">");
                    out.println(" <div class = \"col-xs-7\">");
                    out.print("<img src = \""+ IMAGE_URL + p.getImage() + "\" alt = \"" + p.getName() + "\" height=\"100\" />");
                    out.println("</div>");

                    out.println("<div class = \"col-xs-5\">");
                    out.println("<p>" + p.getBrand() + " " + p.getName() + " </p>");
                    out.println("<p>"+ rb.getString("product.price") +": " + p.getPrice() + " </p>");
                    out.println("<p><a href = \"/main?action=product&id="+p.getId()+"\" class=\"btn btn-default\">"+ rb.getString("product.more.button") +"</a></p>\n");
                    out.println("</div>");
                    out.println("</div>");

                    out.println("<br><br>");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // tag with empty body
        return SKIP_BODY;
    }
}
