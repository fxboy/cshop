package cn.l404.shoppingcart.controller;

import cn.l404.common.pojo.ResultVO;
import cn.l404.shoppingcart.pojo.CartGoods;
import cn.l404.shoppingcart.service.CartService;
import cn.l404.shoppingcart.service.OAuthService;
import cn.l404.shoppingcart.util.RequstUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @auther Fanxing
 * 这是一个简介
 */
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    OAuthService oAuthService;
    @Autowired
    RequstUtils requstUtils;
    @RequestMapping("/list")
    public ResultVO list() {
       // return cartService.goodsList(openId);
       ResultVO res =  JSONObject.parseObject(oAuthService.verification(requstUtils.getToken(),"qq"),ResultVO.class);
       if(res.getCode() == 2000){
           return new ResultVO(2000,"ok",cartService.goodsList(res.getData().toString()));
       }
       return res;
    }

    @RequestMapping("/add")
    public ResultVO add(CartGoods cartGoods) throws Exception {
        if(cartGoods.getGoodsId() == null || cartGoods.getGoodsId() == 0){
            throw new Exception("请选择商品后在放进购物车");
        }
        ResultVO res =  JSONObject.parseObject(oAuthService.verification(requstUtils.getToken(),"qq"),ResultVO.class);
        if(res.getCode() == 2000){
            return new ResultVO(2000,"ok",cartService.addGoodsInCar(cartGoods,res.getData().toString()));
        }
        return res;
    }

    @RequestMapping("/delete")
    public ResultVO remove(CartGoods cartGoods,String openId){
        ResultVO res =  JSONObject.parseObject(oAuthService.verification(requstUtils.getToken(),"qq"),ResultVO.class);
        if(res.getCode() == 2000){
            return new ResultVO(2000,"ok",cartService.deleteGoodsCar(cartGoods,res.getData().toString()));
        }
        return res;
    }


}
