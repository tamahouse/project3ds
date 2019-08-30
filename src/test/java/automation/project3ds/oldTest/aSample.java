package automation.project3ds.oldTest;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class aSample {
	int year;
	int space;
	double l;
	double interest;
	double ib;
	double increase;

	public void setUp(int space, double ib) {
		year = 25;
		l = 0.03; 
		interest = ib-l;
		increase = 1000;
	}
	
	@DataProvider(name = "data")
	public Object[][] data(){
		List<Object[]> temp = new ArrayList<Object[]>();
		Object[] obj0 = new Object[] { 6, 0.080 };
		Object[] obj1 = new Object[] { 6, 0.081 };
		Object[] obj2 = new Object[] { 12, 0.085 };
		Object[] obj3 = new Object[] { 13, 0.086 };
		Object[] obj4 = new Object[] { 24, 0.092 };
		Object[] obj5 = new Object[] { 36, 0.098 };
		Object[] obj6 = new Object[] { 48, 0.1 };
		Object[] obj7 = new Object[] { 60, 0.102 };
		temp.add(obj0);
		temp.add(obj1);
		temp.add(obj2);
		temp.add(obj3);
		temp.add(obj4);
		temp.add(obj5);
		temp.add(obj6);
		temp.add(obj7);
		Object[][] data = temp.toArray(new Object[][] {});
		return data;
		
	}
	
	
	@Test (dataProvider = "data")
	public void test1Title(int space, double ib) {
		this.setUp(space, ib);
		double base= increase + 0;
		int cycle = year*12/space;
		for(int i = 0; i < cycle; i++) {
			base = base*(1+interest*space/12);
		}
		System.out.println(space + " " + ib + " " +base);
	}
	
	
	public void test2Continuos(int space, double ib) {
		
		this.setUp(space, ib);
		System.out.println(increase*year*12);
		double total =0;
		for(int time = 0; time < year*12; time++) {
			double base= increase + 0;
		for(int i = 0; i < Math.floor((year*12-time)/space); i++) {
			base = base*(1+interest*space/12);
		}
		total = total+base;
		}
		System.out.println(space + " " + ib + " " +total);
	}
	

}
