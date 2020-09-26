import java.util.ArrayList;

public class HashMap<K,V> {
private int capacity=16;
private int size = 0;
private ArrayList<Nodes<K,V>>  arr=new ArrayList<>(capacity);
private float loadfactor=0.75f;
private int rehashing=(int)(capacity*loadfactor);
private int triggerrehasing=0;



public int hashing(K key) {
	return key.hashCode() %capacity;
}

public int size() {
	return this.size;
}
public boolean isEmpty() {
	return this.size == 0 ? true : false;
}

public boolean containsKey(K key) {
	if(key == null) {return arr.get(0).key == null ?true:false;}
	int index=hashing(key);
	if(arr.get(index) != null) {
		
		if(arr.get(index).key == key) {return true;}
		Nodes nexts=arr.get(index).next;
		while(nexts != null) {
			if(nexts.key == key) {return true;}
			nexts=nexts.next;
		}
	}
	return false;
	
	
	
	
}
public void rehash() {capacity*=2;
triggerrehasing=0;
ArrayList<Nodes<K,V>>  temp=arr;
arr=new ArrayList<Nodes<K,V>>(capacity);
for(Nodes<K,V> vals:temp) {
	while(vals!=null) {
		put(vals.key, vals.value);
		vals=vals.next;
	}
}
}

public void put(K key,V Value) {
	Nodes<K,V> node=new Nodes<K, V>(); 
	if(key == null) {
		if(arr.get(0).key == null) {
			arr.add(0,node);
		}
	}
	else {
		
		int index=hashing(key);
		if(arr.get(index) == null) {
			arr.add(index,node);
			return;
		}
		else {
			Nodes exist=arr.get(index);
			while(exist!=null) {
				if(exist.key==key) {
					System.out.println("replaec with new value");
					exist.value=Value;
				}
				exist=exist.next;
			}
			exist=node;
		}
		
		
	}
	size++;
	triggerrehasing++;
	if(triggerrehasing>=rehashing) {
		rehash();
	}
}
public Nodes get(K key) {
	if(key == null) {
		return arr.get(0);
	}
	else {
		int index=hashing(key);
		if(arr.get(index).key==key) {return arr.get(index);}
		else {
			Nodes node=arr.get(index).next;
			while(node!=null && node.key!=key) {
				node=node.next;
				
			}
			return node;
			
		}
	}
	
}
}
