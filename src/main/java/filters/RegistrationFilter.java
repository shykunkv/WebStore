package filters;


import javax.servlet.*;
import java.io.IOException;

public class RegistrationFilter implements Filter {

    private FilterConfig filterConfig = null;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String action = servletRequest.getParameter("action");

        if (action != null && action.equals("register")) {

            String login = servletRequest.getParameter("login");
            String password = servletRequest.getParameter("password");
            String mail = servletRequest.getParameter("mail");
            boolean isValid = true;

            if  (login.length() < 3 || login.length() > 16) {
                servletRequest.setAttribute("login_message", "Invalid login");
                isValid = false;
            }

            if (mail.length() < 3 && mail.length() > 25 || mail.indexOf('@') < 2) {
                servletRequest.setAttribute("mail_message", "Invalid mail address");
                isValid = false;
            }

            if (password.length() < 5 || password.length() > 16) {
                servletRequest.setAttribute("password_message", "Invalid password");
                isValid = false;
            }


            if (isValid) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("register.jsp");
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
