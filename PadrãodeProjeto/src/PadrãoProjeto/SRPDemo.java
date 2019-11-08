package PadrãoProjeto;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

class Journal{
	private final List<String> entries = new ArrayList<>();
	
	private static int count = 0;
	
	public void addEntry(String text) {
		entries.add("" +(++count) + " : " + text);
	}
	
	public void removeEntry(int index) {
		entries.remove(index);
	}
	@Override
	public String toString() {
		return String.join(System.lineSeparator(), entries);
	}
	public void save(String filename) throws Exception{
		try (PrintStream out = new PrintStream(filename)) {
			out.print(toString());
		}
	}
	public void load (String filename){}
	
	public void load (URL url){}
	
}
//lida com a responsabilidade de objetos persistentes
class Persistence{
	public void saveToFile(Journal journal, String filename, boolean overwrite) throws Exception{
		if (overwrite || new File (filename).exists())
			try (PrintStream out = new PrintStream(filename)){
				out.println(journal.toString());
			}
	}
	public void load (Journal journal, String filename) {}
	
	public void load (Journal journal, URL url) {}
	
	
}


public class SRPDemo {

	public static void main (String args[]) throws Exception {
		Journal j = new Journal();
		j.addEntry("Caneta Azul");
		j.addEntry("Azul Caneta");
		System.out.println(j);
		
		Persistence p = new Persistence();
		String filename = "E:\\journal.txt";
		//String filename = "c:\\Users\\Alunos\\Documentos\\journal.txt";
		p.saveToFile(j, filename, true);
		
		Runtime.getRuntime().exec("notepad " + filename); 
	}

}
