package kyungmin.katsee.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPI() {
    Info info = new Info()
      .title("관심사 동일 친구 매칭 서비스")
      .description("Katsee API")
      .version("v1");

    String jwtSchemeName = "JWT TOKEN";
    SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);

    Components components = new Components()
      .addSecuritySchemes(jwtSchemeName, new SecurityScheme()
        .name("Authorization")
        .type(SecurityScheme.Type.HTTP)
        .scheme("bearer")
        .bearerFormat("JWT")
        .in(SecurityScheme.In.HEADER));

    return new OpenAPI()
      .info(info)
      .addServersItem(new Server().url("/"))
      .addSecurityItem(securityRequirement)
      .components(components);
  }
}
