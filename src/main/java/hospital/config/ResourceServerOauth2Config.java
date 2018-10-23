package hospital.config;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;


@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerOauth2Config extends GlobalMethodSecurityConfiguration {

  private static Logger logger = LogManager.getLogger(ResourceServerOauth2Config.class);

  private static String oauth2ServerCheck;
  private static String oauth2ServerToken;
  private static String oauth2ResourceId;
  private static String oauth2Client;
  private static String oauth2Secret;
  
  private static String oauth2RedirectClient;
  private static String oauth2RedirectError;
  
  private static Map<String, Map<String, String>> sesiones = new HashMap<>();
  
  
  public static final String USERNAME = "username";
  public static final String UUID = "uuid";
  public static final String MAIL = "mail";
  public static final String DNI = "dni";
  public static final String APP = "aplication";
  public static final String SESIONDATE = "timestamp";
  
  public static final String AUTH_HEADER = "Authorization";
  public static final String AUTH_BASIC = "Basic ";
  public static final String REFRESH_TOKEN = "refresh_token";
  public static final String ACCESS_TOKEN = "access_token";
  public static final String SESSION_TOKEN = "token";
  

  private static String encodeBasicAuth(String username, String pass) {
    String authString = username + ":" + pass;
    byte[] authEncBytes = Base64.getEncoder().encode(authString.getBytes());
    return new String(authEncBytes);
  }

  @Override
  protected MethodSecurityExpressionHandler createExpressionHandler() {
    return new OAuth2MethodSecurityExpressionHandler();
  }
  
  public static Map<String, String> getUserSesion(HttpServletRequest request){
    String token = request.getHeader(AUTH_HEADER).substring("Bearer ".length());
    if(sesiones.get(token) == null){
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
      headers.add(AUTH_HEADER, AUTH_BASIC + ResourceServerOauth2Config
          .encodeBasicAuth(ResourceServerOauth2Config.oauth2Client, ResourceServerOauth2Config.oauth2Secret));
      MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
      map.add(SESSION_TOKEN, token);
      
      HttpEntity<MultiValueMap<String, String>> requestAuth = new HttpEntity<>(map, headers);
      RestTemplate client = new RestTemplate();
      
      try {
        ResponseEntity<ObjectNode> response = client.postForEntity(oauth2ServerCheck, requestAuth, ObjectNode.class);
        Map<String, String> datos = new HashMap<>();
        
        datos.put(USERNAME, response.getBody().get(USERNAME).asText());
        datos.put(MAIL, response.getBody().get(MAIL).asText());
        datos.put(UUID, response.getBody().get(UUID).asText());
        datos.put(APP, response.getBody().get(APP).asText());
        datos.put(DNI, response.getBody().get(DNI).asText());
        datos.put(SESIONDATE, response.getBody().get(SESIONDATE).asText());
        
        
        sesiones.put(token, datos);
        return datos;
      } catch (HttpClientErrorException e) {
        logger.error(e.getResponseBodyAsString(), e);
        return null;
      }
    }else{
      return sesiones.get(token);
    }
  }

  @Primary
  @Bean
  public RemoteTokenServices tokenService() {
    logger.info("REVISIÓN DE TOKEN EN: " + oauth2ServerCheck);
    RemoteTokenServices tokenService = new RemoteTokenServices();
    tokenService.setCheckTokenEndpointUrl(oauth2ServerCheck);
    tokenService.setClientId(oauth2Client);
    tokenService.setClientSecret(oauth2Secret);
    return tokenService;
  }

  @Configuration
  protected static class ResourceServer extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
      logger.info("APLICACIÓN LEVANTADA CON IDENTIFCADOR DE RECURSO: " + oauth2ResourceId);
      resources.resourceId(oauth2ResourceId);
    }
    // Permitir OPTIONS para CORS de todas las peticions cambiar en caso de
    // peticiones OPTIONS.
    @Override
    public void configure(HttpSecurity http) throws Exception {

      http.authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll();
    }

  }

  @RestController
  @CrossOrigin
  @RequestMapping(value = "oauth/autentication")
  protected static class OauthManager {

    @RequestMapping(method = RequestMethod.GET)
    public void autenticate(@RequestParam("code") String code, HttpServletRequest httpReq, HttpServletResponse http) {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
      headers.add(AUTH_HEADER, AUTH_BASIC + ResourceServerOauth2Config
          .encodeBasicAuth(ResourceServerOauth2Config.oauth2Client, ResourceServerOauth2Config.oauth2Secret));
      MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
      map.add("grant_type", "authorization_code");
      map.add("code", code);
      map.add("redirect_uri", httpReq.getRequestURL().toString());
      
      HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
      RestTemplate client = new RestTemplate();
      
      try {
        
        ResponseEntity<ObjectNode> response = client.postForEntity(oauth2ServerToken, request, ObjectNode.class);
        if(response.getBody().get("error") != null){
          http.sendRedirect(ResourceServerOauth2Config.oauth2RedirectError + "?error="
              + response.getBody().get("error").asText());
        }else if(response.getBody().get("scope") != null){
          
          this.getTokenInfo(response.getBody().get(ACCESS_TOKEN).asText());
          
          http.sendRedirect(ResourceServerOauth2Config.oauth2RedirectClient + "/"
              + response.getBody().get(ACCESS_TOKEN).asText() + "/" + response.getBody().get(REFRESH_TOKEN).asText()
              + "/" + response.getBody().get("expires_in").asText() + "/"
              + response.getBody().get("scope").asText());
        }else{
          http.sendRedirect(ResourceServerOauth2Config.oauth2RedirectError 
              + "?error=Actualmente%20no%20tienes%20ningun%20permiso%20para%20acceder%20a%20esta%20apliaci%C3%B3n");
        }
      }
      catch (HttpClientErrorException e) {
        logger.error(e.getResponseBodyAsString(), e);
        try {
          http.sendRedirect(ResourceServerOauth2Config.oauth2RedirectError 
              + "?error="+e.getResponseBodyAsString());
        } catch (IOException ioe) {
          logger.error("Error en sending redirect", ioe);
        }
      }
      catch (IOException e) {
        logger.error("Error en sending redirect", e);
      }
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ObjectNode refreshAutentication(@RequestBody Map<String, String> data) {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
      headers.add(AUTH_HEADER, AUTH_BASIC + ResourceServerOauth2Config
          .encodeBasicAuth(ResourceServerOauth2Config.oauth2Client, ResourceServerOauth2Config.oauth2Secret));
      MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
      map.add("grant_type", REFRESH_TOKEN);
      map.add(REFRESH_TOKEN, data.get("uuid_refresh_session"));
      
      HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
      RestTemplate client = new RestTemplate();
      
      try {
        ResponseEntity<ObjectNode> response = client.postForEntity(oauth2ServerToken, request, ObjectNode.class);
        
        ResourceServerOauth2Config.sesiones.remove(data.get(SESSION_TOKEN));
        return response.getBody();
      } catch (HttpClientErrorException e) {
        logger.error(e.getResponseBodyAsString(), e);
        return null;
      }
      
    }
    
    @RequestMapping(value="check", method = RequestMethod.GET)
    public ObjectNode checkAutentication(HttpServletRequest request) {
      return this.getTokenInfo(request.getHeader(AUTH_HEADER).substring("Bearer ".length()));
    }
    
    private ObjectNode getTokenInfo(String token) {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
      headers.add(AUTH_HEADER, AUTH_BASIC + ResourceServerOauth2Config
          .encodeBasicAuth(ResourceServerOauth2Config.oauth2Client, ResourceServerOauth2Config.oauth2Secret));
      MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
      map.add(SESSION_TOKEN, token);
      logger.info("REVISIÓN TOKEN: " + token);
      
      HttpEntity<MultiValueMap<String, String>> requestAuth = new HttpEntity<>(map, headers);
      RestTemplate client = new RestTemplate();
      
      try {
        ResponseEntity<ObjectNode> response = client.postForEntity(oauth2ServerCheck, requestAuth, ObjectNode.class);
        
        return response.getBody();
      } catch (HttpClientErrorException e) {
        logger.error(e.getResponseBodyAsString(), e);
      }
      
      return null;
    }
  }
  
  //Seteo de propiedades
  @Value("${spring.yauth.checkToken}")
  public void setCheckTokenURL(String url){
    ResourceServerOauth2Config.oauth2ServerCheck = url;
  }
  
  @Value("${spring.yauth.token}")
  public void setTokenURL(String url){
    ResourceServerOauth2Config.oauth2ServerToken = url;
  }
  
  @Value("${spring.yauth.resource}")
  public void setReourceID(String id){
    ResourceServerOauth2Config.oauth2ResourceId = id;
  }
  
  @Value("${spring.yauth.client}")
  public void setClient(String client){
    ResourceServerOauth2Config.oauth2Client= client;
  }
  
  @Value("${spring.yauth.secret}")
  public void setSecret(String secret){
    ResourceServerOauth2Config.oauth2Secret = secret;
  }
  
  @Value("${spring.yauth.local.login}")
  public void setLocalLoginURL(String url){
    ResourceServerOauth2Config.oauth2RedirectClient = url;
  }
  
  @Value("${spring.yauth.local.error}")
  public void setLocal(String url){
    ResourceServerOauth2Config.oauth2RedirectError = url;
  }

}
