package com.milespomeroy;

import com.milespomeroy.health.TemplateHealthCheck;
import com.milespomeroy.jdbi.UserDAO;
import com.milespomeroy.resources.HelloWorldResource;
import com.milespomeroy.resources.UserResource;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.jdbi.DBIFactory;
import com.yammer.dropwizard.jdbi.bundles.DBIExceptionsBundle;
import org.skife.jdbi.v2.DBI;

public class HelloWorldService extends Service<HelloWorldConfiguration> {
    public static void main(String[] args) throws Exception {
        new HelloWorldService().run(args);
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        bootstrap.setName("hello-world");

        bootstrap.addBundle(new AssetsBundle("/assets/", "/"));
        bootstrap.addBundle(new DBIExceptionsBundle());
    }

    @Override
    public void run(HelloWorldConfiguration configuration,
                    Environment environment) throws ClassNotFoundException  {
        final String template = configuration.getTemplate();
        final String defaultName = configuration.getDefaultName();
        environment.addResource(new HelloWorldResource(template, defaultName));
        environment.addHealthCheck(new TemplateHealthCheck(template));

        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDatabaseConfiguration(), "postgresql");
        final UserDAO dao = jdbi.onDemand(UserDAO.class);
        environment.addResource(new UserResource(dao));
    }

}
