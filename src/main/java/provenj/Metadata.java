package provenj;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.UUID;

// Implements in-memory storage for metadata.
public class Metadata implements MetadataIntf {
    protected int    m_bitcoinBlockNumber;
    protected String m_bitcoinBlockHash;
    protected int    m_ethereumBlockNumber;
    protected String m_ethereumBlockHash;
    protected String m_previousIPFSHash;
    protected String m_previousFileHashes;
    protected UUID   m_previousGUID;
    protected UUID   m_guid;
    protected String m_fileName;
    protected String m_fileHashes;
    protected String m_creator;
    public static String[] TAGS = {
            ProvenLib.PROVEN_FILE_NAME,
            ProvenLib.PROVEN_FILE_HASHES,
            ProvenLib.PROVEN_BITCOIN_BLOCK_NUMBER,
            ProvenLib.PROVEN_BITCOIN_BLOCK_HASH,
            ProvenLib.PROVEN_ETHEREUM_BLOCK_NUMBER,
            ProvenLib.PROVEN_ETHEREUM_BLOCK_HASH,
            ProvenLib.PROVEN_PREVIOUS_IPFS_HASH,
            ProvenLib.PROVEN_PREVIOUS_FILE_HASHES,
            ProvenLib.PROVEN_PREVIOUS_GUID,
            ProvenLib.PROVEN_GUID,
            ProvenLib.PROVEN_CREATOR
    };

    public Metadata(){}

    public Metadata(Metadata metadata){
        this.copy(metadata);
    }

    public int    getBitcoinBlockNumber() { return m_bitcoinBlockNumber; }
    public void   setBitcoinBlockNumber(int blockNumber) { m_bitcoinBlockNumber = blockNumber; }
    public void   setBitcoinBlockNumber(String blockNumber) { m_bitcoinBlockNumber = Integer.parseInt(blockNumber); }
    public String getBitcoinBlockHash() { return m_bitcoinBlockHash; }
    public void   setBitcoinBlockHash(String blockHash) { m_bitcoinBlockHash = blockHash; }
    public int    getEthereumBlockNumber() { return m_ethereumBlockNumber; }
    public void   setEthereumBlockNumber(int blockNumber) { m_ethereumBlockNumber = blockNumber; }
    public void   setEthereumBlockNumber(String blockNumber) { m_ethereumBlockNumber = Integer.parseInt(blockNumber); }
    public String getEthereumBlockHash() { return m_ethereumBlockHash; }
    public void   setEthereumBlockHash(String blockHash) { m_ethereumBlockHash = blockHash; }
    public String getPreviousIPFSHash() { return m_previousIPFSHash; }
    public void   setPreviousIPFSHash(String ipfsHash) { m_previousIPFSHash = ipfsHash; }
    public String getPreviousFileHashes() { return m_previousFileHashes; }
    public void   setPreviousFileHashes(String fileHashes) { m_previousFileHashes = fileHashes; }
    public String getFileName() { return m_fileName; }
    public void   setFileName(String fileName) { m_fileName = fileName; }
    public String getFileHashes() { return m_fileHashes; }
    public void   setFileHashes(String fileHashes) { m_fileHashes = fileHashes; }
    public UUID   getPreviousGUID() { return m_previousGUID; }
    public void   setPreviousGUID(UUID previousGUID) { m_previousGUID = previousGUID; }
    public void   setPreviousGUID(String previousGUID) { m_previousGUID = UUID.fromString(previousGUID); }
    public UUID   getGUID() { return m_guid; }
    public void   setGUID(UUID guid) { m_guid = guid; }
    public void   setGUID(String guid) { m_guid = UUID.fromString(guid); }
    public String getCreator() { return m_creator; }
    public void   setCreator(String creator) { m_creator = creator; }

    public Metadata copy(Metadata metadata){
        setBitcoinBlockNumber(metadata.getBitcoinBlockNumber());
        setBitcoinBlockHash(metadata.getBitcoinBlockHash());
        setEthereumBlockNumber(metadata.getEthereumBlockNumber());
        setEthereumBlockHash(metadata.getEthereumBlockHash());
        setPreviousIPFSHash(metadata.getPreviousIPFSHash());
        setPreviousFileHashes(metadata.getPreviousFileHashes());
        setFileName(metadata.getFileName());
        setFileHashes(metadata.getFileHashes());
        setGUID(metadata.getGUID());
        setPreviousGUID(metadata.getPreviousGUID());
        setCreator(metadata.getCreator());
        return this;
    }

    public void setByTag(String tagName, String value) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if( !Arrays.asList(TAGS).contains(tagName)) throw new NoSuchElementException();
        Method method = this.getClass().getDeclaredMethod(String.format("set%s",tagName),String.class);
        method.invoke(this,value);
    }
}
