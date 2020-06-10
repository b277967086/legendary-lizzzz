package com.good.diaodiaode.tebiediao.model;

import android.text.TextUtils;


import java.io.Serializable;
import java.util.List;

/**
 * 接口文档链接：http://km.qutoutiao.net/pages/viewpage.action?pageId=73506640
 *
 * @author yuanchen
 * @version 1.0.0
 * @date 2019/1/23
 * @Copyright 2018 Shanghai Xike Information Technology Service Co., Ltd. All rights reserved.
 */
public class SignInProgressServerModel implements Serializable {

    /**
     * member : {"is_bind_wx":1,"is_bind_zfb":0,"is_bind_tel":1,"is_bind_invite_code":1,"avatar":"http://static.1sapp.com/image/p/2017/05/19/1495187830605523.jpg?imageView2/1/h/240/w/240/q/90","total_balance":"43.43","nickname":"小凡4483","member_id":"184597585"}
     * signIn : {"highlight_days":"1,2,3","treasure_box":0,"amount":{"1":108,"2":126,"3":156,"4":270,"5":420,"6":588,"7":1158},"passive_signin":1,"continuation":3,"today":1,"show":1,"ext_reward":{"2":108,"3":210,"4":312,"7":600},"benefit_signin":{"enable":0,"text":"趣公益","url":"","gyb":0},"million_cash_v2":{"million_open":1}}
     * info : {"invite_reward_right":1}
     * treasureBox : {"isActive":true,"name":"","desc":"豪华宝箱大放送，赶快来抢","isReceived":false,"next":0,"has_next":1,"interval_text":"每4小时开启一次 邀请好友越多奖励越多","alarm_info":{"alarm_info_show":0,"alarm_status":0,"title":"","content":""},"guide_config":{"show_suspend":false,"suspend_text":"","next_day_open_text":"明天再来","open_box_text":"开宝箱","toast":"每4小时开启一次 邀请好友越多奖励越多","show_first":false,"first_text":""},"ab":0}
     * recallTaskGiveGold : {"group":""}
     * signInDoubleGold : {"show":false}
     * redbag : [{"img":"http://static.1sapp.com/image/sp/2019/01/22/5c46d783a307d.gif","url":"http://h5ssl.1sapp.com/qukan_new2/dest/yq/inapp/yq/index.html?source=F204618"}]
     * init_invite_sign : {"enable":0}
     * member_create_time : 2018-08-31 18:05:41
     */
    private SignInBean signIn;

    public static class MemberBean implements Serializable {
        /**
         * is_bind_wx : 1
         * is_bind_zfb : 0
         * is_bind_tel : 1
         * is_bind_invite_code : 1
         * avatar : http://static.1sapp.com/image/p/2017/05/19/1495187830605523.jpg?imageView2/1/h/240/w/240/q/90
         * total_balance : 43.43
         * nickname : 小凡4483
         * member_id : 184597585
         */

        private int is_bind_wx;
        private int is_bind_zfb;
        private int is_bind_tel;
        private int is_bind_invite_code;
        private String avatar;
        private String total_balance;
        private String nickname;
        private String member_id;

        public int getIs_bind_wx() {
            return is_bind_wx;
        }

        public void setIs_bind_wx(int is_bind_wx) {
            this.is_bind_wx = is_bind_wx;
        }

        public int getIs_bind_zfb() {
            return is_bind_zfb;
        }

        public void setIs_bind_zfb(int is_bind_zfb) {
            this.is_bind_zfb = is_bind_zfb;
        }

        public int getIs_bind_tel() {
            return is_bind_tel;
        }

        public void setIs_bind_tel(int is_bind_tel) {
            this.is_bind_tel = is_bind_tel;
        }

        public int getIs_bind_invite_code() {
            return is_bind_invite_code;
        }

        public void setIs_bind_invite_code(int is_bind_invite_code) {
            this.is_bind_invite_code = is_bind_invite_code;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getTotal_balance() {
            return total_balance;
        }

        public void setTotal_balance(String total_balance) {
            this.total_balance = total_balance;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }
    }

