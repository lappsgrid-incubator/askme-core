package org.lappsgrid.askme.core.api


import org.lappsgrid.rabbitmq.Message

/**
 *
 */
class AskmeMessage extends Message<Packet> {

    AskmeMessage() {
        super()
    }

    AskmeMessage(String command, Object body, String... route) {
        super(command, body, route)
    }

    AskmeMessage(String command, Object body, Map<String, String> parameters, String... route) {
        super(command, body, parameters, route)
    }

    AskmeMessage(String command, Object body, List<String> route) {
        super(command, body, route)
    }

    AskmeMessage(String command, Object body, Map<String, String> parameters, List<String> route) {
        super(command, body, parameters, route)
    }

    public String toString() {
        return \
            sprintf("<AskmeMessage %s %s>", id, command) \
            + "\n    route=" + route \
            + "\n    parameters=" + parameters  \
            + "\n    body=" + body.toString()
    }
}
