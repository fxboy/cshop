package cn.l404.shoppingcart.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @auther Fanxing
 * 购物车存放商品的信息
 */
@Data
public class CartGoods implements Serializable {
    private static final long serialVersionUID = 738408669975786898L;
    /**
     *  商品id
     */
    Integer goodsId;

    /**
     *  商品数量
     */
    Integer count;
    /**
     *  商品价格
     */
    //Double price;
    /**
     *  添加时间
     */
    Date createTime;

    public CartGoods(){}

    public CartGoods(Integer goodsId, Integer count, Date createTime) {
        this.goodsId = goodsId;
        this.count = count;
       // this.price = price;
        this.createTime = createTime;
    }
}
