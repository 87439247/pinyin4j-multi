package net.sourceforge.pinyin4j.multipinyin;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by 刘一波 on 16/3/4.
 * E-Mail:yibo.liu@tqmall.com
 */
public class Trie {

    private Hashtable<String, Trie> values = new Hashtable<String, Trie>();//本节点包含的值

    private String pinyin;//本节点的拼音

    private Trie nextTire;//下一个节点,也就是匹配下一个字符

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public Trie getNextTire() {
        return nextTire;
    }

    public void setNextTire(Trie nextTire) {
        this.nextTire = nextTire;
    }

    /**
     * 加载拼音
     *
     * @param inStream 拼音文件输入流
     * @throws IOException
     */
    public synchronized void load(InputStream inStream) throws IOException {
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(inStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                String[] keyAndValue = s.split(" ");
                if (keyAndValue.length != 2) continue;
                Trie trie = new Trie();
                trie.pinyin = keyAndValue[1];
                put(keyAndValue[0], trie);
            }
        } finally {
            if (inputStreamReader != null) inputStreamReader.close();
            if (bufferedReader != null) bufferedReader.close();
        }
    }

    /**
     * 加载多音字拼音词典
     *
     * @param list 拼音列表
     */
    public synchronized void loadMultiPinyin(List<String> list) throws IOException {
        for (String s : list) {
            handleMultiLine(s);
            PinyinLogger.info(s);
        }
    }

    /**
     * 加载多音字拼音词典
     *
     * @param inStream 拼音文件输入流
     */
    public synchronized void loadMultiPinyin(InputStream inStream) throws IOException {
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(inStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                handleMultiLine(s);
            }
        } finally {
            if (inputStreamReader != null) inputStreamReader.close();
            if (bufferedReader != null) bufferedReader.close();
        }
    }

    /**
     * 处理多音词的一行
     *
     * @param s 一行,一个词及其拼音
     */
    private void handleMultiLine(String s) {
        String[] keyAndValue = s.split(" ");
        if (keyAndValue.length != 2) return;

        String key = keyAndValue[0];//多于一个字的字符串
        String value = keyAndValue[1];//字符串的拼音
        char[] keys = key.toCharArray();

        Trie currentTrie = this;
        for (int i = 0; i < keys.length; i++) {
            String hexString = Integer.toHexString(keys[i]).toUpperCase();

            Trie trieParent = currentTrie.get(hexString);
            if (trieParent == null) {//如果没有此值,直接put进去一个空对象
                currentTrie.put(hexString, new Trie());
                trieParent = currentTrie.get(hexString);
            }
            Trie trie = trieParent.getNextTire();//获取此对象的下一个

            if (keys.length - 1 == i) {//最后一个字了,需要把拼音写进去
                trieParent.pinyin = value;
                break;//此行其实并没有意义
            }

            if (trie == null) {
                if (keys.length - 1 != i) {
                    //不是最后一个字,写入这个字的nextTrie,并匹配下一个
                    Trie subTrie = new Trie();
                    trieParent.setNextTire(subTrie);
                    subTrie.put(Integer.toHexString(keys[i + 1]).toUpperCase(), new Trie());
                    currentTrie = subTrie;
                }
            } else {
                currentTrie = trie;
            }

        }
    }

    /**
     * 加载用户自定义的扩展词库
     */
    public synchronized void loadMultiPinyinExtend() throws IOException {
        String path = MultiPinyinConfig.multiPinyinPath;
        if (path != null) {
            PinyinLogger.info(MultiPinyinConfig.multiPinyinPath);
            PinyinLogger.info("开始加载用户扩展文件多音词库");
            try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))))) {
                List<String> buffer = new LinkedList<>();
                String line;
                while ((line = in.readLine()) != null) {
                    buffer.add(line);
                }

                loadMultiPinyin(buffer);
            } catch (Exception e) {
                PinyinLogger
                        .info("无法直接读取文件,如果是在ES中,请使用使用ES工具读取并设置路径,否则请使用HTTP词库或者直接传入文件流或词库列表:"
                                + "PathUtils.get(\n"
                                + "                        new File(Trie.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParent(), \"config\")\n"
                                + "                        .toAbsolutePath().toString();");
            }
        }
    }

    private static ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);

    /**
     * 加载用户自定义的HTTP扩展词库,仅在定时任务中调用,延迟加载
     */
    public synchronized void loadMultiPinyinHttpExtend() throws IOException {
        PinyinLogger.info("开始加载用户扩展HTTP多音词库");
        PinyinLogger.info(MultiPinyinConfig.multiPinyinHttpPath);
        loadMultiPinyin(getRemoteWords(MultiPinyinConfig.multiPinyinHttpPath));
    }

    /**
     * 远程词库监控
     */
    public void monitor() throws IOException {
        String path = MultiPinyinConfig.multiPinyinHttpPath;
        if (path != null) {
            //建立监控线程
            pool.scheduleAtFixedRate(new Monitor(path, this), 0, 60, TimeUnit.SECONDS);
        }
    }

    /**
     * 从远程服务器上下载自定义词条
     */
    private static List<String> getRemoteWords(String location) {

        List<String> buffer = new ArrayList<String>();
        RequestConfig rc =
                RequestConfig.custom().setConnectionRequestTimeout(10 * 1000).setConnectTimeout(
                        10 * 1000).setSocketTimeout(60 * 1000).build();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        BufferedReader in = null;

        HttpGet get = new HttpGet(location);
        get.setConfig(rc);
        try (CloseableHttpResponse response = httpclient.execute(get)) {
            if (response.getStatusLine().getStatusCode() == 200) {

                String charset = "UTF-8";
                //获取编码，默认为utf-8
                if (response.getEntity().getContentType().getValue().contains("charset=")) {
                    String contentType = response.getEntity().getContentType().getValue();
                    charset = contentType.substring(contentType.lastIndexOf("=") + 1);
                }
                in =
                        new BufferedReader(new InputStreamReader(response.getEntity().getContent(),
                                charset));

                String line;
                while ((line = in.readLine()) != null) {
                    buffer.add(line);
                }
                return buffer;
            }
        } catch (IllegalStateException | IOException e) {
            PinyinLogger.error("getRemoteWords {} error", e, location);
        } finally {
            if (in != null) try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buffer;
    }

    public Trie get(String hexString) {
        return values.get(hexString);
    }

    public void put(String s, Trie trie) {
        values.put(s, trie);
    }
}
