package waterDispatch.model.algorithm.sfsAlgorithm;

import waterDispatch.River;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Particle {
    public double[] pos;
    public double fitness;
    public double fitnessTemp;
    public double[] pBest;// The historical optimal location of the current particle
    public static Random rnd = new Random();
    public int dims;
    public double pBest_fitness;// The historical optimal fitness of the current particle
    static DecimalFormat df = new DecimalFormat();
    public static double up ;
    public static double down ;
    public double kBest;
    public int q; // the number of new fractal individuals
    public double Pa;
    public int r;
    public int t;

    public Particle(double up,double down){
        this.up=up;
        this.down=down;
    }

    static int randInt(int low, int uper) {
        return low + (int) (Math.random() * (uper - low + 1));
    }

    static double randDouble(double low, double uper) {
        rnd = new Random();
        return rnd.nextDouble() * (uper - low) + low;
    }

    public double[] initial(int dim, double start,double end) {
        this.dims = dim;
        pos = new double[dims];
        pBest = new double[dims];
        fitness = Double.MAX_VALUE;
        pBest_fitness = Double.MAX_VALUE;
        pos[0]= start;
        pos[dims-1]=end;
        for (int i = 1; i < dims-1; ++i) {
            pos[i] = randDouble(down, up);
        }
        return pos;
    }

    public double evaluate(double errorThreshold,List<Double>level,List<River>rivers,String name) {
        List<Double>posList=new ArrayList<>();
        for (int i = 0; i <pos.length ; i++) {
            posList.add(pos[i]);
        }
        List<List<Double>>levels=new ArrayList<>();
        if(name=="HMSK"){
            levels.add(0,posList);
            levels.add(1,level);

        }else{
            levels.add(0,level);
            levels.add(1,posList);
        }
        FitnessCalculation fitnessCalculation=new FitnessCalculation();
        fitness=fitnessCalculation.getFitness( errorThreshold,levels,rivers);
        if(name=="HMSK"){
            for (int i = 0; i <pos.length; i++) {
                pos[i]=levels.get(0).get(i);
            }
        }else{
            for (int i = 0; i <pos.length; i++) {
                pos[i]=levels.get(1).get(i);
            }
        }
        if (fitness < pBest_fitness) {
            for (int n = 0; n < dims; n++){
                pBest[n] = pos[n];
            }
            pBest_fitness = fitness;
        }
        return fitness;

    }

    public double evaluate(double[] posTemp,double errorThreshold,List<Double>level,List<River>rivers ,String name) {
        List<Double>posList=new ArrayList<>();
        for (int i = 0; i <pos.length ; i++) {
            posList.add(pos[i]);
        }
        List<List<Double>>levels=new ArrayList<>();
        if(name=="HMSK"){
            levels.add(0,posList);
            levels.add(1,level);

        }else{
            levels.add(0,level);
            levels.add(1,posList);
        }
        FitnessCalculation fitnessCalculation=new FitnessCalculation();
        fitness=fitnessCalculation.getFitness( errorThreshold,levels,rivers);
        if(name=="HMSK"){
            for (int i = 0; i <pos.length; i++) {
                pos[i]=levels.get(0).get(i);
            }
        }else{
            for (int i = 0; i <pos.length; i++) {
                pos[i]=levels.get(1).get(i);
            }
        }
        if (fitnessTemp < pBest_fitness) {
            for (int n = 0; n < dims; n++) {
                pBest[n] = posTemp[n];
            }
            pBest_fitness = fitnessTemp;
        }
        return fitnessTemp;

    }

    /**
     * the First Updating Process
     *
     * @param i
     * @param rank
     * @param ε
     * @param poss
     * @param popSize
     * @return
     */
    public double[] fistUpdatingProcess(int i, HashMap<Double, Integer> rank, double ε, double[][] poss, int popSize) {
        Pa = rank.get(fitness) / (double) popSize;
        if (Pa < ε) {
            r = randInt(0, popSize - 1);
            t = randInt(0, popSize - 1);
            while (r == i || t == i) {
                if (r == i) {
                    r = randInt(0, popSize - 1);
                } else if (t == i) {
                    t = randInt(0, popSize - 1);
                }
            }
            double[] pos_r = poss[r];
            double[] pos_t = poss[t];
            for (int j = 1; j < dims-1; j++) {
                pos[j] = pos_r[j] - ε * (pos_t[j] - pos_r[j]);
                pos[j] = boundaryTreatment(pos[j]);
            }
        }
        return pos;
    }

    /**
     * the Second Update Process
     *
     * @param i
     * @param rank
     * @param ε
     * @param poss
     * @param popSize
     * @param global_best_pos
     * @return
     */
    public double[] secondUpdatingProcess(int i, HashMap<Double, Integer> rank, double ε, double[][] poss, int popSize, double[] global_best_pos,double errorThreshold,List<Double>level,List<River>rivers,String name) {
        Pa = rank.get(fitness) / (double) popSize;
        if (Pa < ε) {
            double[] pos_r = poss[r];
            double[] pos_t = poss[t];
            double εCaret = rnd.nextGaussian();
            double ε1 = randDouble(0, 1);
            double[] posTemp = new double[dims];
            if (ε1 > 0.5) {
                for (int j = 1; j < dims-1; j++) {
                    posTemp[j] = pos[j] + εCaret * (pos_t[j] - pos_r[j]);
                    posTemp[j] = boundaryTreatment(posTemp[j]);
                }
            } else {
                for (int j = 1; j < dims-1; j++) {
                    posTemp[j] = pos[j] - εCaret * (pos_t[j] - global_best_pos[j]);
                    posTemp[j] = boundaryTreatment(posTemp[j]);
                }
            }
            if (this.evaluate(posTemp, errorThreshold,level,rivers,name) < this.evaluate(errorThreshold,level,rivers,name)) {
                pos = posTemp;
            }
        }
        return pos;
    }

    /**
     * Gaussian random walk
     */
    public double[] randomWalk(double[] global_best_pos, int iter) {
        double[] σ = this.getσ(global_best_pos, iter);
        for (int i = 1; i < dims-1; i++) {
            pos[i] = NormalDistribution(global_best_pos[i], σ[i])
                    + (randDouble(0, 1) * global_best_pos[i] - randDouble(0, 1) * pos[i]);
            pos[i] = boundaryTreatment(pos[i]);
        }
        return pos;
    }

    public double[] getσ(double[] global_best_pos, int iter) {
        double[] σ = new double[dims];
        for (int i = 0; i < dims; i++) {
            σ[i] = (Math.log(iter) / iter) * (pos[i] - global_best_pos[i]);
        }
        return σ;
    }

    /**
     * Standard normal random distribution
     *
     * @return
     */
    public static double StandardNormalDistribution() {
        Random random = new Random();
        return random.nextGaussian();
    }

    /**
     * normal random distribution
     *
     * @param μ
     * @param σ
     * @return
     */
    public static double NormalDistribution(double μ, double σ) {
        Random random = new Random();
        return σ * random.nextGaussian() + μ;
    }

    public double boundaryTreatment(double data) {
        if (data > up) {
            data = up;
        }
        if (data < down) {
            data = down;
        }
        return data;
    }
}
