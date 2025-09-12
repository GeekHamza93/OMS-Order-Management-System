package com.stackflow.oms.resource;

import com.stackflow.oms.api.dto.CustomerDTO;
import com.stackflow.oms.api.mapper.CustomerMapper;
import com.stackflow.oms.domain.Customer;
import com.stackflow.oms.repository.CustomerRepository;
import com.stackflow.oms.service.CustomerService;
import com.stackflow.oms.support.Pagination;
import io.smallrye.common.annotation.Blocking;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Customers", description = "Customer CRUD endpoints")
public class CustomerResource {

    @Inject CustomerRepository customers;
    @Inject CustomerService service;

    @GET
    @Operation(summary = "List customers")
    @Blocking
    public Response list(@QueryParam("page") @DefaultValue("0") int page,
                         @QueryParam("size") @DefaultValue("20") int size) {
        var p = Pagination.of(page, size);
        var q = customers.findAll().page(p.page(), p.size());
        List<CustomerDTO> items = q.list().stream().map(CustomerMapper::toDTO).toList();
        return Response.ok(items)
                .header("X-Total-Count", customers.count())
                .build();
    }

    @GET @Path("/{id}")
    @Operation(summary = "Get one customer by id")
    public CustomerDTO get(@PathParam("id") Long id) {
        Customer c = customers.findById(id);
        if (c == null) throw new NotFoundException();
        return CustomerMapper.toDTO(c);
    }

    @POST
    @Operation(summary = "Create a customer")
    public Response create(@Valid CustomerDTO dto) {
        Customer c = service.create(CustomerMapper.toEntity(dto));
        return Response.status(Response.Status.CREATED).entity(CustomerMapper.toDTO(c)).build();
    }

    @PUT @Path("/{id}")
    @Operation(summary = "Update a customer")
    public CustomerDTO update(@PathParam("id") Long id, @Valid CustomerDTO dto) {
        Customer updated = service.update(id, CustomerMapper.toEntity(dto));
        if (updated == null) throw new NotFoundException();
        return CustomerMapper.toDTO(updated);
    }

    @DELETE @Path("/{id}")
    @Operation(summary = "Delete a customer")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        if (!customers.deleteById(id)) throw new NotFoundException();
    }
}
