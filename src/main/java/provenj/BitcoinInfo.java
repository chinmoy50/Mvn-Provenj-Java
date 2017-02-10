package provenj;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// Retrieves info on the Bitcoin blockchain
public class BitcoinInfo extends BlockchainInfo {
    protected int getInterval(){
        // In theory there is a new Bitcoin block every 10 minutes
        return 10*60;
    }

    protected void fetchLatest(){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://blockchain.info/latestblock")
                .build();

        try {
            Response response = client.newCall(request).execute();
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(response.body().string());
            m_lastBlockNumber = Integer.parseInt(json.get("height").toString());
            m_lastBlockHash = json.get("hash").toString();
        }
        catch (Exception e) {
            // sometimes bad things happen
        }
    }
}
