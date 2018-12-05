package utilities;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;
@Sources({
    "classpath:${env}.properties" 
})
public interface Environment extends Config {

    String url();	

    String username();

    String password();

    @Key("application.db.url")
    String geturl();
    
    @Key("application.db.hostname")
    String getDBHostname();

    @Key("application.db.port")
    int getDBPort();

    @Key("application.db.username")
    String getDBUsername();

    @Key("application.db.password")
    String getDBPassword();
}

//The way to impelent this interface in diffrecnt environement
//@Test
//@Parameters({"environment"})
//public void forTesting(String environemnt) {
//    ConfigFactory.setProperty("env", environemnt);
//    testEnvironment = ConfigFactory.create(Environment.class);
//    System.out.println(testEnvironment.url());
//
//}