    public static class SignInBean implements Serializable {
        /**
         * highlight_days : 1,2,3
         * treasure_box : 0
         * amount : {"1":108,"2":126,"3":156,"4":270,"5":420,"6":588,"7":1158}
         * passive_signin : 1
         * continuation : 3
         * today : 1
         * show : 1
         * ext_reward : {"2":108,"3":210,"4":312,"7":600}
         * benefit_signin : {"enable":0,"text":"趣公益","url":"","gyb":0}
         * million_cash_v2 : {"million_open":1}
         */
        public String redPacketUrl = "";//红包的图片
        public String commonAnimationJsonUrl;//普通金币动画地址
        public String redPacketAnimationJsonUrl;//红包金币动画地址
        public String signTopLeftImage;//签到左上角图片
        public String signTopLeftTxt;//签到左上角文字
        public String signTopLeftTxtNewUser;//新用户签到左上角文字
        public String signTopLeftEndTxt;//签到最后一天左上角显示的文字
        public int extRedLevel;//命中哪一组实验，0，对照组
        public List<Integer> extRedList;//哪些位置上面显示红包 [1,2,3]
        //主标题
        public String noviceMainTitle;
        //副标题
        public String noviceSubTitle;

        public int openAd;
        public String lottieUrlStart;
        public String lottieUrlEnd;
        public int remindAdTimes;
        public int grantGoldCount;
        public int randomMin;
        public int randomMax;
        // 当前是否观看完激励视频
        public int playAwarded;
        public int tomorrowAmount = -1;

        private String highlight_days;
        private int treasure_box;
        private AmountBean amount;

        private int passive_signin;
        private int continuation;
        private int today;
        private int show;
        private ExtRewardBean ext_reward;


        public String getHighlight_days() {
            return highlight_days;
        }

        public void setHighlight_days(String highlight_days) {
            this.highlight_days = highlight_days;
        }

        public int getTreasure_box() {
            return treasure_box;
        }

        public void setTreasure_box(int treasure_box) {
            this.treasure_box = treasure_box;
        }

        public AmountBean getAmount() {
            return amount;
        }

        public void setAmount(AmountBean amount) {
            this.amount = amount;
        }

        public int getPassive_signin() {
            return passive_signin;
        }

        public void setPassive_signin(int passive_signin) {
            this.passive_signin = passive_signin;
        }

        public int getContinuation() {
            return continuation;
        }

        public void setContinuation(int continuation) {
            this.continuation = continuation;
        }

        public int getToday() {
            return today;
        }

        public void setToday(int today) {
            this.today = today;
        }

        public int getShow() {
            return show;
        }

        public void setShow(int show) {
            this.show = show;
        }

        public ExtRewardBean getExt_reward() {
            return ext_reward;
        }

        public void setExt_reward(ExtRewardBean ext_reward) {
            this.ext_reward = ext_reward;
        }

        public static class AmountBean implements Serializable {
            /**
             * 1 : 108
             * 2 : 126
             * 3 : 156
             * 4 : 270
             * 5 : 420
             * 6 : 588
             * 7 : 1158
             */

            //@SerializedName("1")
            private int _$1;
            //@SerializedName("2")
            private int _$2;
            //@SerializedName("3")
            private int _$3;
            //@SerializedName("4")
            private int _$4;
            //@SerializedName("5")
            private int _$5;
            //@SerializedName("6")
            private int _$6;
            //@SerializedName("7")
            private int _$7;
            //@SerializedName("8")
            private int _$8;
            //@SerializedName("9")
            private int _$9;
            //@SerializedName("10")
            private int _$10;
            //@SerializedName("11")
            private int _$11;
            //@SerializedName("12")
            private int _$12;
            //@SerializedName("13")
            private int _$13;
            //@SerializedName("14")
            private int _$14;
            //@SerializedName("15")
            private int _$15;
            //@SerializedName("16")
            private int _$16;
            //@SerializedName("17")
            private int _$17;
            //@SerializedName("18")
            private int _$18;
            //@SerializedName("19")
            private int _$19;
            //@SerializedName("20")
            private int _$20;
            //@SerializedName("21")
            private int _$21;
            //@SerializedName("22")
            private int _$22;
            //@SerializedName("23")
            private int _$23;
            //@SerializedName("24")
            private int _$24;
            //@SerializedName("25")
            private int _$25;
            //@SerializedName("26")
            private int _$26;
            //@SerializedName("27")
            private int _$27;
            //@SerializedName("28")
            private int _$28;
            //@SerializedName("29")
            private int _$29;
            //@SerializedName("30")
            private int _$30;
            public int get_$1() {
                return _$1;
            }

            public void set_$1(int _$1) {
                this._$1 = _$1;
            }

            public int get_$2() {
                return _$2;
            }

            public void set_$2(int _$2) {
                this._$2 = _$2;
            }

            public int get_$3() {
                return _$3;
            }

