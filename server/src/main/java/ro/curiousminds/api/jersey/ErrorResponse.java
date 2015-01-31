/*
 * Created by Tomas Zverina <zverina@m-atelier.cz>
 * Released under WTFPL.
 */
package ro.curiousminds.api.jersey;

/**
 * Thos response is returned in case of an error.
 */
public class ErrorResponse {

	private String errorCode = null;
	private String errorParam = null;
	private String errorMessage = null;

	public ErrorResponse(Throwable throwable) {
		if (throwable != null) {
			errorCode = throwable.getClass().getSimpleName();
			errorParam = throwable.getMessage();
			errorMessage = throwable.toString();
		}
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorParam() {
		return errorParam;
	}

	public void setErrorParam(String errorParam) {
		this.errorParam = errorParam;
	}
}
