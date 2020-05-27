package waterDispatch.entity;


import java.util.List;

public class InputEntity {
    int timeLength;//调度时段数
    double HMstart;//洪门初水位
    double HMend;//洪门末水位
    double LFstart;//廖坊初水位
    double LFend;//廖坊末水位
    //JXXWeight+LCQWeight=1
    double JXXWeight=0.2;//金溪县分水比例
    double LCQWeight=0.8;//临川区分水比例

    List<Double> waterRequirement;//调度期总需水量
    List<Double[]>selfProducedInflow;//各调度时段自产水量
    List<Double[]>minInstreamingEcologicalFlow;//各调度河道最小水量
    List<Double>industrialWaterWeight;//工业用水比例
    List<Double>agriculturalWaterWeight;//农业用水比例
    List<Double>domesticWaterWeight;//生活用水比例
    List<Double>forestryWaterWeight;//林牧渔用水比例
    List<Double[]>industrialWaterRatio;//工业分水系数
    List<Double[]>agriculturalWaterRatio;//农业分水系数
    List<Double[]>domesticWaterRatio;//生活分水系数
    List<Double[]>forestryWaterRatio;//林牧渔分水系数
    List<Double[]>industrialWaterReturnRatio;//各时段工业用水回归系数
    List<Double[]>agriculturalWaterReturnRatio;//各时段农业用水回归系数
    List<Double[]>domesticWaterReturnRatio;//各时段生活用水回归系数
    List<Double[]>forestryWaterReturnRatio;//各时段林牧渔用水回归系数
    List<Double[]>inflowFrequency;//各时段来水频率

//    double GCXwaterRequirement;//广昌县调度期总需水量
//    double[]GCXselfProducedInflow=new double[timeLength];//广昌县各调度时段自产水量
//    double[]GCXminInstreamingEcologicalFlow=new double[timeLength];//广昌县各调度河道最小水量（后期可根据时间关系改为流量）
//    //四者用水比例相加等于1 （若完成需水预测则可直接得出四者用水总量）
//    double GCXindustrialWaterWeight;//广昌县工业用水比例
//    double GCXagriculturalWaterWeight;//广昌县农业用水比例
//    double GCXdomesticWaterWeight;//广昌县生活用水比例
//    double GCXforestryWaterWeight;//广昌县林牧渔用水比例
//    double[]GCXindustrialWaterRatio=new double[timeLength];//广昌县工业分水系数
//    double[]GCXagriculturalWaterRatio=new double[timeLength];//广昌县农业分水系数
//    double[]GCXdomesticWaterRatio=new double[timeLength];//广昌县生活分水系数
//    double[]GCXforestryWaterRatio=new double[timeLength];//广昌县林牧渔分水系数
//    double[]GCXindustrialWaterReturnRatio=new double[timeLength];//广昌县各时段工业用水回归系数
//    double[]GCXagriculturalWaterReturnRatio=new double[timeLength];//广昌县各时段农业用水回归系数
//    double[]GCXdomesticWaterReturnRatio=new double[timeLength];//广昌县各时段生活用水回归系数
//    double[]GCXforestryWaterReturnRatio=new double[timeLength];//广昌县各时段林牧渔用水回归系数
//    double[]GCXinflowFrequency=new double[timeLength];//广昌县各时段来水频率
//
//    double NFXwaterRequirement;
//    double[]NFXselfProducedInflow=new double[timeLength];
//    double[]NFXminInstreamingEcologicalFlow=new double[timeLength];
//    double NFXindustrialWaterWeight;
//    double NFXagriculturalWaterWeight;
//    double NFXdomesticWaterWeight;
//    double NFXforestryWaterWeight;
//    double[]NFXindustrialWaterRatio=new double[timeLength];
//    double[]NFXagriculturalWaterRatio=new double[timeLength];
//    double[]NFXdomesticWaterRatio=new double[timeLength];
//    double[]NFXforestryWaterRatio=new double[timeLength];
//    double[]NFXindustrialWaterReturnRatio=new double[timeLength];
//    double[]NFXagriculturalWaterReturnRatio=new double[timeLength];
//    double[]NFXdomesticWaterReturnRatio=new double[timeLength];
//    double[]NFXforestryWaterReturnRatio=new double[timeLength];
//    double[]NFXinflowFrequency=new double[timeLength];
//
//    double LAXwaterRequirement;
//    double[]LAXselfProducedInflow=new double[timeLength];
//    double[]LAXminInstreamingEcologicalFlow=new double[timeLength];
//    double LAXindustrialWaterWeight;
//    double LAXagriculturalWaterWeight;
//    double LAXdomesticWaterWeight;
//    double LAXforestryWaterWeight;
//    double[]LAXindustrialWaterRatio=new double[timeLength];
//    double[]LAXagriculturalWaterRatio=new double[timeLength];
//    double[]LAXdomesticWaterRatio=new double[timeLength];
//    double[]LAXforestryWaterRatio=new double[timeLength];
//    double[]LAXindustrialWaterReturnRatio=new double[timeLength];
//    double[]LAXagriculturalWaterReturnRatio=new double[timeLength];
//    double[]LAXdomesticWaterReturnRatio=new double[timeLength];
//    double[]LAXforestryWaterReturnRatio=new double[timeLength];
//    double[]LAXinflowFrequency=new double[timeLength];
//
//    double CRXwaterRequirement;
//    double[]CRXselfProducedInflow=new double[timeLength];
//    double[]CRXminInstreamingEcologicalFlow=new double[timeLength];
//    double CRXindustrialWaterWeight;
//    double CRXagriculturalWaterWeight;
//    double CRXdomesticWaterWeight;
//    double CRXforestryWaterWeight;
//    double[]CRXindustrialWaterRatio=new double[timeLength];
//    double[]CRXagriculturalWaterRatio=new double[timeLength];
//    double[]CRXdomesticWaterRatio=new double[timeLength];
//    double[]CRXforestryWaterRatio=new double[timeLength];
//    double[]CRXindustrialWaterReturnRatio=new double[timeLength];
//    double[]CRXagriculturalWaterReturnRatio=new double[timeLength];
//    double[]CRXdomesticWaterReturnRatio=new double[timeLength];
//    double[]CRXforestryWaterReturnRatio=new double[timeLength];
//    double[]CRXinflowFrequency=new double[timeLength];
//
//    double YHXwaterRequirement;
//    double[]YHXselfProducedInflow=new double[timeLength];
//    double[]YHXminInstreamingEcologicalFlow=new double[timeLength];
//    double YHXindustrialWaterWeight;
//    double YHXagriculturalWaterWeight;
//    double YHXdomesticWaterWeight;
//    double YHXforestryWaterWeight;
//    double[]YHXindustrialWaterRatio=new double[timeLength];
//    double[]YHXagriculturalWaterRatio=new double[timeLength];
//    double[]YHXdomesticWaterRatio=new double[timeLength];
//    double[]YHXforestryWaterRatio=new double[timeLength];
//    double[]YHXindustrialWaterReturnRatio=new double[timeLength];
//    double[]YHXagriculturalWaterReturnRatio=new double[timeLength];
//    double[]YHXdomesticWaterReturnRatio=new double[timeLength];
//    double[]YHXforestryWaterReturnRatio=new double[timeLength];
//    double[]YHXinflowFrequency=new double[timeLength];
//
//    double LCXwaterRequirement;
//    double[]LCXselfProducedInflow=new double[timeLength];
//    double[]LCXminInstreamingEcologicalFlow=new double[timeLength];
//    double LCXindustrialWaterWeight;
//    double LCXagriculturalWaterWeight;
//    double LCXdomesticWaterWeight;
//    double LCXforestryWaterWeight;
//    double[]LCXindustrialWaterRatio=new double[timeLength];
//    double[]LCXagriculturalWaterRatio=new double[timeLength];
//    double[]LCXdomesticWaterRatio=new double[timeLength];
//    double[]LCXforestryWaterRatio=new double[timeLength];
//    double[]LCXindustrialWaterReturnRatio=new double[timeLength];
//    double[]LCXagriculturalWaterReturnRatio=new double[timeLength];
//    double[]LCXdomesticWaterReturnRatio=new double[timeLength];
//    double[]LCXforestryWaterReturnRatio=new double[timeLength];
//    double[]LCXinflowFrequency=new double[timeLength];
//
//    double NCXwaterRequirement;
//    double[]NCXselfProducedInflow=new double[timeLength];
//    double[]NCXminInstreamingEcologicalFlow=new double[timeLength];
//    double NCXindustrialWaterWeight;
//    double NCXagriculturalWaterWeight;
//    double NCXdomesticWaterWeight;
//    double NCXforestryWaterWeight;
//    double[]NCXindustrialWaterRatio=new double[timeLength];
//    double[]NCXagriculturalWaterRatio=new double[timeLength];
//    double[]NCXdomesticWaterRatio=new double[timeLength];
//    double[]NCXforestryWaterRatio=new double[timeLength];
//    double[]NCXindustrialWaterReturnRatio=new double[timeLength];
//    double[]NCXagriculturalWaterReturnRatio=new double[timeLength];
//    double[]NCXdomesticWaterReturnRatio=new double[timeLength];
//    double[]NCXforestryWaterReturnRatio=new double[timeLength];
//    double[]NCXinflowFrequency=new double[timeLength];
//
//    double JXXwaterRequirement;
//    double[]JXXselfProducedInflow=new double[timeLength];
//    double[]JXXminInstreamingEcologicalFlow=new double[timeLength];
//    double JXXindustrialWaterWeight;
//    double JXXagriculturalWaterWeight;
//    double JXXdomesticWaterWeight;
//    double JXXforestryWaterWeight;
//    double[]JXXindustrialWaterRatio=new double[timeLength];
//    double[]JXXagriculturalWaterRatio=new double[timeLength];
//    double[]JXXdomesticWaterRatio=new double[timeLength];
//    double[]JXXforestryWaterRatio=new double[timeLength];
//    double[]JXXindustrialWaterReturnRatio=new double[timeLength];
//    double[]JXXagriculturalWaterReturnRatio=new double[timeLength];
//    double[]JXXdomesticWaterReturnRatio=new double[timeLength];
//    double[]JXXforestryWaterReturnRatio=new double[timeLength];
//    double[]JXXinflowFrequency=new double[timeLength];
//
//    double LCQwaterRequirement;
//    double[]LCQselfProducedInflow=new double[timeLength];
//    double[]LCQminInstreamingEcologicalFlow=new double[timeLength];
//    double LCQindustrialWaterWeight;
//    double LCQagriculturalWaterWeight;
//    double LCQdomesticWaterWeight;
//    double LCQforestryWaterWeight;
//    double[]LCQindustrialWaterRatio=new double[timeLength];
//    double[]LCQagriculturalWaterRatio=new double[timeLength];
//    double[]LCQdomesticWaterRatio=new double[timeLength];
//    double[]LCQforestryWaterRatio=new double[timeLength];
//    double[]LCQindustrialWaterReturnRatio=new double[timeLength];
//    double[]LCQagriculturalWaterReturnRatio=new double[timeLength];
//    double[]LCQdomesticWaterReturnRatio=new double[timeLength];
//    double[]LCQforestryWaterReturnRatio=new double[timeLength];
//    double[]LCQinflowFrequency=new double[timeLength];
//
//    double ZXXwaterRequirement;
//    double[]ZXXselfProducedInflow=new double[timeLength];
//    double[]ZXXminInstreamingEcologicalFlow=new double[timeLength];
//    double ZXXindustrialWaterWeight;
//    double ZXXagriculturalWaterWeight;
//    double ZXXdomesticWaterWeight;
//    double ZXXforestryWaterWeight;
//    double[]ZXXindustrialWaterRatio=new double[timeLength];
//    double[]ZXXagriculturalWaterRatio=new double[timeLength];
//    double[]ZXXdomesticWaterRatio=new double[timeLength];
//    double[]ZXXforestryWaterRatio=new double[timeLength];
//    double[]ZXXindustrialWaterReturnRatio=new double[timeLength];
//    double[]ZXXagriculturalWaterReturnRatio=new double[timeLength];
//    double[]ZXXdomesticWaterReturnRatio=new double[timeLength];
//    double[]ZXXforestryWaterReturnRatio=new double[timeLength];
//    double[]ZXXinflowFrequency=new double[timeLength];
//
//    double DXXwaterRequirement;
//    double[]DXXselfProducedInflow=new double[timeLength];
//    double[]DXXminInstreamingEcologicalFlow=new double[timeLength];
//    double DXXindustrialWaterWeight;
//    double DXXagriculturalWaterWeight;
//    double DXXdomesticWaterWeight;
//    double DXXforestryWaterWeight;
//    double[]DXXindustrialWaterRatio=new double[timeLength];
//    double[]DXXagriculturalWaterRatio=new double[timeLength];
//    double[]DXXdomesticWaterRatio=new double[timeLength];
//    double[]DXXforestryWaterRatio=new double[timeLength];
//    double[]DXXindustrialWaterReturnRatio=new double[timeLength];
//    double[]DXXagriculturalWaterReturnRatio=new double[timeLength];
//    double[]DXXdomesticWaterReturnRatio=new double[timeLength];
//    double[]DXXforestryWaterReturnRatio=new double[timeLength];
//    double[]DXXinflowFrequency=new double[timeLength];
//
//    double GFPYwaterRequirement;
//    double[]GFPYselfProducedInflow=new double[timeLength];
//    double[]GFPYminInstreamingEcologicalFlow;
//    double GFPYindustrialWaterWeight;
//    double GFPYagriculturalWaterWeight;
//    double GFPYdomesticWaterWeight;
//    double GFPYforestryWaterWeight;
//    double[]GFPYindustrialWaterRatio=new double[timeLength];
//    double[]GFPYagriculturalWaterRatio=new double[timeLength];
//    double[]GFPYdomesticWaterRatio=new double[timeLength];
//    double[]GFPYforestryWaterRatio=new double[timeLength];
//    double[]GFPYindustrialWaterReturnRatio=new double[timeLength];
//    double[]GFPYagriculturalWaterReturnRatio=new double[timeLength];
//    double[]GFPYdomesticWaterReturnRatio=new double[timeLength];
//    double[]GFPYforestryWaterReturnRatio=new double[timeLength];
//    double[]GFPYinflowFrequency=new double[timeLength];

