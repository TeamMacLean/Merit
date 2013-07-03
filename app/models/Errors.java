package models;

public enum Errors {

	DENIED, // means that the user explicitly denied the badge from being added
			// to their backpack.

	EXISTS, // means that the badge is already in the user's backpack.

	INACCESSIBLE, // means that the assertion URL provided could not be
					// retrieved. For instance, the assertion URL itself may be
					// malformed, or attempting to access the assertion may have
					// resulted in 404 Not Found or 403 Forbidden.

	MALFORMED, // means that the assertion URL provided exists but was
				// malformed.

	INVALID // means that the assertion URL provided exists and is well-formed,
			// but is not valid. For instance, the recipient of the assertion
			// may not be the currently logged-in user.

}
