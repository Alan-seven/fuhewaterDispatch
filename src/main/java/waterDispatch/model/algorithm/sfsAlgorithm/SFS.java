package waterDispatch.model.algorithm.sfsAlgorithm;

import waterDispatch.River;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class SFS {
    public Particle[] pop;
    public int iteration;
    public double global_best;
    public double[] global_best_pos;
    public static Random rnd = new Random();
    private int dims;
    private int popSize;
    double[][] poss;
    public double[] fits;
    public double[] its;
    public double[] result;

    static int randInt(int low, int uper) {
        return low + (int) (Math.random() * (uper - low + 1));
    }

    static double randDouble(double low, double uper) {
        rnd = new Random();
        return rnd.nextDouble() * (uper - low) + low;
    }

    /**
     *
     * @param dims
     * @param PopSize
     * @param iteration
     */
    public void initial(int dims, int PopSize, int iteration, double up, double down, double start, double end, double errorThreshold, List<Double> level,List<River>rivers,String name) {

        this.popSize = PopSize;
        this.dims = dims;
        this.iteration = iteration;

        global_best = Double.MAX_VALUE;
        global_best_pos = new double[dims];
        pop = new Particle[popSize];
        poss = new double[popSize][dims];
        fits = new double[popSize];
        result = new double[iteration];
        its = new double[iteration];

        // initial population
        for (int i = 0; i < popSize; i++) {
            pop[i] = new Particle(up,down);

            poss[i] = pop[i].initial(dims,start,end);
            fits[i] = pop[i].evaluate( errorThreshold,level,rivers,name);

            if (global_best > pop[i].fitness) {
                global_best = pop[i].fitness;
                for (int n = 0; n < dims; n++) {
                    global_best_pos[n] = pop[i].pos[n];
                }
            }
        }
    }

    public void run(double errorThreshold,List<Double>level,List<River>rivers,String name) {
        int iter = 1;
        while (iter <= iteration) {

            // random walk
            for (int i = 0; i < popSize; i++) {
                poss[i] = pop[i].randomWalk(global_best_pos, iter);
            }
            update( errorThreshold,  level,rivers,name);

            // first update  exploration
            HashMap<Double, Integer> rank = rank(fits);
            double ε = randDouble(0, 1);
            for (int i = 0; i < popSize; i++) {
                poss[i] = pop[i].fistUpdatingProcess(i, rank, ε, poss, popSize);
            }
            update(errorThreshold, level,rivers,name);

            // second update  exploitation
            rank = rank(fits);
            for (int i = 0; i < popSize; i++) {
                poss[i] = pop[i].secondUpdatingProcess(i, rank, ε, poss, popSize, global_best_pos, errorThreshold,level,rivers,name);
            }
            update( errorThreshold, level,rivers,name);

//            System.out.println("The best value for the " + iter + "th iteration: " + global_best);
//            System.out.println("The best location for the " + iter + "th iteration: ");
//            for (int n = 0; n < dims; n++) {
//                System.out.print(global_best_pos[n] + ", ");
//            }
//            System.out.println();
            System.out.println(global_best);
            its[iter - 1] = iter;
            result[iter - 1] = global_best;
            iter++;
        }
    }

    public void update(double errorThreshold,List<Double>level,List<River>rivers,String name) {
        for (int i = 0; i < popSize; i++) {
            fits[i] = pop[i].evaluate(errorThreshold,level,rivers,name);
            if (global_best > pop[i].fitness) {
                global_best = pop[i].fitness;
                for (int n = 0; n < dims; n++) {
                    global_best_pos[n] = pop[i].pos[n];
                }
            }
        }
    }

    /**
     * rank
     *
     * @param input
     * @return
     */
    public static HashMap<Double, Integer> rank(double[] input) {
        double[] temp = new double[input.length];
        System.arraycopy(input, 0, temp, 0, input.length);
        Arrays.sort(temp);

        double[] reshuffle = new double[input.length];
        for(int i = 0; i < reshuffle.length; i++) {
            reshuffle[i] = temp[reshuffle.length-i-1];
        }

        HashMap<Double, Integer> map = new HashMap<>();
        HashMap<Double, Integer> result = new HashMap<>();
        for (int i = 0; i < reshuffle.length; i++) {
            map.put(reshuffle[i], i + 1);
        }

        for (int i = 0; i < reshuffle.length; i++) {
            result.put(input[i], map.get(input[i]));
        }
        return result;
    }

    public double[][] showresult() {
        double[][] output = new double[2][iteration];
        for (int m = 0; m < iteration; m++) {
            output[0][m] = its[m];
            output[1][m] = result[m];
        }
        System.out.println("The optimal solution obtained by SFS:" + global_best);
        System.out.println(global_best);
        return output;
    }

    public  double[] getGlobal_best_pos() {
        return global_best_pos;
    }

    public  void setGlobal_best_pos(double[] global_best_pos) {
        this.global_best_pos = global_best_pos;
    }

    public double getGlobal_best() {
        return global_best;
    }

    public void setGlobal_best(double global_best) {
        this.global_best = global_best;
    }
}