            public void set_$3(int _$3) {
                this._$3 = _$3;
            }

            public int get_$4() {
                return _$4;
            }

            public void set_$4(int _$4) {
                this._$4 = _$4;
            }

            public int get_$5() {
                return _$5;
            }

            public void set_$5(int _$5) {
                this._$5 = _$5;
            }

            public int get_$6() {
                return _$6;
            }

            public void set_$6(int _$6) {
                this._$6 = _$6;
            }

            public int get_$7() {
                return _$7;
            }

            public void set_$7(int _$7) {
                this._$7 = _$7;
            }

            public int get_$8() {
                return _$8;
            }

            public void set_$8(int _$8) {
                this._$8 = _$8;
            }

            public int get_$9() {
                return _$9;
            }

            public void set_$9(int _$9) {
                this._$9 = _$9;
            }

            public int get_$10() {
                return _$10;
            }

            public void set_$10(int _$10) {
                this._$10 = _$10;
            }

            public int get_$11() {
                return _$11;
            }

            public void set_$11(int _$11) {
                this._$11 = _$11;
            }

            public int get_$12() {
                return _$12;
            }

            public void set_$12(int _$12) {
                this._$12 = _$12;
            }

            public int get_$13() {
                return _$13;
            }

            public void set_$13(int _$13) {
                this._$13 = _$13;
            }

            public int get_$14() {
                return _$14;
            }

            public void set_$14(int _$14) {
                this._$14 = _$14;
            }

            public int get_$15() {
                return _$15;
            }

            public void set_$15(int _$15) {
                this._$15 = _$15;
            }

            public int get_$16() {
                return _$16;
            }

            public void set_$16(int _$16) {
                this._$16 = _$16;
            }

            public int get_$17() {
                return _$17;
            }

            public void set_$17(int _$17) {
                this._$17 = _$17;
            }

            public int get_$18() {
                return _$18;
            }

            public void set_$18(int _$18) {
                this._$18 = _$18;
            }

            public int get_$19() {
                return _$19;
            }

            public void set_$19(int _$19) {
                this._$19 = _$19;
            }

            public int get_$20() {
                return _$20;
            }

            public void set_$20(int _$20) {
                this._$20 = _$20;
            }

            public int get_$21() {
                return _$21;
            }

            public void set_$21(int _$21) {
                this._$21 = _$21;
            }

            public int get_$22() {
                return _$22;
            }

            public void set_$22(int _$22) {
                this._$22 = _$22;
            }

            public int get_$23() {
                return _$23;
            }

            public void set_$23(int _$23) {
                this._$23 = _$23;
            }

            public int get_$24() {
                return _$24;
            }

            public void set_$24(int _$24) {
                this._$24 = _$24;
            }

            public int get_$25() {
                return _$25;
            }

            public void set_$25(int _$25) {
                this._$25 = _$25;
            }

            public int get_$26() {
                return _$26;
            }

            public void set_$26(int _$26) {
                this._$26 = _$26;
            }

            public int get_$27() {
                return _$27;
            }

            public void set_$27(int _$27) {
                this._$27 = _$27;
            }

            public int get_$28() {
                return _$28;
            }

            public void set_$28(int _$28) {
                this._$28 = _$28;
            }

            public int get_$29() {
                return _$29;
            }

            public void set_$29(int _$29) {
                this._$29 = _$29;
            }

            public int get_$30() {
                return _$30;
            }

            public void set_$30(int _$30) {
                this._$30 = _$30;
            }
        }

        public static class ExtRewardBean implements Serializable {
            /**
             * 2 : 108
             * 3 : 210
             * 4 : 312
             * 7 : 600
             */
            //@SerializedName("1")
            private int _$1;
            //@SerializedName("2")
            private int _$2;
            //@SerializedName("3")
            private int _$3;
            //@SerializedName("4")
            private int _$4;
            //@SerializedName("5")
            private int _$5;
            //@SerializedName("6")
            private int _$6;
            //@SerializedName("7")
            private int _$7;
            //@SerializedName("8")
            private int _$8;
            //@SerializedName("9")
            private int _$9;
            //@SerializedName("10")
            private int _$10;
            //@SerializedName("11")
            private int _$11;
            //@SerializedName("12")
            private int _$12;
            //@SerializedName("13")
            private int _$13;
            //@SerializedName("14")
            private int _$14;
            //@SerializedName("15")
            private int _$15;
            //@SerializedName("16")
            private int _$16;
            //@SerializedName("17")
            private int _$17;
            //@SerializedName("18")
            private int _$18;
            //@SerializedName("19")
            private int _$19;
            //@SerializedName("20")
            private int _$20;
            //@SerializedName("21")
            private int _$21;
            //@SerializedName("22")
            private int _$22;
            //@SerializedName("23")
            private int _$23;
            //@SerializedName("24")
            private int _$24;
            //@SerializedName("25")
            private int _$25;
            //@SerializedName("26")
            private int _$26;
            //@SerializedName("27")
            private int _$27;
            //@SerializedName("28")
            private int _$28;
            //@SerializedName("29")
            private int _$29;
            //@SerializedName("30")
            private int _$30;
            public int get_$1() {
                return _$1;
            }

