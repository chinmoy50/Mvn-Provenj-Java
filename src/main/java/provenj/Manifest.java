package provenj;

import java.util.UUID;
import org.json.simple.JSONObject;

public class Manifest {
    private String m_fileName;
    private int    m_bitcoinBlockNumber;
    private String m_bitcoinBlockHash;
    private int    m_ethereumBlockNumber;
    private String m_ethereumBlockHash;
    private String m_previousIPFSHash;
    private String m_previousFileHashes;
    private String m_fileHashes;
    private UUID   m_guid;

    public void addFile(String fileName) {
        m_fileName = fileName;
    }

    public String getFileName() {
        return m_fileName;
    }

    public void setBitcoinBlockNumber(int blockNumber) {
        m_bitcoinBlockNumber = blockNumber;
    }

    public int getBitcoinBlockNumber() {
        return m_bitcoinBlockNumber;
    }

    public void setBitcoinBlockHash(String blockHash) {
        m_bitcoinBlockHash = blockHash;
    }

    public String getBitcoinBlockHash() {
        return m_bitcoinBlockHash;
    }

    public void setEthereumBlockNumber(int blockNumber) {
        m_ethereumBlockNumber = blockNumber;
    }

    public int getEthereumBlockNumber() {
        return m_ethereumBlockNumber;
    }

    public void setEthereumBlockHash(String blockHash) {
        m_ethereumBlockHash = blockHash;
    }

    public String getEthereumBlockHash() {
        return m_ethereumBlockHash;
    }

    public void setPreviousIPFSHash(String ipfsHash) {
        m_previousIPFSHash = ipfsHash;
    }

    public String getPreviousIPFSHash() {
        return m_previousIPFSHash;
    }

    public void setPreviousFileHashes(String fileHashes) {
        m_previousFileHashes = fileHashes;
    }

    public String getPreviousFileHashes() {
        return m_previousFileHashes;
    }

    public void setFileHashes(String fileHashes) {
        m_fileHashes = fileHashes;
    }

    public String getFileHashes() {
        return m_fileHashes;
    }

    public void setGUID(UUID guid) {
        m_guid = guid;
    }

    public UUID getGUID() {
        return m_guid;
    }

    @SuppressWarnings("unchecked") // JSONObject doesn't support parameters to make it generic
    public JSONObject get(){
       JSONObject result = new JSONObject();
       result.put(ProvenLib.PROVEN_FILE_NAME,             getFileName());
       result.put(ProvenLib.PROVEN_FILE_HASHES,           getFileHashes());
       result.put(ProvenLib.PROVEN_BITCOIN_BLOCK_NUMBER,  getBitcoinBlockNumber());
       result.put(ProvenLib.PROVEN_BITCOIN_BLOCK_HASH,    getBitcoinBlockHash());
       result.put(ProvenLib.PROVEN_ETHEREUM_BLOCK_NUMBER, getEthereumBlockNumber());
       result.put(ProvenLib.PROVEN_ETHEREUM_BLOCK_HASH,   getEthereumBlockHash());
       result.put(ProvenLib.PROVEN_PREVIOUS_IPFS_HASH,    getPreviousIPFSHash());
       result.put(ProvenLib.PROVEN_PREVIOUS_FILE_HASHES,  getPreviousFileHashes());
       result.put(ProvenLib.PROVEN_GUID,                  getGUID().toString());
       return result;
    }
}
