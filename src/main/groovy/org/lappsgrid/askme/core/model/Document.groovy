package org.lappsgrid.askme.core.model

import org.lappsgrid.askme.core.api.Scores

/**
 *
 */
class Document implements Comparable<Document> {

    String id
    String pmid
    String pmc
    String doi
    String year

    Section title
    Section articleAbstract
    Section body

    String path
    String url
    String license

    /** The total score for the document. */
    float score

    /** The scores for each section. */
    Map<String, Scores> scores

    Document() {
        score = 0.0f
        scores = [:]
    }

    void addScore(String section, String algorithm, float value) {
        Scores forSection = scores.get(section)
        if (forSection == null) {
            forSection = new Scores()
            scores.put(section, forSection)
        }
        forSection[algorithm] = value
    }

    @Override
    int compareTo(Document o) {
        return this.score <=> o.score
    }
}
