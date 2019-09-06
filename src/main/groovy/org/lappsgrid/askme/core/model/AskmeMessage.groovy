package org.lappsgrid.askme.core.model

import org.lappsgrid.askme.core.api.Query
import org.lappsgrid.askme.core.api.Scores
import org.lappsgrid.rabbitmq.Message

/**
 *
 */
class AskmeMessage {
    Query query
    Scores scores
    List<Document> documents

//    AskmeMessage query(Query q) {
//        this.query = q
//        return this
//    }
//
//    AskmeMessage scores(Scores scores) {
//        this.scores = scores
//        retun this
//    }
//
//    AskmeMessage documents(List<Document> documents) {
//        this.documents = documents
//        return this
//    }
//
//    AskmeMessage document(Document document) {
//        if (this.documents == null) {
//            this.documents = []
//        }
//        this.documents.add(document)
//        return this
//    }
}
