package mainPackage;

import sphinxClasses.RecognitionClass;;

public class LogicsClass {

	public static void main(String[] args) {
		//THIS IS A METHOD BODY TODO Auto-generated method stub
		RecognitionClass utterance = new RecognitionClass();
	
		
		if(utterance.equals("CREATE"))
			{
				try{
				//@SuppressWarnings("unused")
			//Process p;
				//p = Runtime.getRuntime().exec("gedit");
				System.out.println("The system heard you said CREATE" );
				}catch(Exception er)
				{System.out.println(er);}
				}
    	if(utterance.equals("OPEN"))
			{
				try{
				System.out.println("The system heard you said OPEN" );
				}catch(Exception er)
				{System.out.println(er);}
				}
        if(utterance.equals("SAVE"))
			{
				try{
				System.out.println("The system heard you said SAVE" );
				}catch(Exception er)
				{System.out.println(er);}
				}
        if(utterance.equals("CLOSE"))
			{
				try{
				System.out.println("YOU HAVE STOPED THE PROGRAM" );
				//recognizer.stopRecognition();
				}catch(Exception er)
				{System.out.println(er);}
				}
    	else if(utterance.equals(null)){
    		System.out.println("I can't hear what you said.\n");
    		}
        }

	}
