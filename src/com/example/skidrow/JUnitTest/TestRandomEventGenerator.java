package com.example.skidrow.JUnitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.example.skidrow.AppUtil;
import com.example.skidrow.Event;
import com.example.skidrow.Game;
import com.example.skidrow.RandomEventGenerator;

public class TestRandomEventGenerator {
	Event e,e1,e2,e3,e4,e5,e51,e41,e31,e21,e11;
	RandomEventGenerator rng= new RandomEventGenerator();
	
	 
	/**
	 * The constructor creates an array of different event types that can happen throughout the game.
	 * This test case will makes sure that the array is consturcted.
	 * @author apavia3
	 *
	 */
	@Test
	public void testConstructor() {
		AppUtil.game=new Game();
		assertEquals("The size of the event array should be 10 since there are 10 different event types.",10, rng.getNumberOfEventTypes());
	}
	/**
	 * This test case will try to pop something of an empty array. pop() should return NULL if there is nothing to pop.
	 */
	@Test
	public void testPopOnEmptyEventList(){
		assertNull("pop() on an empty event list should return NULL", rng.pop());
	}
	/**
	 * This test case will try to peek something of an empty array. peek() should return NULL if there is nothing to peek.
	 */
	@Test
	public void testPeekOnEmptyEventList(){
		assertNull("peek() on an empty event list should return NULL", rng.peek());
	}
	/**
	 * This test case will try to get the event name of the next event on an empty array of events. getNextEventName() should return NULL if there is no event in the array.
	 */
	@Test
	public void testGetNextEventNameOnEmptyEventList(){
		assertNull("getNextEventName() on an empty event list should return NULL", rng.getNextEventName());
	}
	/**
	 * This test case will try to use getNextEventStepNum() on an empty array of events. getNextEventStepNum() should return 0 if there is no event in the array.
	 */
	@Test
	public void testGetNextEventStepNumOnEmptyEventList(){
		assertEquals("getNextEventStepNum() on an empty event list should return 0",0, rng.getNextEventStepNum());
	}
	/**
	 * This test case will try to use getNextEventDuration() on an empty array of events.getNextEventDuration() should return 0 if there is no event in the array.
	 */
	@Test
	public void testGetNextEventDurationOnEmptyEventList(){
		assertEquals("getNextEventDuration() on an empty event list should return 0",0, rng.getNextEventDuration());
	}
	/**
	 * This test case will try to use getNextEventDescription() on an empty array of events. getNextEventDescription() should return null if there is no event in the array.
	 */
	@Test
	public void testGetNextEventDescriptionOnEmptyEventList(){
		assertNull("getNextEventDescription() on an empty event list should return null", rng.getNextEventDescription());
	}
	/**
	 * This test case will try to use checkStartEvent() on an empty array of events. checkStartEvent() should return false if there is no event in the array.
	 */
	@Test
	public void testCheckStartEventOnEmptyEventList(){
		assertFalse("checkStartEvent() on an empty event list should return false", rng.checkStartEvent());
	}
	/**
	 * This test case will try to use checkEndEvent() on an empty array of events. checkEndEvent() should return false if there is no event in the array.
	 */
	@Test
	public void testCheckEndEventOnEmptyEventList(){
		assertFalse("checkEndEvent() on an empty event list should return false", rng.checkEndEvent());
	}
	/**
	 * This test case will try to use getSizeOfEventList() on an empty array of events. getSizeOfEventList() should return 0 if there is no event in the array.
	 */
	@Test
	public void testGetSizeOfEventListOnEmptyEventList(){
		assertEquals("getSizeOfEventList() on an empty event list should return null",0, rng.getSizeOfEventList());
	}
	
	
	
