package app;

import app.config.RabbitConfig;
import app.config.SpringConfig;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class EmailReadServiceLauncher {

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        final Connector connector = new Connector();
        connector.setPort(8082);
        connector.setScheme("http");
        connector.setSecure(false);
        tomcat.setConnector(connector);

        // Create a Spring application context
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(SpringConfig.class);
        appContext.register(RabbitConfig.class);


        tomcat.start();
    }
}
