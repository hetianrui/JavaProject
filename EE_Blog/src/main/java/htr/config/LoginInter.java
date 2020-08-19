package htr.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInter implements HandlerInterceptor {
    //请求前处理，调用此方法
    /*
    @return true:可以继续访问 false:进行后续的处理
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if(session!=null){
            Object username = session.getAttribute("user");
            if(username!=null){
                return true;
            }
        }
        response.sendRedirect("/login");
        return false;
    }
}
