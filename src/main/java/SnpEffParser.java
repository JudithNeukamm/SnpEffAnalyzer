//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class SnpEffParser {
    private String header_info;
    private ArrayList<Integer> snpPos;
    private int index_info;
    private Map<Integer, List<String>> result_treemap;
    private HashMap<Integer, List<String>> map_line_pos = new HashMap();

    public SnpEffParser() {
    }

    public String parse(String snpEffOutputFile, Set<Integer> positions_to_exclude, Set<Integer> positions_to_include) {
        StringBuffer result = new StringBuffer();
        this.parseSNPEffOutput(snpEffOutputFile, positions_to_exclude, positions_to_include);
        String header_final = "";
        List<String> header_final_tmp = new ArrayList();
        String[] header_all = ((String)((List)this.result_treemap.get(-2)).get(0)).split("\t", -1);
        String[] var8 = header_all;
        int var9 = header_all.length;

        for(int var10 = 0; var10 < var9; ++var10) {
            String h = var8[var10];
            if (h.equals("INFO")) {
                header_final_tmp.add((this.result_treemap.get(-1)).get(0));
            } else {
                header_final_tmp.add(h);
            }
        }

        Iterator var17;
        String h_fin;
        for(var17 = header_final_tmp.iterator(); var17.hasNext(); header_final = header_final + h_fin + "\t") {
            h_fin = (String)var17.next();
        }

        result.append(header_final + "\n");
        this.snpPos = new ArrayList(this.result_treemap.keySet());
        var17 = this.snpPos.iterator();

        while(true) {
            Integer position;
            do {
                do {
                    if (!var17.hasNext()) {
                        return result.toString();
                    }

                    position = (Integer)var17.next();
                } while(position == -1);
            } while(position == -2);

            List<String> info_per_pos = (List)this.result_treemap.get(position);
            List<String> entire_line = (List)this.map_line_pos.get(position);

            for(int i = 0; i < info_per_pos.size(); ++i) {
                String info = (String)info_per_pos.get(i);
                info = info.substring(0, info.length() - 1);
                entire_line.set(this.index_info, info);
                String final_line = "";

                String s;
                for(Iterator var15 = entire_line.iterator(); var15.hasNext(); final_line = final_line + s + "\t") {
                    s = (String)var15.next();
                }

                result.append(final_line + "\n");
            }
        }
    }

    private void parseSNPEffOutput(String snpEffOutputFile, Set<Integer> positions_to_exclude, Set<Integer> positions_to_include) {
        this.result_treemap = new TreeMap();

        try {
            BufferedReader br = new BufferedReader(new FileReader(snpEffOutputFile));
            String line = "";

            while(true) {
                String[] header_entire;
                int var34;
                label126:
                do {
                    while(true) {
                        while((line = br.readLine()) != null) {
                            int i;
                            if (line.startsWith("#")) {
                                if (line.contains("ID=ANN")) {
                                    header_entire = line.split(":", -1);
                                    continue label126;
                                }

                                if (line.startsWith("#CHR")) {
                                    header_entire = line.split("\t", -1);
                                    StringBuffer headerLineEnt = new StringBuffer();
                                    String[] var30 = header_entire;
                                    i = header_entire.length;

                                    for(var34 = 0; var34 < i; ++var34) {
                                        String column = var30[var34];
                                        column = column.replace("#", "");
                                        headerLineEnt.append(column);
                                        headerLineEnt.append("\t");
                                    }

                                    List<String> head = new LinkedList();
                                    head.add(headerLineEnt.toString().trim());
                                    this.result_treemap.put(-2, head);
                                }
                            } else {
                                header_entire = line.split("\t", -1);
                                Integer pos = Integer.parseInt(header_entire[1]);
                                List<String> line_final_tmp = new ArrayList();

                                for(i = 0; i < header_entire.length; ++i) {
                                    String s = header_entire[i];
                                    line_final_tmp.add(s);
                                    if (s.startsWith("AC=")) {
                                        this.index_info = i;
                                        String[] slitted2 = s.split(";", -1);
                                        String[] var12 = slitted2;
                                        int var13 = slitted2.length;

                                        for(int var14 = 0; var14 < var13; ++var14) {
                                            String s2 = var12[var14];
                                            if (s2.startsWith("ANN")) {
                                                s2 = s2.substring(4);
                                                String[] splittedInfo = s2.split(",", -1);
                                                String[] var17 = splittedInfo;
                                                int var18 = splittedInfo.length;

                                                for(int var19 = 0; var19 < var18; ++var19) {
                                                    String s_info = var17[var19];
                                                    String[] splittedData = s_info.split("\\|", -1);
                                                    StringBuffer dataLine = new StringBuffer();
                                                    String[] var23 = splittedData;
                                                    int var24 = splittedData.length;

                                                    for(int var25 = 0; var25 < var24; ++var25) {
                                                        String data = var23[var25];
                                                        dataLine.append(data);
                                                        dataLine.append("\t");
                                                    }

                                                    Object dat;
                                                    if (positions_to_exclude == null && positions_to_include == null) {
                                                        dat = new LinkedList();
                                                        if (this.result_treemap.containsKey(pos)) {
                                                            dat = (List)this.result_treemap.get(pos);
                                                        }

                                                        ((List)dat).add(dataLine.toString());
                                                        this.result_treemap.put(pos, (List<String>) dat);
                                                    } else if (positions_to_exclude != null) {
                                                        if (!positions_to_exclude.contains(pos)) {
                                                            dat = new LinkedList();
                                                            if (this.result_treemap.containsKey(pos)) {
                                                                dat = (List)this.result_treemap.get(pos);
                                                            }

                                                            ((List)dat).add(dataLine.toString());
                                                            this.result_treemap.put(pos, (List<String>) dat);
                                                        }
                                                    } else if (positions_to_include != null && positions_to_include.contains(pos)) {
                                                        dat = new LinkedList();
                                                        if (this.result_treemap.containsKey(pos)) {
                                                            dat = (List)this.result_treemap.get(pos);
                                                        }

                                                        ((List)dat).add(dataLine.toString());
                                                        this.result_treemap.put(pos, (List<String>) dat);
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    if (positions_to_exclude == null) {
                                        this.map_line_pos.put(pos, line_final_tmp);
                                    } else if (!positions_to_exclude.contains(pos)) {
                                        this.map_line_pos.put(pos, line_final_tmp);
                                    }
                                }
                            }
                        }

                        return;
                    }
                } while(header_entire.length != 2);

                this.header_info = header_entire[1].trim();
                this.header_info = this.header_info.replaceAll("'", "");
                this.header_info = this.header_info.replaceAll(">", "");
                this.header_info = this.header_info.replaceAll(" ", "");
                String[] splittedHeader = this.header_info.split("\\|", -1);
                StringBuffer headerLine = new StringBuffer();
                String[] var33 = splittedHeader;
                var34 = splittedHeader.length;

                for(int var37 = 0; var37 < var34; ++var37) {
                    String column = var33[var37];
                    headerLine.append(column);
                    headerLine.append("\t");
                }

                List<String> head = new LinkedList();
                head.add(headerLine.toString().trim());
                this.result_treemap.put(-1, head);
            }
        } catch (IOException var27) {
            var27.printStackTrace();
        }
    }
}
