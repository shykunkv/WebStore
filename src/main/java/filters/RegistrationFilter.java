package filters;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ResourceBundle;


/**
 * Filter to check fields in registration page before invoke register action
 * If thi field are filled correctly - invoke action
 * If no - return error message to user (to jsp)
 */
public class RegistrationFilter implements Filter {

    private FilterConfig filterConfig = null;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String action = servletRequest.getParameter("action");



        if (action != null && action.equals("register")) {

            String path = "i18n.webstore";
            HttpServletRequest httpReq = (HttpServletRequest) servletRequest;
            String curLan = (String) httpReq.getSession().getAttribute("language");
            if (curLan != null && !curLan.equals("en"))
                path += "_" + curLan;
            ResourceBundle rb = ResourceBundle.getBundle(path);

            String login = servletRequest.getParameter("login");
            String password = servletRequest.getParameter("password");
            String mail = servletRequest.getParameter("mail");
            boolean isValid = true;

            if  (login.length() < 3 || login.length() > 16) {
                servletRequest.setAttribute("login_message", rb.getString("register.login.error"));
                isValid = false;
            }

            if (mail.length() < 3 && mail.length() > 25 || mail.indexOf('@') < 2) {
                servletRequest.setAttribute("mail_message", rb.getString("register.mail.error"));
                isValid = false;
            }

            if (password.length() < 5 || password.length() > 16) {
                servletRequest.setAttribute("password_message", rb.getString("register.password.error"));
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
