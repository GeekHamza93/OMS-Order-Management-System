package com.stackflow.oms.resource;


import com.stackflow.oms.domain.Order;
import com.stackflow.oms.repository.OrderRepository;
import com.stackflow.oms.service.OrderService;
import com.stackflow.oms.support.Pagination;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/api/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {


    @Inject OrderRepository orders;
    @Inject OrderService service;


    @GET
    public Response list(@QueryParam("page") @DefaultValue("0") int page,
                         @QueryParam("size") @DefaultValue("20") int size,
                         @QueryParam("status") String status,
                         @QueryParam("customerId") Long customerId) {

        var p = Pagination.of(page, size);

        // Convertit le status String -> enum (sécurisé)
        Order.Status st = null;
        if (status != null && !status.isBlank()) {
            try {
                st = Order.Status.valueOf(status.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new BadRequestException("status must be one of: CREATED, PAID, SHIPPED, CANCELED");
            }
        }

        // Construit dynamiquement le where + paramètres
        var jpql = new StringBuilder();
        var params = new java.util.ArrayList<>();

        if (st != null) {
            jpql.append("status = ?1");
            params.add(st);
        }
        if (customerId != null) {
            if (!params.isEmpty()) jpql.append(" and ");
            jpql.append("customerId = ?").append(params.size() + 1);
            params.add(customerId);
        }

        var panacheQuery = jpql.length() == 0
                ? orders.findAll()
                : orders.find(jpql.toString(), params.toArray());

        var items = panacheQuery.page(p.page(), p.size()).list(); // ✅ plus de p.toPage()
        long total = panacheQuery.count();

        return Response.ok(items)
                .header("X-Total-Count", total)
                .build();
    }



    @GET @Path("/{id}")
    public Order get(@PathParam("id") Long id) {
        Order o = orders.findById(id);
        if (o == null) throw new NotFoundException();
        return o;
    }


    @POST
    public Response create(@Valid Order o) {
        return Response.status(Response.Status.CREATED).entity(service.create(o)).build();
    }


    @PUT @Path("/{id}")
    public Order update(@PathParam("id") Long id, @Valid Order changes) {
        Order updated = service.update(id, changes);
        if (updated == null) throw new NotFoundException();
        return updated;
    }


    @DELETE @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        if (!orders.deleteById(id)) throw new NotFoundException();
    }
}