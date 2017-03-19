package stackoverflow.java;




import java.util.Iterator;

/**
 * Created by sinsandi on 2/9/2017.
 */
public class JsonObjectTest {


    public static void main(String[] args) throws JSONException {
            String str = "{\r\n" +
                    "       \"videos\": {\r\n" +
                    "           \"240p\": \"https://frr/file/189ad9b51314e7c490/27f58b1241c53b04/240.mp4\",\r\n" +
                    "           \"360p\": \"https://frr/file/189ad9b51314e7c490/27f58b1241c53b04/360.mp4\",\r\n" +
                    "           \"480p\": \"https://frr/file/189ad9b51314e7c490/27f58b1241c53b04/480.mp4\",\r\n" +
                    "           \"720p\": \"https://frr/file/189ad9b51314e7c490/27f58b1241c53b04/720.mp4\",\r\n" +
                    "           \"1080p\": null,\r\n" +
                    "           \"2k\": null,\r\n" +
                    "           \"4k\": null,\r\n" +
                    "           \"8k\": null,\r\n" +
                    "           \"source\": null\r\n" +
                    "       }}";
            JSONObject json_obj = new JSONObject(str);
            JSONObject allKeys_json=    json_obj.getJSONObject("videos");
            Iterator<String> keys = allKeys_json.keys();
            while( keys.hasNext() ) {
                String keyanme = (String)keys.next();
                String keyvalue = allKeys_json.getString(keyanme);
                if(keyvalue.contains("null"))
                {
                    System.out.println(keyanme+"\t"+keyvalue);
                    json_obj.remove(keyanme);
                }
            }
            System.out.println(allKeys_json);
        }

}
