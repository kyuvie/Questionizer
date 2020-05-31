package b1u3.app.questionize;

import java.util.ArrayList;

public class LineAnalyzer {
    static private LineAnalyzer an = null;
    private LineAnalyzer() {
    }

    public ArrayList<String> analyzeLine(String line, String spliter) {
        ArrayList<String> ret = new ArrayList<>();
        for (String e: line.split(spliter)) {
            ret.add(e.strip());
        }
        return ret;
    }

    // 文全体の解析のメソッド
    public ArrayList<ArrayList<String>> analyzeAll(String all, String spliter) {
        ArrayList<ArrayList<String>> ret = new ArrayList<>();
        for (String line: all.split("\n")) {
            ret.add(this.analyzeLine(line.strip(), spliter));
        }
        return ret;
    }

    static public LineAnalyzer getLineAnalyzer() {
        if (an == null) {
            an = new LineAnalyzer();
        }
        return an;
    }
}
