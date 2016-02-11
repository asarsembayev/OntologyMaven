package sphinxClasses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;

import com.clarkparsia.sparqlowl.parser.antlr.SparqlOwlParser.brackettedExpression_return;

import arq.load;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import ontologyFramework.OFContextManagement.OWLLibrary;
import ontologyFramework.OFContextManagement.OWLReferences;

public class CreateOntoWithAudioInput {
	
	private static String ontoName;
	
	
	//private static OWLReferences OWLRef;
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws NumberFormatException, IOException {
		//THIS IS A METHOD BODY TODO Auto-generated method stub
		//SPHINX PART
		Configuration configuration = new Configuration();

        configuration
        .setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration
        .setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
        configuration
        .setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm");
        
        LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);
            //recognition starts here
            recognizer.startRecognition(true);
		

		//here the Ontology is being created and loaded, the loop ends up when reaches the "stop" input
		while (true) {
			String utterance = recognizer.getResult().getHypothesis();
			//String userInput = getInput("what do you want to do?");
			if (utterance.toUpperCase().equals("create")) {
				try {
					
					System.out.println("You said "+utterance);
					System.out.println("What is the name of Ontology you want to create? " );
					String utterance1 = recognizer.getResult().getHypothesis();
					System.out.println("its " + utterance1);
					ontoName = utterance1.toString();
					String file = "/home/aidos/workspace/OntologicalFramework/files/ontologies/" + ontoName +".owl";
					String iri = "http://www.semanticweb.org/PredefinedOntology";
					int comand = OWLReferences.CREATEcommand;
					OWLReferences OWLRef = new OWLReferences(utterance1, file, iri, comand);
					OWLRef.printOntonolyOnConsole();
					String fileSave = "/home/aidos/workspace/OntologicalFramework/files/ontologies/"+ ontoName + ".owl";
					OWLLibrary.saveOntology(false, fileSave, OWLRef);
				} catch (Exception e) {
					e.printStackTrace();
				}	//the end of creation part
			}
			
			else if (utterance.toUpperCase().equals("load")) {
				try {
					System.out.println("You said "+utterance);
					System.out.println("what is the name of ontology to LOAD?");
					String utterance1 = recognizer.getResult().getHypothesis();
					System.out.println("its " + utterance1);
					String ontoToLoad = utterance1.toString();
					String file = "/home/aidos/workspace/OntologicalFramework/files/ontologies/" + ontoName +".owl";
					String iri = "http://www.semanticweb.org/PredefinedOntology";
					int comand = OWLReferences.LOADFROMFILEcommand;
					
					OWLReferences OWLRef = new OWLReferences(utterance1, file, iri, comand);
					OWLRef.printOntonolyOnConsole();
					String fileSave = "/home/aidos/workspace/OntologicalFramework/files/ontologies/"+ ontoName + ".owl";
					OWLLibrary.saveOntology(false, fileSave, OWLRef);
					System.out.println("you want to load " + ontoToLoad);			
					//OWLReferences owlRef = new OWLReferences(ontoToLoad, file, iri, comand);
					//OWLReferences.getOWLReferences(file);
					
					String userCommand = utterance1.toString();	//MAYBE IT'S NEEDED TO RENAME TO UTTERANCE2
					
					if (userCommand.toUpperCase().equals("sub")) {
						try {
							System.out.println("you want to create subclass in " + ontoToLoad);
							//subclass part
							System.out.println("What's the name of subclass?");	//ask for the name of subclass
							String newSub = recognizer.getResult().getHypothesis();
							//String newSub = getInput("What's the name of subclass?");	//ask for the name of subclass
							//this creates the subclass to the root				
							OWLRef.getOWLReferences(ontoToLoad);
							//loadOnto = OWLReferences.getOWLReferences(ontoToLoad);
							OWLClass Thing = OWLRef.getOWLDataFactory().getOWLThing();
							OWLClass subClas = OWLRef.getOWLClass(newSub);
							OWLRef.getAddAxiom( OWLRef.setSubClassOf( Thing, subClas));				
							//this gonna create the subclass "Thing" of the root Thing
							//OWLAxiom subAxiom = loadOnto.setSubClassOf("Thing", "B");
							//loadOnto.getAddAxiom(subAxiom);
							
							String fileSave1 = "/home/aidos/workspace/OntologicalFramework/files/ontologies/"+ ontoToLoad + ".owl";
							//why FILESAVE is needed to be renamed?
							OWLLibrary.saveOntology(false, fileSave, OWLRef);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					else if (userCommand.toUpperCase().equals("individual")){
						System.out.println("you want to create individual in " + ontoToLoad);
						//ask for the name of indiv, in fututre it should list the subs and the user should choose
						System.out.println("What's the name of the subclass you want to create an indiv in?");
						String className = recognizer.getResult().getHypothesis();
						System.out.println("What's the name of individual?");	//ask for the name of indiv
						String ind = recognizer.getResult().getHypothesis(); 
						//this creates the individual to the subclass				
						OWLRef.getOWLReferences(ontoToLoad);
						//create Individual
						boolean bufferize = true;
						OWLRef.addIndividualB2Class(ind, className, bufferize);
						String fileSave1 = "/home/aidos/workspace/OntologicalFramework/files/ontologies/"+ ontoToLoad + ".owl";
						OWLLibrary.saveOntology(false, fileSave, OWLRef);
					}
					//right now the property assertions are only in type of Strings. I should figure out how to do others
					//CORRECTION: now I know, it may be done with getOWLLiteral
					else if(userCommand.toUpperCase().equals("property")){
						System.out.println("you want to assign a property to individual in further ontology " + ontoToLoad);
						System.out.println("What's the name of individual?");	//ask for the name of indiv
						String indName = recognizer.getResult().getHypothesis();
						System.out.println("What kind of property you want to assign to individual?");	//ask for the property
						String property = recognizer.getResult().getHypothesis();
						System.out.println("What kind of property you want to assign to individual?");	//ask for the property
						String value = recognizer.getResult().getHypothesis();
						boolean bufferize = true;
						
						boolean value2 = false;
						//boolean type;
						OWLRef.getOWLLiteral(value2);
						
						
						OWLRef.getOWLReferences(ontoToLoad);
						//this assigns ptoperty
						OWLRef.addDataPropertyB2Individual(indName, property, value2, bufferize);
						

						//OWLRef.addObjectPropertyB2Individual(indName, property, value, bufferize);
						String fileSave1 = "/home/aidos/workspace/OntologicalFramework/files/ontologies/"+ ontoToLoad + ".owl";
						OWLLibrary.saveOntology(false, fileSave, OWLRef);
					}
					else if (userCommand.toUpperCase().equals("stop")) {
						break;				
					}	
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
/*			else if (userInput.equals("sub")) {
				try {
					System.out.println("you want to create subclass in " + ontoName);
					loadOnto = OWLReferences.getOWLReferences(ontoName);	//this is needed to load the OWLRef
					//subclass part
					String newSub = getInput("What's the name of subclass?");	//ask for the name of subclass
					//this creates the subclass to the root				
					OWLClass Thing = loadOnto.getOWLDataFactory().getOWLThing();
					OWLClass subClas = loadOnto.getOWLClass(newSub);
					loadOnto.getAddAxiom( loadOnto.setSubClassOf( Thing, subClas));				
					//this gonna create the subclass "Thing" of the root Thing
					//OWLAxiom subAxiom = loadOnto.setSubClassOf("Thing", "B");
					//loadOnto.getAddAxiom(subAxiom);
					
					String fileSave = "/home/aidos/workspace/OntologicalFramework/files/ontologies/"+ ontoName + ".owl";
					OWLLibrary.saveOntology(false, fileSave, loadOnto);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} */

			else if (utterance.toUpperCase().equals("stop")) {
				break;				
			}	
		}
		
		

	}
	
/*	private static OWLReferences loadOntology() throws IOException{
		String ontoToLoad = getInput("what is the name of ontology to LOAD?");
		String file = "/home/aidos/workspace/OntologicalFramework/files/ontologies/" + ontoToLoad + ".owl";
		//String iri = "http://www.semanticweb.org/PredefinedOntology";
		//int comand = OWLReferences.LOADFROMFILEcommand;
		System.out.println("you want to load " + ontoToLoad);			
		//OWLReferences owlRef = new OWLReferences(ontoToLoad, file, iri, comand);
		return OWLReferences.getOWLReferences(file);
		//loadOnto = OWLReferences.getOWLReferences(file);	//this is needed to load the OWLRef
	}*/

	private static String getInput(String string) throws IOException {
		//THIS IS A METHOD BODY TODO Auto-generated method stub
		System.out.println(string);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//System.out.println(br.readLine());
		return br.readLine();
	}
}
