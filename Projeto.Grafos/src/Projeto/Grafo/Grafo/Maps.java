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
    static int[][] adjacenciesMatrix = {{0,0,1,1,0},
                                        {0,0,1,0,1},
                                        {0,0,0,1,0},
                                        {0,0,0,0,1},
                                        {0,0,0,0,0}};
    private Random weight;
    private Random coord;
    private static final int[] LIMITEEDGE = {1,10};
    private static final int LIMITEWEIGHT = 9;
    private int size;
    
    public Maps(int tamanho){
        //this.adjacenciesMatrix = new int[tamanho][tamanho];
        this.weight = new Random();
        this.coord = new Random();
        this.size = Maps.adjacenciesMatrix.length;
        //this.setAdjacencieMatrix();
    }
    private void setAdjacencieMatrix(){
        for(int i=0;i < this.adjacenciesMatrix.length;i++){
            int j = 0;
            int[] edges = this.coordGenerator(i);
            while(j < edges.length){
                if(edges[j] == 0)
                    break;
                if(i == edges[j]){
                    ++edges[j];
                    continue;
                }
                try{
                    this.adjacenciesMatrix[i][edges[j]] = 1+this.weight.nextInt(
                            this.LIMITEWEIGHT);
                    j++;
                }catch(IndexOutOfBoundsException ioe){
                    break;
                }
            }
        }
    }
    public int[] coordGenerator(int linha){
        final int LIMCONECTION = 2;
        final int QUANTMAXEADGE = 4;
        int[] point = new int[QUANTMAXEADGE];
        int p = LIMCONECTION+weight.nextInt(LIMCONECTION);
        for(int i=0;i < p ;i++){
            point[i] = linha+coord.nextInt(this.LIMITEEDGE[1]-linha);
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
    public int[] dijkstra(int u, int v){
        int w = u;
        int infinito = 999999999;
        int[] gama = new int[this.size];
        int[] beta = setBeta(this.size);
        int[] pi = new int[this.size];
        gama[u] = 1;
        beta[u] = 0;
        while(w != v){
            int menorBeta = infinito;
            int r_asteristico = -1;
            ArrayList<Integer> edgesW_R = this.edges(w);
            for(Integer r: edgesW_R){
                r = (int)r;
                int alphaW_R = this.alpha(w, r);
                if(gama[r] == 0 && beta[r] > beta[w]+alphaW_R){
                    beta[r] = beta[w] + alphaW_R;
                    pi[r] = w;
                }
            }
            for(int j=0;j < beta.length;j++){
                if(beta[j] != infinito && gama[j] == 0 && beta[j] <= menorBeta){
                    menorBeta = beta[j];
                    r_asteristico = j;
                }
            }
            if(r_asteristico == -1)
                break;
            gama[r_asteristico] = 1;
            w = r_asteristico;
        }
        return pi;
    }
    public int filterDijkstra(int[] pi,int u, int v, 
            ArrayList<Integer> smallerPath){
        if(u != v){
            smallerPath.add(v);
            return this.filterDijkstra(pi, u, pi[v], smallerPath);
        }
        return 0;
    }
    public int[] setBeta(int tamanho){
        int[] gama = new int[tamanho];
        int infinito = 999999999;
        for(int i=0; i < tamanho;i++){
            gama[i] = infinito;
        }
        return gama;
    }
    public int alpha(int w, int r){
        int edge;
        if(this.adjacenciesMatrix[w][r] != 0)
            edge =  this.adjacenciesMatrix[w][r];
        else
            edge = this.adjacenciesMatrix[r][w];
        return edge;
    }
    public ArrayList<Integer> edges(int vertice){
        ArrayList<Integer> eadge = new ArrayList<Integer>();
        for(int i=0;i < this.size;i++){
            if (this.adjacenciesMatrix[vertice][i] != 0)
                eadge.add(i);
            if(this.adjacenciesMatrix[i][vertice] != 0)
                eadge.add(i);
        }
        return eadge;
    }
    public void setAdjacenciesMatriz(int[][] mat){
        this.adjacenciesMatrix = mat;
    }
    public static void main(String[] args) {
        ArrayList<Integer> ar = new ArrayList<Integer>();
        Maps mps = new Maps(10);
        //mps.imprime();
        int[] pi = mps.dijkstra(0, 4);
        for(int i: pi){
            System.err.println(i);
        }
    }
}
