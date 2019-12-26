package automation.project3ds;

public class PS_dotpayingb extends PS_dotpay{

	public PS_dotpayingb(Driver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public PS_dotpayingb2 createPaymentIngb() {
		this.setFirstName();
		this.setLastName();
		this.setEmail();
		this.clickProceedBtn();
		return new PS_dotpayingb2(driver);
	}

}
