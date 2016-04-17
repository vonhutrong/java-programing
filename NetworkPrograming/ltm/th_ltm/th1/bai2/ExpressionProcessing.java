package th_ltm.th1.bai2;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import th_ltm.th1.IStringProcessing;

public class ExpressionProcessing implements IStringProcessing {
	
	private static ExpressionProcessing mInstance = new ExpressionProcessing();
	
	private ScriptEngineManager mEngineMgr = null;
	private ScriptEngine mEngine = null;
	
	private ExpressionProcessing() {
		mEngineMgr = new ScriptEngineManager();
		mEngine = mEngineMgr.getEngineByName("JavaScript");
	}
	
	public static ExpressionProcessing getInstance() {
		return mInstance;
	}
	
	@Override
	public String process(String string) {
		String result = null;
		if (string != null && string.length() > 0){
			try {
				result = mEngine.eval(string).toString();
			} catch (ScriptException e) {
				result = "syntax error";
			}
		}
		return result;
	}

}