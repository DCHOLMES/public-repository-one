package DocGenModel;

import java.io.IOException;

public class DocGenerator {

	public static void generate() {
		
		try {
			
			Runtime run = Runtime.getRuntime();
			Process proc = run.exec(new String[]{"C:\\Users\\Declan\\Desktop\\DocGen.exe", "this is a test"});
		
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	}
}
