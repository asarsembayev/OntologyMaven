package sphinxClasses;

import java.io.IOException;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;

public class SimpleRecognition {

	public static void main(String[] args) throws IOException {
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
            	System.out.println(utterance);
            }

	}

}
