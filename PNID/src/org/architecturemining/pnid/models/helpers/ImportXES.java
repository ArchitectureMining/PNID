package org.architecturemining.pnid.models.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import org.deckfour.xes.extension.std.XConceptExtension;
import org.deckfour.xes.factory.XFactory;
import org.deckfour.xes.factory.XFactoryRegistry;
import org.deckfour.xes.in.XMxmlParser;
import org.deckfour.xes.in.XParser;
import org.deckfour.xes.in.XParserRegistry;
import org.deckfour.xes.model.XLog;

public class ImportXES {

	  private final XFactory factory; 
	  
	  public ImportXES() {
	    factory = XFactoryRegistry.instance().currentDefault();
	  }

	  private InputStream getInputStream(File file) throws IOException {
	    FileInputStream stream = new FileInputStream(file);
	    return stream;
	  }
	  
	  public XLog ReadLog(String fileName) throws IOException {
	    File logFile = new File(fileName);
	    return ReadLog(logFile);
	  }

	  public XLog ReadLog(File file) throws IOException {
	    
	    InputStream input = getInputStream(file);
	    String fileName = file.getName();  
	    XParser parser = new XMxmlParser(factory);
	    Collection<XLog> logs = null;
	    
	    try {
	      logs = parser.parse(input);
	    } catch (Exception e) {
	      logs = null;
	    }
	    
	    // Try another parser
	    if (logs == null) {
	      for (XParser p : XParserRegistry.instance().getAvailable()) {
	        if (p == parser) {
	          continue;
	        }
	        try {
	          logs = p.parse(input);
	          if (logs.size() > 0) {
	            break;
	          }
	        } catch (Exception e1) {
	          logs = null;
	        }
	      }
	    }

	    // Notify when log empty
	    if (logs == null || logs.size() == 0)
	      System.out.println("Warning! No processes contained in log!");

	    XLog log = logs.iterator().next();
	    if (XConceptExtension.instance().extractName(log) == null)
	      XConceptExtension.instance().assignName(log, "Anonymous log imported from " + fileName);

	    if (log.isEmpty())
	      System.out.println("No process instances contained in log!");

	    return log;
	  }
	  
	  public static String GetLogFileExtension(String fileName) {
	    if (fileName.endsWith(".xes")) 
	    	return ".xes";
	    
	    return fileName.substring(fileName.lastIndexOf("."));
	  }
	  
	  public static String GetLogFileName(String fileName) {
	    String ext = GetLogFileExtension(fileName);
	    return fileName.substring(0, fileName.lastIndexOf(ext));
	  }
	  
	  public static XLog ReadXLog(String logFile) throws IOException {
	    ImportXES read = new ImportXES();
	    return read.ReadXLog(logFile);
	  }
	}