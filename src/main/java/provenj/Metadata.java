package provenj;

import java.util.UUID;

// Implements in-memory storage for metadata.
public class Metadata implements MetadataIntf {
    protected int    m_bitcoinBlockNumber;
    protected String m_bitcoinBlockHash;
    protected int    m_ethereumBlockNumber;
    protected String m_ethereumBlockHash;
    protected String m_previousIPFSHash;
    protected String m_previousFileHashes;
    protected UUID   m_guid;

    public int    getBitcoinBlockNumber() { return m_bitcoinBlockNumber; }
    public void   setBitcoinBlockNumber(int blockNumber) { m_bitcoinBlockNumber = blockNumber; }
    public String getBitcoinBlockHash() { return m_bitcoinBlockHash; }
    public void   setBitcoinBlockHash(String blockHash) { m_bitcoinBlockHash = blockHash; }
    public int    getEthereumBlockNumber() { return m_ethereumBlockNumber; }
    public void   setEthereumBlockNumber(int blockNumber) { m_ethereumBlockNumber = blockNumber; }
    public String getEthereumBlockHash() { return m_ethereumBlockHash; }
    public void   setEthereumBlockHash(String blockHash) { m_ethereumBlockHash = blockHash; }
    public String getPreviousIPFSHash() { return m_previousIPFSHash; }
    public void   setPreviousIPFSHash(String ipfsHash) { m_previousIPFSHash = ipfsHash; }
    public String getPreviousFileHashes() { return m_previousFileHashes; }
    public void   setPreviousFileHashes(String fileHashes) { m_previousFileHashes = fileHashes; }
    public UUID   getGUID() { return m_guid; }
    public void   setGUID(UUID guid) { m_guid = guid; }
    public Metadata copy(Metadata metadata){
        setBitcoinBlockNumber(metadata.getBitcoinBlockNumber());
        setBitcoinBlockHash(metadata.getBitcoinBlockHash());
        setEthereumBlockNumber(metadata.getEthereumBlockNumber());
        setEthereumBlockHash(metadata.getEthereumBlockHash());
        setPreviousIPFSHash(metadata.getPreviousIPFSHash());
        setPreviousFileHashes(metadata.getPreviousFileHashes());
        setGUID(metadata.getGUID());
        return this;
    }
}
