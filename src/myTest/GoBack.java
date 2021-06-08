package myTest;
/**
 * 
 * @author C_try
 *
 */
import java.util.HashMap;
import java.util.Map;

public class GoBack {
	public static Map<String,Integer> map;
	
	public static void addIntoMap(String str){
		if(map==null)
			map=new HashMap<String,Integer>();
		if(map.get(str)==null)
			map.put(str, 0);
		int temp=map.get(str);
		temp++;
		map.put(str,temp);
	}
	
//	public static void getAllEle(){
//		for(String ele:map.keySet()){
//			System.out.println(ele+" "+map.get(ele));
//		}
//	}
	
}

