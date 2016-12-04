package provenj;

import java.util.UUID;

// Constants needed across Proven. This is in an interface because it is never instantiated.
public interface MetadataIntf {
    public int getBitcoinBlockNumber();
    public void setBitcoinBlockNumber(int blockNumber);
    public String getBitcoinBlockHash();
    public void setBitcoinBlockHash(String blockHash);
    public int getEthereumBlockNumber();
    public void setEthereumBlockNumber(int blockNumber);
    public String getEthereumBlockHash();
    public void setEthereumBlockHash(String blockHash);
    public String getPreviousIPFSHash();
    public void setPreviousIPFSHash(String ipfsHash);
    public String getPreviousFileHashes();
    public void setPreviousFileHashes(String fileHashes);
    public UUID getGUID();
    public void setGUID(UUID guid);
}
