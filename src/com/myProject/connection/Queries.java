package com.myProject.connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author pnlmm075
 *
 */
public class Queries {
	public static String getQuery(String name){
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("C:/report/myProject-report/src/com/myProject/queries/" + name + ".sql");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        String inputStreamString = new Scanner(fis,"UTF-8").useDelimiter("\\A").next();
      //  System.out.println(inputStreamString);
        return inputStreamString;
	}
}
