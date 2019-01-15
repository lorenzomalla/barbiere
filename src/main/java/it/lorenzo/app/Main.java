package it.lorenzo.app;

public class Main {

	public static void main(String[] args) {
		String passwd = "A1asfhhj@";
		String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
		System.out.println(passwd.matches(pattern));
	}

}
