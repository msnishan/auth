package com.msnishan.auth.config;

import com.msnishan.auth.annotation.HttpRequestContext;
import com.msnishan.gen.type.request.RequestContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.UUID;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private static final String REQUEST_ID = "RequestId";
    private static final String POS_ID = "CompanyId";

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new HandlerMethodArgumentResolver() {
            @Override
            public boolean supportsParameter(MethodParameter parameter) {
                return parameter.hasParameterAnnotation(HttpRequestContext.class);
            }

            @Override
            public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
                String requestId = webRequest.getHeader(REQUEST_ID);
                if (requestId == null) {
                    requestId = UUID.randomUUID().toString();
                }
                if (SecurityContextHolder.getContext().getAuthentication() != null) {
                    Authentication principal = SecurityContextHolder.getContext().getAuthentication();
                    System.out.println(principal.getPrincipal());
                }
                String companyId = webRequest.getHeader(POS_ID);
                return new RequestContext(requestId, null, null, companyId);
            }
        });
    }
}
