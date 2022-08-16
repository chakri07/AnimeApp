package edu.uchicago.chakradhar.services;


import edu.uchicago.chakradhar.models.Quote;
import edu.uchicago.chakradhar.repos.QuoteMongodbRepo;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class QuoteService {

    @Inject
    QuoteMongodbRepo quoteMongodbRepo;

    public List<Quote> getAll() {
        return quoteMongodbRepo.findAll();
    }

    public List<Quote> add(Quote quote) {
        return quoteMongodbRepo.add(quote);
    }

    public List<Quote> getFromEmail(String email) {
        return quoteMongodbRepo.getByEmail(email);
    }

    public Quote getFromId(String id) {
        return  quoteMongodbRepo.get(id);
    }

    public List<Quote> paged(int page){
        return  quoteMongodbRepo.paged(page);
    }

    public String delete(String id){
        return quoteMongodbRepo.delete(id);
    }

    public String updateField(String id, Quote newQuote){
        return quoteMongodbRepo.updateField(id, newQuote);
    }
}
