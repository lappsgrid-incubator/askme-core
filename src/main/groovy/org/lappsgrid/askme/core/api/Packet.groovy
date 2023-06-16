package org.lappsgrid.askme.core.api

import groovy.transform.MapConstructor
import org.lappsgrid.askme.core.api.Query
import org.lappsgrid.askme.core.api.Scores
import org.lappsgrid.askme.core.model.Document

/**
 * Information that is passed between the various services as the
 * question/response is generated.
 */
class Packet {

    /** name of the Solr core/collection or ElasticSearch index */
    String core
    /** the query being processed */
    Query query
    /** Scores that are built up during processing */
    Scores scores
    /** Documents being processed */
    List<Document> documents

    Status status
    String message

    public String toString() {
        int count = (documents == null) ? 0 : documents.size()
        return sprintf("<Packet core=%s query=%s docs=%d>", core, query.query, count)
    }

}
