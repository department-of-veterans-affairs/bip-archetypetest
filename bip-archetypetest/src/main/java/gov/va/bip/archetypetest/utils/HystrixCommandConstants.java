package gov.va.bip.archetypetest.utils;

/**
 * The Class HystrixCommandConstants.
 */
public final class HystrixCommandConstants {

	/** ArchetypeTest Service Thread Pool Group. */
	public static final String ARCHETYPETEST_SERVICE_GROUP_KEY = "ArchetypeTestServiceGroup";

	/**
	 * Do not instantiate
	 */
	private HystrixCommandConstants() {
		throw new UnsupportedOperationException("HystrixCommandConstants is a static class. Do not instantiate it.");
	}
}
