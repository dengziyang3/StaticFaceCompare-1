package com.hzgc.compare.compare;

import com.hzgc.common.Person;
import com.hzgc.common.SearchResult;
import com.hzgc.compare.cache.FeatureCache;
import com.hzgc.compare.es.ElasticSearchClient;
import com.hzgc.jniface.CompareResult;
import com.hzgc.jniface.FaceFeatureInfo;
import com.hzgc.jniface.FaceUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CompareOnePerson {
    private static int RES_FIRST_COMPARE = 500;
    private FeatureCache featureCache;

    public CompareOnePerson(){

        featureCache = FeatureCache.getInstance();
    }

    public SearchResult compare(byte[] bitFeature, float[] feature, float sim){
        long start = System.currentTimeMillis();
        byte[][] query = new byte[1][32];
        query[0] = bitFeature;
        byte[][] diku = featureCache.getFeatureArr();
        ArrayList<CompareResult> compareResults = FaceUtil.faceCompareBitOne(diku, query, RES_FIRST_COMPARE);
        if(compareResults == null || compareResults.size() == 0){
            log.error("First compare faild. No result return.");
            return new SearchResult();
        }
        long firstCompared = System.currentTimeMillis();
        log.info("The time firt compare used is " + (firstCompared - start));

        ArrayList<FaceFeatureInfo> faceFeatureInfos = compareResults.get(0).getPictureInfoArrayList();
        List<String> ids = new ArrayList<>();
        for(FaceFeatureInfo faceFeatureInfo : faceFeatureInfos){
            String id = featureCache.getId(faceFeatureInfo.getIndex());
            ids.add(id);
        }
        List<Person> personDtos = ElasticSearchClient.readFromEs(ids);
        long readFeomES = System.currentTimeMillis();
        log.info("The time read from ES used is " + (readFeomES - firstCompared));

        SearchResult res = compareSecond(personDtos, feature, sim);
        long secondCompared = System.currentTimeMillis();
        log.info("The time second compare used is " + (secondCompared - readFeomES));

        return res;

    }

    public SearchResult compareSecond(List<Person> persons, float[] feature, float sim){
        if(persons == null || persons.size() == 0){
            return new SearchResult();
        }
        SearchResult.Record[] records = new SearchResult.Record[persons.size()];
        int index = 0;
        for(Person person : persons){
            float[] history = FaceUtil.base64Str2floatFeature(person.getTzz());
            float simple = FaceUtil.featureCompare(feature, history);
            SearchResult.Record record = new SearchResult.Record(simple, person);
            records[index] = record;
            index ++;
        }

        SearchResult result = new SearchResult(records);
        result.filterBySim(sim);
        result.sortBySim();

        return result;
    }
}
