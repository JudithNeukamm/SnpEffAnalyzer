//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

public class ReadGFF {
    public ReadGFF() {
    }

    public Set<Integer> getPositionsToExclude(String posToExcludeFile) throws Exception {
        File infile = new File(posToExcludeFile);
        Set<Integer> res = new HashSet();
        if (!infile.exists()) {
            System.err.println("No positions to exclude provided! All positions will be used!");
            return res;
        } else {
            BufferedReader br = new BufferedReader(new FileReader(infile));

            for(String line = br.readLine(); line != null; line = br.readLine()) {
                line = line.trim();
                if (line.length() != 0 && !line.startsWith("#")) {
                    String[] cells = line.split("[\\t]");
                    int start = Integer.parseInt(cells[3]);
                    int end = Integer.parseInt(cells[4]);

                    for(int i = start; i <= end; ++i) {
                        res.add(i);
                    }
                }
            }

            br.close();
            return res;
        }
    }
}
