package b1u3.app.questionize;

import java.util.ArrayList;

public class SimpleAnalyzer extends Analyzer{

    @Override
    public ArrayList<String> analyzeLine(String line, String spliter) {
        ArrayList<String> ret = new ArrayList<>();
        for (String e: line.split(spliter)) {
            ret.add(e.strip());
        }
        return ret;
    }

    // 文全体の解析のメソッド
    /*
     * 最初の行の列に合わせる
     */

    @Override
    public ArrayList<ArrayList<String>> analyzeAll(String all, String spliter) {
        ArrayList<ArrayList<String>> ret = new ArrayList<>();
        int i = 0;
        int initial_line_num = 0;
        for (String line: all.split("\n")) {
            if (i == 0) {
                ArrayList<String> init_line = this.analyzeLine(line.strip(), spliter);
                initial_line_num = init_line.size();
                ret.add(init_line);
            } else {
                ArrayList<String> splited_line = this.analyzeLine(line.strip(), spliter);
                if (splited_line.size() == initial_line_num) {
                    ret.add(splited_line);
                }
            }
            i++;
        }
        return ret;
    }
}
