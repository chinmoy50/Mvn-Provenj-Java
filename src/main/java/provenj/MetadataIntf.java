package provenj;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

// Metadata API for hashes from public blockchains, previous submissions, and identifiers.
public interface MetadataIntf {
    int    getBitcoinBlockNumber();
    void   setBitcoinBlockNumber(int blockNumber);
    void   setBitcoinBlockNumber(String blockNumber);
    String getBitcoinBlockHash();
    void   setBitcoinBlockHash(String blockHash);
    int    getEthereumBlockNumber();
    void   setEthereumBlockNumber(int blockNumber);
    void   setEthereumBlockNumber(String blockNumber);
    String getEthereumBlockHash();
    void   setEthereumBlockHash(String blockHash);
    String getPreviousIPFSHash();
    void   setPreviousIPFSHash(String ipfsHash);
    String getPreviousFileHashes();
    void   setPreviousFileHashes(String fileHashes);
    UUID   getPreviousGUID();
    void   setPreviousGUID(UUID guid);
    void   setPreviousGUID(String guid);
    String getFileName();
    void   setFileName(String fileName);
    String getFileHashes();
    void   setFileHashes(String fileHashes);
    UUID   getGUID();
    void   setGUID(UUID guid);
    void   setGUID(String guid);
    Metadata copy(Metadata metadata);
    public void setByTag(String tagName, String value) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
}
