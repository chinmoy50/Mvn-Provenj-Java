package provenj;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// Retrieves information from public blockchains
public abstract class BlockchainInfo {
    protected int m_lastBlockNumber;
    protected String m_lastBlockHash;
    protected long m_lastRefreshed = 0;

    // Returns the most recent block in the given chain
    public int getLastBlockNumber(){
        refresh();
        return m_lastBlockNumber;
    }

    // Returns the hash of the most recent block in the given chain
    public String getLastBlockHash(){
        refresh();
        return m_lastBlockHash;
    }

    protected void refresh(){
        if ((m_lastRefreshed == 0)
        ||  (m_lastRefreshed + (getInterval()*1000) < System.currentTimeMillis()  )){
            fetchLatest();
            m_lastRefreshed = System.currentTimeMillis();
        }
    }

    protected void fetchLatest(){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(getURL())
                .build();

        try {
            Response response = client.newCall(request).execute();
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(response.body().string());
            applyAttributes(json);
        }
        catch (Exception e) {
            // sometimes bad things happen
        }
    }

    // The refresh interval (in seconds)
    protected abstract int getInterval();

    // The URL for the web service we're calling
    protected abstract String getURL();

    // Call the class to look up the needed attribute in the body
    protected abstract void applyAttributes(JSONObject json);
}
