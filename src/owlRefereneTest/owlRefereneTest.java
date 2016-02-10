package owlRefereneTest;
import java.util.Set;

import org.semanticweb.owlapi.model.OWLNamedIndividual;

import ontologyFramework.OFContextManagement.OWLLibrary;
import ontologyFramework.OFContextManagement.OWLReferences;

public class owlRefereneTest {

	public static void main(String[] args) {
		//THIS IS A METHOD BODY TODO Auto-generated method stub

		String name = "onoName";
		String file = "/home/aidos/workspace/OntologicalFramework/files/ontologies/datatype.owl";
		String iri = "http://www.semanticweb.org/PredefinedOntology";
		int comand = OWLReferences.LOADFROMFILEcommand;
		OWLReferences owlRef = new OWLReferences(name, file, iri, comand);
		
		owlRef.printOntonolyOnConsole();
		
		
		//Thread t = new Thread( new GuiRunner( name, 40000L, 40000L, 400000L, 400000L));
		//t.start();
		
		//create Individual
		String ind = "DT";
		String prop = "hasAidoses";
		int value = 2;
		boolean bufferize = true;
		owlRef.addDataPropertyB2Individual(ind, prop, value, bufferize);
		//create subClass
		owlRef.setSubClassOf("Aidos", "DataType", true, true);
		
		String fileSave = "/home/aidos/workspace/OntologicalFramework/files/ontologies/datatypeSave.owl";
		OWLLibrary.saveOntology(false, fileSave, owlRef);
		
		OWLReferences owlRef2 = OWLReferences.getAllInstances().get(name);
		Set<OWLNamedIndividual> inds = owlRef2.getObjectPropertyB2Individual("A1", "hasListElement");

		System.out.println(inds);
	}

}
