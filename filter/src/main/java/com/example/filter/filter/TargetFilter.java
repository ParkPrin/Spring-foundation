package com.example.filter.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
// 타켓필터를 적용할 때 @WebFilter를 사용함
@WebFilter(urlPatterns = "/api/user/*")
public class TargetFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // 전처리

        //HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        //HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        /**
         * ContentCachingRequestWrapper를 사용하는 이유
         * HttpServletRequest에서 body를 읽어올 때 writeBuffer가 다 읽고나서 커서가 끝으로 위치가 된다.
         * 그러므로 requestbody를 한번 더 읽는다고 했을 때 더 이상 읽을 내용이 없어서 Exception이 발생한다.
         * requestBody를 여러번 반복하기 위해서 해당 내용을 caching에 저장해놓고 읽을 수 있도록 하는 클래스가 ContentCachingRequestWrapper이다
         * 즉 ContentCachingRequestWrapper, ContentCachingResponseWrapper는 여러번 읽을 수 있다.
         */
        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper((HttpServletRequest)request);
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper((HttpServletResponse)response);

        chain.doFilter(httpServletRequest, httpServletResponse);
        // 후처리
        String url = httpServletRequest.getRequestURI();

        String reqContent = new String(httpServletRequest.getContentAsByteArray());

        log.info("request url : {}, request body : {} ", url, reqContent);

        String resContent = new String(httpServletResponse.getContentAsByteArray());

        int httpStatus = httpServletResponse.getStatus();
        httpServletResponse.copyBodyToResponse();

        log.info("response status : {}, responseBody: {}", httpStatus, resContent );

    }
}
