package provenj;

import java.io.File;
import java.io.IOException;

import io.ipfs.kotlin.IPFS;

// Distributes a file over IPFS
public class Distributor {
    public String publishIPFS(String path) throws IOException {
        File file = new File(path);
        return new IPFS().getAdd().file(file, file.getName()).getHash();
    }
}
