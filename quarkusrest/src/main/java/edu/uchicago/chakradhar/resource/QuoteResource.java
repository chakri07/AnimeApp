package edu.uchicago.chakradhar.resource;

import edu.uchicago.chakradhar.models.Quote;
import edu.uchicago.chakradhar.services.QuoteService;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

@Path("/quote")
public class QuoteResource {

    @Inject
    QuoteService quoteService;

    @GET
    public List<Quote> getAll() {
        return quoteService.getAll();
    }

    @POST
    public List<Quote> add(Quote quote) {
        return quoteService.add(quote);
    }

//    @GET
//    @Path("{id}")
//    public Quote getFromId(@PathParam("id") String id) {
//        return quoteService.getFromId(id);
//    }

    @GET
    @Path("{email}")
    public List<Quote> getFromEmail(@PathParam("email") String email) {
        return quoteService.getFromEmail(email);
    }

    @GET
    @Path("/paged/{page}")
    public List<Quote> paged(@PathParam("page") int page) {
        return quoteService.paged(page);
    }

    @DELETE
    @Path("{id}")
    public String delete(@PathParam("id") String id) {
        return quoteService.delete(id);
    }

    @PATCH
    @Path("{id}")
    public String updateField(@PathParam("id") String id, Quote newQuote) {
        return quoteService.updateField(id, newQuote);
    }

}