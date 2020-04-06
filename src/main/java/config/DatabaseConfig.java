package config;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig
{

  @Bean
  public BasicDataSource dataSource() throws URISyntaxException
  {
    URI dbUri = new URI(System.getenv("DATABASE_URL"));
//    URI dbUri = new URI("postgres://rynfomecmeyrah:d927f8780daadbb5f4b0ea1916c067d25a0add3f7e82c111957c3758158d7d2e@ec2-50-17-90-177.compute-1.amazonaws.com:5432/d4jtmoh1u8c1am");
    String username = dbUri.getUserInfo().split(":")[0];
    String password = dbUri.getUserInfo().split(":")[1];
    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':'
        + dbUri.getPort() + dbUri.getPath();

    BasicDataSource basicDataSource = new BasicDataSource();
    basicDataSource.setUrl(dbUrl);
    basicDataSource.setUsername(username);
    basicDataSource.setPassword(password);

    return basicDataSource;
  }
}
