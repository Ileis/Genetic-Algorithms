import java.util.Random;

public class AllocationSchedule{

    private boolean schedule[][];
    private int lines;
    private int columns;
    private int size;

    public AllocationSchedule(int d1, int d2){
        this.size = d1 * d2;
        this.lines = d1;
        this.columns = d2;
        this.schedule = new boolean[this.lines][this.columns];
    }

    public int getSize(){
        return this.size;
    }

    public void setSchedule(String gen){
        if(this.getSize() == gen.length()){

            for(int i = 0; i < this.lines; i++){
                for(int j = 0; j < this.columns; j++){

                    boolean aux = false;
                    
                    if(gen.charAt(j + (i * this.columns)) == '1')
                        aux = true;
                    else if(gen.charAt(j + (i * this.columns)) == '0')
                        aux = false;

                    this.schedule[i][j] = aux;
                }
            }
        }
    }

    public void mutate(){
        char gen[] = this.toString().toCharArray();
        Random r = new Random();

        int indexGenMutate = r.nextInt(this.getSize());

        System.out.println(indexGenMutate);

        if(gen[indexGenMutate] == '1')
            gen[indexGenMutate] = '0';
        else if(gen[indexGenMutate] == '0')
            gen[indexGenMutate] = '1';

        String genCode = "";

        for(char c : gen){
            genCode += c;
        }

        this.setSchedule(genCode);
    }

    public int r1(){
        int fit = 0;

        for(int i = 0; i < this.columns; i++){

            int qtd = 0;

            for(int j = 0; j < this.lines; j++){
                if(this.schedule[j][i])
                    qtd++;
            }

            if(qtd < 1 || qtd > 3)
                fit--;
        }

        return fit;
    }

    public int r2(){
        int fit = 0;

        for(int i = 0; i < this.lines; i++){

            int qtd = 0;

            for(int j = 0; j < this.columns; j++){
                if(this.schedule[i][j])
                    qtd++;
            }

            if(qtd != 5)
                fit--;
        }

        return fit;
    }

    public int r3(){
        int fit = 0;
        int qtdTurnsDay = 3;

        for(int i = 0; i < this.lines; i++){

            int week[] = new int[7];

            for(int j = 0; j < 7; j++){
                week[i] = 0;
            }

            for(int j = 0; j < this.columns; j++){
                if(this.schedule[i][j])
                    week[j / qtdTurnsDay]++;
            }

            int qtd = 0;

            for(int j = 0; j < 7; j++){
                if(week[j] > 0)
                    qtd++;
                else
                    qtd = 0;

                if(qtd > 3){
                    fit--;
                    break;
                }
            }
        }

        return fit;
    }

    public int r4(){
        int fit = 0;

        // criar um vetor de contabilizadores para cada turno
        // vec[3] = [<t1>, <t2>, <t3>]
        // o vetor precisa ser inicializado para cada enfermeiro
        // se existe um turno com contabilizador 1 significa que existe um turno alocado em um horario diferente do usual
        // quebrando a r4.

        for(int i = 0; i < this.lines; i++){
            int nurseCounterTurn[] = new int[3];

            for(int j = 0; j < 3; j++){
                nurseCounterTurn[j] = 0;
            }

            for(int j = 0; j < this.columns; j++){
                if(this.schedule[i][j])
                    nurseCounterTurn[j % 3]++;
            }

            boolean flag = false;

            for(int j = 0; j < 3; j++){
                if(nurseCounterTurn[j] == 1){
                    flag = true;
                    break;
                }
            }

            if(flag) fit--;
        }

        return fit;
    }

    public int getFit(){
        return this.r1() + this.r2() + this.r3() + this.r4();
    }

    public void printMatrix(){

        String out = "";

        for(int i = 0; i < this.lines; i++){
            for(int j = 0; j < this.columns; j++){
                if(this.schedule[i][j])
                    out += "1";
                else
                    out += "0";
            }
            out += "\n";
        }

        System.out.println(out);
    }

    public static AllocationSchedule reproduction(AllocationSchedule a, AllocationSchedule b){
        AllocationSchedule c = null;

        if(a.columns == b.columns && a.lines == b.lines){
            c = new AllocationSchedule(a.lines, a.columns);
            Random r = new Random();

            int limiteInferior = 1;
            int limiteSuperior = a.toString().length();

            int crossOverIndex = r.nextInt(limiteSuperior - limiteInferior + 1) + limiteInferior;

            String genC = a.toString().substring(0, crossOverIndex) + b.toString().substring(crossOverIndex, limiteSuperior);

            System.out.println(crossOverIndex);
            System.out.println(genC);

            c.setSchedule(genC);
        }

        return c;
    }

    @Override
    public String toString() {

        String out = "";

        for(int i = 0; i < this.lines; i++){
            for(int j = 0; j < this.columns; j++){
                if(this.schedule[i][j])
                    out += "1";
                else
                    out += "0";
            }
            // out += "\n";
        }

        return out;
    }
}
