package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import sinhvien.StudentModel;
public class CSVClass {
	public void exportCSVFile(String fileName) throws IOException
	{
		String res = "";
		File f = new File(fileName);
		FileWriter fw;
		try
		{
			fw = new FileWriter(f,false);
		}
		catch(IOException exc)
		{
			System.err.println("\nLoi mo file!\n");
			return;
		}
		
			fw.write(res);
		fw.flush();
		fw.close();
	}
	public void Export(String path){
		File f = new File(path);
		FileWriter fw;
		try
		{
			fw = new FileWriter(f,false);
		}
		catch(IOException exc)
		{
			System.err.println("\nLoi mo file!\n");
			return;
		}
		//BufferedWriter pw = null;
		//try {
			/*try {
				//pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(path) , "UTF-8"));
				pw = new BufferedWriter(
						 new OutputStreamWriter(
				          new FileOutputStream(path), "UTF8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			StringBuilder sb = new StringBuilder();
	        sb.append("Student ID");
	        sb.append(',');
	        sb.append("Name");
	        sb.append('\n');
	        try {
				fw.write(sb.toString());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        try {
				fw.flush();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				fw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        /*try {
				pw.write(sb.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        try {
				pw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
	        System.out.println("done!");
		//} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
		/*try {
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
	
	public List<StudentModel> Import(String path){
		BufferedReader br = null;
		String line = "";
	    String cvsSplitBy = ",";
	    List<StudentModel> lstudent= new ArrayList<StudentModel>();
	    
		 try {
			 try {
					br = new BufferedReader(
							 new InputStreamReader(
					          new FileInputStream(path), "UTF8"));
				} catch (UnsupportedEncodingException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			 try {
				line = br.readLine();
				while((line = br.readLine())!= null){
					String[] student = line.split(cvsSplitBy);
					StudentModel Add = new StudentModel();
					Add.setMaSSV(student[0]);
					Add.setTenSV(student[1]);
					lstudent.add(Add);
				 }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					br.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return null;
			}
			 System.out.println("done!");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				br.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return null;
		}
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return lstudent;
		
	}

}
