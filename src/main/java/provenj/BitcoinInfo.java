package provenj;

import org.json.JSONObject;

// Retrieves info on the Bitcoin blockchain
public class BitcoinInfo extends BlockchainInfo {
    protected int getInterval(){
        // In theory there is a new Bitcoin block every 10 minutes
        return 10*60;
    }

    protected String getURL(){
        return "https://blockchain.info/latestblock";
    }

    protected void applyAttributes(JSONObject json) throws org.json.JSONException {
        setLastBlockNumber(json.getInt("height"));
        setLastBlockHash(json.getString("hash"));
        setLastBlockTime(json.getLong("time"));
    }

    public Metadata apply(Metadata metadata){
        metadata.setBitcoinBlockNumber(getLastBlockNumber());
        metadata.setBitcoinBlockHash(getLastBlockHash());
        return metadata;
    }
}
