package provenj;

import com.adobe.internal.xmp.XMPException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;

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

            if (args.length < 1) throw new ParseException("You must at least provide the path to the file.");

            // parse the command line arguments
            CommandLine line = parser.parse( options, args );

            Metadata metadata = new Metadata();
            for(int i = 0; i < 2*line.getOptions().length; i += 2) {
                metadata.setByTag(line.getOptionValues("D")[i],line.getOptionValues("D")[i+1]);
            }

            Enclosure enclosure = new Enclosure();
            metadata = enclosure.fillEnclosure(Paths.get(args[0]),metadata);
            System.out.println(enclosure.getPath());
        }
        catch( ParseException exp ) {
            // oops, something went wrong
            System.err.println( "Parsing failed. Reason: " + exp.getMessage() );
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (XMPException e) {
            e.printStackTrace();
        }
    }
}
