package cherry.example.web.login;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@EqualsAndHashCode
@ToString
public class LoginFormBase {

	private String loginId;

	private String password;

}
