package util;

public class CommonTools {
	public static boolean checkParameter(String parameter)
	{
		if(parameter == null || parameter.equals(""))
			return false;
		
		return true;
	}
}
