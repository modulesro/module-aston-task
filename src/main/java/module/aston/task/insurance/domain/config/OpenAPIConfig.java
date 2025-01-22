package module.aston.task.insurance.domain.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI defineOpenApi() {
        final var server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Development");

        final var contact = new Contact();
        contact.setName("Branislav Balaz");
        contact.setEmail("branislav.balaz@gmail.com");

        Info information = new Info()
                .title("Insurance calculation API")
                .version("1.0")
                .description("API exposes endpoints to insurance calculation")
                .contact(contact);
        return new OpenAPI().info(information).servers(List.of(server));
    }
}