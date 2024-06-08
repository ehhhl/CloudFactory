package com.wangshijia.utils;

//导入谷歌json包，去读取json文件
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

//把json里面的数据转换成类的形式:读取json文件、写入json文件,通过泛型实现
public class JsonFileStorage<T> {
    private final String filePath;
    private final Gson gson;
    private JsonParser parser;
    private JsonArray jsonArray;

    //构造函数，创建对象
    public JsonFileStorage(String filePath) {
        this.filePath = filePath;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    //读取json文件，把json数据转换成泛型类的集合
    public List<T> readData(Class<T> objectType) throws IOException 
    {   //把json数据转换成类
    	List<T> objectList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        	parser = new JsonParser();
            //将json文件中每个元素，转换成输入泛型的类objectType       	
            jsonArray = parser.parse(reader).getAsJsonArray();
            for (JsonElement jsonElement : jsonArray) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                T object = gson.fromJson(jsonObject, objectType);
                objectList.add(object);
            }
            return objectList;
        }
    }

    //把泛型类的集合转换成json数据并写入json文件
    public void writeData(List<T> data) throws IOException {	//把类转换成json数据
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            gson.toJson(data, writer);
        }
    }
}
