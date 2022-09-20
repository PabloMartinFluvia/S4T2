package cat.ITAcademy.PabloMartin.S4T2.model.services.exceptions;

public class NotFoundException extends RuntimeException {
    
    private static final String DESCRIPTION = "Not Found Exception (404)";

    public NotFoundException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}
