package kz.kcell.apps.fish.resources;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Component;

/**
 * Created by x on 4/29/17.
 */
@Component
@RefreshScope
@Slf4j
public class SpmotResourceLoaderStarter implements Lifecycle {

    @Autowired
    private SpmotResourceLoader spmotResourceLoader;

    //    @Override
    public void start() {
        log.info("SpmotResourceLoaderStarter start");
    }

    //    @Override
    public void stop() {
        log.info("SpmotResourceLoaderStarter stop");

    }

    //    @Override
    public boolean isRunning() {
        return false;
    }

}
