package SimsRESTServer;

import SimsDal.repository.IncidentRepository;
import SimsDal.repository.PhasedplanRepository;
import SimsDal.repository.UserRepository;
import SimsRESTServer.handlers.*;
import SimsRESTServer.restservices.IncidentService;
import SimsRESTServer.restservices.PhasedplanService;
import SimsRESTServer.restservices.UserService;
import logging.Logger;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class RestServer {
    private static final int PORT = 8094;

    public static void main(String[] args) {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        Server jettyServer = new Server(PORT);

        // region Origin header
        FilterHolder cors = context.addFilter(CrossOriginFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
        cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        cors.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,POST,HEAD");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "X-Requested-With,Content-Type,Accept,Origin");
        // endregion

        jettyServer.setHandler(context);
        ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

        // Creating and settings handlers
        IPhasedplanHandler phasedplanHandler = new PhasedplanHandler(new PhasedplanRepository());
        PhasedplanService.setHandler(phasedplanHandler);

        IUserHandler userHandler = new UserHandler(new UserRepository());
        UserService.setHandler(userHandler);

        IIncidentHandler recipeHandler = new IncidentHandler(new IncidentRepository());
        IncidentService.setHandler(recipeHandler);

        // Tells the Jersey Servlet which REST service/class to load
        jerseyServlet.setInitParameter("jersey.config.server.provider.packages", "SimsRESTServer.restservices");

        try {
            jettyServer.start();
            jettyServer.join();;
        } catch (Exception e) {
            Logger.getInstance().log(e);
        } finally {
            jettyServer.destroy();
        }
    }
}
