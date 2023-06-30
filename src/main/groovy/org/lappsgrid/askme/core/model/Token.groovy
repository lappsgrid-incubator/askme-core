package org.lappsgrid.askme.core.model

/**
 *
 */
class Token {

    int start
    int end

    String word
    String lemma
    String pos
    String category

    public String toString() {
        return sprintf("<Token %s %s %s %s", category, start, end, word)
    }
}
