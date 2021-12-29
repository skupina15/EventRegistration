package si.fri.rso.skupina15.v1;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.kumuluz.ee.discovery.annotations.RegisterService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

//@RegisterService
@ApplicationPath("v1")
@CrossOrigin(name = "my-resource")
public class EventRegistrationApplication extends Application {
}
