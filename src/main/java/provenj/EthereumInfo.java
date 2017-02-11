package provenj;

import org.json.JSONObject;

// Retrieves info on the Bitcoin blockchain
public class EthereumInfo extends BlockchainInfo {
    protected int getInterval(){
        // In theory there is a new Ethereum block every 10 seconds.
        return 10;
    }

    protected String getURL(){
        return "https://api.etherscan.io/api?module=proxy&action=eth_getBlockByNumber&tag=latest&boolean=true";
    }

    protected void applyAttributes(org.json.JSONObject json) throws org.json.JSONException {
        JSONObject result = (JSONObject) json.get("result");
        setLastBlockNumber(Integer.decode(result.getString("number")));
        setLastBlockHash(result.getString("hash"));
    }

    public Metadata apply(Metadata metadata){
        metadata.setEthereumBlockNumber(getLastBlockNumber());
        metadata.setEthereumBlockHash(getLastBlockHash());
        return metadata;
    }
}
