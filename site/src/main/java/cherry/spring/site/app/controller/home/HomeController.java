package cherry.spring.site.app.controller.home;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(HomeController.URI_PATH)
public interface HomeController {

	public static final String URI_PATH = "/secure";

	@RequestMapping
	ModelAndView index(Locale locale, SitePreference sitePreference,
			HttpServletRequest request);

}
