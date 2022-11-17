package ARC4Planit.PlantTA.data;

import java.util.HashMap;

public class CartData 
{
	public static HashMap<String, String> getToyAndQtty()
	{
		HashMap<String, String> toyQtty = new HashMap<>();
		toyQtty.put("Stuffed Frog", "2");
		toyQtty.put("Fluffy Bunny", "5");
		toyQtty.put("Valentine Bear", "3");
		
		return toyQtty;
	}
}