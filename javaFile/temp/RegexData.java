import java.util.*;
public class RegexData{
	
	// after connect -- Order MSG
	private final String order= "GET / HTTP / 1.1";
	
	// http , www. deleted
	private final String adDel = "http...(www.)|www.|http:..";
	
	// Address | port Cut Str
	private final String portCut = "(:([^\\/]*))";

	//

		
	// body의 <a herf  추출 기준 
	private final String srcLink = "<a ";
	
	
	//body의 img src 태그 추출 기준 
	private final String srcImg = "<img ";
	
	
	// status code 추출 기준 
	private final String statusStr = ".*HTTP.*";
	
	
	// contents Length 추출 기준 
	private final String contLength = ".*Content-Length:.*";
	
	
	// Contents Type 추출 기준 
	private final String contType = ".*Content-Type:.*";
	
		
	
// ---- Getter Set -----
	
	
	// http, www. deleted getter
	public String getAdDel(){
		return adDel;
	}
	// port getter	
	public String getPortCut(){
		return portCut;
	}
	
	//Order getter
	public String getOrder() {
		return order;
	}
	// srcLink getter
	public String getSrcLink(){
		return srcLink;
	}
	// Src Img getter
	public String getSrcImg(){
		return srcImg;
	}
	// Status code Str getter
	public String getStatusStr(){
		return statusStr;
	}
	// Contents Length Str getter
	public String getContLength(){
		return contLength;
	}
	// Contents Type Str getter
	public String getContType(){
		return contType;
	}
}