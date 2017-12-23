package kz.kcell.apps.spmot.mobile.vaadin.controller;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
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