	/**
	 * This test will try to generate an event. We will verify by peeking into it and making sure the size of the list went up by one.
	 * It will also verify that the all the information from the event can be obtained. Then, the even will be popped and will verify this by
	 * making sure that peek returns null and the size of the event list is equal to zero. We will also verify  that all the information of the 
	 * popped event is the same as the information of the event that was in the list.
	 */
	@Test
	public void testGenerateEvent1(){
		rng.generateEvent();
		assertNotNull("The result of peeking shoudl be not null",rng.peek());
		assertEquals("The size of the event list should be 1",1,rng.getSizeOfEventList());
		e=rng.getCurrE();
		assertSame("Peek() and getCurrE() shoudl return the same in this case",rng.getCurrE(),rng.peek());
		assertNotNull("The current event should not be equal to NULL",e);
		assertEquals("The name of the next event should be equal to the name of the event we got from getCurrE()",e.getName(),rng.getNextEventName());
		assertEquals("The description of the next event should be equal to the description of the event we got from getCurrE()",e.getDescription(),rng.getNextEventDescription());
		assertEquals("The duration of the next event should be equal to the Duration of the event we got from getCurrE()",e.getDuration(),rng.getNextEventDuration());
		assertEquals("The step number of the next event should be equal to the step number of the event we got from getCurrE()",e.getStepNum(),rng.getNextEventStepNum());
		e2=rng.pop();
		assertNull("The result of peeking shoudl be null",rng.peek());
		assertEquals("The size of the event list should be 0",0,rng.getSizeOfEventList());
		assertEquals("The name of the popped event and the one that was in the list should be the same,",e2.getName(),e.getName());
		assertEquals("The description of the popped event and the one that was in the list should be the same,",e.getDescription(),e2.getDescription());
		assertEquals("The duration of the popped event and the one that was in the list should be the same,",e.getDuration(),e2.getDuration());
		assertEquals("The step number of the popped event and the one that was in the list should be the same,",e.getStepNum(),e2.getStepNum());
		
	}
	/**
	 * This test will generate multiple events and will pop them. It will make sure that the events popped were the same as the 
	 * events that were generated.
	 */
	@Test
	public void testGenerateEvent2(){
		assertNull("Pop() should return null on an empty list", rng.pop());
		assertEquals("The size of the event list should be 0",0,rng.getSizeOfEventList());
		rng.generateEvent();
		assertEquals("The size of the event list should be 1",1,rng.getSizeOfEventList());
		assertSame("Peek() and getCurrE() should return the same in this case",rng.getCurrE(),rng.peek());
		e1=rng.getCurrE();
		rng.generateEvent();
		assertEquals("The size of the event list should be 2",2,rng.getSizeOfEventList());
		assertNotSame("Peek() and getCurrE() should return different Events in this case",rng.getCurrE(),rng.peek());
		e2=rng.getCurrE();
		rng.generateEvent();
		assertEquals("The size of the event list should be 3",3,rng.getSizeOfEventList());
		e3=rng.getCurrE();
		rng.generateEvent();
		assertEquals("The size of the event list should be 4",4,rng.getSizeOfEventList());
		assertNotSame("Peek() and getCurrE() should return different Events in this case",rng.getCurrE(),rng.peek());
		e4=rng.getCurrE();
		rng.generateEvent();
		assertEquals("The size of the event list should be 5",5,rng.getSizeOfEventList());
		assertNotSame("Peek() and getCurrE() should return different Events in this case",rng.getCurrE(),rng.peek());
		e5=rng.getCurrE();
		e11=rng.pop();
		assertEquals("The size of the event list should be 4",4,rng.getSizeOfEventList());
		assertNotSame("Peek() and getCurrE() should return different Events in this case",rng.getCurrE(),rng.peek());
		e21=rng.pop();
		assertEquals("The size of the event list should be 3",3,rng.getSizeOfEventList());
		assertNotSame("Peek() and getCurrE() should return different Events in this case",rng.getCurrE(),rng.peek());
		e31=rng.pop();
		assertEquals("The size of the event list should be 2",2,rng.getSizeOfEventList());
		assertNotSame("Peek() and getCurrE() should return different Events in this case",rng.getCurrE(),rng.peek());
		e41=rng.pop();
		assertEquals("The size of the event list should be 1",1,rng.getSizeOfEventList());
		assertSame("Peek() and getCurrE() should return the same event in this case",rng.getCurrE(),rng.peek());
		e51=rng.pop();
		assertEquals("The size of the event list should be 0",0,rng.getSizeOfEventList());
		assertEquals("The names of the two events should be equal", e5.getName(),e51.getName());
		assertEquals("The names of the two events should be equal", e4.getName(),e41.getName());
		assertEquals("The names of the two events should be equal", e3.getName(),e31.getName());
		assertEquals("The names of the two events should be equal", e2.getName(),e21.getName());
		assertEquals("The names of the two events should be equal", e1.getName(),e11.getName());
		assertEquals("The description of the two events should be equal", e5.getDescription(),e51.getDescription() );
		assertEquals("The description  of the two events should be equal", e4.getDescription(),e41.getDescription() );
		assertEquals("The description  of the two events should be equal", e3.getDescription(),e31.getDescription() );
		assertEquals("The description  of the two events should be equal", e2.getDescription() ,e21.getDescription() );
		assertEquals("The description  of the two events should be equal", e1.getDescription() ,e11.getDescription() );
		assertEquals("The duration of the two events should be equal", e5.getDuration(),e51.getDuration());
		assertEquals("The duration of the two events should be equal", e4.getDuration(),e41.getDuration());
		assertEquals("The duration of the two events should be equal", e3.getDuration(),e31.getDuration());
		assertEquals("The duration of the two events should be equal", e2.getDuration(),e21.getDuration());
		assertEquals("The duration of the two events should be equal", e1.getDuration(),e11.getDuration());
		assertNull("Pop() should return null on an empty list", rng.pop());	
	}
	
}
