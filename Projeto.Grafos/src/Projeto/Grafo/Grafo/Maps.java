/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto.Grafo.Grafo;
import java.util.Random;
import java.util.stream.IntStream;
/**
 *
 * @author fc.corporation
 */
public class Maps {
    private int[][] adjacenciesMatrix;
    private Random weight;
    private Random coord;
    public static final int[] LIMITEWEIGHT = {1,200};
    
    public Maps(int tamanho){
        adjacenciesMatrix = new int[tamanho-1][tamanho-1];
        this.weight = new Random();
        this.coord = new Random();
    }
    private void setAdjacencieMatrix(){
        for(int i=0;i < this.adjacenciesMatrix.length;i++){
            
        }
    }
    public int[] coordGenerator(){
        int[] point = new int[2];
        IntStream ints = weight.(0, 4);
        System.err.println(weight.ints(0, 4));
        for(int i: in){
            System.out.println(i);
        }
        //point[0] = coord.nextInt(this.LIMITEWEIGHT[1]);
        return point;
    }
    public static void main(String[] args) {
        Maps mps = new Maps(2);
        int[] rand = mps.coordGenerator();
        System.out.println(rand[0]+","+rand[1]);
    }
    
    
}
