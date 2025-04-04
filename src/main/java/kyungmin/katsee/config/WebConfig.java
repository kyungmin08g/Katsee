package kyungmin.katsee.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
      .allowedOrigins("*")
      .allowedMethods("*");
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/admin/**")
      .addResourceLocations("classpath:/static/admin/");
    registry.addResourceHandler("/user/**")
      .addResourceLocations("classpath:/static/user/");
    registry.addResourceHandler("/images/**")
      .addResourceLocations("classpath:/static/images/");
  }
}
