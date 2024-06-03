/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ev.eval_course_a_pied.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import ev.eval_course_a_pied.entity.user.Role;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class JsonUtility {

    public static <T> T parseJson(String path, Class<?> objectClass) throws Exception{
        // System.out.println(new FileReader(path));
        JsonReader reader = new JsonReader(new BufferedReader(new FileReader(path)));
        Object temp = new Gson().fromJson(reader, objectClass);
        return (T)temp;
    }
    public static List<Menu> parseJson(String path, List<Role> roleName) throws Exception {
        JsonReader reader = new JsonReader(new BufferedReader(new FileReader(path)));
        JsonObject jsonObject = new Gson().fromJson(reader, JsonObject.class);
        List<JsonArray> roleJsonArray = new ArrayList<>();
        for (int i = 0; i < roleName.size(); i++) {
            roleJsonArray.add(jsonObject.getAsJsonArray(roleName.get(i).getRoleName()));
        }

        List<Menu> menuList = new ArrayList<>();
        for(JsonArray jsonArray:roleJsonArray){
            for (JsonElement element : jsonArray) {
                JsonObject menuJson = element.getAsJsonObject();
                String icon = menuJson.get("icon").getAsString();
                String menuName = menuJson.get("menuName").getAsString();
                String menuPath = menuJson.get("menuPath").getAsString();
                menuList.add(new Menu(icon, menuName,menuPath));
            }
        }
        return menuList;
    }
    public static <T> T parseStringToGson(String json,Class <?> objectClass){
        Object temp = new Gson().fromJson(json, objectClass);
        return (T)temp;
    }

    public static String encodeJson(Object object) throws Exception{
        return new Gson().toJson(object);
    }
}
