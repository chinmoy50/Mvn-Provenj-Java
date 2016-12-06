package provenj;

import java.util.UUID;

// Metadata API for hashes from public blockchains, previous submissions, and identifiers.
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
    public String getFileName();
    public void   setFileName(String fileName);
    public String getFileHashes();
    public void   setFileHashes(String fileHashes);
    UUID getGUID();
    void setGUID(UUID guid);
    Metadata copy(Metadata metadata);
}
