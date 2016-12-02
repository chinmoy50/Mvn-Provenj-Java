package provenj;

import java.util.UUID;
import java.io.FileInputStream;
import java.io.FileOutputStream;

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

    public FileOutputStream getFile() {
	try {
            m_outputFile.getFD().sync();
	} catch (Exception e) { }
        return m_outputFile;
    }
}
