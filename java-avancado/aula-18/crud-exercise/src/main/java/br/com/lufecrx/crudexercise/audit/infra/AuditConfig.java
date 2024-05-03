package br.com.lufecrx.crudexercise.audit.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.lufecrx.crudexercise.audit.interceptor.IpAddressInterceptor;

@Configuration
public class AuditConfig implements WebMvcConfigurer {
    
    // @Autowired
    // private AuditInterceptor auditInterceptor;

    @Autowired
    private IpAddressInterceptor ipAddressInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(auditInterceptor);
        registry.addInterceptor(ipAddressInterceptor);
    }
    
}
