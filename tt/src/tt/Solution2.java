package tt;

import java.util.ArrayList;
public class Solution2 {
	public String solution(String sentence) {
        String answer = "";
        int sLength = sentence.length();
        ArrayList<Character> cArray = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        if(sLength <=2) {
        	return "invalid";
        }
        for(int i = 0; i<sLength ; i++) {
        	//�ձ��ڰ� �빮������, �ҹ������� Ȯ��
        	if(sentence.charAt(i) >= 'A' && sentence.charAt(i) <= 'Z') {
        		//�빮�ڶ��, 2�� ��Ģ ����
        		int wCount  = 0;
        		char abChar = ' ';
        		char tmpChar = 0;
        		int j;
        		for(j = i; j <sLength ; j++) {
        			tmpChar = sentence.charAt(j);
        			//���鹮�ڸ� invaild;
        			if(tmpChar == ' ')
        				return "invalid";
        			

        			if(wCount%2 == 0) { // wCount/2�� 0�̸�, ������Ʈ
        				if(tmpChar >= 'A' && tmpChar <= 'Z' ) {
        					sb.append(tmpChar);
        				}else {
        					return "invalid";
        				}
        			}else { // wCount/2�� 1�̸�, ���� ��Ʈ
        				//�빮�ڰ� �ƴ϶��
        				if(!(tmpChar >= 'A' && tmpChar <= 'Z') ) {
        					if(abChar == ' ') {
            					if(cArray.contains(tmpChar)) //���� ���� ��ȣ�� �ٽ� ���ΰ����, invaild;
            						return  "invalid";
            					
        						abChar = tmpChar;
            					
            				}else if (abChar != tmpChar) {
            					i = j-1;
            					sb.append(' ');
            					cArray.add(abChar);
            					break;
            				}
        				}else { //��Ģ 1���� ���� �κ�
        					if(abChar == ' ') {
        						for(int k = j ;j<sLength; k++) {
        							tmpChar = sentence.charAt(k);
        							if(tmpChar >= 'A' && tmpChar <= 'Z' ) {
        								sb.append(tmpChar);
        							}else {
        								sb.deleteCharAt(sb.length()-1);
        								sb.append(' ');
        	        					i = j-1;
        	        					break;
        							}
        						}
       
        					}else {
        						i = j-1;
            					sb.append(' ');
            					cArray.add(abChar);
            					break;
        						
        					}
        					
        				}
        				
        			}
        			wCount++;
        		}
        		if(j>=sLength) {
        			if(tmpChar >= 'A' && tmpChar <= 'Z'){
            			i = sLength;
            			sb.append(' ');
            		}else {
            			return "invaild";
            		}
        		}	
        	}
        	else { //�ҹ��ڶ��, 1����Ģ ����
        		char abChar = sentence.charAt(i);
        		int j;
        		if(cArray.contains(abChar)) //���� ���� ��ȣ�� �ٽ� ���ΰ����, invaild;
					return  "invalid";
        		
        		
        		for(j = i+1; j <sLength ; j++) {
        			char tmpChar = sentence.charAt(j);
        			if(tmpChar == ' ')
        				return "invalid";
        			
        			if(tmpChar >= 'A' && tmpChar <= 'Z') {
        				sb.append(tmpChar);
        			}else {
        				if(tmpChar == abChar) {
        					i= j;
        					sb.append(' ');
        					cArray.add(abChar);
        					break;
        				}else {
        					//2����Ģ�� �ִ°�?
        					int wCount  = 1;
        					char abChar2 = ' ';
        					if(cArray.contains(tmpChar)) //���� ���� ��ȣ�� �ٽ� ���ΰ����, invaild;
        						return  "invalid";
        					
        					for(int k = j ; k < sLength; k++) { // 1,2����Ģ �Ѵ� ����
        						tmpChar = sentence.charAt(k);
        	        			//���鹮�ڸ� invaild;
        	        			if(tmpChar == ' ')
        	        				return "invalid";
        	        			

        	        			if(wCount%2 == 0) { // wCount/2�� 0�̸�, ������Ʈ
        	        				if(tmpChar >= 'A' && tmpChar <= 'Z' ) {
        	        					sb.append(tmpChar);
        	        				}else {
        	        					return "invalid";
        	        				}
        	        			}else { // wCount/2�� 1�̸�, ���� ��Ʈ
        	        				//�빮�ڰ� �ƴ϶��
        	        				if(!(tmpChar >= 'A' && tmpChar <= 'Z') ) {
        	        					if(abChar2 == ' ') {
        	            					if(cArray.contains(tmpChar) || abChar2 == abChar) //���� ���� ��ȣ�� �ٽ� ���ΰ����, invaild;
        	            						return  "invalid";
        	            					
        	        						abChar2 = tmpChar;
        	            					
        	            				}else if(tmpChar == abChar) {
        	            					j = k-1;
        	            					cArray.add(abChar2);
        	            					break;
        	            				}
        	        					else if (abChar2 != tmpChar) {
        	            					return "invalid";
        	            					
        	            				}
        	        				}else { //��Ģ 1���� ���� �κ�
        	        					j = k-1;
        	        					sb.append(' ');
        	        					cArray.add(abChar2);
        	        					break;
        	        				}
        	        				
        	        			}
        	        			wCount++;
        						
        					}
        				}
        			}
        		
        		}
        		if(j >= sLength)
        			return "invalid";
        	}
        }
        sb.deleteCharAt(sb.length()-1);
        answer = sb.toString();
        
        return answer;
    }
}
