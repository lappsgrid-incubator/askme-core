package org.lappsgrid.askme.core

/**
 *
 */
class Configuration {

    public static String PROPERTY_HOST = "RABBIT_HOST"
    public static String PROPERTY_USERNAME = "RABBIT_USERNAME"
    public static String PROPERTY_PASSWORD = "RABBIT_PASSWORD"
    public static String PROPERTY_EXCHANGE = "RABBIT_EXCHANGE"
    public static String PROPERTY_GALAXY_KEY = "GALAXY_KEY"
    public static String PROPERTY_ELASTICHOST = "ELASTIC_HOST"
    public static String PROPERTY_ELASTICPORT = "ELASTIC_PORT"

    public final String HOST
    public final String EXCHANGE
    public final String USERNAME
    public final String PASSWORD
    public final String GALAXY_KEY
    public final String ELASTICHOST
    public final String ELASTICPORT

    public final String QUERY_MBOX = 'query.mailbox'
    public final String SOLR_MBOX = 'solr.mailbox'
    public final String ELASTIC_MBOX = 'elastic.mailbox'
    public final String RANKING_MBOX = 'ranking.mailbox'
    public final String NLP_MBOX = 'nlp.mailbox'
    public final String WEB_MBOX = 'web.mailbox'
    public final String METRICS_MBOX = 'metrics.mailbox'

    Configuration() {
        File file = new File("/run/secrets/askme.ini")
        if (!file.exists()) {
            file = new File("/etc/lapps/askme.ini")
        }
        // Load the ini file if it exists.
        if (file.exists()) {
            Properties properties = new Properties()
            properties.load(new FileReader(file))
            HOST = properties.getProperty(PROPERTY_HOST, "locahost")
            USERNAME = properties.getProperty(PROPERTY_USERNAME, "guest")
            PASSWORD = properties.getProperty(PROPERTY_PASSWORD, "guest")
            EXCHANGE = properties.getProperty(PROPERTY_EXCHANGE, "askme")
            GALAXY_KEY = properties.getProperty(PROPERTY_GALAXY_KEY, 'BADF00D')
			ELASTICHOST = properties.getProperty(PROPERTY_ELASTICHOST, "locahost");
			ELASTICPORT = properties.getProperty(PROPERTY_ELASTICPORT, "9200");
        }

        // Allow environment variables or System properties to override values from the ini file.
        HOST = getPropertyValue(PROPERTY_HOST, HOST)
        USERNAME = getPropertyValue(PROPERTY_USERNAME, USERNAME)
        PASSWORD = getPropertyValue(PROPERTY_PASSWORD, PASSWORD)
        EXCHANGE = getPropertyValue(PROPERTY_EXCHANGE, EXCHANGE)

        System.setProperty("RABBIT_USERNAME", USERNAME)
        System.setProperty("RABBIT_PASSWORD", PASSWORD)
    }

    private String getPropertyValue(String name, String defaultValue) {
        String value = System.getenv(name)
        if (value) {
            return value
        }
        value = System.getProperty(name)
        if (value) {
            return value
        }
        return defaultValue
    }
}
