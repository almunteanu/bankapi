package dev.almuntex.bankapi;

import dev.almuntex.bankapi.context.ApplicationConfiguration;
import jakarta.servlet.ServletContext;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class ApplicationLauncher {

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();

        String portProperty = System.getProperty("server.port");
        int port = portProperty != null ? Integer.parseInt(portProperty) : 8080;

        tomcat.setPort(port);
        tomcat.getConnector();

        Context tomcatContext = tomcat.addContext("", null);

        WebApplicationContext webApplicationContext = createApplicationContext(tomcatContext.getServletContext());
        DispatcherServlet dispatcherServlet = new DispatcherServlet(webApplicationContext);

        Wrapper servlet = Tomcat.addServlet(tomcatContext, "dispatcherServlet", dispatcherServlet);
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/*");

        tomcat.start();
    }

    public static WebApplicationContext createApplicationContext(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(ApplicationConfiguration.class);
        ctx.setServletContext(servletContext);
        ctx.refresh();
        ctx.registerShutdownHook();
        return ctx;
    }
}