    double[]HMSKselfProducedInflow=new double[timeLength];
    double[]HMSKminInstreamingEcologicalFlow=new double[timeLength];
    double[]HMSKinflowFrequency=new double[timeLength];

    double[]LFSKselfProducedInflow=new double[timeLength];
    double[]LFSKminInstreamingEcologicalFlow=new double[timeLength];
    double[]LFSKinflowFrequency=new double[timeLength];


    
    
    
    
    
    
    
    
    
    
    
    
    
    

    public int getTimeLength() {
        return timeLength;
    }

    public void setTimeLength(int timeLength) {
        this.timeLength = timeLength;
    }

    public double getHMstart() {
        return HMstart;
    }

    public void setHMstart(double HMstart) {
        this.HMstart = HMstart;
    }

    public double getHMend() {
        return HMend;
    }

    public void setHMend(double HMend) {
        this.HMend = HMend;
    }

    public double getLFstart() {
        return LFstart;
    }

    public void setLFstart(double LFstart) {
        this.LFstart = LFstart;
    }

    public double getLFend() {
        return LFend;
    }

    public void setLFend(double LFend) {
        this.LFend = LFend;
    }

    public double getJXXWeight() {
        return JXXWeight;
    }

    public void setJXXWeight(double JXXWeight) {
        this.JXXWeight = JXXWeight;
    }

    public double getLCQWeight() {
        return LCQWeight;
    }

    public void setLCQWeight(double LCQWeight) {
        this.LCQWeight = LCQWeight;
    }

    public List<Double> getWaterRequirement() {
        return waterRequirement;
    }

    public void setWaterRequirement(List<Double> waterRequirement) {
        this.waterRequirement = waterRequirement;
    }

    public List<Double[]> getSelfProducedInflow() {
        return selfProducedInflow;
    }

    public void setSelfProducedInflow(List<Double[]> selfProducedInflow) {
        this.selfProducedInflow = selfProducedInflow;
    }

    public List<Double[]> getMinInstreamingEcologicalFlow() {
        return minInstreamingEcologicalFlow;
    }

    public void setMinInstreamingEcologicalFlow(List<Double[]> minInstreamingEcologicalFlow) {
        this.minInstreamingEcologicalFlow = minInstreamingEcologicalFlow;
    }

    public List<Double> getIndustrialWaterWeight() {
        return industrialWaterWeight;
    }

    public void setIndustrialWaterWeight(List<Double> industrialWaterWeight) {
        this.industrialWaterWeight = industrialWaterWeight;
    }

    public List<Double> getAgriculturalWaterWeight() {
        return agriculturalWaterWeight;
    }

    public void setAgriculturalWaterWeight(List<Double> agriculturalWaterWeight) {
        this.agriculturalWaterWeight = agriculturalWaterWeight;
    }

    public List<Double> getDomesticWaterWeight() {
        return domesticWaterWeight;
    }

    public void setDomesticWaterWeight(List<Double> domesticWaterWeight) {
        this.domesticWaterWeight = domesticWaterWeight;
    }

    public List<Double> getForestryWaterWeight() {
        return forestryWaterWeight;
    }

    public void setForestryWaterWeight(List<Double> forestryWaterWeight) {
        this.forestryWaterWeight = forestryWaterWeight;
    }

    public List<Double[]> getIndustrialWaterRatio() {
        return industrialWaterRatio;
    }

    public void setIndustrialWaterRatio(List<Double[]> industrialWaterRatio) {
        this.industrialWaterRatio = industrialWaterRatio;
    }

    public List<Double[]> getAgriculturalWaterRatio() {
        return agriculturalWaterRatio;
    }

    public void setAgriculturalWaterRatio(List<Double[]> agriculturalWaterRatio) {
        this.agriculturalWaterRatio = agriculturalWaterRatio;
    }

    public List<Double[]> getDomesticWaterRatio() {
        return domesticWaterRatio;
    }

    public void setDomesticWaterRatio(List<Double[]> domesticWaterRatio) {
        this.domesticWaterRatio = domesticWaterRatio;
    }

    public List<Double[]> getForestryWaterRatio() {
        return forestryWaterRatio;
    }

    public void setForestryWaterRatio(List<Double[]> forestryWaterRatio) {
        this.forestryWaterRatio = forestryWaterRatio;
    }

    public List<Double[]> getIndustrialWaterReturnRatio() {
        return industrialWaterReturnRatio;
    }

    public void setIndustrialWaterReturnRatio(List<Double[]> industrialWaterReturnRatio) {
        this.industrialWaterReturnRatio = industrialWaterReturnRatio;
    }

    public List<Double[]> getAgriculturalWaterReturnRatio() {
        return agriculturalWaterReturnRatio;
    }

    public void setAgriculturalWaterReturnRatio(List<Double[]> agriculturalWaterReturnRatio) {
        this.agriculturalWaterReturnRatio = agriculturalWaterReturnRatio;
    }

    public List<Double[]> getDomesticWaterReturnRatio() {
        return domesticWaterReturnRatio;
    }

    public void setDomesticWaterReturnRatio(List<Double[]> domesticWaterReturnRatio) {
        this.domesticWaterReturnRatio = domesticWaterReturnRatio;
    }

    public List<Double[]> getForestryWaterReturnRatio() {
        return forestryWaterReturnRatio;
    }

    public void setForestryWaterReturnRatio(List<Double[]> forestryWaterReturnRatio) {
        this.forestryWaterReturnRatio = forestryWaterReturnRatio;
    }

    public List<Double[]> getInflowFrequency() {
        return inflowFrequency;
    }

    public void setInflowFrequency(List<Double[]> inflowFrequency) {
        this.inflowFrequency = inflowFrequency;
    }

