package thread;

import java.util.HashMap;
import java.util.Map;

public class DecreaseLockIntensity {

	/**
	 * 降低锁的力度
	 */
	
	private Map map1 = new HashMap();
	
	private Map map2 = new HashMap();
	
	//降低前：
	public synchronized void addMap1(String k,String v){
		map1.put(k, v);
	}
	
	public synchronized void addMap2(String k,String v){
		map2.put(k, v);
	}
	
	public synchronized void removeMap1(String k){
		map1.remove(k);
	}
	
	public synchronized void removeMap2(String k){
		map2.remove(k);
	}
	
	//降低后
	public void addOptMap1(String k,String v	){
		synchronized(map1){
			map1.put(k, v);
		}
	}
	
	public void addOptMap2(String k,String v	){
		synchronized(map2){
			map2.put(k, v);
		}
	}
	
	
	public void removeOptMap1(String k	){
		synchronized(map1){
			map1.remove(k);
		}
	}
	
	public void removeOptMap2(String k	){
		synchronized(map2){
			map2.remove(k);
		}
	}
}
