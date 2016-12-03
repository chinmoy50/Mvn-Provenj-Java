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
       result.put(ProvenLib.PROVEN_FILE_NAME, m_filename);
       result.put(ProvenLib.PROVEN_BITCOIN_BLOCK_NUMBER, m_bitcoinBlockNumber);
       result.put(ProvenLib.PROVEN_BITCOIN_BLOCK_HASH, m_bitcoinBlockHash);
       result.put(ProvenLib.PROVEN_ETHEREUM_BLOCK_NUMBER, m_ethereumBlockNumber);
       result.put(ProvenLib.PROVEN_ETHEREUM_BLOCK_HASH, m_ethereumBlockHash);
       result.put(ProvenLib.PROVEN_PREVIOUS_IPFS_HASH, m_previousIPFSHash);
       result.put(ProvenLib.PROVEN_PREVIOUS_FILE_HASHES, m_previousFileHashes);
       result.put(ProvenLib.PROVEN_GUID, m_guid.toString());
       return result;
    }
}
