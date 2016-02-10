package sphinxClasses;

/* I'm gonna split the program to logical part and functional part later
 * logical part should only have the loops 
 * *
 */



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import sphinxClasses.TextInputOnto;;

public class MainLogicClass {

	public static void main(String[] args) {
		//THIS IS A METHOD BODY TODO Auto-generated method stub
		public static String getInput(String string) throws IOException {
			//THIS IS A METHOD BODY TODO Auto-generated method stub
			System.out.println(string);
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			//System.out.println(br.readLine());
			return br.readLine();
		}
	}

}