            public void set_$1(int _$1) {
                this._$1 = _$1;
            }

            public int get_$2() {
                return _$2;
            }

            public void set_$2(int _$2) {
                this._$2 = _$2;
            }

            public int get_$3() {
                return _$3;
            }

            public void set_$3(int _$3) {
                this._$3 = _$3;
            }

            public int get_$4() {
                return _$4;
            }

            public void set_$4(int _$4) {
                this._$4 = _$4;
            }

            public int get_$5() {
                return _$5;
            }

            public void set_$5(int _$5) {
                this._$5 = _$5;
            }

            public int get_$6() {
                return _$6;
            }

            public void set_$6(int _$6) {
                this._$6 = _$6;
            }

            public int get_$7() {
                return _$7;
            }

            public void set_$7(int _$7) {
                this._$7 = _$7;
            }

            public int get_$8() {
                return _$8;
            }

            public void set_$8(int _$8) {
                this._$8 = _$8;
            }

            public int get_$9() {
                return _$9;
            }

            public void set_$9(int _$9) {
                this._$9 = _$9;
            }

            public int get_$10() {
                return _$10;
            }

            public void set_$10(int _$10) {
                this._$10 = _$10;
            }

            public int get_$11() {
                return _$11;
            }

            public void set_$11(int _$11) {
                this._$11 = _$11;
            }

            public int get_$12() {
                return _$12;
            }

            public void set_$12(int _$12) {
                this._$12 = _$12;
            }

            public int get_$13() {
                return _$13;
            }

            public void set_$13(int _$13) {
                this._$13 = _$13;
            }

            public int get_$14() {
                return _$14;
            }

            public void set_$14(int _$14) {
                this._$14 = _$14;
            }

            public int get_$15() {
                return _$15;
            }

            public void set_$15(int _$15) {
                this._$15 = _$15;
            }

            public int get_$16() {
                return _$16;
            }

            public void set_$16(int _$16) {
                this._$16 = _$16;
            }

            public int get_$17() {
                return _$17;
            }

            public void set_$17(int _$17) {
                this._$17 = _$17;
            }

            public int get_$18() {
                return _$18;
            }

            public void set_$18(int _$18) {
                this._$18 = _$18;
            }

            public int get_$19() {
                return _$19;
            }

            public void set_$19(int _$19) {
                this._$19 = _$19;
            }

            public int get_$20() {
                return _$20;
            }

            public void set_$20(int _$20) {
                this._$20 = _$20;
            }

            public int get_$21() {
                return _$21;
            }

            public void set_$21(int _$21) {
                this._$21 = _$21;
            }

            public int get_$22() {
                return _$22;
            }

            public void set_$22(int _$22) {
                this._$22 = _$22;
            }

            public int get_$23() {
                return _$23;
            }

            public void set_$23(int _$23) {
                this._$23 = _$23;
            }

            public int get_$24() {
                return _$24;
            }

            public void set_$24(int _$24) {
                this._$24 = _$24;
            }

            public int get_$25() {
                return _$25;
            }

            public void set_$25(int _$25) {
                this._$25 = _$25;
            }

            public int get_$26() {
                return _$26;
            }

            public void set_$26(int _$26) {
                this._$26 = _$26;
            }

            public int get_$27() {
                return _$27;
            }

            public void set_$27(int _$27) {
                this._$27 = _$27;
            }

            public int get_$28() {
                return _$28;
            }

            public void set_$28(int _$28) {
                this._$28 = _$28;
            }

            public int get_$29() {
                return _$29;
            }

            public void set_$29(int _$29) {
                this._$29 = _$29;
            }

            public int get_$30() {
                return _$30;
            }

            public void set_$30(int _$30) {
                this._$30 = _$30;
            }
        }
    }
}
