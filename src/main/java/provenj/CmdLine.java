package provenj;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

// Command-line interface for Provenj
public class CmdLine {
    public static void main( String[] args ) {
        // create the parser
        CommandLineParser parser = new DefaultParser();
        Options options = new Options();
        options.addOption(Option.builder("D")
                .hasArgs()
                .valueSeparator('=')
                .build());

        try {
            // parse the command line arguments
            CommandLine line = parser.parse( options, args );

            System.out.println(line.toString());
        }
        catch( ParseException exp ) {
            // oops, something went wrong
            System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );
        }
    }
}