    //    public double getGCXwaterRequirement() {
//        return GCXwaterRequirement;
//    }
//
//    public void setGCXwaterRequirement(double GCXwaterRequirement) {
//        this.GCXwaterRequirement = GCXwaterRequirement;
//    }
//
//    public double[] getGCXselfProducedInflow() {
//        return GCXselfProducedInflow;
//    }
//
//    public void setGCXselfProducedInflow(double[] GCXselfProducedInflow) {
//        this.GCXselfProducedInflow = GCXselfProducedInflow;
//    }
//
//    public double[] getGCXminInstreamingEcologicalFlow() {
//        return GCXminInstreamingEcologicalFlow;
//    }
//
//    public void setGCXminInstreamingEcologicalFlow(double[] GCXminInstreamingEcologicalFlow) {
//        this.GCXminInstreamingEcologicalFlow = GCXminInstreamingEcologicalFlow;
//    }
//
//    public double getGCXindustrialWaterWeight() {
//        return GCXindustrialWaterWeight;
//    }
//
//    public void setGCXindustrialWaterWeight(double GCXindustrialWaterWeight) {
//        this.GCXindustrialWaterWeight = GCXindustrialWaterWeight;
//    }
//
//    public double getGCXagriculturalWaterWeight() {
//        return GCXagriculturalWaterWeight;
//    }
//
//    public void setGCXagriculturalWaterWeight(double GCXagriculturalWaterWeight) {
//        this.GCXagriculturalWaterWeight = GCXagriculturalWaterWeight;
//    }
//
//    public double getGCXdomesticWaterWeight() {
//        return GCXdomesticWaterWeight;
//    }
//
//    public void setGCXdomesticWaterWeight(double GCXdomesticWaterWeight) {
//        this.GCXdomesticWaterWeight = GCXdomesticWaterWeight;
//    }
//
//    public double getGCXforestryWaterWeight() {
//        return GCXforestryWaterWeight;
//    }
//
//    public void setGCXforestryWaterWeight(double GCXforestryWaterWeight) {
//        this.GCXforestryWaterWeight = GCXforestryWaterWeight;
//    }
//
//    public double[] getGCXindustrialWaterRatio() {
//        return GCXindustrialWaterRatio;
//    }
//
//    public void setGCXindustrialWaterRatio(double[] GCXindustrialWaterRatio) {
//        this.GCXindustrialWaterRatio = GCXindustrialWaterRatio;
//    }
//
//    public double[] getGCXagriculturalWaterRatio() {
//        return GCXagriculturalWaterRatio;
//    }
//
//    public void setGCXagriculturalWaterRatio(double[] GCXagriculturalWaterRatio) {
//        this.GCXagriculturalWaterRatio = GCXagriculturalWaterRatio;
//    }
//
//    public double[] getGCXdomesticWaterRatio() {
//        return GCXdomesticWaterRatio;
//    }
//
//    public void setGCXdomesticWaterRatio(double[] GCXdomesticWaterRatio) {
//        this.GCXdomesticWaterRatio = GCXdomesticWaterRatio;
//    }
//
//    public double[] getGCXforestryWaterRatio() {
//        return GCXforestryWaterRatio;
//    }
//
//    public void setGCXforestryWaterRatio(double[] GCXforestryWaterRatio) {
//        this.GCXforestryWaterRatio = GCXforestryWaterRatio;
//    }
//
//    public double[] getGCXindustrialWaterReturnRatio() {
//        return GCXindustrialWaterReturnRatio;
//    }
//
//    public void setGCXindustrialWaterReturnRatio(double[] GCXindustrialWaterReturnRatio) {
//        this.GCXindustrialWaterReturnRatio = GCXindustrialWaterReturnRatio;
//    }
//
//    public double[] getGCXagriculturalWaterReturnRatio() {
//        return GCXagriculturalWaterReturnRatio;
//    }
//
//    public void setGCXagriculturalWaterReturnRatio(double[] GCXagriculturalWaterReturnRatio) {
//        this.GCXagriculturalWaterReturnRatio = GCXagriculturalWaterReturnRatio;
//    }
//
//    public double[] getGCXdomesticWaterReturnRatio() {
//        return GCXdomesticWaterReturnRatio;
//    }
//
//    public void setGCXdomesticWaterReturnRatio(double[] GCXdomesticWaterReturnRatio) {
//        this.GCXdomesticWaterReturnRatio = GCXdomesticWaterReturnRatio;
//    }
//
//    public double[] getGCXforestryWaterReturnRatio() {
//        return GCXforestryWaterReturnRatio;
//    }
//
//    public void setGCXforestryWaterReturnRatio(double[] GCXforestryWaterReturnRatio) {
//        this.GCXforestryWaterReturnRatio = GCXforestryWaterReturnRatio;
//    }
//
//    public double[] getGCXinflowFrequency() {
//        return GCXinflowFrequency;
//    }
//
//    public void setGCXinflowFrequency(double[] GCXinflowFrequency) {
//        this.GCXinflowFrequency = GCXinflowFrequency;
//    }
//
//    public double getNFXwaterRequirement() {
//        return NFXwaterRequirement;
//    }
//
//    public void setNFXwaterRequirement(double NFXwaterRequirement) {
//        this.NFXwaterRequirement = NFXwaterRequirement;
//    }
//
//    public double[] getNFXselfProducedInflow() {
//        return NFXselfProducedInflow;
//    }
//
//    public void setNFXselfProducedInflow(double[] NFXselfProducedInflow) {
//        this.NFXselfProducedInflow = NFXselfProducedInflow;
//    }
//
//    public double[] getNFXminInstreamingEcologicalFlow() {
//        return NFXminInstreamingEcologicalFlow;
//    }
//
//    public void setNFXminInstreamingEcologicalFlow(double[] NFXminInstreamingEcologicalFlow) {
//        this.NFXminInstreamingEcologicalFlow = NFXminInstreamingEcologicalFlow;
//    }
//
//    public double getNFXindustrialWaterWeight() {
//        return NFXindustrialWaterWeight;
//    }
//
//    public void setNFXindustrialWaterWeight(double NFXindustrialWaterWeight) {
//        this.NFXindustrialWaterWeight = NFXindustrialWaterWeight;
//    }
//
//    public double getNFXagriculturalWaterWeight() {
//        return NFXagriculturalWaterWeight;
//    }
//
//    public void setNFXagriculturalWaterWeight(double NFXagriculturalWaterWeight) {
//        this.NFXagriculturalWaterWeight = NFXagriculturalWaterWeight;
//    }
//
//    public double getNFXdomesticWaterWeight() {
//        return NFXdomesticWaterWeight;
//    }
//
//    public void setNFXdomesticWaterWeight(double NFXdomesticWaterWeight) {
//        this.NFXdomesticWaterWeight = NFXdomesticWaterWeight;
//    }
//
//    public double getNFXforestryWaterWeight() {
//        return NFXforestryWaterWeight;
//    }
//
//    public void setNFXforestryWaterWeight(double NFXforestryWaterWeight) {
//        this.NFXforestryWaterWeight = NFXforestryWaterWeight;
//    }
//
//    public double[] getNFXindustrialWaterRatio() {
//        return NFXindustrialWaterRatio;
//    }
//
//    public void setNFXindustrialWaterRatio(double[] NFXindustrialWaterRatio) {
//        this.NFXindustrialWaterRatio = NFXindustrialWaterRatio;
//    }
//
//    public double[] getNFXagriculturalWaterRatio() {
//        return NFXagriculturalWaterRatio;
//    }
//
//    public void setNFXagriculturalWaterRatio(double[] NFXagriculturalWaterRatio) {
//        this.NFXagriculturalWaterRatio = NFXagriculturalWaterRatio;
//    }
//
//    public double[] getNFXdomesticWaterRatio() {
//        return NFXdomesticWaterRatio;
//    }
//
//    public void setNFXdomesticWaterRatio(double[] NFXdomesticWaterRatio) {
//        this.NFXdomesticWaterRatio = NFXdomesticWaterRatio;
//    }
//
//    public double[] getNFXforestryWaterRatio() {
//        return NFXforestryWaterRatio;
//    }
//
//    public void setNFXforestryWaterRatio(double[] NFXforestryWaterRatio) {
//        this.NFXforestryWaterRatio = NFXforestryWaterRatio;
//    }
//
//    public double[] getNFXindustrialWaterReturnRatio() {
//        return NFXindustrialWaterReturnRatio;
//    }
//
//    public void setNFXindustrialWaterReturnRatio(double[] NFXindustrialWaterReturnRatio) {
//        this.NFXindustrialWaterReturnRatio = NFXindustrialWaterReturnRatio;
//    }
//
//    public double[] getNFXagriculturalWaterReturnRatio() {
//        return NFXagriculturalWaterReturnRatio;
//    }
//
//    public void setNFXagriculturalWaterReturnRatio(double[] NFXagriculturalWaterReturnRatio) {
//        this.NFXagriculturalWaterReturnRatio = NFXagriculturalWaterReturnRatio;
//    }
//
//    public double[] getNFXdomesticWaterReturnRatio() {
//        return NFXdomesticWaterReturnRatio;
//    }
//
//    public void setNFXdomesticWaterReturnRatio(double[] NFXdomesticWaterReturnRatio) {
//        this.NFXdomesticWaterReturnRatio = NFXdomesticWaterReturnRatio;
//    }
//
//    public double[] getNFXforestryWaterReturnRatio() {
//        return NFXforestryWaterReturnRatio;
//    }
//
//    public void setNFXforestryWaterReturnRatio(double[] NFXforestryWaterReturnRatio) {
//        this.NFXforestryWaterReturnRatio = NFXforestryWaterReturnRatio;
//    }
//
//    public double[] getNFXinflowFrequency() {
//        return NFXinflowFrequency;
//    }
//
//    public void setNFXinflowFrequency(double[] NFXinflowFrequency) {
//        this.NFXinflowFrequency = NFXinflowFrequency;
//    }
//
//    public double getLAXwaterRequirement() {
//        return LAXwaterRequirement;
//    }
//
//    public void setLAXwaterRequirement(double LAXwaterRequirement) {
//        this.LAXwaterRequirement = LAXwaterRequirement;
//    }
//
//    public double[] getLAXselfProducedInflow() {
//        return LAXselfProducedInflow;
//    }
//
//    public void setLAXselfProducedInflow(double[] LAXselfProducedInflow) {
//        this.LAXselfProducedInflow = LAXselfProducedInflow;
//    }
//
//    public double[] getLAXminInstreamingEcologicalFlow() {
//        return LAXminInstreamingEcologicalFlow;
//    }
//
//    public void setLAXminInstreamingEcologicalFlow(double[] LAXminInstreamingEcologicalFlow) {
//        this.LAXminInstreamingEcologicalFlow = LAXminInstreamingEcologicalFlow;
//    }
//
//    public double getLAXindustrialWaterWeight() {
//        return LAXindustrialWaterWeight;
//    }
//
//    public void setLAXindustrialWaterWeight(double LAXindustrialWaterWeight) {
//        this.LAXindustrialWaterWeight = LAXindustrialWaterWeight;
//    }
//
//    public double getLAXagriculturalWaterWeight() {
//        return LAXagriculturalWaterWeight;
//    }
//
//    public void setLAXagriculturalWaterWeight(double LAXagriculturalWaterWeight) {
//        this.LAXagriculturalWaterWeight = LAXagriculturalWaterWeight;
//    }
//
//    public double getLAXdomesticWaterWeight() {
//        return LAXdomesticWaterWeight;
//    }
//
//    public void setLAXdomesticWaterWeight(double LAXdomesticWaterWeight) {
//        this.LAXdomesticWaterWeight = LAXdomesticWaterWeight;
//    }
//
//    public double getLAXforestryWaterWeight() {
//        return LAXforestryWaterWeight;
//    }
//
//    public void setLAXforestryWaterWeight(double LAXforestryWaterWeight) {
//        this.LAXforestryWaterWeight = LAXforestryWaterWeight;
//    }
//
//    public double[] getLAXindustrialWaterRatio() {
//        return LAXindustrialWaterRatio;
//    }
//
//    public void setLAXindustrialWaterRatio(double[] LAXindustrialWaterRatio) {
//        this.LAXindustrialWaterRatio = LAXindustrialWaterRatio;
//    }
//
//    public double[] getLAXagriculturalWaterRatio() {
//        return LAXagriculturalWaterRatio;
//    }
//
//    public void setLAXagriculturalWaterRatio(double[] LAXagriculturalWaterRatio) {
//        this.LAXagriculturalWaterRatio = LAXagriculturalWaterRatio;
//    }
//
//    public double[] getLAXdomesticWaterRatio() {
//        return LAXdomesticWaterRatio;
//    }
//
//    public void setLAXdomesticWaterRatio(double[] LAXdomesticWaterRatio) {
//        this.LAXdomesticWaterRatio = LAXdomesticWaterRatio;
//    }
//
//    public double[] getLAXforestryWaterRatio() {
//        return LAXforestryWaterRatio;
//    }
//
//    public void setLAXforestryWaterRatio(double[] LAXforestryWaterRatio) {
//        this.LAXforestryWaterRatio = LAXforestryWaterRatio;
//    }
//
//    public double[] getLAXindustrialWaterReturnRatio() {
//        return LAXindustrialWaterReturnRatio;
//    }
//
//    public void setLAXindustrialWaterReturnRatio(double[] LAXindustrialWaterReturnRatio) {
//        this.LAXindustrialWaterReturnRatio = LAXindustrialWaterReturnRatio;
//    }
//
//    public double[] getLAXagriculturalWaterReturnRatio() {
//        return LAXagriculturalWaterReturnRatio;
//    }
//
//    public void setLAXagriculturalWaterReturnRatio(double[] LAXagriculturalWaterReturnRatio) {
//        this.LAXagriculturalWaterReturnRatio = LAXagriculturalWaterReturnRatio;
//    }
//
//    public double[] getLAXdomesticWaterReturnRatio() {
//        return LAXdomesticWaterReturnRatio;
//    }
//
//    public void setLAXdomesticWaterReturnRatio(double[] LAXdomesticWaterReturnRatio) {
//        this.LAXdomesticWaterReturnRatio = LAXdomesticWaterReturnRatio;
//    }
//
//    public double[] getLAXforestryWaterReturnRatio() {
//        return LAXforestryWaterReturnRatio;
//    }
//
//    public void setLAXforestryWaterReturnRatio(double[] LAXforestryWaterReturnRatio) {
//        this.LAXforestryWaterReturnRatio = LAXforestryWaterReturnRatio;
//    }
//
//    public double[] getLAXinflowFrequency() {
//        return LAXinflowFrequency;
//    }
//
//    public void setLAXinflowFrequency(double[] LAXinflowFrequency) {
//        this.LAXinflowFrequency = LAXinflowFrequency;
//    }
//
//    public double getCRXwaterRequirement() {
//        return CRXwaterRequirement;
//    }
//
//    public void setCRXwaterRequirement(double CRXwaterRequirement) {
//        this.CRXwaterRequirement = CRXwaterRequirement;
//    }
//
//    public double[] getCRXselfProducedInflow() {
//        return CRXselfProducedInflow;
//    }
//
//    public void setCRXselfProducedInflow(double[] CRXselfProducedInflow) {
//        this.CRXselfProducedInflow = CRXselfProducedInflow;
//    }
//
//    public double[] getCRXminInstreamingEcologicalFlow() {
//        return CRXminInstreamingEcologicalFlow;
//    }
//
//    public void setCRXminInstreamingEcologicalFlow(double[] CRXminInstreamingEcologicalFlow) {
//        this.CRXminInstreamingEcologicalFlow = CRXminInstreamingEcologicalFlow;
//    }
//
//    public double getCRXindustrialWaterWeight() {
//        return CRXindustrialWaterWeight;
//    }
//
//    public void setCRXindustrialWaterWeight(double CRXindustrialWaterWeight) {
//        this.CRXindustrialWaterWeight = CRXindustrialWaterWeight;
//    }
//
//    public double getCRXagriculturalWaterWeight() {
//        return CRXagriculturalWaterWeight;
//    }
//
//    public void setCRXagriculturalWaterWeight(double CRXagriculturalWaterWeight) {
//        this.CRXagriculturalWaterWeight = CRXagriculturalWaterWeight;
//    }
//
//    public double getCRXdomesticWaterWeight() {
//        return CRXdomesticWaterWeight;
//    }
//
//    public void setCRXdomesticWaterWeight(double CRXdomesticWaterWeight) {
//        this.CRXdomesticWaterWeight = CRXdomesticWaterWeight;
//    }
//
//    public double getCRXforestryWaterWeight() {
//        return CRXforestryWaterWeight;
//    }
//
//    public void setCRXforestryWaterWeight(double CRXforestryWaterWeight) {
//        this.CRXforestryWaterWeight = CRXforestryWaterWeight;
//    }
//
//    public double[] getCRXindustrialWaterRatio() {
//        return CRXindustrialWaterRatio;
//    }
//
//    public void setCRXindustrialWaterRatio(double[] CRXindustrialWaterRatio) {
//        this.CRXindustrialWaterRatio = CRXindustrialWaterRatio;
//    }
//
//    public double[] getCRXagriculturalWaterRatio() {
//        return CRXagriculturalWaterRatio;
//    }
//
//    public void setCRXagriculturalWaterRatio(double[] CRXagriculturalWaterRatio) {
//        this.CRXagriculturalWaterRatio = CRXagriculturalWaterRatio;
//    }
//
//    public double[] getCRXdomesticWaterRatio() {
//        return CRXdomesticWaterRatio;
//    }
//
//    public void setCRXdomesticWaterRatio(double[] CRXdomesticWaterRatio) {
//        this.CRXdomesticWaterRatio = CRXdomesticWaterRatio;
//    }
//
//    public double[] getCRXforestryWaterRatio() {
//        return CRXforestryWaterRatio;
//    }
//
//    public void setCRXforestryWaterRatio(double[] CRXforestryWaterRatio) {
//        this.CRXforestryWaterRatio = CRXforestryWaterRatio;
//    }
//
//    public double[] getCRXindustrialWaterReturnRatio() {
//        return CRXindustrialWaterReturnRatio;
//    }
//
//    public void setCRXindustrialWaterReturnRatio(double[] CRXindustrialWaterReturnRatio) {
//        this.CRXindustrialWaterReturnRatio = CRXindustrialWaterReturnRatio;
//    }
//
//    public double[] getCRXagriculturalWaterReturnRatio() {
//        return CRXagriculturalWaterReturnRatio;
//    }
//
//    public void setCRXagriculturalWaterReturnRatio(double[] CRXagriculturalWaterReturnRatio) {
//        this.CRXagriculturalWaterReturnRatio = CRXagriculturalWaterReturnRatio;
//    }
//
//    public double[] getCRXdomesticWaterReturnRatio() {
//        return CRXdomesticWaterReturnRatio;
//    }
//
//    public void setCRXdomesticWaterReturnRatio(double[] CRXdomesticWaterReturnRatio) {
//        this.CRXdomesticWaterReturnRatio = CRXdomesticWaterReturnRatio;
//    }
//
//    public double[] getCRXforestryWaterReturnRatio() {
//        return CRXforestryWaterReturnRatio;
//    }
//
//    public void setCRXforestryWaterReturnRatio(double[] CRXforestryWaterReturnRatio) {
//        this.CRXforestryWaterReturnRatio = CRXforestryWaterReturnRatio;
//    }
//
//    public double[] getCRXinflowFrequency() {
//        return CRXinflowFrequency;
//    }
//
//    public void setCRXinflowFrequency(double[] CRXinflowFrequency) {
//        this.CRXinflowFrequency = CRXinflowFrequency;
//    }
//
//    public double getYHXwaterRequirement() {
//        return YHXwaterRequirement;
//    }
//
//    public void setYHXwaterRequirement(double YHXwaterRequirement) {
//        this.YHXwaterRequirement = YHXwaterRequirement;
//    }
//
//    public double[] getYHXselfProducedInflow() {
//        return YHXselfProducedInflow;
//    }
//
//    public void setYHXselfProducedInflow(double[] YHXselfProducedInflow) {
//        this.YHXselfProducedInflow = YHXselfProducedInflow;
//    }
//
//    public double[] getYHXminInstreamingEcologicalFlow() {
//        return YHXminInstreamingEcologicalFlow;
//    }
//
//    public void setYHXminInstreamingEcologicalFlow(double[] YHXminInstreamingEcologicalFlow) {
//        this.YHXminInstreamingEcologicalFlow = YHXminInstreamingEcologicalFlow;
//    }
//
//    public double getYHXindustrialWaterWeight() {
//        return YHXindustrialWaterWeight;
//    }
//
//    public void setYHXindustrialWaterWeight(double YHXindustrialWaterWeight) {
//        this.YHXindustrialWaterWeight = YHXindustrialWaterWeight;
//    }
//
//    public double getYHXagriculturalWaterWeight() {
//        return YHXagriculturalWaterWeight;
//    }
//
//    public void setYHXagriculturalWaterWeight(double YHXagriculturalWaterWeight) {
//        this.YHXagriculturalWaterWeight = YHXagriculturalWaterWeight;
//    }
//
//    public double getYHXdomesticWaterWeight() {
//        return YHXdomesticWaterWeight;
//    }
//
//    public void setYHXdomesticWaterWeight(double YHXdomesticWaterWeight) {
//        this.YHXdomesticWaterWeight = YHXdomesticWaterWeight;
//    }
//
//    public double getYHXforestryWaterWeight() {
//        return YHXforestryWaterWeight;
//    }
//
//    public void setYHXforestryWaterWeight(double YHXforestryWaterWeight) {
//        this.YHXforestryWaterWeight = YHXforestryWaterWeight;
//    }
//
//    public double[] getYHXindustrialWaterRatio() {
//        return YHXindustrialWaterRatio;
//    }
//
//    public void setYHXindustrialWaterRatio(double[] YHXindustrialWaterRatio) {
//        this.YHXindustrialWaterRatio = YHXindustrialWaterRatio;
//    }
//
//    public double[] getYHXagriculturalWaterRatio() {
//        return YHXagriculturalWaterRatio;
//    }
//
//    public void setYHXagriculturalWaterRatio(double[] YHXagriculturalWaterRatio) {
//        this.YHXagriculturalWaterRatio = YHXagriculturalWaterRatio;
//    }
//
//    public double[] getYHXdomesticWaterRatio() {
//        return YHXdomesticWaterRatio;
//    }
//
//    public void setYHXdomesticWaterRatio(double[] YHXdomesticWaterRatio) {
//        this.YHXdomesticWaterRatio = YHXdomesticWaterRatio;
//    }
//
//    public double[] getYHXforestryWaterRatio() {
//        return YHXforestryWaterRatio;
//    }
//
//    public void setYHXforestryWaterRatio(double[] YHXforestryWaterRatio) {
//        this.YHXforestryWaterRatio = YHXforestryWaterRatio;
//    }
//
//    public double[] getYHXindustrialWaterReturnRatio() {
//        return YHXindustrialWaterReturnRatio;
//    }
//
//    public void setYHXindustrialWaterReturnRatio(double[] YHXindustrialWaterReturnRatio) {
//        this.YHXindustrialWaterReturnRatio = YHXindustrialWaterReturnRatio;
//    }
//
//    public double[] getYHXagriculturalWaterReturnRatio() {
//        return YHXagriculturalWaterReturnRatio;
//    }
//
//    public void setYHXagriculturalWaterReturnRatio(double[] YHXagriculturalWaterReturnRatio) {
//        this.YHXagriculturalWaterReturnRatio = YHXagriculturalWaterReturnRatio;
//    }
//
//    public double[] getYHXdomesticWaterReturnRatio() {
//        return YHXdomesticWaterReturnRatio;
//    }
//
//    public void setYHXdomesticWaterReturnRatio(double[] YHXdomesticWaterReturnRatio) {
//        this.YHXdomesticWaterReturnRatio = YHXdomesticWaterReturnRatio;
//    }
//
//    public double[] getYHXforestryWaterReturnRatio() {
//        return YHXforestryWaterReturnRatio;
//    }
//
//    public void setYHXforestryWaterReturnRatio(double[] YHXforestryWaterReturnRatio) {
//        this.YHXforestryWaterReturnRatio = YHXforestryWaterReturnRatio;
//    }
//
//    public double[] getYHXinflowFrequency() {
//        return YHXinflowFrequency;
//    }
//
//    public void setYHXinflowFrequency(double[] YHXinflowFrequency) {
//        this.YHXinflowFrequency = YHXinflowFrequency;
//    }
//
//    public double getLCXwaterRequirement() {
//        return LCXwaterRequirement;
//    }
//
//    public void setLCXwaterRequirement(double LCXwaterRequirement) {
//        this.LCXwaterRequirement = LCXwaterRequirement;
//    }
//
//    public double[] getLCXselfProducedInflow() {
//        return LCXselfProducedInflow;
//    }
//
//    public void setLCXselfProducedInflow(double[] LCXselfProducedInflow) {
//        this.LCXselfProducedInflow = LCXselfProducedInflow;
//    }
//
//    public double[] getLCXminInstreamingEcologicalFlow() {
//        return LCXminInstreamingEcologicalFlow;
//    }
//
//    public void setLCXminInstreamingEcologicalFlow(double[] LCXminInstreamingEcologicalFlow) {
//        this.LCXminInstreamingEcologicalFlow = LCXminInstreamingEcologicalFlow;
//    }
//
//    public double getLCXindustrialWaterWeight() {
//        return LCXindustrialWaterWeight;
//    }
//
//    public void setLCXindustrialWaterWeight(double LCXindustrialWaterWeight) {
//        this.LCXindustrialWaterWeight = LCXindustrialWaterWeight;
//    }
//
//    public double getLCXagriculturalWaterWeight() {
//        return LCXagriculturalWaterWeight;
//    }
//
//    public void setLCXagriculturalWaterWeight(double LCXagriculturalWaterWeight) {
//        this.LCXagriculturalWaterWeight = LCXagriculturalWaterWeight;
//    }
//
//    public double getLCXdomesticWaterWeight() {
//        return LCXdomesticWaterWeight;
//    }
//
//    public void setLCXdomesticWaterWeight(double LCXdomesticWaterWeight) {
//        this.LCXdomesticWaterWeight = LCXdomesticWaterWeight;
//    }
//
//    public double getLCXforestryWaterWeight() {
//        return LCXforestryWaterWeight;
//    }
//
//    public void setLCXforestryWaterWeight(double LCXforestryWaterWeight) {
//        this.LCXforestryWaterWeight = LCXforestryWaterWeight;
//    }
//
//    public double[] getLCXindustrialWaterRatio() {
//        return LCXindustrialWaterRatio;
//    }
//
//    public void setLCXindustrialWaterRatio(double[] LCXindustrialWaterRatio) {
//        this.LCXindustrialWaterRatio = LCXindustrialWaterRatio;
//    }
//
//    public double[] getLCXagriculturalWaterRatio() {
//        return LCXagriculturalWaterRatio;
//    }
//
//    public void setLCXagriculturalWaterRatio(double[] LCXagriculturalWaterRatio) {
//        this.LCXagriculturalWaterRatio = LCXagriculturalWaterRatio;
//    }
//
//    public double[] getLCXdomesticWaterRatio() {
//        return LCXdomesticWaterRatio;
//    }
//
//    public void setLCXdomesticWaterRatio(double[] LCXdomesticWaterRatio) {
//        this.LCXdomesticWaterRatio = LCXdomesticWaterRatio;
//    }
//
//    public double[] getLCXforestryWaterRatio() {
//        return LCXforestryWaterRatio;
//    }
//
//    public void setLCXforestryWaterRatio(double[] LCXforestryWaterRatio) {
//        this.LCXforestryWaterRatio = LCXforestryWaterRatio;
//    }
//
//    public double[] getLCXindustrialWaterReturnRatio() {
//        return LCXindustrialWaterReturnRatio;
//    }
//
//    public void setLCXindustrialWaterReturnRatio(double[] LCXindustrialWaterReturnRatio) {
//        this.LCXindustrialWaterReturnRatio = LCXindustrialWaterReturnRatio;
//    }
//
//    public double[] getLCXagriculturalWaterReturnRatio() {
//        return LCXagriculturalWaterReturnRatio;
//    }
//
//    public void setLCXagriculturalWaterReturnRatio(double[] LCXagriculturalWaterReturnRatio) {
//        this.LCXagriculturalWaterReturnRatio = LCXagriculturalWaterReturnRatio;
//    }
//
//    public double[] getLCXdomesticWaterReturnRatio() {
//        return LCXdomesticWaterReturnRatio;
//    }
//
//    public void setLCXdomesticWaterReturnRatio(double[] LCXdomesticWaterReturnRatio) {
//        this.LCXdomesticWaterReturnRatio = LCXdomesticWaterReturnRatio;
//    }
//
//    public double[] getLCXforestryWaterReturnRatio() {
//        return LCXforestryWaterReturnRatio;
//    }
//
//    public void setLCXforestryWaterReturnRatio(double[] LCXforestryWaterReturnRatio) {
//        this.LCXforestryWaterReturnRatio = LCXforestryWaterReturnRatio;
//    }
//
//    public double[] getLCXinflowFrequency() {
//        return LCXinflowFrequency;
//    }
//
//    public void setLCXinflowFrequency(double[] LCXinflowFrequency) {
//        this.LCXinflowFrequency = LCXinflowFrequency;
//    }
//
//    public double getNCXwaterRequirement() {
//        return NCXwaterRequirement;
//    }
//
//    public void setNCXwaterRequirement(double NCXwaterRequirement) {
//        this.NCXwaterRequirement = NCXwaterRequirement;
//    }
//
//    public double[] getNCXselfProducedInflow() {
//        return NCXselfProducedInflow;
//    }
//
//    public void setNCXselfProducedInflow(double[] NCXselfProducedInflow) {
//        this.NCXselfProducedInflow = NCXselfProducedInflow;
//    }
//
//    public double[] getNCXminInstreamingEcologicalFlow() {
//        return NCXminInstreamingEcologicalFlow;
//    }
//
//    public void setNCXminInstreamingEcologicalFlow(double[] NCXminInstreamingEcologicalFlow) {
//        this.NCXminInstreamingEcologicalFlow = NCXminInstreamingEcologicalFlow;
//    }
//
//    public double getNCXindustrialWaterWeight() {
//        return NCXindustrialWaterWeight;
//    }
//
//    public void setNCXindustrialWaterWeight(double NCXindustrialWaterWeight) {
//        this.NCXindustrialWaterWeight = NCXindustrialWaterWeight;
//    }
//
//    public double getNCXagriculturalWaterWeight() {
//        return NCXagriculturalWaterWeight;
//    }
//
//    public void setNCXagriculturalWaterWeight(double NCXagriculturalWaterWeight) {
//        this.NCXagriculturalWaterWeight = NCXagriculturalWaterWeight;
//    }
//
//    public double getNCXdomesticWaterWeight() {
//        return NCXdomesticWaterWeight;
//    }
//
//    public void setNCXdomesticWaterWeight(double NCXdomesticWaterWeight) {
//        this.NCXdomesticWaterWeight = NCXdomesticWaterWeight;
//    }
//
//    public double getNCXforestryWaterWeight() {
//        return NCXforestryWaterWeight;
//    }
//
//    public void setNCXforestryWaterWeight(double NCXforestryWaterWeight) {
//        this.NCXforestryWaterWeight = NCXforestryWaterWeight;
//    }
//
//    public double[] getNCXindustrialWaterRatio() {
//        return NCXindustrialWaterRatio;
//    }
//
//    public void setNCXindustrialWaterRatio(double[] NCXindustrialWaterRatio) {
//        this.NCXindustrialWaterRatio = NCXindustrialWaterRatio;
//    }
//
//    public double[] getNCXagriculturalWaterRatio() {
//        return NCXagriculturalWaterRatio;
//    }
//
//    public void setNCXagriculturalWaterRatio(double[] NCXagriculturalWaterRatio) {
//        this.NCXagriculturalWaterRatio = NCXagriculturalWaterRatio;
//    }
//
//    public double[] getNCXdomesticWaterRatio() {
//        return NCXdomesticWaterRatio;
//    }
//
//    public void setNCXdomesticWaterRatio(double[] NCXdomesticWaterRatio) {
//        this.NCXdomesticWaterRatio = NCXdomesticWaterRatio;
//    }
//
//    public double[] getNCXforestryWaterRatio() {
//        return NCXforestryWaterRatio;
//    }
//
//    public void setNCXforestryWaterRatio(double[] NCXforestryWaterRatio) {
//        this.NCXforestryWaterRatio = NCXforestryWaterRatio;
//    }
//
//    public double[] getNCXindustrialWaterReturnRatio() {
//        return NCXindustrialWaterReturnRatio;
//    }
//
//    public void setNCXindustrialWaterReturnRatio(double[] NCXindustrialWaterReturnRatio) {
//        this.NCXindustrialWaterReturnRatio = NCXindustrialWaterReturnRatio;
//    }
//
//    public double[] getNCXagriculturalWaterReturnRatio() {
//        return NCXagriculturalWaterReturnRatio;
//    }
//
//    public void setNCXagriculturalWaterReturnRatio(double[] NCXagriculturalWaterReturnRatio) {
//        this.NCXagriculturalWaterReturnRatio = NCXagriculturalWaterReturnRatio;
//    }
//
//    public double[] getNCXdomesticWaterReturnRatio() {
//        return NCXdomesticWaterReturnRatio;
//    }
//
//    public void setNCXdomesticWaterReturnRatio(double[] NCXdomesticWaterReturnRatio) {
//        this.NCXdomesticWaterReturnRatio = NCXdomesticWaterReturnRatio;
//    }
//
//    public double[] getNCXforestryWaterReturnRatio() {
//        return NCXforestryWaterReturnRatio;
//    }
//
//    public void setNCXforestryWaterReturnRatio(double[] NCXforestryWaterReturnRatio) {
//        this.NCXforestryWaterReturnRatio = NCXforestryWaterReturnRatio;
//    }
//
//    public double[] getNCXinflowFrequency() {
//        return NCXinflowFrequency;
//    }
//
//    public void setNCXinflowFrequency(double[] NCXinflowFrequency) {
//        this.NCXinflowFrequency = NCXinflowFrequency;
//    }
//
//    public double getJXXwaterRequirement() {
//        return JXXwaterRequirement;
//    }
//
//    public void setJXXwaterRequirement(double JXXwaterRequirement) {
//        this.JXXwaterRequirement = JXXwaterRequirement;
//    }
//
//    public double[] getJXXselfProducedInflow() {
//        return JXXselfProducedInflow;
//    }
//
//    public void setJXXselfProducedInflow(double[] JXXselfProducedInflow) {
//        this.JXXselfProducedInflow = JXXselfProducedInflow;
//    }
//
//    public double[] getJXXminInstreamingEcologicalFlow() {
//        return JXXminInstreamingEcologicalFlow;
//    }
//
//    public void setJXXminInstreamingEcologicalFlow(double[] JXXminInstreamingEcologicalFlow) {
//        this.JXXminInstreamingEcologicalFlow = JXXminInstreamingEcologicalFlow;
//    }
//
//    public double getJXXindustrialWaterWeight() {
//        return JXXindustrialWaterWeight;
//    }
//
//    public void setJXXindustrialWaterWeight(double JXXindustrialWaterWeight) {
//        this.JXXindustrialWaterWeight = JXXindustrialWaterWeight;
//    }
//
//    public double getJXXagriculturalWaterWeight() {
//        return JXXagriculturalWaterWeight;
//    }
//
//    public void setJXXagriculturalWaterWeight(double JXXagriculturalWaterWeight) {
//        this.JXXagriculturalWaterWeight = JXXagriculturalWaterWeight;
//    }
//
//    public double getJXXdomesticWaterWeight() {
//        return JXXdomesticWaterWeight;
//    }
//
//    public void setJXXdomesticWaterWeight(double JXXdomesticWaterWeight) {
//        this.JXXdomesticWaterWeight = JXXdomesticWaterWeight;
//    }
//
//    public double getJXXforestryWaterWeight() {
//        return JXXforestryWaterWeight;
//    }
//
//    public void setJXXforestryWaterWeight(double JXXforestryWaterWeight) {
//        this.JXXforestryWaterWeight = JXXforestryWaterWeight;
//    }
//
//    public double[] getJXXindustrialWaterRatio() {
//        return JXXindustrialWaterRatio;
//    }
//
//    public void setJXXindustrialWaterRatio(double[] JXXindustrialWaterRatio) {
//        this.JXXindustrialWaterRatio = JXXindustrialWaterRatio;
//    }
//
//    public double[] getJXXagriculturalWaterRatio() {
//        return JXXagriculturalWaterRatio;
//    }
//
//    public void setJXXagriculturalWaterRatio(double[] JXXagriculturalWaterRatio) {
//        this.JXXagriculturalWaterRatio = JXXagriculturalWaterRatio;
//    }
//
//    public double[] getJXXdomesticWaterRatio() {
//        return JXXdomesticWaterRatio;
//    }
//
//    public void setJXXdomesticWaterRatio(double[] JXXdomesticWaterRatio) {
//        this.JXXdomesticWaterRatio = JXXdomesticWaterRatio;
//    }
//
//    public double[] getJXXforestryWaterRatio() {
//        return JXXforestryWaterRatio;
//    }
//
//    public void setJXXforestryWaterRatio(double[] JXXforestryWaterRatio) {
//        this.JXXforestryWaterRatio = JXXforestryWaterRatio;
//    }
//
//    public double[] getJXXindustrialWaterReturnRatio() {
//        return JXXindustrialWaterReturnRatio;
//    }
//
//    public void setJXXindustrialWaterReturnRatio(double[] JXXindustrialWaterReturnRatio) {
//        this.JXXindustrialWaterReturnRatio = JXXindustrialWaterReturnRatio;
//    }
//
//    public double[] getJXXagriculturalWaterReturnRatio() {
//        return JXXagriculturalWaterReturnRatio;
//    }
//
//    public void setJXXagriculturalWaterReturnRatio(double[] JXXagriculturalWaterReturnRatio) {
//        this.JXXagriculturalWaterReturnRatio = JXXagriculturalWaterReturnRatio;
//    }
//
//    public double[] getJXXdomesticWaterReturnRatio() {
//        return JXXdomesticWaterReturnRatio;
//    }
//
//    public void setJXXdomesticWaterReturnRatio(double[] JXXdomesticWaterReturnRatio) {
//        this.JXXdomesticWaterReturnRatio = JXXdomesticWaterReturnRatio;
//    }
//
//    public double[] getJXXforestryWaterReturnRatio() {
//        return JXXforestryWaterReturnRatio;
//    }
//
//    public void setJXXforestryWaterReturnRatio(double[] JXXforestryWaterReturnRatio) {
//        this.JXXforestryWaterReturnRatio = JXXforestryWaterReturnRatio;
//    }
//
//    public double[] getJXXinflowFrequency() {
//        return JXXinflowFrequency;
//    }
//
//    public void setJXXinflowFrequency(double[] JXXinflowFrequency) {
//        this.JXXinflowFrequency = JXXinflowFrequency;
//    }
//
//    public double getLCQwaterRequirement() {
//        return LCQwaterRequirement;
//    }
//
//    public void setLCQwaterRequirement(double LCQwaterRequirement) {
//        this.LCQwaterRequirement = LCQwaterRequirement;
//    }
//
//    public double[] getLCQselfProducedInflow() {
//        return LCQselfProducedInflow;
//    }
//
//    public void setLCQselfProducedInflow(double[] LCQselfProducedInflow) {
//        this.LCQselfProducedInflow = LCQselfProducedInflow;
//    }
//
//    public double[] getLCQminInstreamingEcologicalFlow() {
//        return LCQminInstreamingEcologicalFlow;
//    }
//
//    public void setLCQminInstreamingEcologicalFlow(double[] LCQminInstreamingEcologicalFlow) {
//        this.LCQminInstreamingEcologicalFlow = LCQminInstreamingEcologicalFlow;
//    }
//
//    public double getLCQindustrialWaterWeight() {
//        return LCQindustrialWaterWeight;
//    }
//
//    public void setLCQindustrialWaterWeight(double LCQindustrialWaterWeight) {
//        this.LCQindustrialWaterWeight = LCQindustrialWaterWeight;
//    }
//
//    public double getLCQagriculturalWaterWeight() {
//        return LCQagriculturalWaterWeight;
//    }
//
//    public void setLCQagriculturalWaterWeight(double LCQagriculturalWaterWeight) {
//        this.LCQagriculturalWaterWeight = LCQagriculturalWaterWeight;
//    }
//
//    public double getLCQdomesticWaterWeight() {
//        return LCQdomesticWaterWeight;
//    }
//
//    public void setLCQdomesticWaterWeight(double LCQdomesticWaterWeight) {
//        this.LCQdomesticWaterWeight = LCQdomesticWaterWeight;
//    }
//
//    public double getLCQforestryWaterWeight() {
//        return LCQforestryWaterWeight;
//    }
//
//    public void setLCQforestryWaterWeight(double LCQforestryWaterWeight) {
//        this.LCQforestryWaterWeight = LCQforestryWaterWeight;
//    }
//
//    public double[] getLCQindustrialWaterRatio() {
//        return LCQindustrialWaterRatio;
//    }
//
//    public void setLCQindustrialWaterRatio(double[] LCQindustrialWaterRatio) {
//        this.LCQindustrialWaterRatio = LCQindustrialWaterRatio;
//    }
//
//    public double[] getLCQagriculturalWaterRatio() {
//        return LCQagriculturalWaterRatio;
//    }
//
//    public void setLCQagriculturalWaterRatio(double[] LCQagriculturalWaterRatio) {
//        this.LCQagriculturalWaterRatio = LCQagriculturalWaterRatio;
//    }
//
//    public double[] getLCQdomesticWaterRatio() {
//        return LCQdomesticWaterRatio;
//    }
//
//    public void setLCQdomesticWaterRatio(double[] LCQdomesticWaterRatio) {
//        this.LCQdomesticWaterRatio = LCQdomesticWaterRatio;
//    }
//
//    public double[] getLCQforestryWaterRatio() {
//        return LCQforestryWaterRatio;
//    }
//
//    public void setLCQforestryWaterRatio(double[] LCQforestryWaterRatio) {
//        this.LCQforestryWaterRatio = LCQforestryWaterRatio;
//    }
//
//    public double[] getLCQindustrialWaterReturnRatio() {
//        return LCQindustrialWaterReturnRatio;
//    }
//
//    public void setLCQindustrialWaterReturnRatio(double[] LCQindustrialWaterReturnRatio) {
//        this.LCQindustrialWaterReturnRatio = LCQindustrialWaterReturnRatio;
//    }
//
//    public double[] getLCQagriculturalWaterReturnRatio() {
//        return LCQagriculturalWaterReturnRatio;
//    }
//
//    public void setLCQagriculturalWaterReturnRatio(double[] LCQagriculturalWaterReturnRatio) {
//        this.LCQagriculturalWaterReturnRatio = LCQagriculturalWaterReturnRatio;
//    }
//
//    public double[] getLCQdomesticWaterReturnRatio() {
//        return LCQdomesticWaterReturnRatio;
//    }
//
//    public void setLCQdomesticWaterReturnRatio(double[] LCQdomesticWaterReturnRatio) {
//        this.LCQdomesticWaterReturnRatio = LCQdomesticWaterReturnRatio;
//    }
//
//    public double[] getLCQforestryWaterReturnRatio() {
//        return LCQforestryWaterReturnRatio;
//    }
//
//    public void setLCQforestryWaterReturnRatio(double[] LCQforestryWaterReturnRatio) {
//        this.LCQforestryWaterReturnRatio = LCQforestryWaterReturnRatio;
//    }
//
//    public double[] getLCQinflowFrequency() {
//        return LCQinflowFrequency;
//    }
//
//    public void setLCQinflowFrequency(double[] LCQinflowFrequency) {
//        this.LCQinflowFrequency = LCQinflowFrequency;
//    }
//
//    public double getZXXwaterRequirement() {
//        return ZXXwaterRequirement;
//    }
//
//    public void setZXXwaterRequirement(double ZXXwaterRequirement) {
//        this.ZXXwaterRequirement = ZXXwaterRequirement;
//    }
//
//    public double[] getZXXselfProducedInflow() {
//        return ZXXselfProducedInflow;
//    }
//
//    public void setZXXselfProducedInflow(double[] ZXXselfProducedInflow) {
//        this.ZXXselfProducedInflow = ZXXselfProducedInflow;
//    }
//
//    public double[] getZXXminInstreamingEcologicalFlow() {
//        return ZXXminInstreamingEcologicalFlow;
//    }
//
//    public void setZXXminInstreamingEcologicalFlow(double[] ZXXminInstreamingEcologicalFlow) {
//        this.ZXXminInstreamingEcologicalFlow = ZXXminInstreamingEcologicalFlow;
//    }
//
//    public double getZXXindustrialWaterWeight() {
//        return ZXXindustrialWaterWeight;
//    }
//
//    public void setZXXindustrialWaterWeight(double ZXXindustrialWaterWeight) {
//        this.ZXXindustrialWaterWeight = ZXXindustrialWaterWeight;
//    }
//
//    public double getZXXagriculturalWaterWeight() {
//        return ZXXagriculturalWaterWeight;
//    }
//
//    public void setZXXagriculturalWaterWeight(double ZXXagriculturalWaterWeight) {
//        this.ZXXagriculturalWaterWeight = ZXXagriculturalWaterWeight;
//    }
//
//    public double getZXXdomesticWaterWeight() {
//        return ZXXdomesticWaterWeight;
//    }
//
//    public void setZXXdomesticWaterWeight(double ZXXdomesticWaterWeight) {
//        this.ZXXdomesticWaterWeight = ZXXdomesticWaterWeight;
//    }
//
//    public double getZXXforestryWaterWeight() {
//        return ZXXforestryWaterWeight;
//    }
//
//    public void setZXXforestryWaterWeight(double ZXXforestryWaterWeight) {
//        this.ZXXforestryWaterWeight = ZXXforestryWaterWeight;
//    }
//
//    public double[] getZXXindustrialWaterRatio() {
//        return ZXXindustrialWaterRatio;
//    }
//
//    public void setZXXindustrialWaterRatio(double[] ZXXindustrialWaterRatio) {
//        this.ZXXindustrialWaterRatio = ZXXindustrialWaterRatio;
//    }
//
//    public double[] getZXXagriculturalWaterRatio() {
//        return ZXXagriculturalWaterRatio;
//    }
//
//    public void setZXXagriculturalWaterRatio(double[] ZXXagriculturalWaterRatio) {
//        this.ZXXagriculturalWaterRatio = ZXXagriculturalWaterRatio;
//    }
//
//    public double[] getZXXdomesticWaterRatio() {
//        return ZXXdomesticWaterRatio;
//    }
//
//    public void setZXXdomesticWaterRatio(double[] ZXXdomesticWaterRatio) {
//        this.ZXXdomesticWaterRatio = ZXXdomesticWaterRatio;
//    }
//
//    public double[] getZXXforestryWaterRatio() {
//        return ZXXforestryWaterRatio;
//    }
//
//    public void setZXXforestryWaterRatio(double[] ZXXforestryWaterRatio) {
//        this.ZXXforestryWaterRatio = ZXXforestryWaterRatio;
//    }
//
//    public double[] getZXXindustrialWaterReturnRatio() {
//        return ZXXindustrialWaterReturnRatio;
//    }
//
//    public void setZXXindustrialWaterReturnRatio(double[] ZXXindustrialWaterReturnRatio) {
//        this.ZXXindustrialWaterReturnRatio = ZXXindustrialWaterReturnRatio;
//    }
//
//    public double[] getZXXagriculturalWaterReturnRatio() {
//        return ZXXagriculturalWaterReturnRatio;
//    }
//
//    public void setZXXagriculturalWaterReturnRatio(double[] ZXXagriculturalWaterReturnRatio) {
//        this.ZXXagriculturalWaterReturnRatio = ZXXagriculturalWaterReturnRatio;
//    }
//
//    public double[] getZXXdomesticWaterReturnRatio() {
//        return ZXXdomesticWaterReturnRatio;
//    }
//
//    public void setZXXdomesticWaterReturnRatio(double[] ZXXdomesticWaterReturnRatio) {
//        this.ZXXdomesticWaterReturnRatio = ZXXdomesticWaterReturnRatio;
//    }
//
//    public double[] getZXXforestryWaterReturnRatio() {
//        return ZXXforestryWaterReturnRatio;
//    }
//
//    public void setZXXforestryWaterReturnRatio(double[] ZXXforestryWaterReturnRatio) {
//        this.ZXXforestryWaterReturnRatio = ZXXforestryWaterReturnRatio;
//    }
//
//    public double[] getZXXinflowFrequency() {
//        return ZXXinflowFrequency;
//    }
//
//    public void setZXXinflowFrequency(double[] ZXXinflowFrequency) {
//        this.ZXXinflowFrequency = ZXXinflowFrequency;
//    }
//
//    public double getDXXwaterRequirement() {
//        return DXXwaterRequirement;
//    }
//
//    public void setDXXwaterRequirement(double DXXwaterRequirement) {
//        this.DXXwaterRequirement = DXXwaterRequirement;
//    }
//
//    public double[] getDXXselfProducedInflow() {
//        return DXXselfProducedInflow;
//    }
//
//    public void setDXXselfProducedInflow(double[] DXXselfProducedInflow) {
//        this.DXXselfProducedInflow = DXXselfProducedInflow;
//    }
//
//    public double[] getDXXminInstreamingEcologicalFlow() {
//        return DXXminInstreamingEcologicalFlow;
//    }
//
//    public void setDXXminInstreamingEcologicalFlow(double[] DXXminInstreamingEcologicalFlow) {
//        this.DXXminInstreamingEcologicalFlow = DXXminInstreamingEcologicalFlow;
//    }
//
//    public double getDXXindustrialWaterWeight() {
//        return DXXindustrialWaterWeight;
//    }
//
//    public void setDXXindustrialWaterWeight(double DXXindustrialWaterWeight) {
//        this.DXXindustrialWaterWeight = DXXindustrialWaterWeight;
//    }
//
//    public double getDXXagriculturalWaterWeight() {
//        return DXXagriculturalWaterWeight;
//    }
//
//    public void setDXXagriculturalWaterWeight(double DXXagriculturalWaterWeight) {
//        this.DXXagriculturalWaterWeight = DXXagriculturalWaterWeight;
//    }
//
//    public double getDXXdomesticWaterWeight() {
//        return DXXdomesticWaterWeight;
//    }
//
//    public void setDXXdomesticWaterWeight(double DXXdomesticWaterWeight) {
//        this.DXXdomesticWaterWeight = DXXdomesticWaterWeight;
//    }
//
//    public double getDXXforestryWaterWeight() {
//        return DXXforestryWaterWeight;
//    }
//
//    public void setDXXforestryWaterWeight(double DXXforestryWaterWeight) {
//        this.DXXforestryWaterWeight = DXXforestryWaterWeight;
//    }
//
//    public double[] getDXXindustrialWaterRatio() {
//        return DXXindustrialWaterRatio;
//    }
//
//    public void setDXXindustrialWaterRatio(double[] DXXindustrialWaterRatio) {
//        this.DXXindustrialWaterRatio = DXXindustrialWaterRatio;
//    }
//
//    public double[] getDXXagriculturalWaterRatio() {
//        return DXXagriculturalWaterRatio;
//    }
//
//    public void setDXXagriculturalWaterRatio(double[] DXXagriculturalWaterRatio) {
//        this.DXXagriculturalWaterRatio = DXXagriculturalWaterRatio;
//    }
//
//    public double[] getDXXdomesticWaterRatio() {
//        return DXXdomesticWaterRatio;
//    }
//
//    public void setDXXdomesticWaterRatio(double[] DXXdomesticWaterRatio) {
//        this.DXXdomesticWaterRatio = DXXdomesticWaterRatio;
//    }
//
//    public double[] getDXXforestryWaterRatio() {
//        return DXXforestryWaterRatio;
//    }
//
//    public void setDXXforestryWaterRatio(double[] DXXforestryWaterRatio) {
//        this.DXXforestryWaterRatio = DXXforestryWaterRatio;
//    }
//
//    public double[] getDXXindustrialWaterReturnRatio() {
//        return DXXindustrialWaterReturnRatio;
//    }
//
//    public void setDXXindustrialWaterReturnRatio(double[] DXXindustrialWaterReturnRatio) {
//        this.DXXindustrialWaterReturnRatio = DXXindustrialWaterReturnRatio;
//    }
//
//    public double[] getDXXagriculturalWaterReturnRatio() {
//        return DXXagriculturalWaterReturnRatio;
//    }
//
//    public void setDXXagriculturalWaterReturnRatio(double[] DXXagriculturalWaterReturnRatio) {
//        this.DXXagriculturalWaterReturnRatio = DXXagriculturalWaterReturnRatio;
//    }
//
//    public double[] getDXXdomesticWaterReturnRatio() {
//        return DXXdomesticWaterReturnRatio;
//    }
//
//    public void setDXXdomesticWaterReturnRatio(double[] DXXdomesticWaterReturnRatio) {
//        this.DXXdomesticWaterReturnRatio = DXXdomesticWaterReturnRatio;
//    }
//
//    public double[] getDXXforestryWaterReturnRatio() {
//        return DXXforestryWaterReturnRatio;
//    }
//
//    public void setDXXforestryWaterReturnRatio(double[] DXXforestryWaterReturnRatio) {
//        this.DXXforestryWaterReturnRatio = DXXforestryWaterReturnRatio;
//    }
//
//    public double[] getDXXinflowFrequency() {
//        return DXXinflowFrequency;
//    }
//
//    public void setDXXinflowFrequency(double[] DXXinflowFrequency) {
//        this.DXXinflowFrequency = DXXinflowFrequency;
//    }
//
//    public double getGFPYwaterRequirement() {
//        return GFPYwaterRequirement;
//    }
//
//    public void setGFPYwaterRequirement(double GFPYwaterRequirement) {
//        this.GFPYwaterRequirement = GFPYwaterRequirement;
//    }
//
//    public double[] getGFPYselfProducedInflow() {
//        return GFPYselfProducedInflow;
//    }
//
//    public void setGFPYselfProducedInflow(double[] GFPYselfProducedInflow) {
//        this.GFPYselfProducedInflow = GFPYselfProducedInflow;
//    }
//
//    public double[] getGFPYminInstreamingEcologicalFlow() {
//        return GFPYminInstreamingEcologicalFlow;
//    }
//
//    public void setGFPYminInstreamingEcologicalFlow(double[] GFPYminInstreamingEcologicalFlow) {
//        this.GFPYminInstreamingEcologicalFlow = GFPYminInstreamingEcologicalFlow;
//    }
//
//    public double getGFPYindustrialWaterWeight() {
//        return GFPYindustrialWaterWeight;
//    }
//
//    public void setGFPYindustrialWaterWeight(double GFPYindustrialWaterWeight) {
//        this.GFPYindustrialWaterWeight = GFPYindustrialWaterWeight;
//    }
//
//    public double getGFPYagriculturalWaterWeight() {
//        return GFPYagriculturalWaterWeight;
//    }
//
//    public void setGFPYagriculturalWaterWeight(double GFPYagriculturalWaterWeight) {
//        this.GFPYagriculturalWaterWeight = GFPYagriculturalWaterWeight;
//    }
//
//    public double getGFPYdomesticWaterWeight() {
//        return GFPYdomesticWaterWeight;
//    }
//
//    public void setGFPYdomesticWaterWeight(double GFPYdomesticWaterWeight) {
//        this.GFPYdomesticWaterWeight = GFPYdomesticWaterWeight;
//    }
//
//    public double getGFPYforestryWaterWeight() {
//        return GFPYforestryWaterWeight;
//    }
//
//    public void setGFPYforestryWaterWeight(double GFPYforestryWaterWeight) {
//        this.GFPYforestryWaterWeight = GFPYforestryWaterWeight;
//    }
//
//    public double[] getGFPYindustrialWaterRatio() {
//        return GFPYindustrialWaterRatio;
//    }
//
//    public void setGFPYindustrialWaterRatio(double[] GFPYindustrialWaterRatio) {
//        this.GFPYindustrialWaterRatio = GFPYindustrialWaterRatio;
//    }
//
//    public double[] getGFPYagriculturalWaterRatio() {
//        return GFPYagriculturalWaterRatio;
//    }
//
//    public void setGFPYagriculturalWaterRatio(double[] GFPYagriculturalWaterRatio) {
//        this.GFPYagriculturalWaterRatio = GFPYagriculturalWaterRatio;
//    }
//
//    public double[] getGFPYdomesticWaterRatio() {
//        return GFPYdomesticWaterRatio;
//    }
//
//    public void setGFPYdomesticWaterRatio(double[] GFPYdomesticWaterRatio) {
//        this.GFPYdomesticWaterRatio = GFPYdomesticWaterRatio;
//    }
//
//    public double[] getGFPYforestryWaterRatio() {
//        return GFPYforestryWaterRatio;
//    }
//
//    public void setGFPYforestryWaterRatio(double[] GFPYforestryWaterRatio) {
//        this.GFPYforestryWaterRatio = GFPYforestryWaterRatio;
//    }
//
//    public double[] getGFPYindustrialWaterReturnRatio() {
//        return GFPYindustrialWaterReturnRatio;
//    }
//
//    public void setGFPYindustrialWaterReturnRatio(double[] GFPYindustrialWaterReturnRatio) {
//        this.GFPYindustrialWaterReturnRatio = GFPYindustrialWaterReturnRatio;
//    }
//
//    public double[] getGFPYagriculturalWaterReturnRatio() {
//        return GFPYagriculturalWaterReturnRatio;
//    }
//
//    public void setGFPYagriculturalWaterReturnRatio(double[] GFPYagriculturalWaterReturnRatio) {
//        this.GFPYagriculturalWaterReturnRatio = GFPYagriculturalWaterReturnRatio;
//    }
//
//    public double[] getGFPYdomesticWaterReturnRatio() {
//        return GFPYdomesticWaterReturnRatio;
//    }
//
//    public void setGFPYdomesticWaterReturnRatio(double[] GFPYdomesticWaterReturnRatio) {
//        this.GFPYdomesticWaterReturnRatio = GFPYdomesticWaterReturnRatio;
//    }
//
//    public double[] getGFPYforestryWaterReturnRatio() {
//        return GFPYforestryWaterReturnRatio;
//    }
//
//    public void setGFPYforestryWaterReturnRatio(double[] GFPYforestryWaterReturnRatio) {
//        this.GFPYforestryWaterReturnRatio = GFPYforestryWaterReturnRatio;
//    }
//
//    public double[] getGFPYinflowFrequency() {
//        return GFPYinflowFrequency;
//    }
//
//    public void setGFPYinflowFrequency(double[] GFPYinflowFrequency) {
//        this.GFPYinflowFrequency = GFPYinflowFrequency;
//    }


