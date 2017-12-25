package kz.kcell.apps.fish.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({
//        "classpath:META-INF/cxf/cxf.xml",
//        "classpath:META-INF/cxf/cxf-servlet.xml",
//        "classpath:META-INF/cxf/cxf-extension-jaxws.xml",
//        "file:config/cxf.xml"
        "classpath:beans.xml"
})

public class CxfConfig {
}
