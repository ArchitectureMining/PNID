package org.architecturemining.pnid.models.xlog;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.deckfour.xes.model.XAttributeMap;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.impl.XEventImpl;

/**
 * @author J.J. Ree
 *
 * Model for an event with the assignment of identifiers to variables.
 *
 */
public class EventModel extends XEventImpl  {
	
	private Map<String,Integer> varMap;
	
	public EventModel(XEvent event) {
		super(event.getID());
		this.initVarMap(event);
	}
	
	public Map<String,Integer> getVarMap() {
		return varMap;
	}

	public void setVarMap(Map<String,Integer> varMap) {
		this.varMap = varMap;
	}
	
	private void initVarMap(XEvent event){
		this.varMap = new HashMap<String,Integer>();
		
		// Get assignments from resource attribute.
		XAttributeMap attrs = event.getAttributes();
		String resourcesValue = "";
		
		if (attrs.containsKey("org:resource"))
			resourcesValue = attrs.get("org:resource").toString();
		
		// Split on ","
		String[] assignments = Arrays.stream( resourcesValue.split("\\s*,\\s*")).filter(s -> !s.isEmpty()).toArray(String[]::new);
		
		// Parse all variable assignments.
		for (String ass : assignments) {
			String var = ass.replaceAll("[^a-zA-Z ]", "");
			Integer identifier = Integer.parseInt(ass.replaceAll("[^0-9 ]", ""));
			
			if (varMap.containsKey(var) == true)
				System.out.print("Error while preprocessing log: duplicate identifier assignment at an event");
				
			varMap.put(var, identifier);
		}
	}
}
