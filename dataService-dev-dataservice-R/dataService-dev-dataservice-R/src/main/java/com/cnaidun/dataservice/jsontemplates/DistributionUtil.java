package com.cnaidun.dataservice.jsontemplates;

import com.alibaba.fastjson.JSONObject;
import com.cnaidun.dataservice.utils.MessageProperties;

/**
 * 模板转换分配类
 */
public class DistributionUtil {

    /**
     * 按照业务类型workTypeCode分配模板方法
     * @param jsonObject
     * @return
     */
    public static JSONObject distribute(JSONObject jsonObject){

        String workTypeCode=jsonObject.getString(MessageProperties.WORKTYPECODE );
        //出入境模块
        if("ENTRYANDEXIT0001".equals(workTypeCode)){
            EntryandexitTemplate e1=new EntryandexitTemplate();
            return e1.ENTRYANDEXIT0001(jsonObject);
        }
        if("ENTRYANDEXIT0002".equals(workTypeCode)){
            EntryandexitTemplate e1=new EntryandexitTemplate();
            return e1.ENTRYANDEXIT0002(jsonObject);
        }
        if("ENTRYANDEXIT0003".equals(workTypeCode)){
            EntryandexitTemplate e1=new EntryandexitTemplate();
            return e1.ENTRYANDEXIT0003(jsonObject);
        }
        if("ENTRYANDEXIT0004".equals(workTypeCode)){
            EntryandexitTemplate e1=new EntryandexitTemplate();
            return e1.ENTRYANDEXIT0004(jsonObject);
        }
        //交巡警模块
        if("INTERSECTION0001".equals(workTypeCode)){
            IntersectionTemplate it=new IntersectionTemplate();
            return it.INTERSECTION0001(jsonObject);
        }
        if("INTERSECTION0003".equals(workTypeCode)){
            IntersectionTemplate it=new IntersectionTemplate();
            return it.INTERSECTION0003(jsonObject);
        }
        if("INTERSECTION0005".equals(workTypeCode)){
            IntersectionTemplate it=new IntersectionTemplate();
            return it.INTERSECTION0005(jsonObject);
        }
        //户政模块
        if("HOUSEHOLD0001".equals(workTypeCode)){
            HouseholdTemplate it=new HouseholdTemplate();
            return it.HOUSEHOLD0001(jsonObject);
        }
        if("HOUSEHOLD0002".equals(workTypeCode)){
            HouseholdTemplate it=new HouseholdTemplate();
            return it.HOUSEHOLD0002(jsonObject);
        }
        if("HOUSEHOLD0003".equals(workTypeCode)){
            HouseholdTemplate it=new HouseholdTemplate();
            return it.HOUSEHOLD0003(jsonObject);
        }
        if("HOUSEHOLD0004".equals(workTypeCode)){
            HouseholdTemplate it=new HouseholdTemplate();
            return it.HOUSEHOLD0004(jsonObject);
        }
        if("HOUSEHOLD0005".equals(workTypeCode)){
            HouseholdTemplate it=new HouseholdTemplate();
            return it.HOUSEHOLD0005(jsonObject);
        }
        if("HOUSEHOLD0006".equals(workTypeCode)){
            HouseholdTemplate it=new HouseholdTemplate();
            return it.HOUSEHOLD0006(jsonObject);
        }
        if("HOUSEHOLD0007".equals(workTypeCode)){
            HouseholdTemplate it=new HouseholdTemplate();
            return it.HOUSEHOLD0007(jsonObject);
        }
        if("HOUSEHOLD0008".equals(workTypeCode)){
            HouseholdTemplate it=new HouseholdTemplate();
            return it.HOUSEHOLD0008(jsonObject);
        }
        if("PUBLICSECURITY0023".equals(workTypeCode)){
            PublicsecurityTemplate it=new PublicsecurityTemplate();
            return it.PUBLICSECURITY0023(jsonObject);
        }
        if("PUBLICSECURITY0024".equals(workTypeCode)){
            PublicsecurityTemplate it=new PublicsecurityTemplate();
            return it.PUBLICSECURITY0024(jsonObject);
        }
        if("PUBLICSECURITY0025".equals(workTypeCode)){
            PublicsecurityTemplate it=new PublicsecurityTemplate();
            return it.PUBLICSECURITY0025(jsonObject);
        }

         return null;
    }
}
