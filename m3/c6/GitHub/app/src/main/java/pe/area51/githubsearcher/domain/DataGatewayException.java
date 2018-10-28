package pe.area51.githubsearcher.domain;

public class DataGatewayException extends Exception {

    public DataGatewayException(String message) {
        super(message);
    }

    public DataGatewayException(String message, Throwable cause) {
        super(message, cause);
    }
}
