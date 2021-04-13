package org.architecturemining.pnid.models.pnid;

import java.util.Set;

import org.informationsystem.ismsuite.pnidprocessor.petrinet.MarkedPetriNet;

/**
 * @author J.J. Ree
 *
 * Model for a Petri net with identifiers (PNID).
 *
 */
public class PNIDModel extends MarkedPetriNet {
	private MarkedPetriNet net;
	
	public PNIDModel(MarkedPetriNet net) {
		this.net = net;
	}
	
	public MarkedPetriNet GetNet() {
		return net;
	}
	
	public Set<String> transitions() {
		return net.transitions();
	}
	
	public Set<String> places() {
		return net.places();
	}
	 
	 @Override
	public String toString() {
		return net.toString();
	}
}
