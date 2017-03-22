package com.good.diaodiaode.tebiediao;

/**
 * Created by ex-lizheng102 on 2017-03-01.
 */

public class Do2Utils {

    public Do2Utils() {
    }

    /**
     *
     * @param health  血量
     * @param atk     对方攻击
     * @param armor   护甲
     * @return        挨打次数
     */
    public static float getNumber(float health,float atk,float armor){

        float damageReduction = (float) (0.06*armor/(1+0.06*armor));

      return  health/((1-damageReduction)*atk);

    }


}
