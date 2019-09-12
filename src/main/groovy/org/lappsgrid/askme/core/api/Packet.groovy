package org.lappsgrid.askme.core.api

import org.lappsgrid.askme.core.api.Query
import org.lappsgrid.askme.core.api.Scores
import org.lappsgrid.askme.core.model.Document

/**
 *
 */
class Packet {
    Query query
    Scores scores
    List<Document> documents
}
