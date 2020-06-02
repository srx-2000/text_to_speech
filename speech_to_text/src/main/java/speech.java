import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Util;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * @author srx
 * @description
 * @create 2020-06-01 14:58:44
 */
public class speech {
    public static final String APP_ID = "20149756";
    public static final String API_KEY = "j5c4iRkMiKMRxfQ18PTO6WC7";
    public static final String SECRET_KEY = "RMsRBqkibliEGavt06U7jaUEg4cQOpm9";
    public String tone = "5";
    public String anchor = "0";
    public String volume = "5";
    public String speed = "5";
    public String text="欢迎使用";
    public String local="./output";
    public speech(String[] info) {
        this.speed=info[2];
        this.tone=info[0];
        this.anchor=info[1];
        this.volume=info[3];
        this.text=info[4];
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);
        HashMap options=set_options(this.speed,this.tone,this.anchor,this.volume);
        // 调用接口，其中options是一个可以提供具体参数的哈希图，其中对应的意思看上面的put方法解释
        TtsResponse res = client.synthesis(this.text, "zh", 1, options);
//        生成的音频数据存放在data中
        byte[] data = res.getData();
        //服务器返回的内容，合成成功时为null,失败时包含error_no等信息
        JSONObject res1 = res.getResult();
        if (data != null) {
            try {
                Util.writeBytesToFileSystem(data, local+".mp3");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (res1 != null) {
            System.out.println(res1.toString(2));
        }
    }
    public static String[] get_info(){
        ObjectInputStream objectInputStream = null;
        BufferedInputStream bufferedInputStream=null;
        String[] info={"5","0","5","5","欢迎使用"};
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket socket = serverSocket.accept();
            bufferedInputStream=new BufferedInputStream(socket.getInputStream());
            objectInputStream = new ObjectInputStream(bufferedInputStream);
            info = (String[]) objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }
    public static HashMap<String,Object> set_options(String speed, String tone, String anchor, String volume){
        HashMap<String,Object> options=new HashMap<String, Object>();
//        语速，取值0-9，默认为5中语速
        options.put("spd",speed);
//        音调，取值0-9，默认为5中语调
        options.put("pit",tone);
//        发音人选择, 0为女声，1为男声，
//        3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女
        options.put("per",anchor);
//        音量，取值0-15，默认为5中音量
        options.put("vol",volume);
        return options;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public static void main(String[] args) {
        speech speech=new speech(get_info());
//        speech.setLocal("D:\\测试音频");

    }

}
