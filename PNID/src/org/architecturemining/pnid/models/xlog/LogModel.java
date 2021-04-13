package org.architecturemining.pnid.models.xlog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.architecturemining.pnid.models.helpers.ImportXES;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;

/**
 * @author J.J. Ree
 *
 * Model for a log containing a list of traces.
 *
 */
@SuppressWarnings("serial")
public class LogModel extends ArrayList<TraceModel>{
	private ArrayList<TraceModel> log;
	
	public LogModel(String path) throws IOException {
		LoadXLog(path);
	}
	
	public LogModel(XLog log) throws IOException {
		LoadXLog(log);
	}
	
	public void LoadXLog(String path) throws IOException {
		ImportXES helper = new ImportXES();
		SetXLog(helper.ReadLog(path));
	}
	
	public void LoadXLog(XLog log) throws IOException {
		SetXLog(log);
	}
	
	private void SetXLog(XLog xlog) {
		ArrayList<TraceModel> traces = new ArrayList<TraceModel>();
		
		for (XTrace trace : xlog) {
			traces.add(new TraceModel(trace));
		}
		
	    this.log = traces;
	 }
	  
	public ArrayList<TraceModel> GetXLog() {
	    return log;
	}
}
