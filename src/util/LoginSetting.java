package util;

public class LoginSetting {
	public static void login(String loginid,String password) {
		//login empty
		if (CheckString.empty(loginid)) {		//is empty
			throw new PosException("Login ID should not be empty!");
		}
		
		//password empty
		if (CheckString.empty(password)) {
			throw new PosException("Password should not be empty!");
		}
		
		//login wrong
		if (!loginid.equals(PosSetting.get("pos.user.name"))) {
			throw new PosException("Please check loginId!");
		}
		
		//password wrong
		if (!password.equals(PosSetting.get("pos.user.password"))) {
			throw new PosException("Please check password!");
		}
	}
}
