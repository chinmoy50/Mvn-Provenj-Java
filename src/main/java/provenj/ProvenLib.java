package provenj;

// Constants needed across Proven. This is in an interface because it is never instantiated.
public interface ProvenLib {
    public static String PROVEN_NAMESPACE             = "http://ns.facted.net/proven/1.0/facted/";
    public static String PROVEN_PREFIX                = "Proven";
    public static String PROVEN_INDEX                 = "index.html";
    public static String PROVEN_MANIFEST              = "manifest.json";
    public static String PROVEN_PAYLOAD_DIRECTORY     = "payload";
    public static String PROVEN_FILE_NAME             = "FileName";
    public static String PROVEN_FILE_HASHES           = "FileHashes";
    public static String PROVEN_BITCOIN_BLOCK_NUMBER  = "BitcoinBlockNumber";
    public static String PROVEN_BITCOIN_BLOCK_HASH    = "BitcoinBlockHash";
    public static String PROVEN_ETHEREUM_BLOCK_NUMBER = "EthereumBlockNumber";
    public static String PROVEN_ETHEREUM_BLOCK_HASH   = "EthereumBlockHash";
    public static String PROVEN_PREVIOUS_IPFS_HASH    = "PreviousIPFSHash";
    public static String PROVEN_PREVIOUS_FILE_HASHES  = "PreviousFileHashes";
    public static String PROVEN_GUID                  = "GUID";
}
