package cn.l404.shoppingcart.service;

import cn.l404.shoppingcart.pojo.CartGoods;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
    List<CartGoods> goodsList(String openId);
    Boolean addGoodsInCar(CartGoods goodsList,String openId);
    Boolean deleteGoodsCar(CartGoods goods,String openId);
}
