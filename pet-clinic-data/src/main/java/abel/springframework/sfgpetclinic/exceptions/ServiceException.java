package abel.springframework.sfgpetclinic.exceptions;

public class ServiceException extends RuntimeException {
    private static final String MESSAGE = "Service Level Exception";

    public ServiceException(String source, String message) {
        System.out.println("A " + MESSAGE + " has ocurred in: " + source + " because of " + message);
    }
}
