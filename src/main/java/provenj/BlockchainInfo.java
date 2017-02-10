package provenj;

// Retrieves information from public blockchains
public abstract class BlockchainInfo {
    protected int m_lastBlockNumber;
    protected String m_lastBlockHash;
    protected volatile long m_lastRefreshed = 0;

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

    // Get the most recent info from whatever source
    protected abstract void fetchLatest();

    // The refresh interval (in seconds)
    protected abstract int getInterval();
}
