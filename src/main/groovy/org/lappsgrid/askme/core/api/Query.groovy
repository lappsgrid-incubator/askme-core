package org.lappsgrid.askme.core.api

import org.lappsgrid.askme.core.model.Token;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Query
{
    /** The text of the question entered by the user. */
    String question;
    /** The Solr query that was generated from the question. */
    String query;
    /** The key terms from the question used to generate the query. */
    List<String> terms;
    int count

    public Query() { }

    public Query(Object object) {
        if (object instanceof String) {
            this.question = (String) object
        }
        else if (object instanceof Map) {
            Map map = (Map) object
            this.question = map.question
            this.query = map.query
            this.count = map.count
            if (map.terms) {
                this.terms = map.terms.clone()
            }
        }
        else {
            throw new UnsupportedOperationException("Invalid object type. Must be a Map or a String")
        }
    }

    public Query(String question, int count = 100, String query = null, List<String> terms = null) {
        this.question = question;
        this.query = query;
        this.terms = terms;
        this.count = count
    }

    public Query question(String question) {
        this.question = question;
        return this;
    }

    public Query query(String query) {
        this.query = query;
        return this;
    }

    public Query terms(List<String> terms) {
        this.terms = terms;
        return this;
    }

    public Query term(String term) {
        if (terms == null) {
            terms = new ArrayList<>();
        }
        this.terms.add(term);
        return this;
    }

    public Query count(int n) {
        this.count = n
        return this
    }

//    public void setQuestion(String question)
//    {
//        this.question = question;
//    }
//
//    public void setQuery(String query)
//    {
//        this.query = query;
//    }
//
//    public void setTerms(List<String> terms)
//    {
//        this.terms = terms;
//    }
//
//    public String getQuestion()
//    {
//        return question;
//    }
//
//    public String getQuery()
//    {
//        return query;
//    }
//
//    public List<String> getTerms()
//    {
//        return terms;
//    }

    public boolean contains(String term) {
        return terms.contains(term);
    }

    public boolean contains(Token token) {
        return terms.contains(token.getWord()) || terms.contains(token.getLemma());
    }
}