package kz.kcell.apps.fish.app;

import kz.kcell.app.bonus_cmdr.ws.stub.AuthService;
import kz.kcell.app.bonus_cmdr.ws.stub.CompanyService;
import lombok.Data;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("bonus_cmdr.services")
@Data
public class CxfConfig {

    private String authServiceWsdlUrl;
    private String companyServiceWsdlUrl;

    @Bean
    public AuthService authService() {
        return factory(AuthService.class, authServiceWsdlUrl);
    }

    @Bean
    public CompanyService companyService() {
        return factory(CompanyService.class, companyServiceWsdlUrl);
    }

    <T> T factory(Class<T> serviceClass, String address) {
        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
        factoryBean.setServiceClass(serviceClass);
        factoryBean.setAddress(address);
        return (T) factoryBean.create();

    }
}
