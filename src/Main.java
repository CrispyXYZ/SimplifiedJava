import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class Main {
	
	private static String fileContent;
	private static Map<String, String> translateMap;
	
	public static void main(String[] args) {
		try {
			fileContent = readFile(args[0]);
			//process
			if(args.length < 3){
				translateMap = new HashMap<>();
				translateMap.put("im_", "import");
				translateMap.put("ju\\.", "java.util.");
				translateMap.put("jl\\.", "java.lang.");
				translateMap.put("pri_", "private");
				translateMap.put("pro_", "protected");
				translateMap.put("pu_", "public");
				translateMap.put("s_", "static");
				translateMap.put("f_", "final");
				translateMap.put("ab_", "abstract");
				translateMap.put("sync_", "synchronized");
				translateMap.put("tran_", "transient");
				translateMap.put("voli_", "volatile");
				translateMap.put("const", "static final");
				translateMap.put("_main", "public static void main(String[] args)");
				translateMap.put("println_", "System.out.println");
				translateMap.put("forever", "while(true)");
			}else{
				translateMap = getProperties(args[2]);
			}
			
			translateMap.put("\\$mainClass", "public class "+args[1].split("\\.")[0]);
			translateMap.put("\\$n", args[1].split("\\.")[0]);
			
			for (Map.Entry<String, String> entry : translateMap.entrySet()) {  
				fileContent = fileContent.replaceAll(entry.getKey(), entry.getValue());
			}
			
			/* DEPRECATED
			fileContent = fileContent
				//Simplified packages
				.replaceAll("pk_", "package")
				.replaceAll("im_", "import")
				.replaceAll("ju\\.", "java.util.")
				.replaceAll("jl\\.", "java.lang.")
				
				//Simplified modifiers
				.replaceAll("pri_", "private")
				.replaceAll("pro_", "protected")
				.replaceAll("pu_", "public")
				.replaceAll("s_", "static")
				.replaceAll("f_", "final")
				.replaceAll("ab_", "abstract")
				.replaceAll("sync_", "synchronized")
				.replaceAll("tran_", "transient")
				.replaceAll("voli_", "volatile")
				.replaceAll("const", "static final")
				
				//Simplified code
				.replaceAll("\\$mainClass", "public class "+args[1].split("\\.")[0])
				.replaceAll("_main", "public static void main(String[] args)")
				.replaceAll("println_", "System.out.println")
				.replaceAll("forever", "while(true)")
				;
			*/
			writeFile(args[1], fileContent);
		} catch (IOException e) {
			System.out.println("Error: "+e.getMessage());
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Error: Parameter error. Requires TWO or THREE parameter (inFileName, outFileName and configFileName).");
		}
    }
    

	private static Map<String, String> getProperties(String filepath) {
		Map<String, String> map = new HashMap<String, String>();
		InputStream in = null;
		Properties p = new Properties();
		try {
			in = new BufferedInputStream(new FileInputStream(new File(filepath)));
			p.load(in);
		} catch (Exception e) {
			System.out.println(e);
		}
		Set<Entry<Object, Object>> entrySet = p.entrySet();
		for (Entry<Object, Object> entry : entrySet) {
			map.put((String) entry.getKey(), (String) entry.getValue());
		}
		return map;
	}
	
	private static String readFile(String inFilePath) throws IOException {
		// Construct BufferedReader from FileReader
		BufferedReader br = new BufferedReader(new FileReader(new File(inFilePath)));

		String line = null;
		String content = "";
		while ((line = br.readLine()) != null) {
			content += line + "\n";
		}

		br.close();
		return content;
	}
	
	private static void writeFile(String outFilePath, String content) throws IOException{
		File outFile = new File(outFilePath);
		if(!outFile.exists()){
			outFile.createNewFile();
		}
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(outFile));
		
		bw.write(content);
		bw.flush();
		bw.close();
	}
}
