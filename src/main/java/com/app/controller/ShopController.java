package com.app.controller;

import com.app.service.ShopService;
import com.common.utils.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * Created by yujingyang on 2017/4/10.
 */
@Controller
@RequestMapping(value = "shop")
public class ShopController {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    ShopService shopService;


    @RequestMapping(value = "getAll/{shopId}",method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getAll(@PathVariable("shopId") Integer shopId){
        try {
            return WebUtil.result(shopService.getAll(shopId));
        }catch (Exception e){
            e.printStackTrace();
            return WebUtil.error("获取全部商品失败");
        }
    }

    @RequestMapping(value = "getHotSale/{shopId}", method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getHotSale(@PathVariable("shopId") Integer shopId){
        try {
            return WebUtil.result(shopService.getHotSale(shopId));
        }catch (Exception e){
            e.printStackTrace();
            return WebUtil.error("获取热销商品失败");
        }
    }

    @RequestMapping(value = "getShopMainPage/{shopId}", method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getShopMainPage(@PathVariable("shopId") Integer shopId){
        try {
            return WebUtil.result(shopService.getShopMainPage(shopId));
        }catch (Exception e){
            e.printStackTrace();
            return WebUtil.error("获取店铺首页失败");
        }
    }

    @RequestMapping(value = "getActivityImgUrl/{shopId}", method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getActivityImgUrl(@PathVariable("shopId") Integer shopId){
        try {
            return WebUtil.result(shopService.getShopActivityImgUrl(shopId));
        }catch (Exception e){
            e.printStackTrace();
            return WebUtil.error("服务器提了一个问题");
        }
    }

    @RequestMapping(value = "getTelephone/{shopId}", method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getTelephone(@PathVariable("shopId") Integer shopId){
        try {
            return WebUtil.result(shopService.getTelephone(shopId));
        }catch (Exception e){
            e.printStackTrace();
            return WebUtil.error("服务器提了一个问题");
        }
    }




}
