package provenj;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.PendingException;
import org.json.simple.JSONObject;
import java.util.UUID;
import static org.junit.Assert.assertEquals;

public class Stepdefs {

    // Build manifest
    Manifest manifest = new Manifest();
    String m_fileName;

    @Given("^a JPEG file named \"([^\"]*)\"$")
    public void a_JPEG_file_named(String fileName) throws Throwable {
	manifest.addFile(fileName);
        m_fileName = fileName;
    }

    @Given("^the current Bitcoin block number (\\d+)$")
    public void the_current_Bitcoin_block_number(int blockNumber) throws Throwable {
	manifest.setBitcoinBlockNumber(blockNumber);
    }

    @Given("^the current Bitcoin block hash \"([^\"]*)\"$")
    public void the_current_Bitcoin_block_hash(String blockHash) throws Throwable {
	manifest.setBitcoinBlockHash(blockHash);
    }

    @Given("^the current Ethereum block number (\\d+)$")
    public void the_current_Ethereum_block_number(int blockNumber) throws Throwable {
	manifest.setEthereumBlockNumber(blockNumber);
    }

    @Given("^the current Ethereum block hash \"([^\"]*)\"$")
    public void the_current_Ethereum_block_hash(String blockHash) throws Throwable {
	manifest.setEthereumBlockHash(blockHash);
    }

    @Given("^the IPFS hash from the last submitted file \"([^\"]*)\"$")
    public void the_IPFS_hash_from_the_last_submitted_file(String ipfsHash) throws Throwable {
	manifest.setPreviousIPFSHash(ipfsHash);
    }

    @Given("^the other hashes from the last submitted file \"([^\"]*)\"$")
    public void the_other_hashes_from_the_last_submitted_file(String fileHashes) throws Throwable {
	manifest.setPreviousFileHashes(fileHashes);
    }

    @Given("^the GUID for the submission \"([^\"]*)\"$")
    public void the_GUID_for_the_submission(String guid) throws Throwable {
	manifest.setGUID(UUID.fromString(guid));
    }

    @When("^I ask for a manifest file$")
    public void i_ask_for_a_manifest_file() throws Throwable {
        JSONObject json = manifest.get();
    }

    @Then("^manifest\\.FileName should equal \"([^\"]*)\"$")
    public void manifest_FileName_should_equal(String filename) throws Throwable {
        JSONObject json = manifest.get();
        assertEquals(json.get("FileName"),filename);
    }

    @Then("^manifest\\.BitcoinBlockNumber should be (\\d+)$")
    public void manifest_BitcoinBlockNumber_should_be(int bitcoinBlockNumber) throws Throwable {
        JSONObject json = manifest.get();
        assertEquals((int)json.get("BitcoinBlockNumber"),bitcoinBlockNumber);
    }

    @Then("^manifest\\.BitcoinBlockHash should equal \"([^\"]*)\"$")
    public void manifest_BitcoinBlockHash_should_equal(String hash) throws Throwable {
        JSONObject json = manifest.get();
        assertEquals(json.get("BitcoinBlockHash"),hash);
    }

    @Then("^manifest\\.EthereumBlockNumber should equal (\\d+)$")
    public void manifest_EthereumBlockNumber_should_equal(int num) throws Throwable {
        JSONObject json = manifest.get();
        assertEquals((int)json.get("EthereumBlockNumber"),num);
    }

    @Then("^manifest\\.EthereumBlockHash should equal \"([^\"]*)\"$")
    public void manifest_EthereumBlockHash_should_equal(String hash) throws Throwable {
        JSONObject json = manifest.get();
        assertEquals(json.get("EthereumBlockHash"),hash);
    }

    @Then("^manifest\\.PreviousIFPSHash should equal \"([^\"]*)\"$")
    public void manifest_PreviousIFPSHash_should_equal(String hash) throws Throwable {
        JSONObject json = manifest.get();
        assertEquals(json.get("PreviousIPFSHash"),hash);
    }

    @Then("^manifest\\.PreviousFileHashes should equal \"([^\"]*)\"$")
    public void manifest_PreviousFileHashes_should_equal(String hashes) throws Throwable {
        JSONObject json = manifest.get();
        assertEquals(json.get("PreviousFileHashes"),hashes);
    }

    @Then("^manifest\\.GUID should equal \"([^\"]*)\"$")
    public void manifest_GUID_should_equal(String guid) throws Throwable {
        JSONObject json = manifest.get();
        assertEquals(json.get("GUID"),guid);
    }

    String index;

    @When("^I create an index$")
    public void i_create_an_index() throws Throwable {
        index = IndexCreator.create(manifest);
        assert(index.matches("^<html>.*</html>$"));
    }

    @Then("^the output file should list the file name$")
    public void the_output_file_should_list_the_file_name() throws Throwable {
        assert(index.matches(String.format("^.*%s.*$", m_fileName)));
    }

    @Then("^the output file should have a static link to the file$")
    public void the_output_file_should_have_a_static_link_to_the_file() throws Throwable {
        assert(index.matches(String.format("^.*a href.*%s.*$",m_fileName)));
    }

    @Then("^the output file should include the last Ethereum hash$")
    public void the_output_file_should_include_the_hash_information_for_the_file() throws Throwable {
        assert(index.matches(String.format("^.*%s.*$",manifest.get().get("EthereumBlockHash"))));
    }
}
