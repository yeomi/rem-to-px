package remtopx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

	public static final String DEFAUTL_REGEX = "(\\d?[.]?\\d+)[ ]?rem";
	public static final Float DEFAULT_MULTIPLICATOR = 10f;
	
	private String regex;
	private Matcher matcher;
	private Pattern pattern;
	private StringBuffer stringBuffer;
	private Float multiplicator;
	
	public String getRegex() { return this.regex; }
	public void setRegex(String regex) { this.regex = regex; }
	public Float getMultiplicator() { return this.multiplicator; }
	public void setMultiplicator(Float multiplicator) { this.multiplicator = multiplicator; }
	
	public Parser()
	{
		this(DEFAUTL_REGEX, DEFAULT_MULTIPLICATOR);
	}
	
	public Parser(String regex, Float multiplicator)
	{
		setRegex(regex);
		setMultiplicator(multiplicator);
		this.pattern = Pattern.compile(regex);
		
	}
	
	public String remToPx(String raw)
	{
		this.matcher = pattern.matcher(raw);
		this.stringBuffer = new StringBuffer();
		
		while (this.matcher.find()) {
			Float value = Float.parseFloat(this.matcher.group(1));
			value *= this.multiplicator;
			
			if((value % 1) == 0) 
			{
				this.matcher.appendReplacement(this.stringBuffer, (int) value.floatValue() + "px");
			} 
			else
			{
				this.matcher.appendReplacement(this.stringBuffer, value + "px");		
			}
			
		}
		this.matcher.appendTail(this.stringBuffer);
		
		return this.stringBuffer.toString();
	}
}
