package provenj;

import java.util.UUID;
import org.json.simple.JSONObject;

public class Manifest {
    private String m_filename;
    private int    m_bitcoinBlockNumber;
    private String m_bitcoinBlockHash;
    private int    m_ethereumBlockNumber;
    private String m_ethereumBlockHash;
    private String m_previousIPFSHash;
    private String m_previousFileHashes;
    private UUID   m_guid;

    public void addFile(String filename) {
	m_filename = filename;
    }

    public void setBitcoinBlockNumber(int blockNumber) {
	m_bitcoinBlockNumber = blockNumber;
    }

    public void setBitcoinBlockHash(String blockHash) {
	m_bitcoinBlockHash = blockHash;
    }

    public void setEthereumBlockNumber(int blockNumber) {
	m_ethereumBlockNumber = blockNumber;
    }

    public void setEthereumBlockHash(String blockHash) {
	m_ethereumBlockHash = blockHash;
    }

    public void setPreviousIPFSHash(String ipfsHash) {
	m_previousIPFSHash = ipfsHash;
    }

    public void setPreviousFileHashes(String fileHashes) {
	m_previousFileHashes = fileHashes;
    }

    public void setGUID(UUID guid) {
	m_guid = guid;
    }

    @SuppressWarnings("unchecked") // JSONObject doesn't support parameters to make it generic
    public JSONObject get(){
       JSONObject result = new JSONObject();
       result.put("FileName", m_filename);
       result.put("BitcoinBlockNumber", m_bitcoinBlockNumber);
       result.put("BitcoinBlockHash", m_bitcoinBlockHash);
       result.put("EthereumBlockNumber", m_ethereumBlockNumber);
       result.put("EthereumBlockHash", m_ethereumBlockHash);
       result.put("PreviousIPFSHash", m_previousIPFSHash);
       result.put("PreviousFileHashes", m_previousFileHashes);
       result.put("GUID", m_guid.toString());
       return result;
    }
}
