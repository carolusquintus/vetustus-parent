package com.carm.vetustus.rest;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import static javax.ws.rs.core.MediaType.*;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carm.vetustus.domain.OfficeDTO;
import com.carm.vetustus.model.Office;
import com.carm.vetustus.persist.dao.OfficeDao;
import com.carm.vetustus.service.OfficeService;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Component
@Path("/offices")
public class OfficeResource {

	private static Logger LOG = LoggerFactory.getLogger(OfficeResource.class);

	@Autowired
	private OfficeService officeService;

//	@POST
//	@Consumes({ APPLICATION_JSON })
//	@Produces({ TEXT_HTML })
//	public Response createOffice(Office office,
//			@Context HttpServletRequest request) {
//		final String key = officeDao.save(office);
//		final String url = request.getRequestURL().toString();
//
//		return Response.status(Response.Status.CREATED)
//				.entity("A new office has been added.").header("Location", url)
//				.build();
//	}

	@GET
	@Path("{id}")
	@Produces({ APPLICATION_JSON, APPLICATION_XML})
	public Response getOfficeByOfficeCode(
			@PathParam("id") String id)
			throws JsonGenerationException, JsonMappingException, IOException {

		final OfficeDTO dto = officeService.getOfficeById(id);

		LOG.info(dto.toString());

		return Response.status(200).entity(dto)
				.header("Access-Control-Allow-Headers", "X-extra-header")
				.allow("OPTIONS").build();
	}
}
