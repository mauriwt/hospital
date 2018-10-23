package hospital.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Configuration
public class ClientAngularConfig {

  @RestController
  @CrossOrigin
  @RequestMapping(value = "base/env")
  public class RestManager {

    @Autowired
    private Environment env;

    @RequestMapping(method = RequestMethod.GET)
    public String enviroment() {
      return env.getProperty("spring.profiles.active");
    }

  }

}
