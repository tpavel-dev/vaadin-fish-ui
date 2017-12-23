package kz.kcell.apps.spmot.coda;

import kz.kcell.apps.common.ws.WebServiceBinder;
import kz.kcell.apps.pentagon.coda.ws.client.stub.*;
import kz.kcell.apps.spmot.Config;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.xml.sax.SAXException;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceClient;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.net.URISyntaxException;


/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 15 09 2014
 */
@Component
@Slf4j
// @Lazy
@ConfigurationProperties(prefix = "CODAServiceProducer")
public class CODAServiceProducer {

    @Getter @Setter
    private String codaUser;

    @Getter @Setter
    private String codaPassword;

    @Autowired
    private SpringBus bus;

    @PostConstruct
    public void init() {
        if(StringUtils.isBlank(codaUser)) {
            log.error("user not defined");
        }

        if(StringUtils.isBlank(codaPassword)) {
            log.error("password not defined");
        }

        Assert.state(StringUtils.isNotBlank(codaUser) && StringUtils.isNotBlank(codaPassword),
                "User or password not defined");

        log.info("Producer init.");
    }

    @Bean
    public RefBooksServicesWS getRefBooksServicesWS() throws IllegalAccessException, URISyntaxException, JAXBException, IOException, XPathExpressionException, SAXException, ParserConfigurationException, NoSuchFieldException {
        return createServiceProxy(RefBooksServicesWS.class, RbServices.class);
    }

    @Bean
    public UserServicesWS getUserServicesWS() throws IllegalAccessException, URISyntaxException, JAXBException, IOException, XPathExpressionException, SAXException, ParserConfigurationException, NoSuchFieldException {
        return createServiceProxy(UserServicesWS.class, UserServices.class);
    }

    @Bean
    public BossServicesWS getBossServicesWS() throws IllegalAccessException, URISyntaxException, JAXBException, IOException, XPathExpressionException, SAXException, ParserConfigurationException, NoSuchFieldException {
        return createServiceProxy(BossServicesWS.class, BossServices.class);
    }

    @Bean
    public BillingServicesWS getBillingServicesWS() throws IllegalAccessException, URISyntaxException, JAXBException, IOException, XPathExpressionException, SAXException, ParserConfigurationException, NoSuchFieldException {
        return createServiceProxy(BillingServicesWS.class, BillingServices.class);
    }

    @Bean
    public MessagingWS getMessagingWS() throws IllegalAccessException, URISyntaxException, JAXBException, IOException, XPathExpressionException, SAXException, ParserConfigurationException, NoSuchFieldException {
        return createServiceProxy(MessagingWS.class, MessagingService.class);
    }

    @Bean
    public BalancesServicesWS getBalancesServicesWS() throws IllegalAccessException, URISyntaxException, JAXBException, IOException, XPathExpressionException, SAXException, ParserConfigurationException, NoSuchFieldException {
        return createServiceProxy(BalancesServicesWS.class, Balances.class);
    }

    @Bean
    public MsisdnServicesWS getMsisdnServicesWS() throws IllegalAccessException, URISyntaxException, JAXBException, IOException, XPathExpressionException, SAXException, ParserConfigurationException, NoSuchFieldException {
        return createServiceProxy(MsisdnServicesWS.class, MsisdnServices.class);
    }

    @Bean
    public CustomerServicesWS getCustomerServicesWS() throws IllegalAccessException, URISyntaxException, JAXBException, IOException, XPathExpressionException, SAXException, ParserConfigurationException, NoSuchFieldException {
        return createServiceProxy(CustomerServicesWS.class, CustomerServices.class);
    }

//    @Bean
//    public LSWS getLsService() throws IllegalAccessException, URISyntaxException, JAXBException, IOException, XPathExpressionException, SAXException, ParserConfigurationException, NoSuchFieldException {
//        return createServiceProxy(LSWS.class, LSWebService.class);
//    }

    @Bean
    public CodaMigrationWS getMigrationServicesWS() throws IllegalAccessException, URISyntaxException, JAXBException, IOException, XPathExpressionException, SAXException, ParserConfigurationException, NoSuchFieldException {
        return createServiceProxy(CodaMigrationWS.class, CodaMigration.class);
    }

    private <T> T createServiceProxy(
            Class<T> serviceInterface,
            Class<?> serviceClass

    ) throws NoSuchFieldException,
            IllegalAccessException,
            IOException,
            ParserConfigurationException,
            SAXException,
            XPathExpressionException,
            URISyntaxException,
            JAXBException {

        WebServiceClient webServiceClient = serviceClass.getAnnotation(WebServiceClient.class);

        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setBus(bus);
        factory.setWsdlLocation(webServiceClient.wsdlLocation());
        factory.setServiceName((QName) FieldUtils.readStaticField(serviceClass, "SERVICE"));

        T service = factory.create(serviceInterface);
        BindingProvider provider = (BindingProvider) service;

        WebServiceBinder.addUserCredentialsToPort(provider, codaUser, codaPassword);
        WebServiceBinder.setWSTimeout(provider, Config.DEFAULT_WS_TIMEOUT);

        log.info("Create service proxy: {}  as {} = {}"
                ,serviceInterface.getSimpleName()
                ,codaUser
                ,factory.getWsdlLocation()
        );

        return service;
    }

}
