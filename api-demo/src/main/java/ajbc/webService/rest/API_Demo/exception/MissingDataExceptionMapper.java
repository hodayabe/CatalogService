package ajbc.webService.rest.API_Demo.exception;
import ajbc.webService.rest.API_Demo.enums.InternalErrorCode;
import ajbc.webService.rest.API_Demo.models.ErrorMessage;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
@Provider
public class MissingDataExceptionMapper implements ExceptionMapper<MissingDataException>{

	@Override
	public Response toResponse(MissingDataException e) {
		ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), InternalErrorCode.INVALID_ID, "google.com");
		return Response.status(Status.NOT_FOUND)
		.entity(errorMessage)
		.build();
	}
}