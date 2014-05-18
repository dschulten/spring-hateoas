package org.springframework.hateoas.uber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.springframework.hateoas.Link;

public class AbstractUberNodeTest {

	private static final String URL_PREVIOUS = "http://www.example.com/previous";
	private static final String URL_NEXT = "http://www.example.com/next";
	private DummyUberNode dummyUberNode;
	private UberNode foo = new UberNode();
	private UberNode bar = new UberNode();
	private Link linkPrevious = new Link(URL_PREVIOUS, Link.REL_PREVIOUS);
	private Link linkNext = new Link(URL_NEXT, Link.REL_NEXT);

	class DummyUberNode extends AbstractUberNode {

	}

	@Before
	public void setUp() throws Exception {
		dummyUberNode = new DummyUberNode();

		foo.setName("foo");
		foo.addData(new UberNode());

		bar.setName("bar");
		bar.addData(new UberNode());

	}

	@Test
	public void testIteratorWithLinkWithinData() throws Exception {

		dummyUberNode.addData(foo);
		dummyUberNode.addLink(linkNext);
		dummyUberNode.addData(bar);

		int i = 0;
		String[] expected = { "foo", "bar" };
		for (UberNode uberNode : dummyUberNode) {
			assertEquals(expected[i++], uberNode.getName());
		}
	}

	@Test
	public void testIteratorWithLinkAtStartOfData() throws Exception {
		dummyUberNode.addLink(linkNext);
		dummyUberNode.addData(foo);
		dummyUberNode.addData(bar);

		int i = 0;
		String[] expected = { "foo", "bar" };
		for (UberNode uberNode : dummyUberNode) {
			assertEquals(expected[i++], uberNode.getName());
		}
	}

	@Test
	public void testIteratorWithLinkAtEndOfData() throws Exception {

		dummyUberNode.addData(foo);
		dummyUberNode.addData(bar);
		dummyUberNode.addLink(linkNext);

		int i = 0;
		String[] expected = { "foo", "bar" };
		for (UberNode uberNode : dummyUberNode) {
			assertEquals(expected[i++], uberNode.getName());
		}
	}

	@Test
	public void testIteratorOverDataOnly() throws Exception {
		dummyUberNode.addData(foo);
		dummyUberNode.addData(bar);

		int i = 0;
		String[] expected = { "foo", "bar", };
		for (UberNode uberNode : dummyUberNode) {
			assertEquals(expected[i++], uberNode.getName());
		}
	}

	@Test
	public void testIteratorOverDataReverse() throws Exception {
		dummyUberNode.addData(bar);
		dummyUberNode.addData(foo);

		int i = 0;
		String[] expected = { "bar", "foo" };
		for (UberNode uberNode : dummyUberNode) {
			assertEquals(expected[i++], uberNode.getName());
		}
	}

	@Test
	public void testGetFirstByRel() throws Exception {
		dummyUberNode.addData(bar);
		dummyUberNode.addLink(linkPrevious);

		assertNotNull("rel previous not found", dummyUberNode.getFirstByRel(Link.REL_PREVIOUS));
	}
	
	@Test
	public void testGetFirstByName() throws Exception {
		dummyUberNode.addLink(linkPrevious);
		dummyUberNode.addData(foo);
		dummyUberNode.addData(bar);

		assertNotNull("item bar not found", dummyUberNode.getFirstByName("bar"));
		assertNotNull("item foo not found", dummyUberNode.getFirstByName("foo"));
		assertNull(dummyUberNode.getFirstByName("noSuchName"));
	}

	@Test
	public void testAddLinks() throws Exception {
		dummyUberNode.addLinks(Arrays.asList(linkNext, linkPrevious));
		assertEquals(URL_NEXT, dummyUberNode.getFirstByRel(Link.REL_NEXT).getUrl());
		assertEquals(URL_PREVIOUS, dummyUberNode.getFirstByRel(Link.REL_PREVIOUS).getUrl());
		assertNull(dummyUberNode.getFirstByRel("noSuchRel"));
		
	}
}
