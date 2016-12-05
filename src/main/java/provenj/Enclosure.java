package provenj;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Create an enclosure which is a temporary directory that contains all assets to be submitted
 */

public class Enclosure {
    protected Path m_path;

    public Enclosure() throws IOException {
        m_path = Files.createTempDirectory(ProvenLib.PROVEN_PREFIX);
        Files.createDirectory(getPath(ProvenLib.PROVEN_PAYLOAD_DIRECTORY));
    }

    public Path getPath(String element){
        return Paths.get(m_path.toString(), element);
    }
}
