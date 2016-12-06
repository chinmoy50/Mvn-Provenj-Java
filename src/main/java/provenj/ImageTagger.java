package provenj;

import com.adobe.internal.xmp.XMPMeta;
import com.adobe.internal.xmp.XMPException;
import java.io.FileInputStream;
import java.io.FileOutputStream;


// Applies XMP tags to a JPEG image
public class ImageTagger extends Metadata {
    public ImageTagger(Metadata metadata) {
        super(metadata);
    }

    public void tagImage(FileInputStream inputFile, FileOutputStream outputFile) throws XMPException {
        XMPMeta meta = XmpUtil.createXMPMeta();

        meta.setProperty(XmpUtil.PROVEN_NAMESPACE, ProvenLib.PROVEN_BITCOIN_BLOCK_NUMBER,  getBitcoinBlockNumber());
        meta.setProperty(XmpUtil.PROVEN_NAMESPACE, ProvenLib.PROVEN_BITCOIN_BLOCK_HASH,    getBitcoinBlockHash());
        meta.setProperty(XmpUtil.PROVEN_NAMESPACE, ProvenLib.PROVEN_ETHEREUM_BLOCK_NUMBER, getEthereumBlockNumber());
        meta.setProperty(XmpUtil.PROVEN_NAMESPACE, ProvenLib.PROVEN_ETHEREUM_BLOCK_HASH,   getEthereumBlockHash());
        meta.setProperty(XmpUtil.PROVEN_NAMESPACE, ProvenLib.PROVEN_PREVIOUS_IPFS_HASH,    getPreviousIPFSHash());
        meta.setProperty(XmpUtil.PROVEN_NAMESPACE, ProvenLib.PROVEN_PREVIOUS_FILE_HASHES,  getPreviousFileHashes());
        meta.setProperty(XmpUtil.PROVEN_NAMESPACE, ProvenLib.PROVEN_GUID,                  getGUID().toString());

        XmpUtil.writeXMPMeta(inputFile, outputFile, meta);
    }
}
