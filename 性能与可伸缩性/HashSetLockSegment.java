package thread;

import java.util.HashSet;
import java.util.Set;

public class HashSetLockSegment {
	
	private Set<String> set = new HashSet<String>();

	Object[] locks = new Object[8];
	
	public HashSetLockSegment(){
		
		for(int i=0;i<locks.length;i++){
			locks[i] = new Object();
		}
		
	}
	
	public void add(String v){
		Object obj = locks[Math.abs(v.hashCode())];
		
		synchronized(obj){
			set.add(v);
		}
	}
	
	public void remove(String v){
		Object obj = locks[Math.abs(v.hashCode())];
		
		synchronized(obj){
			set.remove(v);
		}
	}
	
	public boolean isExists(String v){
		Object obj = locks[Math.abs(v.hashCode())];
		
		synchronized(obj){
			if (set.contains(v)){
				return true;
			}else{
				return false;
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
