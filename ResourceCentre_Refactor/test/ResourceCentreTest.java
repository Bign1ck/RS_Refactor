import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ResourceCentreTest {
	private Camcorder cc1;
	private Camcorder cc2;
	private Chromebook cb1;
	private Chromebook cb2;

	private ArrayList<Camcorder> camcorderList;
	private ArrayList<Chromebook> chromebookList;

	public ResourceCentreTest() {
		super();
	}

	@Before
	public void setUp() throws Exception {
		// prepare test data
		cc1 = new Camcorder("CC0011", "Nikon HDSLR", 40);
		cc2 = new Camcorder("CC0012", "Sony DSC-RX100M7", 20);
		cb1 = new Chromebook("CB0011", "My Google Chromebook 1st", "Mac OS");
		cb2 = new Chromebook("CB0012", "SAMSUNG Chromebook 4+", "Win 10");

		camcorderList = new ArrayList<Camcorder>();
		chromebookList = new ArrayList<Chromebook>();
	}

	@Test
	public void testAddCamcorder() {
		// Item list is not null, so that can add a new item - boundary
		assertNotNull("Check if there is valid Camcorder arraylist to add to", camcorderList);
		// Given an empty list, after adding 1 item, the size of the list is 1 - normal
		// The item just added is as same as the first item of the list
		ResourceCentre.addCamcorder(camcorderList, cc1);
		assertEquals("Check that Camcorder arraylist size is 1", 1, camcorderList.size());
		assertSame("Check that Camcorder is added", cc1, camcorderList.get(0));

		// Add another item. test The size of the list is 2? -normal
		// The item just added is as same as the second item of the list
		ResourceCentre.addCamcorder(camcorderList, cc2);
		assertEquals("Check that Camcorder arraylist size is 2", 2, camcorderList.size());
		assertSame("Check that Camcorder is added", cc2, camcorderList.get(1));
	}

	@Test
	public void testAddChromebook() {
		// Item list is not null, so that can add a new item - boundary
		assertNotNull("Test if there is valid Chromebook arraylist to add to", chromebookList);

		// Given an empty list, after adding 1 item, the size of the list is 1 - normal
		// The item just added is as same as the first item of the list
		ResourceCentre.addChromebook(chromebookList, cb1);
		assertEquals("Test that Chromebook arraylist size is 1", 1, chromebookList.size());
		assertSame("Test that Chromebook is added", cb1, chromebookList.get(0));

		// Add another item. test The size of the list is 2? - normal
		// The item just added is as same as the second item of the list
		ResourceCentre.addChromebook(chromebookList, cb2);
		assertEquals("Test that Chromebook arraylist size is 2", 2, chromebookList.size());
		assertSame("Test that Chromebook is added", cb2, chromebookList.get(1));
	}

	@Test
public void testRetrieveAllCamcorder() {
    // Test if Item list is not null but empty -boundary
    assertNotNull("Test if there is valid Camcorder arraylist to retrieve item", camcorderList);

    // test if the list of camcorders retrieved from the SourceCentre is empty - boundary
    String allCamcorder = ResourceCentre.retrieveAllCamcorder(camcorderList);
    String testOutput = "";

    assertEquals("Check that ViewAllCamcorderlist", testOutput, allCamcorder);

    // Given an empty list, after adding 2 items, test if the size of the list is 2 - normal
    ResourceCentre.addCamcorder(camcorderList, cc1);
    ResourceCentre.addCamcorder(camcorderList, cc2);
    assertEquals("Test that Camcorder arraylist size is 2", 2, camcorderList.size());

    // test if the expected output string is the same as the list of camcorders retrieved from the SourceCentre
    allCamcorder = ResourceCentre.retrieveAllCamcorder(camcorderList);

    testOutput = String.format("%-10s %-30s %-10s %-10s %-20s\n", "CC0011", "Nikon HDSLR", "Yes", "", 40);
    testOutput += String.format("%-10s %-30s %-10s %-10s %-20s\n", "CC0012", "Sony DSC-RX100M7", "Yes", "", 20);

    // Split the expected and actual output into lines
    String[] expectedLines = testOutput.split("\\r?\\n");
    String[] actualLines = allCamcorder.split("\\r?\\n");

    // Compare each line
    for (int i = 0; i < expectedLines.length; i++) {
        assertEquals("Line " + (i + 1) + " mismatch", expectedLines[i].trim(), actualLines[i].trim());
    }
}

	@Test

	public void testRetrieveAllChromeebook() {

		// fail("Not yet implemented");

		// Test if Item list is not null but empty - boundary

		assertNotNull("Test if there is valid Chromebook arraylist to retrieve item from", chromebookList);

		// test if the list of Chromebook retrieved from the SourceCentre is empty -
		// boundary

		String allChrombook = ResourceCentre.retrieveAllChromebook(chromebookList);

		String testOutput = "";

		assertEquals("Test that the retrieved Chromebooklist is empty?", testOutput, allChrombook);

		// Given an empty list, after adding 2 items, test if the size of the list is 2
		// - normal

		ResourceCentre.addChromebook(chromebookList, cb1);

		ResourceCentre.addChromebook(chromebookList, cb2);

		assertEquals("Test that chromebook arraylist size is 2", 2, chromebookList.size());

		// test if the expected output string same as the list of chromebooks retrieved
		// from the SourceCentre

		allChrombook = ResourceCentre.retrieveAllChromebook(chromebookList);

		testOutput = String.format("%-10s %-30s %-10s %-10s %-20s\n", "CB0011", "My Google Chromebook 1st", "Yes", "",
				"Mac OS");

		testOutput += String.format("%-10s %-30s %-10s %-10s %-20s\n", "CB0012", "SAMSUNG Chromebook 4+", "Yes", "",
				"Win 10");

		assertEquals("Test that ViewAllChromebooklist", testOutput, allChrombook);

	}

	@Test
	public void testDoLoanCamcorder() {
		// boundary
		assertNotNull("test if there is valid Camcorder arraylist to loan from", camcorderList);

		ResourceCentre.addCamcorder(camcorderList, cc1);
		// normal
		Boolean ok = ResourceCentre.doLoanCamcorder(camcorderList, "CC0011", "8-8-2020");
		assertTrue("Test if an available item is ok to loan?", ok);
		// error condition
		ok = ResourceCentre.doLoanCamcorder(camcorderList, "CC0011", "8-8-2020");
		assertFalse("Test if an same item is NOT ok to loan again?", ok);
		// error condition
		ResourceCentre.addCamcorder(camcorderList, cc2);
		cc2.setIsAvailable(false);
		ok = ResourceCentre.doLoanCamcorder(camcorderList, "CC0012", "8-8-2020");
		assertFalse("Test that un-available item is NOT ok to loan?", ok);
		// error condition
		ok = ResourceCentre.doLoanCamcorder(camcorderList, "CC0013", "8-8-2020");
		assertFalse("Test that non-esiting item is NOT ok to loan?", ok);

	}

	@Test
	public void testDoLoanChromebook() {
		// boundary
		assertNotNull("Test if there is valid Chromebook arraylist to add to", chromebookList);
		ResourceCentre.addChromebook(chromebookList, cb1);
		// normal
		Boolean ok = ResourceCentre.doLoanChromebook(chromebookList, "CB0011", "8-8-2020");
		assertTrue("Test if an available item is ok to loan?", ok);
		// error condition
		ok = ResourceCentre.doLoanChromebook(chromebookList, "CB0011", "8-8-2020");
		assertFalse("Test if the same item is NOT ok to loan again?", ok);
		// error
		ResourceCentre.addChromebook(chromebookList, cb2);
		cb2.setIsAvailable(false);
		ok = ResourceCentre.doLoanChromebook(chromebookList, "CB0012", "8-8-2020");
		assertFalse("Test that un-available item is NOT ok to loan?", ok);
		// error condition
		ok = ResourceCentre.doLoanChromebook(chromebookList, "CB0013", "8-8-2020");
		assertFalse("Test that non-esiting item is NOT ok to loan?", ok);

	}

	@Test
	public void testDoReturnCamcorder() {
		// boundary
		assertNotNull("Test if there is valid Camcorder arraylist to add to", camcorderList);
		ResourceCentre.addCamcorder(camcorderList, cc1);
		// error
		Boolean isReturned = ResourceCentre.doReturnCamcorder(camcorderList, "CC0011");
		assertFalse("Test if available camcorder CC0011 is returned -false?", isReturned);
		// normal
		ResourceCentre.addCamcorder(camcorderList, cc2);
		cc2.setIsAvailable(false);
		isReturned = ResourceCentre.doReturnCamcorder(camcorderList, "CC0012");
		assertTrue("Test if loaned out amcorder CC0012 is returned- true", isReturned);
		// error
		isReturned = ResourceCentre.doReturnCamcorder(camcorderList, "CC0013");
		assertFalse("Test if non-existing amcorder CC0013 is returned - false?", isReturned);

	}

	@Test
	public void testDoReturnChromebook() {
		// fail("Not yet implemented");
		// write your code here
		String tag = "CB0011";
		String dueDate = "8-8-2020";
		boolean Valid_loan_cb = false;
		Boolean Valid_return_cb = false;
		Boolean checked_in = true;

		if (chromebookList.isEmpty())
			chromebookList.add(0, cb1);
		chromebookList.add(1, cb2);
		// Test case 1: Return a loaned out item
		for (Chromebook cb : chromebookList) {
			Valid_loan_cb = (cb.getAssetTag().equalsIgnoreCase(tag))
					&& (ResourceCentre.doLoanChromebook(chromebookList, tag, dueDate));
			if (Valid_loan_cb) {
				break;
			}
			cb.setIsAvailable(Valid_loan_cb);
		}
		assertTrue("Test if CB0011 is successfully loaned out.", Valid_loan_cb);

		Valid_return_cb = Valid_loan_cb && ResourceCentre.doReturnChromebook(chromebookList, tag)
				&& chromebookList.get(0).getIsAvailable();
		if (Valid_return_cb) {
			checked_in = true;
		}
		assertTrue("Test that CB0011 is now available.", Valid_return_cb);
		// Test case 2: Return an item that is not loaned out
		Valid_return_cb = ResourceCentre.doReturnChromebook(chromebookList, "CB0011");
		assertFalse("Test that the return fails.", Valid_return_cb);
		// Test case 3: Return an item that does not exist
		Valid_return_cb = ResourceCentre.doReturnChromebook(chromebookList, "CB0099");
		assertFalse("Test that the return fails.", Valid_return_cb);
		// Test case 4: Checked in when student returns loaned item
		if (Valid_return_cb) {
			checked_in = true;
		}
		assertTrue("Test if CB0011 is successfully checked in", checked_in);
	}

	@After
	public void tearDown() throws Exception {
		cc1 = null;
		cc2 = null;
		cb1 = null;
		cb2 = null;
		camcorderList = null;
		chromebookList = null;

	}

}
