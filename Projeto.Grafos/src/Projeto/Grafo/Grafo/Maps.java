/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto.Grafo.Grafo;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author fc.corporation
 */
public class Maps {
    private int[][] adjacenciesMatrix;
    private Random weight;
    private Random coord;
    private static final int[] LIMITEEDGE = {1,10};
    private static final int LIMITEWEIGHT = 10;
    private int size;
    
    public Maps(int tamanho){
        adjacenciesMatrix = new int[tamanho][tamanho];
        this.weight = new Random();
        this.coord = new Random();
        this.size = tamanho;
        this.setAdjacencieMatrix();
    }
    private void setAdjacencieMatrix(){
        for(int i=0;i < this.adjacenciesMatrix.length;i++){
            int j = 0;
            int[] edges = this.coordGenerator(i);
            while(j < edges.length){
                if(edges[j] == 0)
                    break;
                if(i == j){
                    j++;
                    continue;
                }
                this.adjacenciesMatrix[i][edges[j]] = 1+this.weight.nextInt(
                        this.LIMITEWEIGHT);
                j++;
            }
        }
    }
    public int[] coordGenerator(int linha){
        final int LIMCONECTION = 2;
        final int QUANTMAXEADGE = 4;
        int[] point = new int[QUANTMAXEADGE];
        int p = LIMCONECTION+weight.nextInt(LIMCONECTION);
        for(int i=0;i < p ;i++){
            point[i] = (linha)+coord.nextInt(this.LIMITEEDGE[1]-linha);
        }
        return point;
    }
    public void imprime(){
        for(int i=0;i < this.adjacenciesMatrix.length;i++){
            for(int j=0; j< this.adjacenciesMatrix.length;j++){
                System.out.printf("%d, ", adjacenciesMatrix[i][j]);
                if(j+1 == this.adjacenciesMatrix.length)
                    System.out.printf("\n");
            }
        }
    }
    public void dijkstra(int u, int v){
        int w = u;
        int[] gama = new int[this.size];
        int[] beta = setbeta(this.size);
        int[] pi = new int[this.size];
        gama[u] = 1;
        beta[u] = 0;
    }
    public int[] setbeta(int tamanho){
        int[] gama = new int[tamanho];
        int infinito = 999999999;
        for(int i=0; i < tamanho;i++){
            gama[i] = infinito;
        }
        return gama;
    }
    public Object edges(int vertice){
        ArrayList<Integer> eadge = new ArrayList<Integer>();
        for(int i=0;i < this.size;i++){
            if (this.adjacenciesMatrix[vertice][i] != 0)
                eadge.add(this.adjacenciesMatrix[vertice][i]);
            if(this.adjacenciesMatrix[i][vertice] != 0)
                eadge.add(this.adjacenciesMatrix[i][vertice]);
        }
        return eadge;
    }
    public static void main(String[] args) {
        Maps mps = new Maps(10);
        mps.imprime();
        }
}