    public double[] getHMSKselfProducedInflow() {
        return HMSKselfProducedInflow;
    }

    public void setHMSKselfProducedInflow(double[] HMSKselfProducedInflow) {
        this.HMSKselfProducedInflow = HMSKselfProducedInflow;
    }

    public double[] getHMSKminInstreamingEcologicalFlow() {
        return HMSKminInstreamingEcologicalFlow;
    }

    public void setHMSKminInstreamingEcologicalFlow(double[] HMSKminInstreamingEcologicalFlow) {
        this.HMSKminInstreamingEcologicalFlow = HMSKminInstreamingEcologicalFlow;
    }

    public double[] getHMSKinflowFrequency() {
        return HMSKinflowFrequency;
    }

    public void setHMSKinflowFrequency(double[] HMSKinflowFrequency) {
        this.HMSKinflowFrequency = HMSKinflowFrequency;
    }

    public double[] getLFSKselfProducedInflow() {
        return LFSKselfProducedInflow;
    }

    public void setLFSKselfProducedInflow(double[] LFSKselfProducedInflow) {
        this.LFSKselfProducedInflow = LFSKselfProducedInflow;
    }

    public double[] getLFSKminInstreamingEcologicalFlow() {
        return LFSKminInstreamingEcologicalFlow;
    }

    public void setLFSKminInstreamingEcologicalFlow(double[] LFSKminInstreamingEcologicalFlow) {
        this.LFSKminInstreamingEcologicalFlow = LFSKminInstreamingEcologicalFlow;
    }

    public double[] getLFSKinflowFrequency() {
        return LFSKinflowFrequency;
    }

    public void setLFSKinflowFrequency(double[] LFSKinflowFrequency) {
        this.LFSKinflowFrequency = LFSKinflowFrequency;
    }
}
