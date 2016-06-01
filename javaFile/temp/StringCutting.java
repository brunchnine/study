import java.util.regex.*;

public class StringCutting {
	RegexData rgxData = new RegexData();
	
	RegexData rd = new RegexData();

	// protocol Cutting /
	public String addressCut(String scurl){
		Pattern p = Pattern.compile(rgxData.getAdDel());
		Matcher m = p.matcher(scurl);
		String result = m.replaceAll("");
		return result;
		
		}

//    // address Cut
//    public String urlCut(String address, String regxStart, String rgxEnd){
//        String result = address.substring(address.indexOf(regxStart),address.indexOf(rgxEnd));
//        return result;
//    }
//
//    // port cut
//
//    public int portCut(String address, String regxStart, String rgxEnd){
//        String result = address.substring(address.indexOf(regxStart),address.indexOf(rgxEnd));
//        int iResult = Integer.parseInt(result);
//        return iResult;
//    }

	// host cut
	public String hostCut(String address){
		String result = address.substring(0,address.indexOf(":"));
		return result;
	}
	
	// port Cut
	public int portCut(String address){
		int endPoint = address.indexOf("/");
		if(endPoint == -1)
			endPoint = address.length();
		String result = address.substring(address.indexOf(":")+1,endPoint);
		int iResult = Integer.parseInt(result);
        return iResult;
	}
	
	// path Cut
	public String pathCut(String address){
		int startPoint = address.indexOf("/");
		if(startPoint==-1) {
			return "";
		} else {

			String result = address.substring(startPoint, address.length());

			return result;
		}
	}
	

	
	// Body Tag Counter
	public int bodyCounter(String conts, String getSrc){
		Pattern p = Pattern.compile(getSrc);
	    Matcher m = p.matcher(conts);
	    int count = 0;
	      for( int i = 0; m.find(i); i = m.end()){
	      count++;
	      }
		return count;
	}
	

	// Code Export generic
	// startStr 에 기준 정규식 받아서 처리 후 return
	public String tagSearch(String conts, String getStatusStr){
		Pattern p = Pattern.compile(getStatusStr);
		Matcher m = p.matcher(conts);
		String result = "";
		while(m.find())
		result = conts.substring(m.start(),m.end());
		if(result == ""){
			result = "찾을수 없음";
		}
		return result;
	}
}

