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
    /*static int[][] adjacenciesMatrix = {{0,0,5,9,0},
                                        {0,0,3,0,10},
                                        {0,0,0,9,0},
                                        {0,0,0,0,100},
                                        {0,0,0,0,0}};*/
    private int[][] adjacenciesMatrix;
    public int[][] stationBusTaxi;
    private ArrayList<ArrayList<Integer>> pathBusStation;
    private final Random WEIGHT;
    private final Random COORD;
    private static final int[] LIMITEEDGE = {1,50};
    private static final int LIMITEWEIGHT = 9;
    private final int SIZE;
    
    public Maps(int tamanho){
        this.adjacenciesMatrix = new int[tamanho][tamanho];
        this.stationBusTaxi = new int[tamanho][tamanho];
        this.pathBusStation = new ArrayList<ArrayList<Integer>>();
        this.WEIGHT = new Random();
        this.COORD = new Random();
        this.SIZE = adjacenciesMatrix.length;
        this.setAdjacencieMatrix();
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
                    this.adjacenciesMatrix[i][edges[j]] = 1+this.WEIGHT.nextInt(
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
        int p = LIMCONECTION+WEIGHT.nextInt(LIMCONECTION);
        for(int i=0;i < p ;i++){
            point[i] = linha+COORD.nextInt(this.LIMITEEDGE[1]-linha);
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
    public void imprime2(){
        for(int i=0;i < this.stationBusTaxi.length;i++){
            for(int j=0; j< this.stationBusTaxi.length;j++){
                System.out.printf("%d, ", stationBusTaxi[i][j]);
                if(j+1 == this.stationBusTaxi.length)
                    System.out.printf("\n");
            }
        }
    }
    public ArrayList<Integer> dijkstra(int u, int v){
        ArrayList<Integer> piFiltrade = new ArrayList<Integer>();
        int w = u;
        int infinito = 999999999;
        int[] gama = new int[this.SIZE];
        int[] beta = setBeta(this.SIZE);
        int[] pi = new int[this.SIZE];
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
        this.filterDijkstra(pi, u, v, piFiltrade);
        piFiltrade.add(u);
        return piFiltrade;
        //return pi;
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
        for(int i=0;i < this.SIZE;i++){
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
    public void generatorBusTaxiStation(){
        final int quantStation = 50;
        final int LIMITESTATION = 5;
        ArrayList<Integer> numGenerated = new ArrayList<Integer>();
        Random stationGenerator = new Random();
        //int station = stationGenerator.nextInt(Maps.LIMITEEDGE[1]-LIMITESTATION);
        int station = 0;
        int nextStation;
        for(int i=0;i < quantStation;i++){
            numGenerated.add(station);
            nextStation = station + 1 + stationGenerator.nextInt(LIMITESTATION);
            this.stationBusTaxi[station][nextStation] = 1;
            this.pathBusStation.add(this.dijkstra(station, nextStation));
            station = nextStation;
            if(station + LIMITESTATION >= Maps.LIMITEEDGE[1]){
                try{
                    station = this.adequadeStation(numGenerated, LIMITESTATION);
                }catch(java.lang.NullPointerException npe){}
            }
            
        }
    }
    public int adequadeStation(ArrayList<Integer> stations, int constant){
        Integer station = null;
        for(int i=0;i < stations.size();i++){
            if(stations.get(i) + constant < Maps.LIMITEEDGE[1]){
                station = stations.get(i);
                stations.remove(i);
                break;
            }
        }
        if(station == null){
            throw new java.lang.NullPointerException();
        }
        return station;
    }
    public static void main(String[] args) {
        Maps mps = new Maps(50);
        mps.generatorBusTaxiStation();
        ArrayList<Integer> pi = mps.dijkstra(0, 2);
        mps.imprime2();
    }
}
