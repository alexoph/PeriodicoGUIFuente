package clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Minizinc {
	private Periodico periodico;
	

	public void dnz(Periodico periodico1) {
		this.periodico = periodico1;
		String optimo = "";
		optimo += "Pages = " + periodico.getPaginas() + ";\nTemas = { ";
		optimo += periodico.getArticulos().get(0).getTema();
		for (int i = 1; i < periodico.getArticulos().size(); i++){
			optimo += ", " +  periodico.getArticulos().get(i).getTema();
		}
		optimo += " };\npotencial = [" + periodico.getArticulos().get(0).getPotencial();
		for (int i = 1; i < periodico.getArticulos().size(); i++){
			optimo += ", " +  periodico.getArticulos().get(i).getPotencial();
		}
		optimo += "];\n\nCaracteristicas = { Min, Max}\n";
		optimo += "consumption = [| " + periodico.getArticulos().get(0).getPaginasMin() + ", " + + periodico.getArticulos().get(0).getPaginasMax() + " |";
		for (int i = 1; i < periodico.getArticulos().size(); i++){
			optimo += "\n\t\t" + periodico.getArticulos().get(i).getPaginasMin() + ", " + + periodico.getArticulos().get(i).getPaginasMax() + " |";
		}
		optimo += "];";
		
		writeFile(optimo);
		
		//System.out.println(optimo);
	}
	
	public String mzn() {
		String result = "";
		Runtime rt = Runtime.getRuntime();
		String[] commands = {"minizinc", "--solver",  "gecode", "miniz/Periodico.mzn"};
		Process proc = null;
		try {
			proc = rt.exec(commands);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BufferedReader stdInput = new BufferedReader(new 
		     InputStreamReader(proc.getInputStream()));

		BufferedReader stdError = new BufferedReader(new 
		     InputStreamReader(proc.getErrorStream()));

		// Read the output from the command
		//System.out.println("Here is the standard output of the command:\n");
		String s = null;
		try {
			while ((s = stdInput.readLine()) != null) {
				result += s + "\n";
			    //System.out.println(s);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Read any errors from the attempted command
		System.out.println("Here is the standard error of the command (if any):\n");
		try {
			while ((s = stdError.readLine()) != null) {
			    System.out.println(s);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	private void writeFile(String dzn) {
		try {
		      File myObj = new File("miniz/PeriodicoDatos.dzn");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {
		        System.out.println("File already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    } 
		
		try {
		      FileWriter myWriter = new FileWriter("miniz/PeriodicoDatos.dzn");
		      myWriter.write(dzn);
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    } 
	}
	
}


