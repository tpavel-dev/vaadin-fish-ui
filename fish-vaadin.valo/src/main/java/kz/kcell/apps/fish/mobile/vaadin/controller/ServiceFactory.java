package kz.kcell.apps.fish.mobile.vaadin.controller;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 30 09 2014
 */
@Component
@Deprecated
public class ServiceFactory {

    public static ServiceFactory instance;
    @PostConstruct
    public void init() {
        instance = this;
    }



}
