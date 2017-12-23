package kz.kcell.apps.common;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 04 01 2015
 */
@Slf4j
public class EnvPropertiesPrinter {
//    private static Logger log  = Logger.getLogger(PrintEnvProperties.class);

//    static {
//        printEnv();
//    }

    public static void printEnv() {
        StringBuilder buff = new StringBuilder();
        buff.append("\n === ENV ============= ");
        System.getenv().forEach((k,v) -> buff.append("\n"+k+": "+v));
        buff.append("\n ====PROPERTIES ====== ");
        System.getProperties().forEach((k, v) -> buff.append("\n"+k+": "+v));
        log.info(buff.toString());
    }

    @PostConstruct
    public void init(){
        printEnv();
    }
}
