package sphinxClasses;

import java.io.IOException;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.recognizer.Recognizer;

public class ReClass {

	private static String utterance2;

	public static void main(String[] args) throws IOException, InterruptedException {
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
/*            	String utterance2 = recognizer.getResult().getHypothesis();
            	System.out.println("utterance2 is "+utterance2);*/
            	OntoLogic.main(args, utterance, recognizer);

            }
	}
	
	public static void setUtterance(ReClass utterance) {
	}
	
	public static void setUtterance2(String utterance2) {
	}

	public String getUtternace(String utterance){
		return utterance;		
	}

	public static String getUtterance2() {
		return utterance2;
	}
	
	public static void setRecognizer(ReClass recognizer){
	}
	
	public static Recognizer getRecognizer(Recognizer recognizer){
		return recognizer;
	}
}
