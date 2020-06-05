package b1u3.app.questionize;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.file.Paths;
import java.util.Iterator;
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
        } catch (IOException e) {
            System.err.println("Creating Questionizer is failed");
            System.exit(0);
        }
        Iterator<Question> qit = qn.iterator();
        while(qit.hasNext()) {
            Question q = qit.next();
            System.out.println(q.getStatement());
            q.shuffle();
            for(String choice: q.getChoices()) {
                System.out.println(choice);
            }
        }
    }
}
