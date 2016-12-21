package com.good.diaodiaode.tebiediao;

/**
 * Created by jzxiang on 16/4/21.
 */
public enum Type {
    // 五种选择模式，年月日时分，年月日，时分，月日时分，年月
    ALL,
    YEAR_MONTH_DAY,
    HOURS_MINS,
    MONTH_DAY_HOUR_MIN,
    YEAR_MONTH,
    YEAR,
    HOUR_RANGE,

    //LinkageWheelPickDialog使用的单选模式、双选模式、三选模式
    LINKAGE_SINGLE,
    LINKAGE_DOUBLE,
    LINKAGE_TREBLE,
}
