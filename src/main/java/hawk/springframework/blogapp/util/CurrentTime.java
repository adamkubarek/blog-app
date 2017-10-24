package hawk.springframework.blogapp.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CurrentTime {
	public static String get() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
		String currentTime = LocalDateTime.now().format(formatter);
		
		return currentTime;
	}
}
