package org.lappsgrid.askme.core;

import java.util.function.Consumer

/**
 *
 */
class CSVParser {

    enum State {
        start, in_field, single_quote, end_quote, skip
    }

    CSVParser() {

    }

    List<String[]> parse(File file) {
        return parse(file.newInputStream())
    }

    List<String[]> parse(InputStream stream) {
        List<String[]> result = []
        stream.eachLine {
            result.add(parseLine(it))
        }
        return result
    }

    List<String[]> parse(Reader reader) {
        List<String[]> result = []
        reader.eachLine {
            result.add(parseLine(it))
        }
        return result
    }
	
	
	public Object[] parseLine(String line) {
		//println "Parsing line: ${line.length()}"
		char[] chars = line.toCharArray();
								
		List<String> fields =  new ArrayList<String>();
		StringBuilder buffer = new StringBuilder();
		State state = State.start;
		State previous = State.start;
		for (int i = 0; i < chars.length; ++i) {
			
			char ch = chars[i];
			char nextch='\"';;
			if(i+1<chars.length){
				nextch = chars[i+1];
			}
			
			if (state == State.start) {
				if (ch == '"') {
					state = State.single_quote;
				}
				else {
					state = State.in_field;
					buffer.append(ch);
				}
			}
			else if (state == State.skip) {
				// We are skipping a second double quote.
				state = previous;
				previous = State.start;
			}
			else if (state == State.in_field) {
				if (ch == ',' && nextch == '\"') {
					fields.add(buffer.toString());
					buffer = new StringBuilder();
					state = State.start;
				}
				else if(ch== ',' && nextch == '}'){
					fields.add(buffer.toString());
					buffer = new StringBuilder();
					state = State.start;
				}
				else if(ch=='{') {
					buffer = new StringBuilder();
					state = State.start;
				}
				else if (ch == '"') {
					if (chars[i+1] == '"') {
						buffer.append('"');
						previous = state;
						state = State.skip;
					}
					else {
						state = State.end_quote;
					}
				}
				else {
					buffer.append(ch);
				}
			}
			else if (state == State.single_quote) {
				if (ch == '"') {
					if (i+1<chars.length && chars[i+1] == '"') {
						buffer.append('"');
						previous = state;
						state = State.skip;
					}
					else {
		                //buffer.append(ch)
						state = State.end_quote;
					}
				}
				else {
					buffer.append(ch);
				}
			}
			else if ( state == State.end_quote) {
				if (ch == ',') {
					fields.add(buffer.toString());
					buffer = new StringBuilder();
					state = State.start;
				}
				else if (ch == '}'){
					fields.add(buffer.toString());
					buffer = new StringBuilder();
					state = State.start;
				}
				else {
					buffer.append(ch);
					state = State.in_field;
				}
			}
			else {
				//throw new Exception("Invalid state $state");
			}
		}
								
		return fields.toArray();
	}
	
	String [] getQueryIDAndValue(String line) {
		
		String [] tokens = line.split(":");
		
		// get ID
		String [] identry = tokens[1].split(",");
		String id = identry[0];
	
		// get VALUE
		String value = tokens[2];
		if(value.endsWith("metadata")) {
			value = value.substring(0, (value.length()-10));
			value = value.substring(0, (value.length()-1));
		}else {
			value = value.substring(0, (value.length()-1)); 
		}
		
			
		System.out.println("ID:" + id);
		System.out.println("Query:" + value );
		
		
		String [] queryIDValue = new String [2];
		queryIDValue[0] = id;
		queryIDValue[1] = value;
		return (queryIDValue);
	}
}

