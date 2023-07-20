package projectA.filter;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@WebFilter(urlPatterns = "/**")
public class LoginFilter implements Filter {
    @Value("${sso_server}")
    private String serverHost;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException,  ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String token = httpServletRequest.getParameter("token");

        if (this.check(token)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            String redirectUrl = serverHost + "/login";
            response.sendRedirect(redirectUrl);
        }

    }

    /**
     * 验证token
     *
     * @param token
     * @return
     * @throws Exception
     */
    private boolean check(String token) throws IOException {
        if (token == null || token.trim().length() == 0) {
            return false;
        }
        OkHttpClient client = new OkHttpClient();
        // 这里是验证token的
        String url = serverHost + "/checkJwt?token=" + token;
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        return Boolean.parseBoolean(response.body().string());
    }
}
