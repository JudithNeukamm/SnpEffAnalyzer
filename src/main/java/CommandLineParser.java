//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.Comparator;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class CommandLineParser {
    private static final String CLASS_NAME = "User option parser";
    private final Communicator  c;

    public CommandLineParser(Communicator communicator) {
        this.c = communicator;
    }

    public void parse(String[] args) {
        Options helpOptions = new Options();
        helpOptions.addOption("h", "help", false, "show this help page");
        Options options = new Options();
        options.addOption("h", false, "Shows this help page.");
        options.addOption(Option.builder("i").argName("INPUT").desc("The input file (= output of SNPEff).").required().hasArg().build());
        options.addOption(Option.builder("exclude").argName("EXCLUDE").desc("Positions to exclude (gff file).").hasArg().build());
        options.addOption(Option.builder("include").argName("INCLUDE").desc("Positions to consider (txt file, one position per line).").hasArg().build());
        String header = "SNPEffParser version 1.0\nPlease run with: \njava -jar -Xmx4g /data/software/SNPEffParser.jar [options]\n\nAttention!! It is important that the full path to the files is specified!!\n\n";
        HelpFormatter formatter = new HelpFormatter();
        formatter.setOptionComparator((Comparator)null);
        formatter.setWidth(130);
        DefaultParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption('h')) {
                formatter.printHelp("DamageProfiler", header, options, (String)null, true);
                System.exit(0);
            }

            if (cmd.hasOption('i')) {
                this.c.setInput(cmd.getOptionValue('i'));
            }

            if (cmd.hasOption("exclude")) {
                this.c.setFile_exclude(cmd.getOptionValue("exclude"));
            }

            if (cmd.hasOption("include")) {
                this.c.setFile_include(cmd.getOptionValue("include"));
            }
        } catch (ParseException var8) {
            formatter.printHelp("User option parser", options);
            System.err.println(var8.getMessage());
            System.exit(1);
        }

    }

    public Communicator getCommunicator() {
        return this.c;
    }
}
