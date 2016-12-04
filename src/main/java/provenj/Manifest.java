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

    public void setGUID(UUID guid) {
	m_guid = guid;
    }

    public UUID getGUID() {
	return m_guid;
    }

    @SuppressWarnings("unchecked") // JSONObject doesn't support parameters to make it generic
    public JSONObject get(){
       JSONObject result = new JSONObject();
       result.put("FileName", getFileName());
       result.put("BitcoinBlockNumber", getBitcoinBlockNumber());
       result.put("BitcoinBlockHash", getBitcoinBlockHash());
       result.put("EthereumBlockNumber", getEthereumBlockNumber());
       result.put("EthereumBlockHash", getEthereumBlockHash());
       result.put("PreviousIPFSHash", getPreviousIPFSHash());
       result.put("PreviousFileHashes", getPreviousFileHashes());
       result.put("GUID", getGUID().toString());
       return result;
    }
}
