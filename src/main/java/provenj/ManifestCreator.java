package provenj;

import org.json.JSONObject;

// Creates a manifest.json file to be included in the Proven enclosure.
public class ManifestCreator extends Metadata {
    ManifestCreator(Metadata metadata){
        super(metadata);
    }

    @SuppressWarnings("unchecked") // JSONObject doesn't support parameters to make it generic
    public JSONObject get() {
        JSONObject result = new JSONObject();
        try{
            result.put(ProvenLib.PROVEN_MANIFEST_VERSION_TAG,  ProvenLib.PROVEN_MANIFEST_VERSION);
            result.put(ProvenLib.PROVEN_FILE_NAME,             getFileName());
            result.put(ProvenLib.PROVEN_FILE_HASHES,           getFileHashes());
            result.put(ProvenLib.PROVEN_BITCOIN_BLOCK_NUMBER,  getBitcoinBlockNumber());
            result.put(ProvenLib.PROVEN_BITCOIN_BLOCK_HASH,    getBitcoinBlockHash());
            result.put(ProvenLib.PROVEN_ETHEREUM_BLOCK_NUMBER, getEthereumBlockNumber());
            result.put(ProvenLib.PROVEN_ETHEREUM_BLOCK_HASH,   getEthereumBlockHash());
            result.put(ProvenLib.PROVEN_PREVIOUS_IPFS_HASH,    getPreviousIPFSHash());
            result.put(ProvenLib.PROVEN_PREVIOUS_FILE_HASHES,  getPreviousFileHashes());
            result.put(ProvenLib.PROVEN_GUID,                  getGUID().toString());
        }
        catch( Exception e) {
            // stuff happens
        }
        return result;
    }
}
