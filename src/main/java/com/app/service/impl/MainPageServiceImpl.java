package com.app.service.impl;

import com.common.dao.ClassifyDao;
import com.common.dao.GoodDao;
import com.common.dao.ShopDao;
import com.common.dao.UserDao;
import com.common.model.po.ClassifyPO;
import com.common.model.po.GoodPO;
import com.common.model.po.ShopPO;
import com.common.model.po.UserPO;
import com.app.service.MainPageService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.common.utils.Constants;
import com.common.utils.enums.GoodStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yujingyang on 2017/3/30.
 */
@Service
public class MainPageServiceImpl implements MainPageService {

    @Autowired
    private ClassifyDao classifyDao;

    @Autowired
    private GoodDao goodDao;

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private UserDao userDao;

    public List<ClassifyPO> getClassify(){
        return classifyDao.queryClassify();
    }

    @Override
    public Map<String, Object> getMainPageGood() {
        List<GoodPO> goodPOs= goodDao.queryGoodsByStatus(GoodStatus.MAIN_PAGE_GOOD);
        List<GoodPO> goodPOs1 = goodDao.queryGoodsByStatus(GoodStatus.IS_ADDVER);
        //添加优品牌
        ShopPO shopPO = shopDao.queryShopByStatus(Constants.SHOP_OUTSTANDING).get(0);


        //按分类添加热门商品
        Map<String,Object> result = new HashMap();
        result.put("outstanding_shop_goods",new ArrayList<GoodPO>());
        result.put("advertisement",new ArrayList<GoodPO>());
        for(GoodPO po : goodPOs){
            Integer goodClassify = po.getClassifyId();
            if(result.get(String.valueOf(goodClassify)) == null){
                result.put(String.valueOf(goodClassify),new ArrayList<GoodPO>());
            }
            //TODO 优品牌商品是否有特殊状态
            if(po.getShopId() == shopPO.getId()){
                ((ArrayList<GoodPO>)result.get("outstanding_shop_goods")).add(po);
            }
            if((po.getStatus() & GoodStatus.IS_ADDVER) > 0){
                ((ArrayList<GoodPO>)result.get("advertisement")).add(po);
            }



            List<GoodPO> poList = (List<GoodPO>) result.get(String.valueOf(goodClassify));
            poList.add(po);
        }
        Map<String, String> mapMap = new HashMap();
        mapMap.put("outstanding_shop_name",shopPO.getName());
        mapMap.put("outstanding_shop_id",String.valueOf(shopPO.getId()));
        result.put("outstanding_shop_info",mapMap);
        return result;
    }

    @Override
    public Map<String, Object> getUserInfo(Integer userId) {
        Map<String,Object> result = new HashMap();
        if(userId == null){
            return null;
        }
        UserPO userPO = userDao.queryUserById(userId);
        //TODO 以后再写比率 这里先使用暂时的比率
        if(userPO==null)
            return null;
        result.put("userName",userPO.getUserName());
        result.put("headImg",userPO.getHeadImg());
        result.put("diamond",userPO.getDiamond());
        result.put("coin",userPO.getCoin());
        result.put("point",userPO.getPoint());
        result.put("price",Math.floor(userPO.getPoint()*0.3+userPO.getCoin()*0.4));
        return result;
    }


}
