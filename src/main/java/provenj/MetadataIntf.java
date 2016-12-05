package provenj;

import java.util.UUID;

// Constants needed across Proven. This is in an interface because it is never instantiated.
public interface MetadataIntf {
    int getBitcoinBlockNumber();
    void setBitcoinBlockNumber(int blockNumber);
    String getBitcoinBlockHash();
    void setBitcoinBlockHash(String blockHash);
    int getEthereumBlockNumber();
    void setEthereumBlockNumber(int blockNumber);
    String getEthereumBlockHash();
    void setEthereumBlockHash(String blockHash);
    String getPreviousIPFSHash();
    void setPreviousIPFSHash(String ipfsHash);
    String getPreviousFileHashes();
    void setPreviousFileHashes(String fileHashes);
    UUID getGUID();
    void setGUID(UUID guid);
    Metadata copy(Metadata metadata);
}
