import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.beans.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.servlet.Filter;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;

@RestController
@Configuration
@EnableAutoConfiguration
public class Example {

	@RequestMapping("/")
	String home() {
		return "Hello World!";
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Example.class, args);
	}

  @Bean
  public FilterRegistrationBean someFilterRegistration() {

      FilterRegistrationBean registration = new FilterRegistrationBean();
      registration.setFilter(someFilter());
      registration.addUrlPatterns("/*");
      registration.addInitParameter("paramName", "paramValue");
      registration.setName("someFilter");
      registration.setOrder(1);
      return registration;
  } 

  @Bean 
  ServletWebServerFactory servletWebServerFactory(){
    return new TomcatServletWebServerFactory();
  }

  public Filter someFilter() {
      return new CustomFilter();
  }

}