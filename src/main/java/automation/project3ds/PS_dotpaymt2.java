package automation.project3ds;

public class PS_dotpaymt2 extends PS_dotpay2{

	public PS_dotpaymt2(Driver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public ThankyouPage finishPayment() {
		this.clickAcceptBtn();
		this.clickNextBtn();
		return new ThankyouPage(driver);
	}
}
