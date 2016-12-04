package provenj;

import com.adobe.internal.xmp.XMPMeta;
import com.adobe.internal.xmp.XMPException;
import java.util.UUID;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageTags {
    private FileInputStream m_inputFile;
    private FileOutputStream m_outputFile;
    private int    m_bitcoinBlockNumber;
    private String m_bitcoinBlockHash;
    private int    m_ethereumBlockNumber;
    private String m_ethereumBlockHash;
    private String m_previousIPFSHash;
    private String m_previousFileHashes;
    private UUID   m_guid;

    public ImageTags(FileInputStream inputFile, FileOutputStream outputFile) {
        m_inputFile = inputFile;
        m_outputFile = outputFile;
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

    public FileOutputStream getFile() throws IOException, XMPException {
        try {
            XMPMeta meta = XmpUtil.createXMPMeta();

            meta.setProperty(XmpUtil.PROVEN_NAMESPACE, ProvenLib.PROVEN_BITCOIN_BLOCK_NUMBER, m_bitcoinBlockNumber);
            meta.setProperty(XmpUtil.PROVEN_NAMESPACE, ProvenLib.PROVEN_BITCOIN_BLOCK_HASH, m_bitcoinBlockHash);
            meta.setProperty(XmpUtil.PROVEN_NAMESPACE, ProvenLib.PROVEN_ETHEREUM_BLOCK_NUMBER, m_ethereumBlockNumber);
            meta.setProperty(XmpUtil.PROVEN_NAMESPACE, ProvenLib.PROVEN_ETHEREUM_BLOCK_HASH, m_ethereumBlockHash);
            meta.setProperty(XmpUtil.PROVEN_NAMESPACE, ProvenLib.PROVEN_PREVIOUS_IPFS_HASH, m_previousIPFSHash);
            meta.setProperty(XmpUtil.PROVEN_NAMESPACE, ProvenLib.PROVEN_PREVIOUS_FILE_HASHES, m_previousFileHashes);
            meta.setProperty(XmpUtil.PROVEN_NAMESPACE, ProvenLib.PROVEN_GUID, m_guid.toString());

            XmpUtil.writeXMPMeta(m_inputFile, m_outputFile, meta);
        } catch (Exception e) {
            throw e;
        }
        return m_outputFile;
    }
}
