package provenj;

import org.json.JSONObject;
import org.json.JSONTokener;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// Retrieves information from public blockchains
public abstract class BlockchainInfo {
    private int m_lastBlockNumber;
    private String m_lastBlockHash;
    private long m_lastRefreshed = 0;
    private long m_lastBlockTime = 0;

    // Returns the most recent block in the given chain
    public int getLastBlockNumber(){
        refresh();
        return m_lastBlockNumber;
    }

    protected void setLastBlockNumber(int blockNumber){
        m_lastBlockNumber = blockNumber;
    }

    // Returns the hash of the most recent block in the given chain
    public String getLastBlockHash(){
        refresh();
        return m_lastBlockHash;
    }

    protected void setLastBlockHash(String blockHash){
        m_lastBlockHash = blockHash;
    }

    protected void refresh(){
        if ((m_lastRefreshed == 0)
        ||  (m_lastRefreshed + (getInterval()*1000) < System.currentTimeMillis()  )){
            fetchLatest();
            m_lastRefreshed = getLastBlockTime() * 1000;
        }
    }

    protected void setLastBlockTime(long lastBlockTime){
        m_lastBlockTime = lastBlockTime;
    }

    // Returns the UNIX epoch of the most recent block
    public long getLastBlockTime(){
        return m_lastBlockTime;
    }

    protected void fetchLatest(){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(getURL())
                .build();

        try {
            Response response = client.newCall(request).execute();
            JSONObject json = (JSONObject) new JSONTokener(response.body().string()).nextValue();
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
    protected abstract void applyAttributes(JSONObject json) throws org.json.JSONException;

    // Set the blockchain-specific info in the metadata
    public abstract Metadata apply(Metadata metadata);
}
