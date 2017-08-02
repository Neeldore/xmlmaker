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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
/**
 * 
 * @author Neelkant K
 *
 */
class Makexml {
	private static final Logger mylogger = Logger.getLogger(Makexml.class.getName());
	private static final String FILE_NAME = "out.xml";
	private Makexml(){}   
	/** 
	 * @param args
	 */
		 public static void main(String[] args){
			try{
				DocumentBuilderFactory builder =  DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = builder.newDocumentBuilder();
				Document doc = docBuilder.newDocument();
				Element root = doc.createElement("root");
				doc.appendChild(root);
				createElement(doc , "orderdetails" ,  root,"Neelkant" );
				createElement(doc , "Lastname", root , "example");
				createElement(doc , "year_of_birth" , root , "example3" );
				transformIt(doc);
			}
			catch(Exception e){
				mylogger.log(Level.FINE, "exception"+e);
			}
			
		}
		 /**
		  * 
		  * @param doc contains the document
		  * @param elementname contains the element name
		  * @param elementtext contains the element text 
		  * @param appendto contains the element onto which you have to append the child 
		  */
		 public static void createElement(Document doc , String elementname  , Element appendto, String elementtext   ){
			 Element temp = doc.createElement(elementname);
			 temp.appendChild(doc.createTextNode(elementtext));
			 appendto.appendChild(temp);
		 }
		 /**
		  * 
		  * @param doc 
		  * @param elementname
		  * @param appendto
		  */
		 public static void createElement(Document doc , String elementname ,  Element appendto  ){
			 Element temp = doc.createElement(elementname);
			 appendto.appendChild(temp);
		 }
		 /**
		  * 
		  * @param doc
		  */
		 public static void transformIt(Document doc){
			 try{
			 TransformerFactory tf = TransformerFactory.newInstance();
				Transformer trans = tf.newTransformer();
				DOMSource doms = new DOMSource(doc);
				File file = new File(FILE_NAME);
				StreamResult result = new StreamResult(file);
				trans.transform(doms, result);
				mylogger.info("file created ");
			 }
			 catch(Exception e){
				 mylogger.info("file not created "+e);
			 }
		 }
}
