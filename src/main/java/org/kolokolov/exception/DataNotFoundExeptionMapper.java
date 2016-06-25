package org.kolokolov.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.kolokolov.model.ErrorMessage;

@Provider
public class DataNotFoundExeptionMapper implements ExceptionMapper<DataNotFoundException>{
	
	

	public DataNotFoundExeptionMapper() {}

	@Override
	public Response toResponse(DataNotFoundException exception) {
		ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), 404, "http://javabrains.koushik.org");
		return Response.
				status(Status.NOT_FOUND).
				entity(errorMessage).
				build();
	}

}
