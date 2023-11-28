package app;

import app.configs.RabbitConfig;
import app.configs.SpringConfig;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class QrReadServiceLauncher {

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        final Connector connector = new Connector();
        connector.setPort(8081);
        connector.setScheme("http");
        connector.setSecure(false);
        tomcat.setConnector(connector);

        // Create a Spring application context
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(SpringConfig.class);
        appContext.register(RabbitConfig.class);
        // Create a DispatcherServlet and register it with Tomcat
//        DispatcherServlet dispatcherServlet = new DispatcherServlet(appContext);
//
//        Context ctx = tomcat.addContext("", null);
//        Wrapper dispatcher = Tomcat.addServlet(ctx, "dispatcher", dispatcherServlet);
//        dispatcher.setLoadOnStartup(1);
//        dispatcher.addMapping("/*");

        tomcat.start();
    }
}
