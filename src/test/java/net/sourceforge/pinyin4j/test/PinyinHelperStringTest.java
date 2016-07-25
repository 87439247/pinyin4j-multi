package net.sourceforge.pinyin4j.test;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import net.sourceforge.pinyin4j.multipinyin.MultiPinyinConfig;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

/**
 * Created by 刘一波 on 16/3/4.
 * E-Mail:yibo.liu@tqmall.com
 */
public class PinyinHelperStringTest {
    static HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();

    static {
        outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        outputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        outputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
    }

    @Test
    public void testMulti() throws Exception {
        MultiPinyinConfig.multiPinyinPath = "/Users/yiboliu/my_multi_pinyin.txt";
        Assert.assertEquals("he;he;.;.;.", PinyinHelper.toHanYuPinyinString("呵呵...", outputFormat,
                ";", true));
        Assert.assertEquals("xi;xue;gui;.;.;.", PinyinHelper.toHanYuPinyinString("吸血鬼...",
                outputFormat, ";", true));
        Assert.assertEquals("xi;xue;gui;ri;ji;.;.;.", PinyinHelper.toHanYuPinyinString("吸血鬼日记...",
                outputFormat, ";", true));
        Assert.assertEquals("wohaiyaoqutushuguanhuanshu...", PinyinHelper.toHanYuPinyinString(
                "我还要去图书馆还书...", outputFormat, null, true));
        Assert.assertEquals("yiwuyishi", PinyinHelper.toHanYuPinyinString("一五一十", outputFormat, "",
                true));
        Assert.assertEquals("nv;yi;ming;fei;zhuan", PinyinHelper.toHanYuPinyinString("女医明妃传",
                outputFormat, ";", true));
        Assert.assertEquals("yi.ren.zuo.shi.yi.ren.dang.hai", PinyinHelper.toHanYuPinyinString(
                "一人做事一人当还", outputFormat, ".", true));
        Assert.assertEquals("meng,zhi,an,hun,qu", PinyinHelper.toHanYuPinyinString("梦之安魂曲",
                outputFormat, ",", true));
        Assert.assertEquals("chang,chun,.,.", PinyinHelper.toHanYuPinyinString("长春..",
                outputFormat, ",", true));
        Assert.assertEquals("chang,chun,bu,lao,/", PinyinHelper.toHanYuPinyinString("长春不老/",
                outputFormat, ",", true));
        Assert.assertEquals("liu,yi,bo", PinyinHelper.toHanYuPinyinString("刘一波", outputFormat, ",",
                true));
        Assert.assertEquals("wo,cao", PinyinHelper.toHanYuPinyinString("我艹", outputFormat, ",",
                true));
        Assert.assertEquals("bao,ma,ben,chi", PinyinHelper.toHanYuPinyinString("宝马奔驰",
                outputFormat, ",", false));
        Assert.assertEquals("he;he", PinyinHelper.toHanYuPinyinString("呵呵...", outputFormat, ";",
                false));
        Assert.assertEquals("xi;xue;gui", PinyinHelper.toHanYuPinyinString("吸血鬼...", outputFormat,
                ";", false));
        Assert.assertEquals("xi;xue;gui;ri;ji", PinyinHelper.toHanYuPinyinString("吸血鬼日记...",
                outputFormat, ";", false));
        Assert.assertEquals("wo;hai;yao;qu;tu;shu;guan;huan;shu", PinyinHelper.toHanYuPinyinString(
                "我还要去图书馆还书...", outputFormat, ";", false));
        Assert.assertEquals("yi;wu;yi;shi", PinyinHelper.toHanYuPinyinString("一五一十", outputFormat,
                ";", false));
        Assert.assertEquals("nv;yi;ming;fei;zhuan", PinyinHelper.toHanYuPinyinString("女医明妃传",
                outputFormat, ";", false));
        Assert.assertEquals("nv;yi;ming;fei;zhuan;yi;wu;yi;shi;xi;xue;gui", PinyinHelper
                .toHanYuPinyinString("女医明妃传..一五一十.吸血鬼", outputFormat, ";", false));
        Assert.assertEquals("nv;yi;ming;fei;zhuan;.;.;yi;wu;yi;shi;.;xi;xue;gui", PinyinHelper
                .toHanYuPinyinString("女医明妃传..一五一十.吸血鬼", outputFormat, ";", true));
    }

    @Test
    public void testRate() throws Exception {
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("/Users/yiboliu/sougou.dic");
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            long time = System.nanoTime();//时间
            long times = 0;//次数
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                PinyinHelper.toHanYuPinyinString(line, outputFormat, null, false);
                times++;
            }
            long lasted = System.nanoTime() - time;
            System.out.println(String.format("共花费%s纳秒", lasted));
            System.out.println(String.format("共转换%s次数", times));
            long average = lasted / times;
            System.out.println(String.format("平均耗时%s纳秒", average));
            //平均耗时小于15微秒
            Assert.assertTrue(average < 15000);
        } finally {
            try {
                assert bufferedReader != null;
                bufferedReader.close();
            } catch (Exception e) {}
            try {
                inputStreamReader.close();
            } catch (Exception e) {}
            try {
                fileInputStream.close();
            } catch (Exception e) {}
        }

    }

    /**
     * 测试远程词库是否可用
     */
    public void testRemoteDic() throws BadHanyuPinyinOutputFormatCombination, InterruptedException {
        MultiPinyinConfig.multiPinyinHttpPath = "http://127.0.0.1:7777/ik_words/multi_pinyin.txt";

        Assert.assertEquals("he;he;.;.;.", PinyinHelper.toHanYuPinyinString("呵呵...", outputFormat,
                ";", true));
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals("kong;tiao", PinyinHelper.toHanYuPinyinString("空调", outputFormat, ";",
                true));
    }
}
