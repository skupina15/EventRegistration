package si.fri.rso.skupina15.dtos;

public class NotificationDTO {
    private String host_email;
    private String participant_name;
    private String participant_email;
    private String event_title;


    public NotificationDTO(String host_email, String participant_name, String participant_email, String event_title) {
        this.host_email = host_email;
        this.participant_name = participant_name;
        this.participant_email = participant_email;
        this.event_title = event_title;
    }

    public String getHost_email() {
        return host_email;
    }
    public void setHost_email(String host_email) {
        this.host_email = host_email;
    }

    public String getParticipant_name() {
        return participant_name;
    }

    public void setParticipant_name(String participant_name) {
        this.participant_name = participant_name;
    }

    public String getParticipant_email() {
        return participant_email;
    }

    public void setParticipant_email(String participant_email) {
        participant_email = participant_email;
    }

    public String getEvent_title() {
        return event_title;
    }

    public void setEvent_title(String event_title) {
        this.event_title = event_title;
    }
}
