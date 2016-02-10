package sphinxClasses;

import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;

import edu.cmu.sphinx.result.Result;
import ontologyFramework.OFContextManagement.OWLLibrary;
import ontologyFramework.OFContextManagement.OWLReferences;

import edu.cmu.sphinx.decoder.*;
import edu.cmu.sphinx.recognizer.Recognizer;

public class OntoLogic {

	
	private static String newName;
	private static Result result;
	//private static OWLLibrary OWLRef;
	private static OWLReferences loadOnto;

	public static void main(String[] args, String utterance, LiveSpeechRecognizer recognizer) {
		//maybe to delete
		Configuration configuration = new Configuration();
        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
        configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm");
        
        
        if (utterance.equals("CREATE")) {
			try {
					System.out.println("You said "+utterance);
					LiveSpeechRecognizer recognizer2 = new LiveSpeechRecognizer(configuration);
					//Thread.sleep(3000);
					recognizer.stopRecognition();
					recognizer2.startRecognition(true);
					String utterance2 = recognizer2.getResult().getHypothesis();
					newName = utterance2.toString();
					System.out.println("utterance2 is "+utterance2);;
					
					//askName();
					
					//newName = utterance;
					
					
					String file = "/home/aidos/workspace/OntologicalFramework/files/ontologies/" + newName +".owl";
					String iri = "http://www.semanticweb.org/PredefinedOntology";
					int comand = OWLReferences.CREATEcommand;
					OWLReferences OWLRef = new OWLReferences(newName, file, iri, comand);
					OWLRef.printOntonolyOnConsole();
					
					//maybe to delete later
					if (utterance2.equals("BOTTOM")) {
						System.out.println("utterance2 is CREATE CLASS TOP");
						OWLRef.setSubClassOf("Aidos", "Thing", true, true);
					} else {
						System.out.println("utterance2 is empty");
						//OWLRef.setSubClassOf("ERROS", "Thing", true, true);
					}
					//maybe to delete later
					
					//maybe to delete later
/*					if (utterance.equals("HUMAN")) {
						System.out.println("utterance is CREATE CLASS TOP");
						OWLRef.setSubClassOf("Aidos", "Thing", true, true);
					} else {
						System.out.println("Utterance is the same");
					}*/
					//maybe to delete later

					
					
					String fileSave = "/home/aidos/workspace/OntologicalFramework/files/ontologies/"+ newName + ".owl";
					OWLLibrary.saveOntology(false, fileSave, OWLRef);
					recognizer2.stopRecognition();
					recognizer.startRecognition(true);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        
        
		if (utterance.equals("CREATE SUBCLASS")) {
			System.out.println("you want to create subclass in " + newName);
			loadOnto = OWLReferences.getOWLReferences(newName);
			//this creates the subclass to the root
			OWLClass things = loadOnto.getOWLDataFactory().getOWLThing();
			OWLClass subClas = loadOnto.getOWLClass( "SubClass");
			loadOnto.getAddAxiom( loadOnto.setSubClassOf( things, subClas));
			
			//this gonna create the subclass "Thing" of the root Thing
			//OWLAxiom subAxiom = loadOnto.setSubClassOf("Thing", "B");
			//loadOnto.getAddAxiom(subAxiom);
			
			String fileSave = "/home/aidos/workspace/OntologicalFramework/files/ontologies/"+ newName + ".owl";
			OWLLibrary.saveOntology(false, fileSave, loadOnto);
			//OWLRef.setSubClassOf("Aidos", "Thing", true, true, OWLReferences.getOWLReferences(newName));
		} else {
			System.out.println("utterance2 is empty");
			//OWLRef.setSubClassOf("ERROS", "Thing", true, true);
		}
        
    	if (utterance.equals("CLOSE")) {
    		try {
 				System.out.println("YOU HAVE STOPED THE PROGRAM" );
 				recognizer.stopRecognition();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}            							
		}
        

	}
	
    public static void askName() throws InterruptedException{
    	System.out.println("What is the name ");
    }

	public static ReClass getUtterance() {
		return getUtterance();
	}
/*	public static ReClass getUtterance2(){
		return getUtterance2();
	}*/

	public static Result getResult() {
		return result;
	}

	public static void setResult(Result result) {
		OntoLogic.result = result;
	}
}
