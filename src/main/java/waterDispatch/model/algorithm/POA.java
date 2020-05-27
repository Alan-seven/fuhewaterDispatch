package waterDispatch.model.algorithm;

import waterDispatch.*;
import waterDispatch.entity.InputEntity;
import waterDispatch.model.algorithm.sfsAlgorithm.SFS;
import java.util.ArrayList;
import java.util.List;

public class POA {
    private double best=1;
    double[]HMbest_pos;
    double[]LFbest_pos;
    public void poaCalculation(List<River>rivers, int PopSize, int iteration, double errorThreshold, int Num, InputEntity inputEntity){
        int timeLength=inputEntity.getTimeLength();
        HMbest_pos=new double[timeLength+1];
        LFbest_pos=new double[timeLength+1];
        HydroStation HMhydroStation=(HydroStation)rivers.get(0).getNodes().get(1);
        HydroStation LFhydroStation=(HydroStation)rivers.get(0).getNodes().get(3);
        double HMup=HMhydroStation.getLevelNormal();
        double HMdown=HMhydroStation.getLevelDead();
        double LFup=LFhydroStation.getLevelNormal();
        double LFdown=LFhydroStation.getLevelDead();;
        double[] HMlevel=new double[timeLength+1];
        HMlevel[0]=inputEntity.getHMstart();
        HMlevel[timeLength]=inputEntity.getHMend();
        double[] LFlevel=new double[timeLength+1];
        LFlevel[0]=inputEntity.getLFstart();;
        LFlevel[timeLength]=inputEntity.getLFend();
        for (int i = 1; i <timeLength; i++) {
            LFlevel[i]=Math.random()*(LFup-LFdown)+LFdown;
        }
        for (int i = 0; i <=timeLength; i++) {
            LFbest_pos[i]=LFlevel[i];;
        }
        SFS sfs=new SFS();
        int count=0;
        do {
            List<Double>LFlevellist= new ArrayList<>();
            for (int i = 0; i < LFlevel.length; i++) {
                LFlevellist.add(LFlevel[i]);
            }
            sfs.initial(timeLength + 1, PopSize, iteration, HMup, HMdown, inputEntity.getHMstart(), inputEntity.getHMend(), errorThreshold, LFlevellist,rivers,"HMSK");
            sfs.run(errorThreshold, LFlevellist,rivers,"HMSK");
            if(sfs.getGlobal_best()<best){
                best=sfs.getGlobal_best();
                HMbest_pos=sfs.getGlobal_best_pos();
            }
            for (int i = 0; i <timeLength; i++) {
                rivers.get(0).getNodes().get(1).getTimeData().get(i).setLevelEnd(HMbest_pos[i+1]);
            }
            System.out.println(best);
            for (int i = 0; i <HMbest_pos.length ; i++) {
                HMlevel[i]=HMbest_pos[i];
            }
            List<Double>HMlevellist=new ArrayList<>();
            for (int i = 0; i < HMlevel.length; i++) {
                HMlevellist.add(HMlevel[i]);
            }
            sfs.initial(timeLength + 1, PopSize, iteration, LFup, LFdown, inputEntity.getLFstart(), inputEntity.getLFend(), errorThreshold,HMlevellist,rivers,"LFSK");
            sfs.run(errorThreshold, HMlevellist,rivers,"HMSK");
            if(sfs.getGlobal_best()<best){
                best=sfs.getGlobal_best();
                LFbest_pos=sfs.getGlobal_best_pos();
            }
            for (int i = 0; i <timeLength; i++) {
                rivers.get(0).getNodes().get(3).getTimeData().get(i).setLevelEnd(LFbest_pos[i+1]);
            }
            for (int i = 0; i <LFbest_pos.length ; i++) {
                LFlevel[i]=LFbest_pos[i];
            }
            System.out.println(best);
            count++;
            System.out.println(count);
        }while (count<Num);
    }
}
