package psquiza;

import easyaccept.EasyAccept;

public class Psquiza {

	private Sistema sistema;
	
	public Psquiza() {
		sistema = new Sistema();
	}
	
	public static void main(String[] args) {
		args = new String[] {"psquiza.Psquiza", "testes_aceitacao/use_case_1.txt", "testes_aceitacao/use_case_2.txt",
							 "testes_aceitacao/use_case_3.txt", "testes_aceitacao/use_case_4.txt"};
		
		EasyAccept.main(args);
	}
}
