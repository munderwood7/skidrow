package com.example.skidrow.JUnitTest;

import java.util.ArrayList;

import com.example.skidrow.Event;
import com.example.skidrow.Game;
import com.example.skidrow.Market;
import com.example.skidrow.Player;

import junit.framework.TestCase;

/**
 * The class <code>GameTest</code> contains tests for the class {@link
 * <code>Game</code>}
 *
 * @pattern JUnit Test Case
 *
 * @generatedBy CodePro at 11/19/12 6:53 PM
 *
 * @author les7uve
 *
 * @version $Revision$
 */
public class GameTest extends TestCase {

	/**
	 * The object that is being tested.
	 *
	 * @see com.example.skidrow.Game
	 */
	private Game fixture = new Game();
	private Market currMarket;
	private Event currEvent;
	private Player player;

	/**
	 * Construct new test instance
	 *
	 * @param name the test name
	 */
	public GameTest(String name) {
		super(name);
	}

	/**
	 * Perform pre-test initialization
	 *
	 * @throws Exception
	 *
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		// Add additional set up code here
		currMarket = new Market(7);
		String good= "Weed";
		currEvent =  new Event("DROUGHT","The drought is over",4,2, " There has been a drought for an extended period that has decreased the levels of psilocybin and psilocin in the last batch of shrooms.", 2, good,"Atlanta");
		player = new Player("Lesly", 4, 4, 4, 4, "medium");
	}

	/**
	 * Perform post-test clean up
	 *
	 * @throws Exception
	 *
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		// Add additional tear down code here
		currMarket = null;
		currEvent =  null;
		player = null;
	}

	/**
	 * Run the String marketToPlayer(String, int) method test
	 */
	public void testMarketToPlayer() {
		fail("Newly generated method - fix or disable");
		// add test code here
		String drug = "Weed";
		int quantity = 1;
		String result = fixture.marketToPlayer(drug, quantity);
		//assertTrue("",player.getMoney());
	}
}

/*$CPS$ This comment was generated by CodePro. Do not edit it.
 * patternId = com.instantiations.assist.eclipse.pattern.testCasePattern
 * strategyId = com.instantiations.assist.eclipse.pattern.testCasePattern.junitTestCase
 * additionalTestNames = 
 * assertTrue = false
 * callTestMethod = true
 * createMain = false
 * createSetUp = true
 * createTearDown = true
 * createTestFixture = true
 * createTestStubs = false
 * methods = marketToPlayer(QString;!I)
 * package = com.example.skidrow.JUnitTest
 * package.sourceFolder = SkidRow/src
 * superclassType = junit.framework.TestCase
 * testCase = GameTest
 * testClassType = com.example.skidrow.Game
 */