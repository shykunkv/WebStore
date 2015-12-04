package filters;

import utils.ResourceBundleUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ResourceBundle;


/**
 * Filter to check fields in login page before invoke login action
 * If thi field are filled correctly - invoke action
 * If no - return error message to user (to jsp)
 */
public class LoginFilter implements Filter {

    private FilterConfig filterConfig = null;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // get current action
        String action = servletRequest.getParameter("action");

        if (action != null && action.equals("login")) {

            // get resource bundle for current language
            HttpServletRequest httpReq = (HttpServletRequest) servletRequest;
            String curLanguage = (String) httpReq.getSession().getAttribute("language");
            ResourceBundle rb = ResourceBundleUtil.getResourceBundle(curLanguage);

            // get login params
            String login = servletRequest.getParameter("login");
            String password = servletRequest.getParameter("password");

            if (login == null || login.length() == 0 || password == null || password.length() == 0) {
                servletRequest.setAttribute("message", rb.getString("login.message"));
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
