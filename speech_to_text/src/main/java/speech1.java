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
import java.util.HashSet;
import java.util.Scanner;

/**
 * @author srx
 * @description
 * @create 2020-06-01 09:55:36
 */
public class speech1 {
    //设置APPID/AK/SK
    public static final String APP_ID = "20149756";
    public static final String API_KEY = "j5c4iRkMiKMRxfQ18PTO6WC7";
    public static final String SECRET_KEY = "RMsRBqkibliEGavt06U7jaUEg4cQOpm9";

    public static void main(String[] args) {
        // 初始化一个AipSpeech
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
//        client.setConnectionTimeoutInMillis(2000);
//        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
//        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

//        创建一个HashMap用来存放相关调节参数
//        Scanner input=new Scanner(System.in);
////        String text=input.nextLine();
        String tone="5";
        String anchor="0";
        String volume="5";
        String speed="5";
        String text="欢迎使用";
        ObjectInputStream objectInputStream = null;
        BufferedInputStream bufferedInputStream=null;
        String[] info;
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket socket = serverSocket.accept();
            bufferedInputStream=new BufferedInputStream(socket.getInputStream());
            objectInputStream = new ObjectInputStream(bufferedInputStream);
            info = (String[]) objectInputStream.readObject();
//            for (int i = 0; i <info.length ; i++) {
//                System.out.println(info[i]);
//            }
//            list[0] = get_tone();
//            list[1] = get_archor();
//            list[2] = get_speed();
//            list[3] = get_volume();
//            list[4] = get_text();
            tone=info[0];
            anchor=info[1];
            speed=info[2];
            volume=info[3];
            text=info[4];
        } catch (Exception e) {
            e.printStackTrace();
        }

        HashMap options=set_options(speed,tone,anchor,volume);
        // 调用接口，其中options是一个可以提供具体参数的哈希图，其中对应的意思看上面的put方法解释
        TtsResponse res = client.synthesis(text, "zh", 1, options);
//        生成的音频数据存放在data中
        byte[] data = res.getData();
        //服务器返回的内容，合成成功时为null,失败时包含error_no等信息
        JSONObject res1 = res.getResult();
        if (data != null) {
            try {
                Util.writeBytesToFileSystem(data, "output.mp3");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (res1 != null) {
            System.out.println(res1.toString(2));
        }

    }
    public static HashMap<String,Object> set_options(String speed,String tone,String anchor,String volume){
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
}
