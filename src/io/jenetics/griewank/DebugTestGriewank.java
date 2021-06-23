package io.jenetics.griewank;
/**
 * @author Chenxing Li
 * @email lichenxingbeijing@163.com
 */
public class DebugTestGriewank {
	public static void main(String[] args) {
	    float[] rep = new float[] { 0f, 0f };
	    float   gri = griewank(rep);
	    System.out.println("gri: "+gri);
	  }
	 public static float griewank(float[] rep) {
		 //griewank code reference:
		 //http://www.sfu.ca/~ssurjano/griewank.html
		 float sum  = 0.0f;
		 float prod = 1.0f;
		   for (int i=0; i<rep.length; i++) {
		       System.out.println("rep["+i+"]: "+rep[i]);
			   float temp1 = rep[i]*rep[i];
			   System.out.println("temp1: "+temp1);
			   sum += temp1;
			   System.out.println("sum: "+sum);
			   float temp2 = (float) (Math.cos(rep[i]/Math.sqrt(i+1)));
			   System.out.println("temp2: "+temp2);
			   prod *= temp2;
			   System.out.println("prod: "+prod);
	 }
			return (1/4000)*sum - prod + 1.0f;
}
	 public static final int DIM = 2; 

}

