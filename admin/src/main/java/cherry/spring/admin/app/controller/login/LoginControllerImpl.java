package cherry.spring.admin.app.controller.login;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(LoginController.URI_PATH)
public class LoginControllerImpl implements LoginController {

	public static final String VIEW_PATH = "login/index";

	@RequestMapping
	@Override
	public ModelAndView index(Locale locale, SitePreference sitePreference,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(VIEW_PATH);
		mav.addObject(new LoginForm());
		return mav;
	}

}
