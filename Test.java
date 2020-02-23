/*
* @Author : Tran Leo - Ibouda El Mahdi
*/
package Tri1;

public class Test {

	public static void main(String[] args) {
		/*SacADos sac = new SacADos(args[0],Float.parseFloat(args[1]));
		sac.resolve(args[2]);*/
		
		
		SacADos sac1 = new SacADos("./test.txt", 30);
		sac1.resolve("glouton");
		
		SacADos sac2 = new SacADos("./test.txt", 30);
		sac2.resolve("methode_dynamique");
				
		SacADos sac3 = new SacADos("./test.txt", 30);
		sac3.resolve("PSE");
		
		
		
	}
}
