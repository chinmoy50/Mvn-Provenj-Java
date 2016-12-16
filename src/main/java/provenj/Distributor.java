package provenj;

import java.io.IOException;
import java.nio.file.Path;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;

// Distributes a file over IPFS
public class Distributor {
    public String publishIPFS(Path path) throws IOException {
        IPFS ipfs = new IPFS("/ip4/127.0.0.1/tcp/5001");
        MerkleNode addResult = ipfs.add(new NamedStreamable.FileWrapper(path.toFile()));
        return (addResult.hash.toString());
    }
}
