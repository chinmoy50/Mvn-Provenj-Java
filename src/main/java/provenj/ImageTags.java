package provenj;

import com.adobe.internal.xmp.XMPMeta;
import com.adobe.internal.xmp.XMPException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

// Applies XMP tags to a JPEG image
public class ImageTags extends Metadata {
    private FileInputStream m_inputFile;
    private FileOutputStream m_outputFile;

    public ImageTags(FileInputStream inputFile, FileOutputStream outputFile) {
        m_inputFile = inputFile;
        m_outputFile = outputFile;
    }

    public FileOutputStream getFile() throws XMPException {
        XMPMeta meta = XmpUtil.createXMPMeta();

        meta.setProperty(XmpUtil.PROVEN_NAMESPACE, ProvenLib.PROVEN_BITCOIN_BLOCK_NUMBER, m_bitcoinBlockNumber);
        meta.setProperty(XmpUtil.PROVEN_NAMESPACE, ProvenLib.PROVEN_BITCOIN_BLOCK_HASH, m_bitcoinBlockHash);
        meta.setProperty(XmpUtil.PROVEN_NAMESPACE, ProvenLib.PROVEN_ETHEREUM_BLOCK_NUMBER, m_ethereumBlockNumber);
        meta.setProperty(XmpUtil.PROVEN_NAMESPACE, ProvenLib.PROVEN_ETHEREUM_BLOCK_HASH, m_ethereumBlockHash);
        meta.setProperty(XmpUtil.PROVEN_NAMESPACE, ProvenLib.PROVEN_PREVIOUS_IPFS_HASH, m_previousIPFSHash);
        meta.setProperty(XmpUtil.PROVEN_NAMESPACE, ProvenLib.PROVEN_PREVIOUS_FILE_HASHES, m_previousFileHashes);
        meta.setProperty(XmpUtil.PROVEN_NAMESPACE, ProvenLib.PROVEN_GUID, m_guid.toString());

        XmpUtil.writeXMPMeta(m_inputFile, m_outputFile, meta);

        return m_outputFile;
    }
}
