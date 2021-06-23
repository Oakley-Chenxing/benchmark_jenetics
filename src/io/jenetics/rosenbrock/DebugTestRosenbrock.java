package io.jenetics.rosenbrock;
/**
 * @author Chenxing Li
 * @email lichenxingbeijing@163.com
 */
public class DebugTestRosenbrock {
	public static void main(String[] args) {
	    double[] rep = new double[] { 0.456, 0.239 };
	    double   res = rosenbrock(rep);
	    System.out.println("res: "+res);
	  }
	  
	  public static double rosenbrock(double[] rep) {
	    // Rosenbrock code developed from
	    // https://www.codota.com/code/java/methods/org.uma.jmetal.problem.singleobjective.Rosenbrock/getNumberOfVariables
	    double sum = 0.0;

	    for (int i=0; i<rep.length-1; i++) {
	     double temp1 = (rep[i] * rep[i]) - rep[i + 1];
	     double temp2 = rep[i] - 1.0;
	     sum += (100.0 * temp1 * temp1) + (temp2 * temp2);
	    }
	    return sum;
	  }
	  
	  public static final int DIM = 2; 
}
