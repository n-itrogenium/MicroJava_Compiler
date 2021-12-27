package rs.ac.bg.etf.pp1;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import rs.ac.bg.etf.pp1.util.Log4JUtils;
import rs.ac.bg.etf.pp1.CompilerExt;
import rs.ac.bg.etf.pp1.test.CompilerError;

public class MJParserTest {

	static {
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
	}
	
	public static void main(String[] args) throws Exception {
		CompilerExt compiler = new CompilerExt();
		List<CompilerError> errors = compiler.compile(args[0], args[1]);
		if (errors != null)
			System.err.println(errors);
	}
}
