package co.edu.icesi.dev.uccareapp.transport.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan("co.edu.icesi.dev.uccareapp.controller")
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    ProductFormatter productformatter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(productformatter);
    }
}
