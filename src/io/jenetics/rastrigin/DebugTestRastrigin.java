package io.jenetics.rastrigin;
/**
 * @author Chenxing Li
 * @email lichenxingbeijing@163.com
 */
public class DebugTestRastrigin {
	public static void main(String[] args) {
	    double[] rep = new double[] { 0.500, 0.100 };
	    double   ras = rastrigin(rep);
	    System.out.println("ras: "+ras);
	  }
	 public static double rastrigin(double[] rep) {
		 //Rastrigin code developed from
		 //https://www.javatips.net/api/MOEAFramework-master/src/org/moeaframework/problem/BBOB2016/Rastrigin.java
		 double sum = 0.0;

		    for (int i=0; i<rep.length; i++) {
		     double temp1 = Math.cos(2.0 * Math.PI * rep[i]);
		     double temp2 =  rep[i] * rep[i];
		     sum += (-10.0 * temp1 ) + temp2 + 10;
	 }
			return sum;
}
	 public static final int DIM = 2; 

}
