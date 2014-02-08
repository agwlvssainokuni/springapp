package cherry.spring.site.app.controller.login;

import cherry.spring.site.app.controller.BaseForm;

public class LoginForm extends BaseForm {

	private static final long serialVersionUID = 1L;

	private String loginId;

	private String password;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
