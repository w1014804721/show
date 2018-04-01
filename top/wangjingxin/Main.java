package top.wangjingxin;


import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static Map<String, Integer> map = new HashMap<>();

    static {
        map.put("oe", 0);
        map.put("n", 0);
        map.put("z", 0);
        map.put("oK", 1);
        map.put("6", 1);
        map.put("5", 1);
        map.put("ow", 2);
        map.put("-", 2);
        map.put("A", 2);
        map.put("oi", 3);
        map.put("o", 3);
        map.put("i", 3);
        map.put("7e", 4);
        map.put("v", 4);
        map.put("P", 4);
        map.put("7K", 5);
        map.put("4", 5);
        map.put("k", 5);
        map.put("7w", 6);
        map.put("C", 6);
        map.put("s", 6);
        map.put("7i", 7);
        map.put("S", 7);
        map.put("l", 7);
        map.put("Ne", 8);
        map.put("c", 8);
        map.put("F", 8);
        map.put("NK", 9);
        map.put("E", 9);
        map.put("q", 9);
    }

    public static void main(String[] args) throws IOException {
        if(args.length==0){
            System.out.println("请输入文件路径");
            return;
        }
        String path = args[0];
        JSONObject.parseObject(new String(Files.readAllBytes(Paths.get(path))))
                .getJSONObject("data")
                .getJSONArray("list")
                .forEach(Main::handle);
    }

    private static void handle(Object node) {
        JSONObject object = (JSONObject) node;
        System.out.println(object.getString("fromNick") + " " + object.getString("topicName") + " " + parse(object.getString("fromEncodeUin")));
    }

    private static String parse(String code) {
        StringBuilder sb = new StringBuilder();
        char[] chars = code.substring(4, code.length()).toCharArray();
        for (int i = 0; i < chars.length; i++) {
            sb.append(map.get(a(chars[i], i+1==chars.length?'*':chars[i + 1])) != null?map.get(a(chars[i], chars[++i])):map.get(a(chars[i])));
        }
        return sb.toString();
    }

    private static String a(char... chars) {
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            sb.append(c);
        }
        return sb.toString();
    }
}
