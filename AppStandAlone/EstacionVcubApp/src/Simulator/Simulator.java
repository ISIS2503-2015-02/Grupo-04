package Simulator;

import Runner.Main;

public class Simulator {

	public static void main(String[] args) {
		for(int i = 0; i<40;i++){
			System.out.println(i+"-esima Estacion iniciada.");
			new Main (i);
		}
	}

}
