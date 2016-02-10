package sphinxClasses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;

import com.clarkparsia.sparqlowl.parser.antlr.SparqlOwlParser.brackettedExpression_return;

import arq.load;
import ontologyFramework.OFContextManagement.OWLLibrary;
import ontologyFramework.OFContextManagement.OWLReferences;

public class CreateOntoWithTextInput {
	
	private static String ontoName;
	
	
	//private static OWLReferences OWLRef;
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws NumberFormatException, IOException {
		//THIS IS A METHOD BODY TODO Auto-generated method stub
		//String userInput = getInput("what do you want to do?");

		//here the Ontology is being created and loaded, the loop ends up when reaches the "stop" input
		while (true) {
			String userInput = getInput("what do you want to do?");
			if (userInput.equals("create")) {
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
				}	//the end of creation part
			}
			
			else if (userInput.equals("load")) {
				try {
					String ontoToLoad = getInput("what is the name of ontology to LOAD?");
					//loadOntology();
					String file = "/home/aidos/workspace/OntologicalFramework/files/ontologies/" + ontoToLoad + ".owl";
					String iri = "http://www.semanticweb.org/PredefinedOntology";
					int comand = OWLReferences.LOADFROMFILEcommand;
					System.out.println("you want to load " + ontoToLoad);			
					//OWLReferences owlRef = new OWLReferences(ontoToLoad, file, iri, comand);
					OWLReferences OWLRef = new OWLReferences(ontoToLoad, file, iri, comand);
					OWLRef.printOntonolyOnConsole();
					//OWLReferences.getOWLReferences(file);
					//loadOnto = OWLReferences.getOWLReferences(file);	//this is needed to load the OWLRef
					String userInput2 = getInput("what do you want to do?");
					
					if (userInput2.equals("sub")) {
						try {
							System.out.println("you want to create subclass in " + ontoToLoad);
							//subclass part
							String newSub = getInput("What's the name of subclass?");	//ask for the name of subclass
							//this creates the subclass to the root				
							OWLRef.getOWLReferences(ontoToLoad);
							//loadOnto = OWLReferences.getOWLReferences(ontoToLoad);
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
					else if (userInput2.equals("ind")){
						System.out.println("you want to create individual in " + ontoToLoad);
						//ask for the name of indiv, in fututre it should list the subs and the user should choose
						String className = getInput("What's the name of the subclass you want to create an indiv in?");
						String ind = getInput("What's the name of individual?");	//ask for the name of indiv
						//this creates the individual to the subclass				
						OWLRef.getOWLReferences(ontoToLoad);
						//create Individual
						boolean bufferize = true;
						OWLRef.addIndividualB2Class(ind, className, bufferize);
						String fileSave = "/home/aidos/workspace/OntologicalFramework/files/ontologies/"+ ontoToLoad + ".owl";
						OWLLibrary.saveOntology(false, fileSave, OWLRef);
					}
					//right now the property assertions are only in type of Strings. I should figure out how to do others 
					else if(userInput2.equals("prop")){
						System.out.println("you want to assign a property to individual in further ontology " + ontoToLoad);
						String indName = getInput("What's the name of individual?");	//ask for the name of indiv
						String property = getInput("What kind of property you want to assign to individual?");	//ask for the property
						String value = getInput("What kind of property you want to assign to individual?");	//ask for the property
						boolean bufferize = true;
						
						boolean value2 = false;
						//boolean type;
						OWLRef.getOWLLiteral(value2);
						
						
						OWLRef.getOWLReferences(ontoToLoad);
						//this assigns ptoperty
						OWLRef.addDataPropertyB2Individual(indName, property, value2, bufferize);
						

						//OWLRef.addObjectPropertyB2Individual(indName, property, value, bufferize);
						String fileSave = "/home/aidos/workspace/OntologicalFramework/files/ontologies/"+ ontoToLoad + ".owl";
						OWLLibrary.saveOntology(false, fileSave, OWLRef);
					}
					else if (userInput2.equals("stop")) {
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

			else if (userInput.equals("stop")) {
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
