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
    /**
     * Name of the Solr core/collection or ElasticSearch index
     */
    String core
    /**
     * The query being processed.
     */
    Query query
    /**
     * Scores that are built up during processing
     */
    Scores scores
    /**
     * Documents being processed
     */
    List<Document> documents

    Status status
    String message

}
