package util;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

public class DuploMap {
    public static Map<String, String> convert(String json) {
	    	Gson gson = new Gson();
	    	Type stringStringMap = new TypeToken<Map<String, String>>(){}.getType();
	    	Map<String, String> map = gson.fromJson(json, stringStringMap);
	    return map;
    }
}
