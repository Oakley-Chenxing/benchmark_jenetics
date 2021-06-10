package io.jenetics.sphere;

public class DebugtestSphere {
	 public static void main(String[] args) {
		    double[] rep = new double[] { 0.1, 0.2 };
		    double   res = sphere(rep);
		    System.out.println("res: "+res);
		  }
		  
		  public static double sphere(double[] rep) {
		    // Sphere code developed from
		    //
		    double sum = 0.0;

		    for (int i=0; i<rep.length; i++) {
		     double temp1 = rep[i] * rep[i];
		     sum += temp1 * temp1;
		    }
		    return sum;
		  }
		  
		  public static final int DIM = 2; 
}


