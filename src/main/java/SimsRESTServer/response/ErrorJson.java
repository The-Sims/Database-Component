package SimsRESTServer.response;

public class ErrorJson {
    private String message;

    public ErrorJson(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
