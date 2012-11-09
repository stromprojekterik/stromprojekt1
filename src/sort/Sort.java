package sort;

import java.util.Iterator;
import java.util.LinkedList;

import data.StromWert;


public class Sort{
	public static LinkedList<StromWert> sortTwoList(LinkedList<StromWert> first, LinkedList<StromWert> second){
		LinkedList<StromWert> newList = new LinkedList<StromWert>();
		Iterator<StromWert> i1 = first.iterator();
		Iterator<StromWert> i2 = second.iterator();
		
		StromWert	t1=i1.next(),
					t2=i2.next();
		
		while (i1.hasNext() || i2.hasNext()) {
			if(i1.hasNext() && t1.isBefore(t2)){
				newList.add(t1);
				t1 = i1.next();
			}
			else{
				newList.add(t2);
				t2 = i2.next();
			}
		}
		
		return newList;
	}
}
