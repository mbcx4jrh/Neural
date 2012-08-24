package neural.parsec.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import neural.parsec.ast.DoubleParameter;
import neural.parsec.ast.IntegerParameter;
import neural.parsec.ast.Parameter;

import org.junit.Before;
import org.junit.Test;

public class TestValidation {
	
	private ValidationFactory validationFactory;
	private List<Parameter> params;

	@Before
	public void setUpValidationFactory() {
		validationFactory = new ValidationFactory("scripts/test.validation");
		params = new ArrayList<Parameter>();
	}
	
	@Test
	public void testDefaults() {
		Validator validator = validationFactory.getValidator("network.doesnotexist");
		validator.validate(params);
		assertTrue(validator.isValid());
		
		validator = validationFactory.getValidator("network.blank");
		validator.validate(params);
		
		assertTrue(validator.isValid());
		
		params.add(new IntegerParameter("blah", 22));
		validator.validate(params);
		assertTrue(!validator.isValid());
	}
	
	@Test
	public void testOptionalValidator() {
		Validator validator = validationFactory.getValidator("network.feedforward");
		validator.validate(params);
		assertTrue(validator.isValid());
		
		params.add(new DoubleParameter("threshold", 0.5));
		validator.validate(params);
		assertTrue(validator.isValid());
		
		params.add(new DoubleParameter("thold", 0.4));
		validator.validate(params);
		assertTrue(!validator.isValid());
	}
	
	@Test
	public void testHopfieldValidator() {
		Validator validator = validationFactory.getValidator("network.hopfield");
		
		validator.validate(params);
		assertTrue(!validator.isValid());
		String[] msgs = validator.getIssues();
		assertEquals(1, msgs.length);
		assertEquals("Mandantory parameter 'size' is missing", msgs[0]);
		
		params.add(new IntegerParameter("size", 10));
		validator.validate(params);
		assertTrue(validator.isValid());
		
		params.add(new IntegerParameter("notneeded", 42));
		validator.validate(params);
		assertTrue(!validator.isValid());
		assertEquals(1, msgs.length);
		assertEquals("Mandantory parameter 'size' is missing", msgs[0]);
	}

}
