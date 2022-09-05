package tk.gushizone.common.filter;

import cn.hutool.extra.servlet.ServletUtil;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author gushizone@gmail.com
 * @date 2022/4/18 00:47
 */
//@Component
public class OidcFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        ServletUtil.getHeader((HttpServletRequest) request, "", "UTF-8");


    }
}
