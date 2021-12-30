package si.fri.rso.skupina15.v1.resources;

import com.kumuluz.ee.rest.beans.QueryParameters;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import si.fri.rso.skupina15.beans.CDI.RegistrationBean;
import si.fri.rso.skupina15.beans.CDI.UserBean;
import si.fri.rso.skupina15.beans.config.RestProperties;
import si.fri.rso.skupina15.config.Properties;
import si.fri.rso.skupina15.dtos.NotificationDTO;
import si.fri.rso.skupina15.entities.Item;
import si.fri.rso.skupina15.entities.Persone;
import si.fri.rso.skupina15.entities.Registration;
import si.fri.rso.skupina15.services.NotificationApi;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.logging.Logger;

@ApplicationScoped
@Path("registrations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RegistrationResource {
    @Inject
    private RestProperties restProperties;

    private Logger log = Logger.getLogger(RegistrationResource.class.getName());

    @Inject
    private RegistrationBean registrationBean;

    @Inject
    private UserBean userBean;

    @Inject
    private Properties properties;

    @Context
    protected UriInfo uriInfo;

    @Inject
    @RestClient
    private NotificationApi notificationApi;

    @Operation(description = "Returns a list of registrations.", summary = "List of registrations", tags = "registrations", responses = {
            @ApiResponse(responseCode = "200",
                    description = "List of registrations",
                    content = @Content(
                            array = @ArraySchema(schema = @Schema(implementation = Registration.class))),
                    headers = {@Header(name = "X-Total-Count", schema = @Schema(type = "integer"))}
            )})
    @GET
    public Response getRegistrations() {
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        Long count = registrationBean.registrationsCount(query);
        List<Registration> registrations = registrationBean.findAllRegistrations(query);

        return Response.ok(registrations).header("X-Total-Count", count).build();
    }


    @Operation(description = "Returns selected registrations.", summary = "Selected registration", tags = "registration", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Selected registration",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Registration.class))
            )})
    @GET
    @Path("{id}")
    public Response getRegistration(@Parameter(description = "The id that needs to be fetched", required = true)
                                        @PathParam("id") Integer id){
        Registration i = registrationBean.findRegistration(id);
        if (i != null){
            return Response.ok(i).build();
        }
        else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Operation(description = "Add registration.", summary = "New registration", tags = "registration", responses = {
            @ApiResponse(responseCode = "200",
                    description = "New registration",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Registration.class))
            )})
    @POST
    public Response addRegistration(@RequestBody(description = "Created registration object", required = true,
            content = @Content(schema = @Schema(implementation = Registration.class))) Registration i){
        Registration registration = registrationBean.createRegistration(i);
        if(registration == null){
            log.info("Invalid API input.");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        if(!properties.getMaintenece_mode()){
            log.info("sending");
            Persone p = userBean.findPersone(registration.getPersone().getId_persone());
            Persone host = userBean.findPersone(registration.getEvent().getHost().getId_persone());
            if(p.getEmail() != null && p.getUser_name() != null && host.getEmail() != null ) {

                // start image processing over async API
                CompletionStage<String> stringCompletionStage =
                        notificationApi.processImageAsynch(new NotificationDTO(host.getEmail(),
                                p.getUser_name(), p.getEmail(), i.getEvent().getTitle()));

                stringCompletionStage.whenComplete((s, throwable) -> System.out.println(s));
                stringCompletionStage.exceptionally(throwable -> {
                    log.severe(throwable.getMessage());
                    return throwable.getMessage();
                });
            } else {
                log.info("Incomplete registration");
            }
        }

        return Response.status(Response.Status.CREATED).entity(registration).build();
    }

    @Operation(description = "Change selected registration.", summary = "Change registration", tags = "registration",
            responses = {@ApiResponse(responseCode = "200",
                    description = "Changed registration",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Registration.class))
            )})
    @PUT
    @Path("{id}")
    public Response UpdateRegistration(@Parameter(description = "The id that needs to be updated", required = true)
                                           @PathParam("id") Integer id, @RequestBody(description = "Created registration object",
            required = true, content = @Content(schema = @Schema(implementation = Registration.class))) Registration i){
        Registration registration = registrationBean.updateRegistration(id, i);
        if(registration == null){
            log.info("Registration for update does not exist");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.CREATED).entity(registration).build();
    }

    @Operation(description = "Delete choosen registration.", summary = "Deleted registration", tags = "registration", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Deleted registration",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Integer.class))
            )})
    @DELETE
    @Path("{id}")
    public Response deleteRegistration(@Parameter(description = "The id that needs to be deleted", required = true)
                                           @PathParam("id") Integer id){
        Integer registration = registrationBean.deleteRegistration(id);
        return Response.status(Response.Status.OK).entity(registration).build();
    }
}
