package si.fri.rso.skupina15.v1;

<<<<<<< HEAD
import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.kumuluz.ee.discovery.annotations.RegisterService;
=======
//import com.kumuluz.ee.discovery.annotations.RegisterService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
>>>>>>> 9fe8021ad0c5be16f35e4ede81ae6930ad9d6429

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

//@RegisterService
@OpenAPIDefinition(info = @Info(title = "API for registrations", version = "v1", contact = @Contact(email = "rso@fri.uni-lj.si"), license = @
        License(name = "dev"), description = "API for registrations."), servers = @Server(url ="http://20.120.67.65:8080/registrations/v1"))
@ApplicationPath("v1")
@CrossOrigin(name = "my-resource")
public class EventRegistrationApplication extends Application {
}
