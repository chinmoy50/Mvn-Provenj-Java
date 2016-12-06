package provenj;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// Creates an enclosure which is a temporary directory that contains all assets to be submitted
public class Enclosure {
    protected Path m_path;

    protected void init() throws IOException {
        m_path = Files.createTempDirectory(ProvenLib.PROVEN_PREFIX);
        Files.createDirectory(getPath(ProvenLib.PROVEN_CONTENT_DIRECTORY));
    }

    public Enclosure() throws IOException {
        init();
    }

    public Path getPath(String element){
        return Paths.get(m_path.toString(), element);
    }
}
