package CabDriver.CabDriverBackend.exceptions;

public class ResourceDoesNotExistException extends RuntimeException {
  public ResourceDoesNotExistException(String message) {
    super(message);
  }
}
