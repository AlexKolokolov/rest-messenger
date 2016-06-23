package org.kolokolov.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.kolokolov.model.ErrorMessage;

//Uncomment annotation to make this mapper work
//@Provider
public class GenericExeptionMapper implements ExceptionMapper<Exception>{
	
	public GenericExeptionMapper() {
		System.out.println("Mapper created");
	}

	@Override
	public Response toResponse(Exception exception) {
		ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), 500, "http://javabrains.koushik.org");
		return Response.
				status(Status.INTERNAL_SERVER_ERROR).
				entity(errorMessage).
				build();
	}
}
