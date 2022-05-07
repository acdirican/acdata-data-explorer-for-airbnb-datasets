
package com.acdirican.discoverairbnb;
import java.io.IOException;
import com.acdirican.discoverairbnb.gui.MainFrame;
import com.acdirican.discoverairbnb.parser.Parser;

/**
 * The starter class for the project's GUI
 * 
 * @author Ahmet Cengizhan Dirican
 * @version v1.0 May 2022
 *
 */
public class StartGUI {

    /**
     * main()-Method,start GUI
     * @param args
     */
	public static void main(String[] args) throws IOException {
		Parser.allowDebugPrint(true);
		MainFrame main =  new MainFrame();
		main.init();
			
//		
//		//Te see the available types of room type
//		System.out.println(Parser.getColumnSet("airbnbistanbul.csv", 8));
//		
//		//Te see the available neighborhoods
//		System.out.println(Parser.getColumnSet("airbnbistanbul.csv", 4));
//		
//		//To see the some part of the dataset in columns 
//		Parser.summarize("airbnbistanbul.csv", 0, 20, 0, 3);
//		
//		Dataset ds  = DatasetManager.create("airbnbistanbul.csv");
//		System.out.println(ds.titles());
	}
}
