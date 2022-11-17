package ARC4Planit.PlanitTA.reusableMethods;

import java.util.ArrayList;
import java.util.HashMap;

public class ReusableMethods 
{
	public static float roundFloat(float floatNum) 
	{
		return (float)(Math.round(floatNum*100)/100.0);
	}
	public static Object[] hashMapToArray(HashMap<String,String> map)
	{
		HashMap<String, String> toyQtty = map;
		Object[] toysArr = toyQtty.entrySet().toArray();
		return toysArr;
		
	}
	
	public static String[] hashMapKeyToArray(HashMap<String,String> map)
	{
		ArrayList<String> al = new ArrayList<String>();
		al.addAll(map.keySet());
		String[] array = al.toArray(new String[0]);
		return array;
	}
	public static String[] hashMapValueToArray(HashMap<String,String> map)
	{
		ArrayList<String>  al = new ArrayList<String>();
		al.addAll(map.values());
		String[] array = al.toArray(new String[0]);
		return array;
	}
}
