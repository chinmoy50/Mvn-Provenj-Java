package provenj;

import org.json.simple.JSONObject;

// Creates a manifest.json file to be included in the Proven enclosure.
public class Manifest extends Metadata {
    @SuppressWarnings("unchecked") // JSONObject doesn't support parameters to make it generic
    public JSONObject get() {
       JSONObject result = new JSONObject();
       result.put(ProvenLib.PROVEN_FILE_NAME,             getFileName());
       result.put(ProvenLib.PROVEN_FILE_HASHES,           getFileHashes());
       result.put(ProvenLib.PROVEN_BITCOIN_BLOCK_NUMBER,  getBitcoinBlockNumber());
       result.put(ProvenLib.PROVEN_BITCOIN_BLOCK_HASH,    getBitcoinBlockHash());
       result.put(ProvenLib.PROVEN_ETHEREUM_BLOCK_NUMBER, getEthereumBlockNumber());
       result.put(ProvenLib.PROVEN_ETHEREUM_BLOCK_HASH,   getEthereumBlockHash());
       result.put(ProvenLib.PROVEN_PREVIOUS_IPFS_HASH,    getPreviousIPFSHash());
       result.put(ProvenLib.PROVEN_PREVIOUS_FILE_HASHES,  getPreviousFileHashes());
       result.put(ProvenLib.PROVEN_GUID,                  getGUID().toString());
       return result;
    }
}
