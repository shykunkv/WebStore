package filters;

import javax.servlet.*;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Filter that check correctness of quantity field in shopping cart
 */
public class UpdateFilter implements Filter {

    private FilterConfig filterConfig = null;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        LinkedList<Number> l = new <Integer>LinkedList();
        String action = servletRequest.getParameter("action");

        if (action != null && action.equals("updateCart")) {

            boolean isValid = true;

            try {
                int quantity = Integer.parseInt(servletRequest.getParameter("quantity"));
                if (quantity <= 0)
                    isValid = false;
            } catch (NumberFormatException e) {
                isValid = false;
            }

            if (isValid) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("cart.jsp");
                requestDispatcher.forward(servletRequest, servletResponse);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void destroy() {

    }
}
