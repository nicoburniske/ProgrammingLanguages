import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) throws IOException, ParseException {
        //args[0] = count/context/replace
        //args[1] = filePath

        Object obj = new JSONParser().parse(new FileReader(args[1]));
        S s = convertToS(obj);
        if ("count".equals(args[0])) {
            System.out.println(s.count());
        } else if ("replace".equals(args[0])) {
            System.out.println(s.replace().toJSON());
        } else if ("context".equals(args[0])) {
            System.out.println(s.context().toJSON());
        } else {
            throw new IllegalArgumentException("Error: an illegal function was requested");
        }
    }

    public static S convertToS(Object obj) {
        if (obj instanceof String) {
            String str = (String) obj;
            return new SName(str);
        } else if (obj instanceof JSONArray) {
            JSONArray array = (JSONArray) obj;
            List<S> sArray = new ArrayList();
            for (int ii = 0; ii < array.size(); ii++) {
                sArray.add(convertToS(array.get(ii)));
            }
            return new SArray(sArray);
        } else {
            throw new IllegalArgumentException("Error: JSON was not a S");
        }
    }
}
