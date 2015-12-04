package tags;

import ents.Cart;
import ents.CartItem;
import utils.ResourceBundleUtil;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.ResourceBundle;


/**
 * Tag that print cart table to jps page
 */
public class ShowCart extends TagSupport {

    public int doStartTag() throws JspException {

        //get resource bundle for current language
        String curLanguage = (String) pageContext.getSession().getAttribute("language");
        ResourceBundle rb = ResourceBundleUtil.getResourceBundle(curLanguage);

        //get current shopping cart from servlet request
        Cart cart = (Cart) pageContext.getSession().getAttribute("cart"); // get current cart from session
        JspWriter out = pageContext.getOut();
        double total = 0.0;

        try {
            if (cart == null || cart.getCartItems().size() == 0) {
                out.print("<h2>"+rb.getString("cart.empty")+"</h2>");
            } else {
                out.print("<table class = \"table\">");
                out.print("<caption><h3>"+ rb.getString("cart.title") +"</h3></caption>");
                out.print("<thead><tr>");
                out.print("<th scope = \"col\" width=\"50px\">"+ rb.getString("cart.product.title") +"</th>");
                out.print("<th scope = \"col\">"+ rb.getString("cart.price.title") +"</th>");
                out.print(" <th></th><th></th><th></th  >");
                out.print("<th scope = \"col\">"+ rb.getString("cart.total.title") +"</th>");
                out.print("</tr></thead>");
                out.print("<tbody>");

                // print every item
                for (CartItem ci : cart.getCartItems()) {
                    out.print("<tr>");
                        out.print("<td>");
                            out.print("<a href = \"/main?action=product&id=" + ci.getProductId() + "\">" + ci.getProduct().getName() + "</a>");
                        out.print("</td>");
                        out.print("<td>" + ci.getProduct().getPrice() + "</td>");
                        out.print("<form method = \"POST\" action = \"/main\" class = \"cart\">");
                            out.print("<td>");
                                out.print("<label for = \"quantity\">"+ rb.getString("cart.quantity.title") +": </label>");
                                out.print("<input type = \"text\" name = \"quantity\" value = \"" + ci.getQuantity() + "\" id = \"quantity\" size = \"1\" class = \"quantity\" maxlength = \"3\" />\n");
                                total += ci.getQuantity() * ci.getProduct().getPrice();
                            out.print("</td>");
                            out.print("<td>");
                                out.print("<input type = \"hidden\" name = \"action\" value = \"updateCart\" />");
                                out.print("<input type = \"hidden\" name = \"item_id\" value = \"" + ci.getId() + "\" />");
                                out.print("<input type = \"submit\" name = \"submit\" value = \""+ rb.getString("cart.update.button") +"\" class=\"btn btn-default\" />");
                            out.print("</td>");
                        out.print("</form>");
                        out.print("<form method = \"POST\" action = \"/main\" class = \"cart\">");
                            out.print("<td>");
                                out.print("<input type = \"hidden\" name = \"item_id\" value = \"" + ci.getId() + "\" />");
                                out.print("<input type = \"hidden\" name = \"action\" value = \"deleteItem\" />");
                                out.print("<input type = \"submit\" name = \"submit\" value = \""+ rb.getString("cart.delete.button") +"\" class=\"btn btn-default\" />");
                            out.print("</td>");
                            out.print("<td>" + ci.getQuantity() * ci.getProduct().getPrice() + "</td>");
                        out.print("</form>");
                    out.print("</tr>");
                }

                out.print("</tbody>");


                out.print("<tfoot><tr><th colspan = \"5\">"+ rb.getString("cart.subtotal.title") +":</th><th>" + total + "</th></tr><tr>\n");
                if (cart != null && cart.getCartItems() != null && cart.getCartItems().size() != 0) {
                    out.print("<th colspan = \"6\">\n");
                    out.print("<form method = \"POST\" action = \"/main\" class = \"cart\">");
                    out.print("<input type = \"hidden\" name = \"action\" value = \"checkout\" />\n");
                    out.print("<input type = \"submit\" name = \"submit\" value = \""+ rb.getString("cart.checkout.button") +"\" class=\"btn btn-default\" />\n");
                    out.print("</form>");
                    out.print("</th></tr></tfoot>");
                }

                out.print("</table>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // tag with empty body
        return SKIP_BODY;
    }
}
