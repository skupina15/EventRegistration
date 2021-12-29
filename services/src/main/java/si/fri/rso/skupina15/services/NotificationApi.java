package si.fri.rso.skupina15.services;

import javax.enterprise.context.Dependent;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import si.fri.rso.skupina15.dtos.NotificationDTO;

import java.util.concurrent.CompletionStage;

@Path("notifications")
@RegisterRestClient(configKey="notification-api")
@Dependent
public interface NotificationApi {

    @POST
    CompletionStage<String> processImageAsynch(NotificationDTO notificationDto);

}
