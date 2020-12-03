//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ReadTextFile {
    public ReadTextFile() {
    }

    public Set<Integer> getPositionsToInclude(String text_file) {
        HashSet list = new HashSet();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(text_file));

            for(String line = reader.readLine(); line != null && !line.equals(""); line = reader.readLine()) {
                list.add(Integer.parseInt(line.trim()));
            }

            reader.close();
        } catch (IOException var5) {
            var5.printStackTrace();
        }

        return list;
    }
}
