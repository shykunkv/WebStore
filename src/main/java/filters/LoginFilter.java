package filters;

import javax.servlet.*;
import java.io.IOException;

public class LoginFilter implements Filter {
    private FilterConfig filterConfig = null;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String action = servletRequest.getParameter("action");

        if (action != null && action.equals("login")) {

            String login = servletRequest.getParameter("login");
            String password = servletRequest.getParameter("password");

            if (login == null || login.length() == 0 || password == null || password.length() == 0) {
                servletRequest.setAttribute("message", "You must fill all fields!");
                RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("login.jsp");
                requestDispatcher.forward(servletRequest, servletResponse);
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
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
