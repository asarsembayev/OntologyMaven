package sphinxClasses;

import java.io.IOException;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import ontologyFramework.OFContextManagement.OWLLibrary;
import ontologyFramework.OFContextManagement.OWLReferences;
//import ontologyFramework.OFContextManagement.OWLReferences;


public class RecognitionClass {
	
	static int i=1;
	static String resultText;
	
	String name = null;
	String file = "/home/aidos/workspace/OntologicalFramework/files/ontologies/datatype.owl";
	String iri = "http://www.semanticweb.org/PredefinedOntology";
	private static String newName;
	//int comand = OWLReferences.LOADFROMFILEcommand;
	
	
	
	public static void main(String[] args) throws IOException {
		//THIS IS A METHOD BODY TODO Auto-generated method stub
		//THIS IS A METHOD BODY TODO Auto-generated method stub
        Configuration configuration = new Configuration();

        configuration
        .setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration
        .setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
        configuration
        .setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm");
        
        //LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);
        LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);
            //
            recognizer.startRecognition(true);
            while(true){
            	String utterance = recognizer.getResult().getHypothesis();

            	//ONTOLOGY CREATION PART
            	if(utterance.equals("CREATE ONTOLOGY"))
     			{
     				try{
     					System.out.println("You said "+utterance);
     						System.out.println("What is the name of Ontology you want to create? " );
     						String utterance1 = recognizer.getResult().getHypothesis();
     						System.out.println("its " + utterance1);
     						newName = utterance1.toString();
     						String file = "/home/aidos/workspace/OntologicalFramework/files/ontologies/" + newName +".owl";
     						String iri = "http://www.semanticweb.org/PredefinedOntology";
     						int comand = OWLReferences.CREATEcommand;
     						OWLReferences OWLRef = new OWLReferences(utterance1, file, iri, comand);
     						OWLRef.printOntonolyOnConsole();
     						String fileSave = "/home/aidos/workspace/OntologicalFramework/files/ontologies/"+ newName + ".owl";
     						OWLLibrary.saveOntology(false, fileSave, OWLRef);

     				}catch(Exception er)
     				{System.out.println(er);}
     				}
            	if(utterance.equals("CREATE CLASS"))
     			{
     				try{
     					System.out.println("You said "+utterance);
     						System.out.println("What is the name of Class you want to create? " );
     						String utterance1 = recognizer.getResult().getHypothesis();
     						System.out.println("its " + utterance1);
     						newName = utterance1.toString();
     						//OWLRef.setSubClassOf(newName, "Thing", true, true);
     						
     						//OWLLibrary.saveOntology(false, fileSave, OWLRef);

     				}catch(Exception er)
     				{System.out.println(er);}
     				}
            	if(utterance.equals("LOAD LOCAL"))
     			{
     				try{
     					loadFromLocalOnto.main(args);     				
     				}catch(Exception er)
     				{System.out.println(er);}
     				}
            	if(utterance.equals("LOAD WEB"))
     			{
     				try{
     					loadFromWebOnto.main(args);     				
     				}catch(Exception er)
     				{System.out.println(er);}
     				}
                if(utterance.equals("SAVE"))
     			{
     				try{
     					System.out.println("What is the name of Ontology you want to SAVE? " );
     					//saveOnto.main(args);
     				}catch(Exception er)
     				{System.out.println(er);}
     				}
                if(utterance.equals("CLOSE"))
     			{
     				try{
     				System.out.println("YOU HAVE STOPED THE PROGRAM" );
     				recognizer.stopRecognition();
     				}catch(Exception er)
     				{System.out.println(er);}
     				}
            	else if(utterance.equals(null)){
            		System.out.println("I can't hear what you said.\n");
            	}
            }
	}
}

            
