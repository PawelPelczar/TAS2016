package pl.amu.service.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import pl.amu.service.rest.HelloResource;
import pl.amu.service.rest.QuestionResource;
import pl.amu.service.rest.SurveyResource;
import pl.amu.service.rest.UserResource;
import pl.amu.service.rest.exception.CustomExceptionMapper;

@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(HelloResource.class);
        register(UserResource.class);
        register(QuestionResource.class);
        register(SurveyResource.class);
        register(CustomExceptionMapper.class);
    }
}
