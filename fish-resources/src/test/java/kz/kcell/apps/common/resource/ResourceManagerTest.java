/*
package kz.kcell.apps.common.resource;

import kz.kcell.apps.spmot.resources.ResourceManager;
import kz.kcell.apps.spmot.resources.SpmotResourceLoader;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

*/
/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 07 04 2016
 *//*

public class ResourceManagerTest {

   @Before
   public void ini() {
       ConsoleAppender console = new ConsoleAppender(); //create appender
       //configure the appender
       String PATTERN = "%d [%p|%c|%C{1}] %m%n";
       console.setLayout(new PatternLayout(PATTERN));
       console.setThreshold(Level.FATAL);
       console.activateOptions();
       //add appender to any Logger (here is root)
//       Logger.getRootLogger().addAppender(console);

       FileAppender fa = new FileAppender();
       fa.setName("FileLogger");
//       fa.setFile("mylog.log");
       fa.setLayout(new PatternLayout("%d %-5p [%c{1}] %m%n"));
       fa.setThreshold(Level.DEBUG);
//       fa.setAppend(true);
       fa.activateOptions();

       //add appender to any Logger (here is root)
//       Logger.getRootLogger().addAppender(fa)l
       //repeat with all other desired appenders
    }

   @Test
    public void overridResourcesFromSystemEnviroment() throws Exception {
       SpmotResourceLoader resourceManager = new SpmotResourceLoader();
       Arrays.stream(MyResourceBundle.values()).forEach(resourceManager::overridResourcesFromSystemEnviroment);
    }
}*/
