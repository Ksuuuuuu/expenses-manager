package app;

import app.configs.MvcInitializer;
import app.configs.SpringConfig;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(SpringConfig.class);
        context.register(MvcInitializer.class);
        context.refresh();
    }
}
