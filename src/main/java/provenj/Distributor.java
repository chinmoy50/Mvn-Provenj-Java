package provenj;

import java.io.IOException;
import java.nio.file.Path;

import io.ipfs.kotlin.IPFS;

// Distributes a file over IPFS
public class Distributor {
    public String publishIPFS(Path path) throws IOException {
        return new IPFS().getAdd().file(path.toFile()).getHash();
    }
}
