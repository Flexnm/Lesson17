package beans;

import java.sql.Date;

public class Test {
	public static void main(String[] args) {
		
		Category cat = Category.CLOTHES;
		Date startDate = new Date(1578000000000l);
		Date endDate = new Date(1630000000000l);
		
		Coupon c1 = new Coupon(1, 1, cat, "This coupon", "This is a coupon for this thing", startDate, endDate, 100, 10.5, "img");
		
		System.out.println(c1);
		
		
		
		
	}

}
