package vttp2023.batch3.csf.assessment.cnserver;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

import vttp2023.batch3.csf.assessment.cnserver.models.News;
import vttp2023.batch3.csf.assessment.cnserver.models.TagCount;

public class Utils {

    public static JsonObject toJSON(String json){
        JsonReader r = (JsonReader) Json.createReader(new StringReader(json));
        return r.readObject();
    }

    public static News toNewsObject(Document doc) {
        // JsonObject o = toJSON(news);
        News n = new News();
        n.setTitle(doc.getString("title"));
        n.setDescription(doc.getString("description"));
        List<String> tags = new ArrayList<>();
		tags.add(doc.getString("tags"));
        n.setTags(tags);
        return n;
    }

    public static News toNewsObject(String news) {
        JsonObject o = toJSON(news);
        News n = new News();
        n.setTitle(o.getString("title"));
        n.setDescription(o.getString("description"));
        List<String> tags = new ArrayList<>();
		tags.add(o.getString("tags"));
        n.setTags(tags);
        return n;
    }

    public static TagCount toTagCountObj(Document doc) {
        TagCount tagCount = new TagCount(doc.getString("tag"), doc.getInteger("count"));
        return tagCount;
    }
}
