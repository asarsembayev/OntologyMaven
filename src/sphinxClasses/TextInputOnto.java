package sphinxClasses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.semanticweb.owlapi.model.OWLClass;

import ontologyFramework.OFContextManagement.OWLLibrary;
import ontologyFramework.OFContextManagement.OWLReferences;
import sphinxClasses.MainLogicClass;

public class TextInputOnto {
	
	private static String ontoName;
	private static String ontoToLoad;
	private static OWLReferences OWLRef;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//THIS IS A METHOD BODY TODO Auto-generated method stub
		//here the Ontology is being created and loaded, the loop ends up when reaches the "stop" input
		
		// IF CREATE
		public static OWLReferences createMethod(){
		try {
			ontoName = getInput("What's the name of ontology to create?");	//ask for the name of ontology
			String file = "/home/aidos/workspace/OntologicalFramework/files/ontologies/" + ontoName +".owl";
			String iri = "http://www.semanticweb.org/PredefinedOntology";
			int comand = OWLReferences.CREATEcommand;
			OWLReferences OWLRef = new OWLReferences(ontoName, file, iri, comand);
			OWLRef.printOntonolyOnConsole();
			
			
			String fileSave = "/home/aidos/workspace/OntologicalFramework/files/ontologies/"+ ontoName + ".owl";
			OWLLibrary.saveOntology(false, fileSave, OWLRef);
		} catch (Exception e) {
			e.printStackTrace();
		}
		}//the end of creation part
		//END OF CREATE
		
		//IF LOAD
		public static OWLReferences LoadMethod(){
		try {
			String ontoToLoad = getInput("what is the name of ontology to LOAD?");
			//loadOntology();
			String file = "/home/aidos/workspace/OntologicalFramework/files/ontologies/" + ontoToLoad + ".owl";
			String iri = "http://www.semanticweb.org/PredefinedOntology";
			int comand = OWLReferences.LOADFROMFILEcommand;
			System.out.println("you want to load " + ontoToLoad);			
			OWLReferences OWLRef = new OWLReferences(ontoToLoad, file, iri, comand);
			OWLRef.printOntonolyOnConsole();
			
		} catch (Exception e) {
			e.printStackTrace();
			}
			//String userInput2 = getInput("what do you want to do?");
		
			public static void SubMethod(){
				try {
					System.out.println("you want to create subclass in " + ontoToLoad);
					//subclass part
					String newSub = getInput("What's the name of subclass?");	//ask for the name of subclass
					//this creates the subclass to the root				
					OWLRef.getOWLReferences(ontoToLoad);

					OWLClass Thing = OWLRef.getOWLDataFactory().getOWLThing();
					OWLClass subClas = OWLRef.getOWLClass(newSub);
					OWLRef.getAddAxiom( OWLRef.setSubClassOf( Thing, subClas));				
					//this gonna create the subclass "Thing" of the root Thing
					//OWLAxiom subAxiom = loadOnto.setSubClassOf("Thing", "B");
					//loadOnto.getAddAxiom(subAxiom);
					
					String fileSave = "/home/aidos/workspace/OntologicalFramework/files/ontologies/"+ ontoToLoad + ".owl";
					OWLLibrary.saveOntology(false, fileSave, OWLRef);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
				

		
			//String userInput = getInput("what do you want to do?");

	private static String getInput(String string) throws IOException {
		//THIS IS A METHOD BODY TODO Auto-generated method stub
		System.out.println(string);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//System.out.println(br.readLine());
		return br.readLine();
	}
		}
}
}