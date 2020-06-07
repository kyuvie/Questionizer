package b1u3.app.questionize;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.file.Paths;
import java.util.Iterator;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {
    static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        ArgumentParser ap = ArgumentParsers.newFor("B1u3 Questionizer").build().defaultHelp(true).description("give you questions which have three choices");
        ap.addArgument("-f", "--file").nargs(1).help("Questions and answers table file splited by \"-\"");
        Namespace ns = null;
        try {
            ns = ap.parseArgs(args);
        } catch (ArgumentParserException e) {
            ap.handleError(e);
            System.exit(0);
        }
        Questionizer qn = null;
        try {
            qn = new Questionizer(Paths.get(ns.<String> getList("file").get(0)));
        } catch (Exception e) {
            ap.printHelp();
            System.exit(0);
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Iterator<Question> qit = qn.iterator();
        Integer all = 0;
        Integer coverage = 0;
        while(qit.hasNext()) {
            Question q = qit.next();
            all++;
            System.out.println(q.getStatement());
            q.shuffle();
            int i = 0;
            for(String choice: q.getChoices()) {
                System.out.println(i + ":" + choice);
                i++;
            }
            Integer line = null;
            while (true) {
                try {
                    System.out.print("Your answer:");
                    line = Integer.valueOf(reader.readLine());
                    if (!(0 <= line && line <= q.getChoices().size()-1)) {
                        throw new NumberFormatException("Out of choice size!!");
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Wrong format: Please input a number from 0 to " + (q.getChoices().size()-1));
                    continue;
                } catch (IOException e) {
                    System.err.println("Input stream error");
                    System.exit(0);
                }
            }
            if (qn.ans(q, line)) {
                System.out.println("OK");
                coverage++;
            } else {
                System.out.println("NG");
            }
        }
        // Out of Questions loop
        System.out.println("Coverage: " + coverage + "/" + all);
    }
}
