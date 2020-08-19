package htr.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class ControllerInterceptor {
    @ExceptionHandler(Exception.class)
    public ModelAndView handler(Exception e){
        StringWriter sw=new StringWriter();
        PrintWriter pw=new PrintWriter(sw);
        e.printStackTrace(pw);
        ModelAndView mw=new ModelAndView();
        mw.addObject("message",e.getMessage());
        mw.addObject("stackTrace",e.toString());
        mw.setViewName("error");
        return mw;
    }
}
