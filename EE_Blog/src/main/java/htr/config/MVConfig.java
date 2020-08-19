package htr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MVConfig implements WebMvcConfigurer {
    /*
    拦截器：对访问敏感url进行处理
    addInterceptor()方法的参数是处理类，此类是处理是请求前拦截还是请求后拦截
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInter())
                .excludePathPatterns("/css/**")
                .excludePathPatterns("/fonts/**")
                .excludePathPatterns("/images/**")
                .excludePathPatterns("/js/**")
                .excludePathPatterns("/plugins/**")
                .excludePathPatterns("/")
                .excludePathPatterns("/login")
                .excludePathPatterns("/login2");
//               .excludePathPatterns("/123/login1")
//       .excludePathPatterns("/login.html");
    }
}
