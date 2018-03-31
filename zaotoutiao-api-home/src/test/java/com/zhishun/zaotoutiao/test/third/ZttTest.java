/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.test.third;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhishun.zaotoutiao.biz.service.IThirdInfoService;
import com.zhishun.zaotoutiao.biz.service.IUserService;
import com.zhishun.zaotoutiao.common.util.RedPackUtil;
import com.zhishun.zaotoutiao.core.model.entity.User;
import com.zhishun.zaotoutiao.core.model.thirdVo.ResponseResult;
import com.zhishun.zaotoutiao.test.common.TestConfig;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 测试类
 * @author 闫迎军(YanYingJun)
 * @version $Id: ZttTest, v0.1 2018年02月10日 15:57闫迎军(YanYingJun) Exp $
 */

public class ZttTest extends TestConfig{

    @Autowired
    private IUserService userService;

    @Autowired
    private IThirdInfoService thirdInfoService;

    @Test
    public void testUser(){
        User user1 = userService.getUserByUserId((long)19);
        System.out.println(JSON.toJSON(user1));
    }

    @Test
    public void testThird(){
       /* ResponseResult responseResult = thirdInfoService.getToken("147");
        System.out.println(JSON.toJSON(responseResult));
        String data = responseResult.getData();
        JSONObject jsonObject = JSONObject.parseObject(data);

        ResponseResult responseResult1 = thirdInfoService.listInformationFlow("979464897", jsonObject.getString("access_token"), "fun", "0", "147");
        System.out.println(JSON.toJSON(responseResult1));*/

        /*ResponseResult responseResult2 = thirdInfoService.channelInformation(jsonObject.getString("access_token"), "0");
        System.out.println(JSON.toJSON(responseResult2));

        String str = "eyJjb2RlIjoibUJsdkx5TUpxYTJNIiwicGxheUxpbmsiOiJodHRwOlwvXC9rLjM2MGthbi5jb21cL3ZpZGVvXC9wbGF5P2lkPW1CbHZMeU1KcWEyTSZmPWpzb24mdWNoZWNrPWFkMmY5ODVlMjUwYWM3ZTA3OGYzMTE4NDQ0YWQxZWMyJnVpZD00YmEyNmFmMDg5ZjkzMWJmMTE4NGU0MzUzZDYwMDY1ZiZ2ZXJzaW9uPTEuMC4yJnNpZ249ZXhfMmViNjA0YzEmcmVzaWduPWV4XzJlYjYwNGMxJmlzX3JlY29tPTEmc3RyYXRlZ3k9Mi42LjI4LjQxLmE3YmxxMDllbGNiei45Lmo3bC4zNC52aGp0a20uLjEuODVhbnkxZ2U0a2hrJmNoYW5uZWxfaWQ9MCZlbmQ9bW9iaWxlX2FuZHJvaWQmaXNfbmV3X3JlY29tPTEmbWFya2V0PSZuZXdzX3Nka192ZXJzaW9uPSIsInBpY1VybCI6Imh0dHA6XC9cL3AwLnFoaW1nLmNvbVwvdDAxZGI0YzQ5MDkwZGFlYjk5MC5qcGciLCJ0b3RhbFRpbWVTdHIiOiIwMDoxNCIsInBsYXlDbnQiOjEyNn0=";
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            String text = String.valueOf(decoder.decodeBuffer(str));
            System.out.println("&&&&"+text);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //String u = "http://sh.qihoo.com/mob/transcoding?url=http%3A%2F%2Fwww.guancha.cn%2Fsociety%2F2018_03_07_449294.shtml&check=b105664cd450596b&uid=4ba26af089f931bf1184e4353d60065f&sign=ex%5F2eb604c1&sv=1&templetctl=7&360newsdetail=1&hsitetype=1&ucheck=f471514a37c9c09d1cd7e1a9541b2826&hscmt=1\\";
        /*ResponseResult responseResult3 = thirdInfoService.pressClick(jsonObject.getString("access_token"), "0");
        System.out.println(JSON.toJSON(responseResult3));*/

        /*ResponseResult responseResult = thirdInfoService.channelList("android", null, null, null);
        System.out.println(JSON.toJSON(responseResult));*/

        /*ResponseResult responseResult1 = thirdInfoService.ListVideos("android", null,null, null, "13");
        System.out.println(JSON.toJSON(responseResult1));*/


        /*ResponseResult responseResult2 = thirdInfoService.getVideoDetail("android", "1","1", "2", "lnoRydGDaNQ7", "eyJzdHJhdGVneSI6IjIuNi4yOC44LmFldGxsNGFjenhtdS45Lmd0bi4xOS52bnlzZmsuLjEuNXh4byIsImNoYW5uZWxfaWQiOiIyNCJ9", "192.168.2.0");
        System.out.println(responseResult2);*/
        /*ResponseResult responseResult3 = thirdInfoService.getVideoPlayAddress("android", null,null, null, "oB2KXjw2wVw4", "eyJzdHJhdGVneSI6IjIuNi4yOC4xOC5hN2tjdzQwNHY3bGQuMS43Z2wuMy52bnk2MWkuLjExLjFhc2Z4YTJ5NWk0eCIsImNoYW5uZWxfaWQiOiIxMyJ9");
        System.out.println(JSON.toJSON(responseResult3));*/

        //ResponseResult responseResult4 = thirdInfoService.relatedVideo("android", null,null, null, "oB2KXjw2wVw4", "eyJzdHJhdGVneSI6IjIuNi4yOC4xOC5hN2tjdzQwNHY3bGQuMS43Z2wuMy52bnk2MWkuLjExLjFhc2Z4YTJ5NWk0eCIsImNoYW5uZWxfaWQiOiIxMyJ9");
        //System.out.println(JSON.toJSON(responseResult4));

        /*Date str = DateUtil.toDate("1517491504", DateUtil.DEFAULT_DATETIME_FORMAT);
        String str1 = DateUtil.toString(str, DateUtil.DEFAULT_DATETIME_FORMAT);
        System.out.println(str1);*/


        /*Date date = new Date(Long.valueOf("1520576377")*1000L);
        System.out.println(DateUtil.toString(date, DateUtil.DEFAULT_DATETIME_FORMAT));*/
        /*ResponseResult responseResult = thirdInfoService.getToken("89");
        System.out.println(JSON.toJSON(responseResult));
        String data = responseResult.getData();
        JSONObject jsonObject = JSONObject.parseObject(data);*/
        /*ResponseResult responseResult1 = thirdInfoService.listInformationFlow("979464897", jsonObject.getString("access_token"), "youlike", "0", "89", 10);
        System.out.println(JSON.toJSON(responseResult1));*/
        /*String url = null;
        try {
            url = thirdInfoService.pressClick(jsonObject.getString("access_token"), "0",
                    "youlike", "h", "funny", "funny,multi,test_filter_V24,normal_op,joke,gif,dfs_monit,gaoxiao,sts1,fts3,,\n" +
                            "nbbn_call_S:5:p8000012:全类:妹子:搞笑,nbbn_cfunny_P:全类:妹子:搞笑,nbbn_cfunny_S:5:16959:搞笑:gif,nbbn_cfunny_S:5:17570:gif:搞笑,nbbn_cfunny_S:5:m30830:gif:搞笑,\n" +
                            "nbbn_kfunny_1:搞笑,nbbn_kfunny_2:搞笑,nbbn_sfunny_搞笑,nbbn_tfunny,,nbbc_tfunny_V153,nbbr_tfunny_V153__100__1660__382300__27__13__100,tag,tagt_91010,\n" +
                            "uusdkother_0,nbbh_merger66.se.bjyt,uusdkother,,nbbs_0:1", "搞笑GIF:媳妇出门不忘带上锅，真正的吃货", "9a0937fcb5a58aa52f8cbdf151f4898d",
                    "t", "1","89");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(url);*/

        /*String str = "{'packageName': 'InternetRadio.all','appVersion': '1.2.3','idIdfa': 'ssss','idImei': 'xxxx','height': 1136,'width': 640," +
                "'brand': 'apple','model': 'iPhone6','osVersion': '1.1.2','osType': 1,'type': 1,'ip': '218.66.169.71','domain':'78447','urls':'55899','userAgent':'Mozilla/5.0 (Linux; U; android 2.3.7; en-us; Nexus One Build/FRF91) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1'}";
        JSONObject json = JSON.parseObject(str);
        JSONObject jsonObject = thirdInfoService.getAdInfo(json);
        System.out.println(jsonObject);*/

        //先随机产生一个下标再获取元素

        /*int[] doc = {0,1,2,3,4,5,6,7,8,9};

        for(int i = 0;i<10;i++){
            int index = (int) (Math.random() * doc.length);
            int random = doc[index];
            if(random>=5){
                System.out.println("&&&"+random);
            }
            if(random >=3){
                System.out.println("***"+random);
            }

        }*/

        //Calendar cal = Calendar.getInstance();
        //System.out.println(cal.get(Calendar.DAY_OF_MONTH));

        /*Random random = new Random();
        System.out.println(random.nextInt(100));*/


































    }


}
