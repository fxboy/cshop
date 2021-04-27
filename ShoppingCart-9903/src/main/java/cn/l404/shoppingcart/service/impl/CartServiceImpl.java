package cn.l404.shoppingcart.service.impl;

import cn.l404.shoppingcart.pojo.CartGoods;
import cn.l404.shoppingcart.service.CartService;
import cn.l404.shoppingcart.util.RedisUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @auther Fanxing
 * 这是一个简介
 */
@Service("cas")
public class CartServiceImpl implements CartService {
    @Autowired
    RedisUtils redisUtils;
    @Value("${shopcar.key}")
    String skey;
    @Override
    public List<CartGoods> goodsList(String openId) {
        List<CartGoods> list = null;
       try{
           list =  JSONArray.parseArray(redisUtils.get(skey+":"+openId).toString(),CartGoods.class);
       }catch (Exception ex){
           System.out.println("购物车查询：" + ex.getMessage());
       }
       return list;

    }

    @Override
    public Boolean addGoodsInCar(CartGoods goods,String openId) {
        goods.setCreateTime(new Date());
        // 获取购物车的列表
        List<CartGoods> goodsla = goodsList(openId);
        if(goodsla == null){
            goodsla = new ArrayList<>();
        }
        Integer i = search(0,goods,goodsla);
        if(i == null){
            System.out.println("i == null");
            goodsla.add(goods);
        }else{
            System.out.println("i == "+ i);
            int count = goodsla.get(i).getCount() + goods.getCount();
            goodsla.get(i).setCount(Math.max(count, 0));
        }
        // 保存到redis内
        return redisUtils.put(skey+":"+openId, JSON.toJSONString(goodsla),3600 * 24 *30);
    }

    @Override
    public Boolean deleteGoodsCar(CartGoods goods, String openId) {
        List<CartGoods> goodsla = goodsList(openId);
        if(goodsla == null){
            goodsla = new ArrayList<>();
        }
        Integer iab = search(0,goods,goodsla);
        if(iab == null){
           return true;
        }else{
            int i = iab;
          goodsla.remove(i);
            System.out.println(goodsla);
            System.out.println("删除成功？？");
        }
        return redisUtils.put(skey+":"+openId,JSON.toJSONString(goodsla),3600 * 24 *30);
    }

    public Integer search(int i, CartGoods cartGoods, List<CartGoods> goodsList){
        if(goodsList.size() == 0 || i == goodsList.size()){
            return null;
        }
        if(cartGoods.getGoodsId() == goodsList.get(i).getGoodsId()){
            System.out.println("遍历的值：" + i);
            return i;
        }
        return search(i+1,cartGoods,goodsList);
    }
}
