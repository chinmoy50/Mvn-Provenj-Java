package provenj;

import com.adobe.internal.xmp.XMPMeta;
import com.adobe.internal.xmp.XMPException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;


// Applies XMP tags to a JPEG image
public class ImageTagger extends Metadata {
    public ImageTagger(Metadata metadata) {
        super(metadata);
    }

    public void tagImage(FileInputStream inputFileStream, FileOutputStream outputFileStream) throws XMPException {
        XMPMeta meta = XmpUtil.createXMPMeta();

        meta.setProperty(XmpUtil.PROVEN_NAMESPACE, ProvenLib.PROVEN_BITCOIN_BLOCK_NUMBER,  getBitcoinBlockNumber());
        meta.setProperty(XmpUtil.PROVEN_NAMESPACE, ProvenLib.PROVEN_BITCOIN_BLOCK_HASH,    getBitcoinBlockHash());
        meta.setProperty(XmpUtil.PROVEN_NAMESPACE, ProvenLib.PROVEN_ETHEREUM_BLOCK_NUMBER, getEthereumBlockNumber());
        meta.setProperty(XmpUtil.PROVEN_NAMESPACE, ProvenLib.PROVEN_ETHEREUM_BLOCK_HASH,   getEthereumBlockHash());
        meta.setProperty(XmpUtil.PROVEN_NAMESPACE, ProvenLib.PROVEN_PREVIOUS_IPFS_HASH,    getPreviousIPFSHash());
        meta.setProperty(XmpUtil.PROVEN_NAMESPACE, ProvenLib.PROVEN_PREVIOUS_FILE_HASHES,  getPreviousFileHashes());

        // Include the previous GUID if available
        if(null != getPreviousGUID()) {
            meta.setProperty(XmpUtil.PROVEN_NAMESPACE, ProvenLib.PROVEN_PREVIOUS_GUID, getPreviousGUID().toString());
        }

        // Generate a GUID if none specified
        if (null == getGUID()){
            setGUID(UUID.randomUUID());
        }

        meta.setProperty(XmpUtil.PROVEN_NAMESPACE, ProvenLib.PROVEN_GUID,                  getGUID().toString());
        meta.setProperty(XmpUtil.PROVEN_NAMESPACE, ProvenLib.PROVEN_CREATOR,               getCreator());

        XmpUtil.writeXMPMeta(inputFileStream, outputFileStream, meta);
    }

    public Metadata tagAndClose(FileInputStream inputFileStream, FileOutputStream outputFileStream) throws XMPException, IOException {
        // apply the metadata to the images
        tagImage(inputFileStream, outputFileStream);
        inputFileStream.close();
        outputFileStream.close();
        return this;
    }
}
