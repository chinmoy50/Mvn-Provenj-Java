package provenj;

import org.json.simple.JSONObject;

// Retrieves info on the Bitcoin blockchain
public class EthereumInfo extends BlockchainInfo {
    protected int getInterval(){
        // In theory there is a new Ethereum block every 10 seconds.
        return 10;
    }

    protected String getURL(){
        return "https://api.etherscan.io/api?module=proxy&action=eth_getBlockByNumber&tag=latest&boolean=true";
    }

    protected void applyAttributes(JSONObject json) {
        JSONObject result = (JSONObject) json.get("result");
        m_lastBlockNumber = Integer.decode(result.get("number").toString());
        m_lastBlockHash = result.get("hash").toString();
    }
}
