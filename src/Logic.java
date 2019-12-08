import java.util.ArrayList;
import java.util.List;

public class Logic {
	
	static int posicaoSaidaLinha;
	static int posicaoSaidaColuna;
	static List<String> caminhosPercorridos = new ArrayList<>(); 
	 
	public static void main(String[] args) {
		//gerarMatriz(4,5);
		
		 int[][] matriz = new int[][]{{1,1,1,0,1},
			 						  {1,0,1,1,1},
		                              {1,0,1,1,0},
		                              {0,1,1,1,1},
		                              {1,0,0,1,9}}; 
		                              

		 percorrerMatriz(matriz);
		 moverRobo(matriz);
	} 
	
	private static void moverRobo(int[][] matriz) {
		int ultimaColuna = 0;
		int j = 0;
		for (int i = 0; i < matriz.length; i++) {
			for (j = ultimaColuna; j < matriz[i].length; ) {
				if((i-1 == posicaoSaidaLinha && j == posicaoSaidaColuna) || (i == posicaoSaidaLinha && j-1 ==posicaoSaidaColuna) 
						|| (i+1 == posicaoSaidaLinha && j == posicaoSaidaColuna) ||
						(i == posicaoSaidaLinha && j+1 == posicaoSaidaColuna)) {
					System.out.println(i + "x" +  j);
					System.out.println(posicaoSaidaLinha + "x" +  posicaoSaidaColuna);
					matriz[posicaoSaidaLinha][posicaoSaidaColuna] = 2;
					System.out.println("Saiu!");
					
			          for (String s : caminhosPercorridos) {
			  			System.out.println(s);
			  		}
					System.exit(1);
				}
				
				if(posicaoSaidaColuna == j) {
					if(i+1<=matriz.length -1 && matriz[i+1][j] != 0) { 
						System.out.println(i + "x" +  j);
						matriz = baixo(matriz,i,j, matriz.length, matriz[i].length);
 						ultimaColuna = j;
						break;
					}else {
						if((j-1 >= 0 && matriz[i][j-1] !=0 )) { 
							System.out.println(i + "x" +  j);
							matriz = esquerda(matriz,i,j, matriz.length, matriz[i].length);
 							matriz[i][j] = 0;
							ultimaColuna = --j;
							continue;
						}
						if(j+1<=matriz[i].length-1 && matriz[i][j+1] != 0) {
							System.out.println(i + "x" +  j);
							matriz = direita(matriz,i,j, matriz.length, matriz[i].length);
 							matriz[i][j] = 0;
							j++;
							continue;
						}else {
							System.out.println(i + "x" +  j);
							matriz = cima(matriz,i,j, matriz.length, matriz[i].length);
 							matriz[i][j] = 0;
							i--;
							ultimaColuna = j;;
						}
					}
				}else {
					if(posicaoSaidaLinha == i) {
						if(posicaoSaidaLinha == i && posicaoSaidaColuna < j && matriz[i][j-1] != 0) {
							System.out.println(i + "x" +  j);
							matriz = esquerda(matriz,i,j, matriz.length, matriz[i].length);
 							matriz[i][j] = 0;
							ultimaColuna = --j;
							continue;
						}else {
							if(posicaoSaidaLinha == i && posicaoSaidaColuna > j && matriz[i][j+1] != 0) {
								System.out.println(i + "x" +  j);
								matriz = direita(matriz,i,j, matriz.length, matriz[i].length);
 								matriz[i][j] = 0;
								j++;
								continue;
							}else {
								if(i+1<=matriz.length -1 && matriz[i+1][j] != 0) {
									System.out.println(i + "x" +  j);
									matriz = baixo(matriz,i,j, matriz.length, matriz[i].length);
 									ultimaColuna = j;
									break;
								}else {
									System.out.println(i + "x" +  j);
									matriz = cima(matriz,i,j, matriz.length, matriz[i].length);
 									matriz[i][j] = 0;
									i--;
									ultimaColuna = j;;
								}
							}
						}
					}else {
						if(i+1<=matriz.length -1 && matriz[i+1][j] != 0) {
							System.out.println(i + "x" +  j);
							matriz = baixo(matriz,i,j, matriz.length, matriz[i].length);
 							ultimaColuna = j;
							break;
						}else {
							if(j+1<=matriz[i].length-1 && matriz[i][j+1] != 0) { 
								System.out.println(i + "x" +  j);
								matriz = direita(matriz,i,j, matriz.length, matriz[i].length);
 								matriz[i][j] = 0;
								j++;
								continue;
							}else {
								if(j-1 >= 0 && matriz[i][j-1] != 0 && posicaoSaidaColuna !=j) {
									System.out.println(i + "x" +  j);
									matriz = esquerda(matriz,i,j, matriz.length, matriz[i].length);
 									matriz[i][j] = 0;
									ultimaColuna = --j;
									continue;
								}else {
									System.out.println(i + "x" +  j);
									matriz = cima(matriz,i,j, matriz.length, matriz[i].length);
 									matriz[i][j] = 0;
									i--;
									ultimaColuna = j;;
								}
							}
						} 
					} 
				}
			}		
		} 
	}
	
	private static int[][] direita(int[][]matriz, int linha, int coluna, int tamanhoLinha,int tamanhoColuna) {

		 matriz[linha][++coluna] = 2; 
			
		return matriz;
	}
	
	private static int[][] baixo(int[][]matriz, int linha, int coluna, int tamanhoLinha,int tamanhoColuna){
 
			matriz[++linha][coluna] = 2; 
		
		return matriz;
	}
	
	private static int[][] esquerda(int[][]matriz, int linha, int coluna, int tamanhoLinha,int tamanhoColuna) {
 
			matriz[linha][--coluna] = 2; 
		
		return matriz;
	}
	
	private static int[][] cima(int[][]matriz, int linha, int coluna, int tamanhoLinha,int tamanhoColuna) {
 
	    matriz[--linha][coluna] = 2; 
		
		return matriz;
	}
	
	private static void percorrerMatriz(int[][] matriz) {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) { 
				if(matriz[i][j] == 9) {
					posicaoSaidaLinha = i;
					posicaoSaidaColuna = j; 
				} 
			}		
		}
		
	}
 
}
