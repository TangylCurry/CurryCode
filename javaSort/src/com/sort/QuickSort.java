package com.sort;

public class QuickSort {
	
	static int [] a = new int [] {12,23,5,1,88,56,47,9,19,99,44,66,39,90,100,0,1000,990,2,9380,80};
	
	public static void main(String[] args) {
		
		quickSort(a,0,a.length-1);
		
		for(int x : a) {
			System.out.println(x);
		}
	}
	private static void  quickSort(int [] a, int l, int r ) {
		if (l < r) {
			int i = l ;
			int j = r ;
			int key = a[l]; 
			while (i < j){  
	            while(i < j && a[j] >= key) j--;    
	            	if(i < j) a[i++] = a[j];
	            while(i < j && a[i] < key)  i++;    
	            	if(i < j) a[j--] = a[i]; 
	        }  
	        a[i] = key;
			quickSort(a,l, i-1);
			quickSort(a,i+1 , r);
		}
		
	}

}
