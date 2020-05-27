package waterDispatch.controller;

import waterDispatch.River;
import waterDispatch.entity.InputEntity;
import waterDispatch.entity.ResultEntity;
import waterDispatch.model.InitializaBasin;
import waterDispatch.model.OutputLoad;
import waterDispatch.model.algorithm.POA;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ModelController {
    public ResultEntity newBuiltPlanController(InputEntity inputEntity) throws IOException {
        ResultEntity result=new ResultEntity();
        List<River> rivers=new ArrayList<>();
        int PopSize=50;//粒子数
        int iteration=100;//SFS迭代次数
        double errorThreshold=0.00001;//河道生态流量误差阈值
        int Num=20;//POA迭代次数
        InitializaBasin initializaBasin=new InitializaBasin();
        initializaBasin.initializationByInterface(rivers,inputEntity);
        POA poa=new POA();
        poa.poaCalculation(rivers,PopSize,iteration,errorThreshold,Num,inputEntity);
        OutputLoad outputLoad=new OutputLoad();
        outputLoad.resultSaving(rivers,result,inputEntity);
        return result;
    }
}
