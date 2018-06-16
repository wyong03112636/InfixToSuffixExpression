/**
 * 
 */
/**
 * @author wyong
 *
 */
package Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InfixToSuffixExpression{
	
	//比较操作符号优先级
	public boolean comparePrior(String symbol1,String symbol2){
		if("(".equals(symbol2)){
			return true;
		}
		if("*".equals(symbol1)||"/".equals(symbol1)){
			
			if("+".equals(symbol2)||"-".equals(symbol2)){
				return true;
			}
		}
		return false;
		
	}
	public String[] toSuffixExpression(String [] exp){
		List<String> newExpression = new ArrayList<String>();
		Stack<String> stack = new Stack<String>();
		for (int i = 0; i < exp.length; i++) {
			if("(".equals(exp[i])){ // 优先级最高直接入栈
				stack.push(exp[i]);
			}else if("+".equals(exp[i])||"-".equals(exp[i])||"*".equals(exp[i])||"/".equals(exp[i])){
				if(!stack.empty()){
					String s = stack.pop();//取出栈顶元素
					if(comparePrior(exp[i], s)){  //判断现有的操作数与栈顶的操作数的优先级，如果大于栈顶的优先级则继续压入栈
						stack.push(s);
					}else{
						newExpression.add(s);
					}
				}
				stack.push(exp[i]);
			}else if(")".equals(exp[i])){ // 如果是“）”则一直出栈直到遇到“（”
				while(!stack.empty()){
					String s = stack.pop();
					if(!"(".equals(exp[i])){
						newExpression.add(s);
					}else{
						break;
					}
				}
			}else{
				newExpression.add(exp[i]);
			}
				
		}
		while(!stack.empty()){
			String s = stack.pop();
			newExpression.add(s);
		}
		String[] s = newExpression.toArray(new String[0]);//转换为String数组
		String[] temp = new String[s.length];
		for (int i = 0; i < s.length; i++) {            //将数组中的“（”移除
			if(!"(".equals(s[i])){
				temp[i] = s[i];
			}
		}
		return temp;
	}
	public static void main(String[] args) {
		String [] expression = {"1","+","2","*","(","3","+","4",")","/","(","7","-","4",")","+","2"};
		InfixToSuffixExpression itf = new InfixToSuffixExpression();
		String [] newExpression = itf.toSuffixExpression(expression);
		for (int i = 0; i < newExpression.length; i++) {
			System.out.print(newExpression[i]+" ");
		}
	}
}