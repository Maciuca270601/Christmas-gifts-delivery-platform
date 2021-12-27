package fileio;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    private final FileWriter file;

    public Writer (final String path) throws IOException {
        this.file = new FileWriter(path);
    }

    /*
     * Transforms the output in a JSONObject
     */
    public JSONObject writeFile (final String message) throws IOException {
        JSONObject object = new JSONObject();
        object.put(" ", message);
        return object;
    }

    /*
     * Writes to the file and closes it
     */
    public void closeJson(final JSONArray array) {
        try {
            file.write(array.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
