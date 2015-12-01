package tags;

import ents.Cart;
import ents.CartItem;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;


/**
 * Tag that print cart table to jps page
 */
public class ShowCart extends TagSupport {

    public int doStartTag() throws JspException {

        Cart cart = (Cart) pageContext.getSession().getAttribute("cart"); // get current cart from session
        JspWriter out = pageContext.getOut();
        double total = 0.0;

        try {
            if (cart == null || cart.getCartItems().size() == 0) {
                out.print("<h2>Your shopping cart is empty</h2>");
            } else {
                out.print("<table class = \"table\">");
                out.print("<caption><h3>Your shopping cart</h3></caption>");
                out.print("<thead><tr>");
                out.print("<th scope = \"col\" width=\"50px\">Product</th>");
                out.print("<th scope = \"col\">Price</th>");
                out.print(" <th></th><th></th><th></th  >");
                out.print("<th scope = \"col\">Total</th>");
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
                                out.print("<label for = \"quantity\">Quantity: </label>");
                                out.print("<input type = \"text\" name = \"quantity\" value = \"" + ci.getQuantity() + "\" id = \"quantity\" size = \"1\" class = \"quantity\" maxlength = \"3\" />\n");
                                total += ci.getQuantity() * ci.getProduct().getPrice();
                            out.print("</td>");
                            out.print("<td>");
                                out.print("<input type = \"hidden\" name = \"action\" value = \"updateCart\" />");
                                out.print("<input type = \"hidden\" name = \"item_id\" value = \"" + ci.getId() + "\" />");
                                out.print("<input type = \"submit\" name = \"submit\" value = \"Update\" class=\"btn btn-default\" />");
                            out.print("</td>");
                        out.print("</form>");
                        out.print("<form method = \"POST\" action = \"/main\" class = \"cart\">");
                            out.print("<td>");
                                out.print("<input type = \"hidden\" name = \"item_id\" value = \"" + ci.getId() + "\" />");
                                out.print("<input type = \"hidden\" name = \"action\" value = \"deleteItem\" />");
                                out.print("<input type = \"submit\" name = \"submit\" value = \"Remove\" class=\"btn btn-default\" />");
                            out.print("</td>");
                            out.print("<td>" + ci.getQuantity() * ci.getProduct().getPrice() + "</td>");
                        out.print("</form>");
                    out.print("</tr>");
                }

                out.print("</tbody>");


                out.print("<tfoot><tr><th colspan = \"5\">Cart Subtotal:</th><th>" + total + "</th></tr><tr>\n");
                if (cart != null && cart.getCartItems() != null && cart.getCartItems().size() != 0) {
                    out.print("<th colspan = \"6\">\n");
                    out.print("<form method = \"POST\" action = \"/main\" class = \"cart\">");
                    out.print("<input type = \"hidden\" name = \"action\" value = \"checkout\" />\n");
                    out.print("<input type = \"submit\" name = \"submit\" value = \"Checkout now\" class=\"btn btn-default\" />\n");
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
