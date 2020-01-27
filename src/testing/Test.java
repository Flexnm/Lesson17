package testing;

import beans.Category;

public class Test {
	public static void main(String[] args) {
		
		Category myC = Category.FOOD;
		Category myC2 = Category.valueOf("ELECTRONICS");
		
		System.out.println(myC.ordinal());
		
		System.out.println(myC2);
		
		
	}

}
