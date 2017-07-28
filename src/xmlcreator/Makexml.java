package xmlcreator;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
/**
 * 
 * @author Neelkant K
 *
 */
class Makexml {
	private static final Logger mylogger = Logger.getLogger(Makexml.class.getName());
	private Makexml(){}   
	/** 
	 * @param args
	 */
		 public static void main(String[] args){
			try{
				DocumentBuilderFactory builder =  DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = builder.newDocumentBuilder();
				Document doc = docBuilder.newDocument();
				Element root = doc.createElement("students");
				doc.appendChild(root);
				
				Attr attr = doc.createAttribute("type");
				attr.setNodeValue("1");
				root.setAttributeNode(attr);

				Element firstname = doc.createElement("Firstname");
				firstname.appendChild(doc.createTextNode("Neelkant"));
				root.appendChild(firstname);

				Element lastname = doc.createElement("Lastname");
				lastname.appendChild(doc.createTextNode("jain"));
				root.appendChild(lastname);

				Element yob = doc.createElement("year_of_birth");
				yob.appendChild(doc.createTextNode("13/06/1995"));
				root.appendChild(yob);
				
				
				TransformerFactory tf = TransformerFactory.newInstance();
				Transformer trans = tf.newTransformer();
				DOMSource doms = new DOMSource(doc);
				File file = new File("out.xml");
				StreamResult result = new StreamResult(file);
				trans.transform(doms, result);
				mylogger.info("file created ");
			}
			catch(Exception e){
				mylogger.log(Level.FINE, "exception"+e);
			}
		}
}
