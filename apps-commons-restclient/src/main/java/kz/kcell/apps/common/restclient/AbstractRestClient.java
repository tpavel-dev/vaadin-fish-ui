package kz.kcell.apps.common.restclient;

import kz.kcell.apps.common.restclient.interceptors.MyClientResponseFilter;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 16 09 2014
 */
@Slf4j
public abstract class AbstractRestClient<SERVICE> {
    private Class<SERVICE> serviceClass;

    @Getter
    private SERVICE service;

    private static int POOL_SIZE = 100;

    @Getter @Setter
    private String service_url;

    protected AbstractRestClient(Class<SERVICE> serviceClass) {
        this.serviceClass = serviceClass;
//        buildProxy();
    }

    public AbstractRestClient(@NotNull Class<SERVICE> serviceClass, @NotNull String service_url) {
        this.serviceClass = serviceClass;
        this.service_url = service_url;
//        buildProxy();
    }

    @PostConstruct
    public void buildProxy() {
        ResteasyClient client = new ResteasyClientBuilder().connectionPoolSize(POOL_SIZE).build();
//        client.register(new MyClientRequestFilter());
        client.register(new MyClientResponseFilter());
//        client.register(new BaseAuthenticator(config.getUser(), config.getPassword()));
        log.info("Create ResteasyWebTarget:{} for url {}", getClass().getSimpleName(), service_url);
        ResteasyWebTarget target = client.target(service_url);
        log.info("Created ResteasyWebTarget:{} for url {}", getClass().getSimpleName(), service_url);

        service = target.proxy(serviceClass);
        log.info("Build {} poolSize {} for url {} ", getClass().getSimpleName(), POOL_SIZE, target.getUri());
    }

}
