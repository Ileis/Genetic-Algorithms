import java.util.ArrayList;
import java.util.Random;

public class GeneticAlgorithm {
    private ArrayList<AllocationSchedule> generation;
    private int generationSize;
    private int iterarions;
    private float elitism;
    private float mutation;

    public GeneticAlgorithm(int nurseQtd, int columnsQtd, int generationSize ,int iterations, float elitism, float mutation){
        this.generationSize = generationSize;
        this.iterarions = iterations;
        this.elitism = elitism;
        this.mutation = mutation;
    }

    private void mutateGeneration(){
        int qtdMutant = (int) this.mutation * this.generationSize;

        for(int i = 0; i < qtdMutant; i++){
            Random r = new Random();

            this.generation.get(r.nextInt(this.generation.size())).mutate();
        }
    }
}
