package com.milespomeroy;

import com.yammer.dropwizard.config.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class HelloWorldConfiguration extends Configuration {
    @NotEmpty
    @JsonProperty
    private String template;

    @Valid
    @NotNull
    @JsonProperty
    private DatabaseConfiguration database = new DatabaseConfiguration();

    @NotEmpty
    @JsonProperty
    private String defaultName = "Stranger";

    public String getTemplate() {
        return template;
    }

    public String getDefaultName() {
        return defaultName;
    }

    public DatabaseConfiguration getDatabaseConfiguration() {
        return database;
    }
}
