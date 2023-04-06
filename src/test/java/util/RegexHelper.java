package util;

import org.openqa.selenium.WebElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexHelper {
    public static String changeText(WebElement webElement, String regex) {
        String input = webElement.getText();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        matcher.find();
        String output = matcher.group().replaceAll("\\s", "");
        return output;
    }
}
