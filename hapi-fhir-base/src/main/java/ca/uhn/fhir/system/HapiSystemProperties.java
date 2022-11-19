package ca.uhn.fhir.system;

import org.apache.commons.lang3.time.DateUtils;

public final class HapiSystemProperties {
	private static final String SUPPRESS_HAPI_FHIR_VERSION_LOG = "suppress_hapi_fhir_version_log";
	private static final String DISABLE_STATUS_BASED_REINDEX = "disable_status_based_reindex";
	/**
	 * This is provided for testing only! Use with caution as this property may change.
	 */
	private static final String TEST_SYSTEM_PROP_VALIDATION_RESOURCE_CACHES_MS = "TEST_SYSTEM_PROP_VALIDATION_RESOURCE_CACHES_MS";
	private static final String UNIT_TEST_CAPTURE_STACK = "unit_test_capture_stack";
	private static final String STACKFILTER_PATTERN_PROP = "log.stackfilter.pattern";
	private static final String HAPI_CLIENT_KEEPRESPONSES = "hapi.client.keepresponses";
	private static final String TEST_MODE = "test";
	private static final String UNIT_TEST_MODE = "unit_test_mode";
	private static final long DEFAULT_TEST_SYSTEM_PROP_VALIDATION_RESOURCE_CACHES_MS = 10 * DateUtils.MILLIS_PER_SECOND;

	private HapiSystemProperties() {
	}

	/**
	 * This property is used by unit tests - do not rely on it in production code
	 * as it may change at any time. If you want to capture responses in a reliable
	 * way in your own code, just use client interceptors
	 */
	public static void enableHapiClientKeepResponses() {
		System.setProperty(HAPI_CLIENT_KEEPRESPONSES, "true");
	}

	public static void disableHapiClientKeepResponses() {
		System.clearProperty(HAPI_CLIENT_KEEPRESPONSES);
	}

	/**
	 * This property is used by unit tests - do not rely on it in production code
	 * as it may change at any time. If you want to capture responses in a reliable
	 * way in your own code, just use client interceptors
	 */
	public static boolean isHapiClientKeepResponsesEnabled() {
		return ("true".equals(System.getProperty(HAPI_CLIENT_KEEPRESPONSES)));
	}

	/**
	 * This property gets used in the logback.xml file.
	 * It causes logged stack traces to skip a number of packages that are
	 * just noise.
	 */

	public static void setStackFilterPattern(String thePattern) {
		System.setProperty(STACKFILTER_PATTERN_PROP, thePattern);
	}

	/**
	 * Set the validation resource cache expireAfterWrite timeout in milliseconds
	 *
	 * @param theMillis
	 */
	public static void setTestValidationResourceCachesMs(long theMillis) {
		System.setProperty(TEST_SYSTEM_PROP_VALIDATION_RESOURCE_CACHES_MS, "" + theMillis);
	}

	/**
	 * Get the validation resource cache expireAfterWrite timeout in milliseconds.  If it has not been set, the default
	 * value is 10 seconds.
	 */

	public static long getTestValidationResourceCachesMs() {
		String property = System.getProperty(TEST_SYSTEM_PROP_VALIDATION_RESOURCE_CACHES_MS);
		if (property == null) {
			return DEFAULT_TEST_SYSTEM_PROP_VALIDATION_RESOURCE_CACHES_MS;
		}
		return Long.parseLong(property);
	}

	/**
	 * When this property is primarily used to control application shutdown behavior
	 */
	public static void enableTestMode() {
		System.setProperty(TEST_MODE, "true");
	}

	public static boolean isTestModeEnabled() {
		return "true".equals(System.getProperty(TEST_MODE));
	}

	/**
	 * This property is used to ensure unit test behaviour is deterministic.  It is also used to add extra logging for unit tests.
	 */
	public static void enableUnitTestMode() {
		System.setProperty(UNIT_TEST_MODE, "true");
	}

	public static boolean isUnitTestModeEnabled() {
		return "true".equals(System.getProperty(UNIT_TEST_MODE));
	}

	/**
	 * This property prevents stack traces from getting truncated and includes the full stack trace in failed search responses.
	 */
	public static void enableUnitTestCaptureStack() {
		System.setProperty(UNIT_TEST_CAPTURE_STACK, "true");
	}

	public static void disableUnitTestCaptureStack() {
		System.clearProperty(HapiSystemProperties.UNIT_TEST_CAPTURE_STACK);
	}

	public static boolean isUnitTestCaptureStackEnabled() {
		return "true".equals(System.getProperty(HapiSystemProperties.UNIT_TEST_CAPTURE_STACK));
	}

	public static boolean isDisableStatusBasedReindex() {
		return "true".equals(System.getProperty(DISABLE_STATUS_BASED_REINDEX));
	}

	public static void disableStatusBasedReindex() {
		System.setProperty(DISABLE_STATUS_BASED_REINDEX, "true");
	}

	/**
	 * This property sets {@link DaoConfig#setStatusBasedReindexingDisabled(Boolean)} to true when the system starts up.
	 */
	public static void enableStatusBasedReindex() {
		System.clearProperty(DISABLE_STATUS_BASED_REINDEX);
	}

	/**
	 * This property is used to suppress the logging of the HAPI FHIR version on startup.
	 */
	// TODO KHS use this in cdr
	public static void enableSuppressHapiFhirVersionLog() {
		System.setProperty(SUPPRESS_HAPI_FHIR_VERSION_LOG, "true");
	}

	public static boolean isSuppressHapiFhirVersionLogEnabled() {
		return "true".equals(System.getProperty(SUPPRESS_HAPI_FHIR_VERSION_LOG));
	}

}