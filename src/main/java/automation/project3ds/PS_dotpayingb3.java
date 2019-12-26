package automation.project3ds;

public class PS_dotpayingb3 extends PS_dotpay2{

	public PS_dotpayingb3(Driver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public PS_dotpayingb2 accept() {
		this.clickAcceptBtn();
		return new PS_dotpayingb2(driver);
	}

}
