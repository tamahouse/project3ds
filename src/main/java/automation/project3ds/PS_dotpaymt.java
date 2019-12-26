package automation.project3ds;
public class PS_dotpaymt extends PS_dotpay{

	public PS_dotpaymt(Driver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public PS_dotpaymt2 createPayment() {
		this.setFirstName();
		this.setLastName();
		this.setEmail();
		this.clickProceedBtn();
		return new PS_dotpaymt2(driver);
	}
}
