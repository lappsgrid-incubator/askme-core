package org.lappsgrid.askme.core.model

import groovy.transform.MapConstructor
import org.lappsgrid.askme.core.api.Scores

/**
 *
 */
@MapConstructor
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
	
	String UserLicense;
	String authKeyWords;
	String authors;
	String contents_url;
	String contents;
	String coverDate;
	String eissn;
	String endingPage;
	String fetched;
	String file_urls;
	String filepath;
	String issn;
	String issue;
	String metadata_update;
	String online_pubdate;
	String openaccess;
	String preprint;
	String priority;
	String publication_date;
	String publisher;
	String pubname;
	String sha1;
	String source;
	String startingPage;
	String tags;
	String time;
	String vol;


	/** native score from search platform **/
	float nscore;

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
