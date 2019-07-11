package automation.project3ds;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PrettyPrint {

	public static String formatXML(String xmlString) throws TransformerFactoryConfigurationError, TransformerException {
		Source xmlInput = new StreamSource(new StringReader(xmlString));
        StringWriter stringWriter = new StringWriter();
        StreamResult xmlOutput = new StreamResult(stringWriter);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        transformerFactory.setAttribute("indent-number", 5);
        Transformer transformer = transformerFactory.newTransformer(); 
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(xmlInput, xmlOutput);
        String str = xmlOutput.getWriter().toString();
        str = "<pre><xmp>"+str+"</xmp></pre>";
        return str;
	}
	
	public static String formatJson(JsonNode jsonString ) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String indented = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonString);
		indented = "<pre>"+indented+"</pre>";;
		return indented;
	}
}
