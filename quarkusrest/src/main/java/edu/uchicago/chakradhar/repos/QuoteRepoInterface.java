package edu.uchicago.chakradhar.repos;

import edu.uchicago.chakradhar.models.Quote;

import javax.ws.rs.PathParam;
import java.util.List;

public interface QuoteRepoInterface {

    List<Quote> findAll();

    List<Quote> add(Quote quote);

    Quote get(String id);

    List<Quote> getByEmail(String userEmail);

    List<Quote> paged(int page);

    String delete(@PathParam("id") String id);

    String updateField(@PathParam("id") String id, Quote newQuote);

}
