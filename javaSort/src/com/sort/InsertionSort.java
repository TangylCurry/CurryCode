package com.sort;

public class InsertionSort {

	public static void main(String[] args) {

		int[] a = new int [] {2,1,3,6,9,4,8,5,7,0};
		for (int i = 1 ; i <a.length; i++ ){
			int tem = a[i] ,j = i;
			while( j > 0 && tem < a[j-1] ){ 
				a[j] = a[j-1];
				j--;
			}
			a[j] = tem;
		}
		for(int x : a){
			System.out.println(x);
		}
	}
}
